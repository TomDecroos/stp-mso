package steiner;

import disjointset.Node;
import basic.Point;

/**
 * This extension allows a node (and thus the entire set) to be reset to the way it was
 * during a certain point of execution in an algorithm.
 * @author Tom
 *
 */
public class ResettableNode extends Node {

	private Node resetParent;
	private int resetRank;
	
	public ResettableNode(double x, double y) {
		super(x,y);
	}
	public ResettableNode(Point p) {
		super(p);
	}
	public void setResetPoint() {
		resetParent = parent;
		resetRank = rank;
	}
	public void reset() {
		parent = resetParent;
		rank = resetRank;
	}
	

}
