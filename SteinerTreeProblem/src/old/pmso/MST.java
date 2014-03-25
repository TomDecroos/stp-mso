package old.pmso;

import old.steiner.Edge;
import disjointset.Node;

public class MST {
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
	public MST(Node[] nodes,Edge[] edges) {
		this.edges = edges;
		this.nodes = nodes;
	}
	public double getSqBottleneck() {
		return edges[edges.length-1].getSquaredLength();
	}
	public double getBottleneck() {
		return edges[edges.length-1].getLength();
	}
	public double getTotalLength() {
		double length = 0;
		for(Edge edge : edges) {
			length += edge.getLength();
		}
		return length;
	}
	
}
