package old.visual;

import java.awt.Graphics;

import basic.Line;
import basic.Point;
import basic.Tree;


@SuppressWarnings("serial")
public class TreeDrawer extends PointsDrawer {
	
	protected Tree tree;
	
	public TreeDrawer(Tree tree) {
		super(tree.getPoints());
		this.tree = tree;
	}
	
	public TreeDrawer(Tree tree, int psize) {
		super(tree.getPoints(),psize);
		this.tree = tree;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Line line : tree.getLines()) {
			paintLine(g,line);
		}
		g.drawString("Bottleneck: " + tree.getBottleneck().getLength() , 150, 50);
	}
	
	protected void paintLine(Graphics g,Line line) {
		Point a = convert(line.getA());
		Point b = convert(line.getB());
		g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
	}
	
}
