package pmso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import basic.Point;
import basic.Tree;
import kruskall.Kruskall;
import steiner.Edge;
import steiner.STP;
import steiner.SteinerTree;
import disjointset.DisjointSet;
import disjointset.Node;

/**
 * Thought experiment: try doing pso, but with multiple swarms. 1 swarm per steiner point
 * @author Tom
 *
 */
public class PMSOold {
	STP stp;
	int n;
	int k;
	Particle[][] swarms;
	Node[] nodes;
	Edge[] edges;
	double w1;
	public double w2;
	
	public PMSOold(STP problem,int swarmsize,double w1,double w2) {
		this.stp = problem;
		this.n = problem.getPoints().length;
		this.k = problem.getK();
		this.w1 = w1;
		this.w2 = w2;
		setSwarms(swarmsize);
		setNodes(problem.getPoints());
		this.edges = Kruskall.getTreeEdges(nodes);
	}
	
	public Tree getTree() {
		return new Tree(nodes,edges);
	}
	
	public SteinerTree getSteinerTree() {
		Node[] steinerNodes = Arrays.copyOfRange(nodes, n, n+k);
		return new SteinerTree(getTree(),steinerNodes);
	}

	/**
	 * Generates the swarms with random particles.
	 * There are k swarms that each have the given swarmsize.
	 * A swarm is nothing more than an array of particles.
	 * @param swarmsize
	 */
	private void setSwarms(int swarmsize) {
		swarms = new Particle[k][swarmsize];
		for(int i=0;i<this.k;i++) {
			for(int j=0;j<swarmsize;j++) {
				swarms[i][j] = new Particle(Math.random(),Math.random());
			}
		}
	}
	
	/**
	 * Initalizes the nodes.
	 * nodes 0..n-1: the regular nodes
	 * nodes n..n+k-1: the steiner nodes,
	 * 		initialized from a random particle of the corresponding swarms;
	 * @param points
	 */
	private void setNodes(Point[] points) {
		this.nodes = new Node[n + k];
		for(int i=0;i<n;i++) {
			nodes[i] = new Node(points[i]);
		}
		for(int i=0;i<k;i++) {
			double x = swarms[i][0].px;
			double y = swarms[i][0].py;
			nodes[n+i] = new Node(x,y);
		}
	}
	
	/**
	 * Evolves the swarms 1 cycle.
	 */
	public void evolve() {
		for(int i=0;i<k;i++) {
			for(int j=0;j<swarms[i].length;j++) {
				Particle p = swarms[i][j];
				// fly
				p.vx = w1*p.vx + w2*(nodes[n+i].getX()-p.px)*Math.random();
				p.vy = w1*p.vy + w2*(nodes[n+i].getY()-p.py)*Math.random();
				p.px += p.vx;
				p.py += p.vy;
				
				// calculate
				if(!better3(p,n+i) && better2(p,n+i)) System.out.println(getBetter3Cnt(p, n+i));
				//if(better(p,n+i) && !better2(p,n+i)) System.out.println("FTW");
				
				if(better2(p,n+i)) {
					setNewNode2(p,n+i);
				}
			}
		}
	}
	
	public void evolve(int cycles) {
		for (int i = 0; i < cycles; i++) {
			evolve();
		}
	}
	
	/**
	 * This particle is better, so set it as the new best node for its swarm.
	 * @param p
	 * @param index
	 */
	/*
	private void setNewNode(Particle p, int index) {
		Node node = new Node(p.px,p.py);
		Edge[] newEdges = getEdges(node);
		edges = new Edge[n+k-1];
		int i = 0;
		// do kruskall to construct the new tree
		for(Edge edge : newEdges) {
			// check if the edge is not an old edge containing the old steiner node
			// and if the edge creates no loops
			if(edge.getA() != nodes[index] && edge.getB() != nodes[index] && !DisjointSet.sameSet(edge.getA(), edge.getB())) {
				DisjointSet.union(edge.getA(), edge.getB());
				edges[i] = edge;
				// if a complete tree has been constructed, break.
				if(i>= n+k-2) {
					break;
				} else {
					i++;
				}
			}
		}
		// set the node as the new best node.
		nodes[index] = node;
		DisjointSet.reset(nodes);
	}*/

	private void setNewNode2(Particle p,int index) {
		nodes[index] = new Node(p.px,p.py);
		edges = Kruskall.getTreeEdges(nodes);
	}
	private boolean better2(Particle p,int index) {
		Node backup = nodes[index];
		nodes[index] = new Node(p.px,p.py);
		Edge[] newEdges = Kruskall.getTreeEdges(nodes);
		nodes[index] = backup;
		return newEdges[n+k-1-1].getSquaredLength() <= edges[n+k-1-1].getSquaredLength();
	}
	
