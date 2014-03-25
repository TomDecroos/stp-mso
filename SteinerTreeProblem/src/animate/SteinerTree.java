package animate;

import basic.Line;
import basic.Point;
import basic.Tree;


public class SteinerTree extends Tree {
	
	private Point[] steinerPoints;
	
	public SteinerTree(Point[] points, Line[] lines,Point[] steinerPoints) {
		super(points, lines);
		this.steinerPoints = steinerPoints;
	}
	
	public SteinerTree(Tree tree, Point[] steinerPoints) {
		this(tree.getPoints(),tree.getLines(),steinerPoints);
	}
	
	public Point[] getSteinerPoints() {
		return steinerPoints;
	}
	
	
}
