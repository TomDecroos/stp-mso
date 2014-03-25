package old.kruskall;

import java.util.Arrays;

import old.steiner.Edge;



import basic.Point;
import basic.Tree;

import disjointset.DisjointSet;
import disjointset.Node;


public class Kruskall extends DisjointSet {
	
	private Node[] nodes;
	private Edge[] allEdges;
	private Edge[] treeEdges;
	private int nbOfTreeEdges = 0;
	
	public Kruskall(Node[] nodes) {
		this.nodes = nodes;
		this.allEdges = getAllEdges(nodes);
	}
	
	/**
	 * This method may only be executed once.
	 */
	public void execute() {
		int n = nodes.length;
		treeEdges = new Edge[n-1];
		
		for (Edge edge : allEdges) {
			if(noLoops(edge)) {
				add(edge);
				if( nbOfTreeEdges >= n-1) break; 
			}
		}
	}
	
	public Tree getMST() {
		return new Tree(nodes,treeEdges);
	}
	
	/**
	 * Checks that this edge can be added to the forest without creating loops.
	 */
	private boolean noLoops(Edge edge) {
		return super.find(edge.getA()) != super.find(edge.getB());
	}
	
	/**
	 * Adds an edge to the array of tree edges
	 * Joins the disjoined sets of both points of the line into 1 new set.
	 */
	private void add(Edge edge) {
		treeEdges[nbOfTreeEdges++] = edge;
		super.union(edge.getA(),edge.getB());
	}

	public static Edge[] getAllEdges(Node[] nodes) {
		int n = nodes.length;
		// a complete graph has n nodes and n*(n-1)/2 edges
		int nbOfAllEdges = n * (n-1) / 2;
		Edge[] allEdges = new Edge[nbOfAllEdges];
		
		int nbOfEdges = 0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				allEdges[nbOfEdges++] = new Edge(nodes[i],nodes[j]);
			}
		}
		Arrays.sort(allEdges);
		return allEdges;
	}

	public static Node[] getNodes(Point[] points) {
		int n = points.length;
		Node[] nodes = new Node[n];
		for(int i=0;i<n;i++) {
			nodes[i] = new Node(points[i]);
		}
		return nodes;
	}

	/**
	 * Returns a sorted array of the edges of the minimal spanning tree of the given nodes.
	 * @param nodes
	 * @return
	 */
	public static Edge[] getTreeEdges(Node[] nodes) {
		Edge[] edges  = getAllEdges(nodes);
		// n nodes, n-1 edges
		Edge[] treeEdges = new Edge[nodes.length-1];
		int i = 0;
		for(Edge edge : edges) {
			if(!DisjointSet.sameSet(edge.getA(), edge.getB())) {
				treeEdges[i] = edge;
				DisjointSet.union(edge.getA(), edge.getB());
				if(i >= nodes.length-1) break;
				i++;
			}
		}	
		DisjointSet.reset(nodes);
		return treeEdges;
	}
}
