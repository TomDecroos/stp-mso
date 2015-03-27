package mst;

import java.util.ArrayList;

import disjointset.Node;

public class MinimalSpanningTree {
	private Edge[] edges;
	private Node[] nodes;
	
	/**
	 * @return the edges
	 */
	public Edge[] getEdges() {
		return edges;
	}
	
	/**
	 * @return the nodes
	 */
	public Node[] getNodes() {
		return nodes;
	}
	
	public MinimalSpanningTree(Node[] nodes,Edge[] edges) {
		this.edges = edges;
		this.nodes = nodes;
	}

	public double getSqBottleneck() {
		return edges[edges.length-1].getSquaredLength();
	}
	public double getBottleneck() {
		return edges[edges.length-1].getLength();
	}
	public double getLength() {
		double length = 0;
		for(Edge edge : edges) {
			length += edge.getLength();
		}
		return length;
	}
	
	public boolean isOptimal(Node node) {
		ArrayList<Node> neighbours = getNeighbours(node);
		if(neighbours.size() != 3) return false;
		Node[] n = new Node[3];
		for(int i=0;i<3;i++) {
			double x = neighbours.get(i).getX() - node.getX();
			double y = neighbours.get(i).getY() - node.getY();
			double abs = Math.sqrt(x*x+y*y);
			n[i] = new Node(x/abs,y/abs);
		}
		Edge[] e = new Edge[3];
		for(int i=0;i<3;i++) {
			e[i] = new Edge(n[i%3],n[(i+1)%3]);
		}
		double err = Math.pow(2, -8);
		return Math.abs(e[0].getLength() - e[1].getLength()) < err
				&& Math.abs(e[1].getLength() - e[2].getLength()) < err;
		
	}
	
	private ArrayList<Node> getNeighbours(Node node){
		ArrayList<Node> neighbours = new ArrayList<>();
		for(Edge edge : edges) {
			if(edge.getA().equals(node)) neighbours.add(edge.getB());
			if(edge.getB().equals(node)) neighbours.add(edge.getA());
		}
		return neighbours;
	}
}
