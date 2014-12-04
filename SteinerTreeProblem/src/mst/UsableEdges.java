package mst;

import java.util.ArrayList;


import disjointset.Node;

/**
 * A class representing the possible edges to use for adding a new point
 * to a minimal spanning tree.
 * @author Tom
 *
 */
public class UsableEdges {
	public static int MAX=10;
	private ArrayList<Edge> edges;
	private Node node;
	
	public UsableEdges(Node node) {
		this.edges = new ArrayList<Edge>();
		this.node = node;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public void add(Node[] nodes) {
		for(Node node: nodes) {
			add(node);
		}
	}
	
	public void add(Node node) {
		Edge edge = new Edge(this.node,node);
		if(edges.size() < MAX) {
			edges.add(edge);
		} else {
			Edge bottleneck = getBottleneck();
			if(edge.getSquaredLength() < bottleneck.getSquaredLength()) {
				edges.remove(bottleneck);
				edges.add(edge);
			}
		}
	}

	private Edge getBottleneck() {
		Edge bottleneck = edges.get(0);
		for(Edge edge : edges) {
			if( edge.getSquaredLength() > bottleneck.getSquaredLength()) bottleneck = edge;
		}
		return bottleneck;
	}
}
