package pmso;

import disjointset.Node;
import steiner.Edge;

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
	
}
