package old.visual;

import java.awt.Color;
import java.awt.Graphics;

import animate.SteinerTree;
import basic.Line;
import basic.Point;


@SuppressWarnings("serial")
public class SteinerTreeDrawer extends TreeDrawer {
	
	private Point[] steinerPoints;
	
	public SteinerTreeDrawer(SteinerTree tree) {
		super(tree);
		this.steinerPoints = tree.getSteinerPoints(); 
	}
	
	public SteinerTreeDrawer(SteinerTree tree, int psize) {
		super(tree,psize);
		this.steinerPoints = tree.getSteinerPoints();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.red);
		for(Point p : steinerPoints) {
			paintPoint(g,p);
		}
		
		for(Line l : tree.getLines()) {
			if(isSteinerLine(l)) {
				paintLine(g,l);
			}
		}
		g.setColor(Color.green);
		paintLine(g,tree.getBottleneck());
	}

	private boolean isSteinerLine(Line line) {
		for(Point p : steinerPoints) {
			if( p == line.getA() || p == line.getB()) return true;
		}
		return false;
	}
}
