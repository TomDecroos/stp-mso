package disjointset;

import basic.Point;


/**
 * This class extends the class 'point' and adds functionality to be used in a
 * disjoint-set data structure.
 * http://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
public class Node extends Point {
	
	public Node parent;
	public int rank;

	/**
	 * Constructs a node in the euclidean space.
	 * The node is also a set consisting of 1 node at time of construction.
	 */
	public Node(double x, double y) {
		super(x, y);
		this.parent = this;
		this.rank = 0;
	}
	
	public Node(Point p) {
		this(p.getX(),p.getY());
	}
	
	public Node clone() {
		return new Node(getX(),getY());
	}
}