	private boolean better3(Particle p,int index) {
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		ArrayList<Node> disconnectedNodes = new ArrayList<Node>();
		for(Edge edge:this.edges) {
			if(edge.getA() != nodes[index] && edge.getB() != nodes[index]) {
				edgelist.add(edge);
			} else {
				disconnectedNodes.add(edge.getA() == nodes[index] ? edge.getB() : edge.getA());
			}
		}
		for(int i=0;i<disconnectedNodes.size();i++) {
			for(int j=0;j<disconnectedNodes.size();j++) {
				Edge edge = new Edge(disconnectedNodes.get(i),disconnectedNodes.get(j));
				if(edge.getSquaredLength() <= getBottleneck()) {
					edgelist.add(edge);
				}
			}
		}
		Node node = new Node(p.px,p.py);
		for(Node node2:nodes) {
			Edge edge = new Edge(node,node2);
			if(node2 != nodes[index] && edge.getSquaredLength() <= getBottleneck()) {
				edgelist.add(edge);
			}
		}
		Collections.sort(edgelist);
		int cnt = 0;
		boolean res = false;
		for(Edge edge: edgelist) {
			if(!DisjointSet.sameSet(edge.getA(),edge.getB())) {
				DisjointSet.union(edge.getA(), edge.getB());
				cnt++;
			}
			if(cnt == n+k-1)  res = true;
		}
		DisjointSet.reset(nodes);
		return res;
	}
		
	private int getBetter3Cnt(Particle p,int index) {
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		ArrayList<Node> disconnectedNodes = new ArrayList<Node>();
		for(Edge edge:this.edges) {
			if(edge.getA() != nodes[index] && edge.getB() != nodes[index]) {
				edgelist.add(edge);
			} else {
				disconnectedNodes.add(edge.getA() == nodes[index] ? edge.getB() : edge.getA());
			}
		}
		for(int i=0;i<disconnectedNodes.size();i++) {
			for(int j=0;j<disconnectedNodes.size();j++) {
				Edge edge = new Edge(disconnectedNodes.get(i),disconnectedNodes.get(j));
				if(edge.getSquaredLength() <= getBottleneck()) {
					edgelist.add(edge);
				}
			}
		}
		Node node = new Node(p.px,p.py);
		for(Node node2:nodes) {
			Edge edge = new Edge(node,node2);
			if(node2 != nodes[index] && edge.getSquaredLength() <= getBottleneck()) {
				edgelist.add(edge);
			}
		}
		Collections.sort(edgelist);
		int cnt = 0;
		for(Edge edge: edgelist) {
			if(!DisjointSet.sameSet(edge.getA(),edge.getB())) {
				DisjointSet.union(edge.getA(), edge.getB());
				cnt++;
			}
		}
		DisjointSet.reset(nodes);
		return cnt;

	}
	/**
	 * do kruskall, but with limited amount of edges
	 * @param p
	 * @param index
	 * @return better solution or not.
	 */
	/*private boolean better(Particle p, int index) {
		Node node = new Node(p.px,p.py);
		int cnt = 0;
		for(Edge edge : getEdges(node)) {
			// check if the edge is not an old edge containing the old steiner node
			// and if the edge creates no loops
			if( edge.getA() != nodes[index] &&
				edge.getB() != nodes[index] &&
				!DisjointSet.sameSet(edge.getA(), edge.getB()))
			{
				DisjointSet.union(edge.getA(), edge.getB());
				cnt++;
				// if a complete tree has been constructed,
				// this is a better or equivalent solution;
				if(cnt >= n+k-1) {
					DisjointSet.reset(nodes);
					return true;
				}
			}
		}
		DisjointSet.reset(nodes);
		return false;
	}*/
	
	/**
	 * 
	 * @return 	all the possible edges for the kruskall algorithm.
	 * 			The edges corresponding to the current best node are still in the array though,
	 * 			so they should be filtered out first during the kruskall algorithm 
	 */
	/*private Edge[] getEdges(Node node) {
		double bottleneck = edges[edges.length-1].getSquaredLength();
		ArrayList<Edge> newEdges = new ArrayList<Edge>();
		for(Node other : this.nodes) {
			Edge edge = new Edge(node,other);
			if(edge.getSquaredLength() <= bottleneck) {
				newEdges.add(edge);
			}
		}
		Edge[] allEdges = merge(edges,newEdges.toArray(new Edge[0]));
		Arrays.sort(allEdges); // sort all the usable edges
		return allEdges;
	}*/
	
	/*private <T> T[] merge(T[] a, T[] b) {
		T[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}*/
	
	public static class Particle {
		public Particle(double x,double y) {
			this.px = x;
			this.py = y;
		}
		double px;
		double py;
		double vx;
		double vy;
	}

	public double getBottleneck() {
		return edges[edges.length-1].getSquaredLength();
	}

}
