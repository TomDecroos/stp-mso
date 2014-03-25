package old.visual;

import java.awt.*;

import javax.swing.JFrame;

import basic.Point;



@SuppressWarnings("serial")
public class PointsDrawer extends Canvas{
	
	/**
	 * The array of points to be drawn
	 */
	private Point[] points;
	
	/**
	 * The size of each point
	 */
	public int psize = 10;
	
	/**
	 * Constructor
	 */
	public PointsDrawer(Point[] points) {
		this.points = points;
	}

	public PointsDrawer(Point[] points, int psize) {
		this.points = points;
		this.psize = psize;
	}
	
	@Override
	/**
	 * Paints every point in the array
	 */
	public void paint(Graphics g) {
		
		for(Point point : points) {
			paintPoint(g,point);
		}
	}
	public void paintPoint(Graphics g,Point point) {
		Point p = convert(point);
		g.fillOval( (int) p.getX()-psize/2, (int) p.getY()-psize/2, psize, psize);
	}
	
	protected Point convert(Point p) {
		double x = (0.1 + 0.8 * (p.getX()/getMaxX())) * this.getWidth();
		double y = (0.1 + 0.8 * (p.getY()/getMaxY())) * this.getHeight();
		return new Point(x,y);
	}
	
	private double getMaxX() {
		double w = points[0].getX();
		for(Point point : points) {
			if(point.getX() > w) w = point.getX();
		}
		return w;
	}
	
	private double getMaxY() {
		double h = points[0].getY();
		for(Point point : points) {
			if(point.getY() > h) h = point.getY();
		}
		return h;
	}

	public int getDotsize() {
		return psize;
	}

	public void setDotsize(int dotsize) {
		this.psize = dotsize;
	}
	
	public void draw(int width,int height) {
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(width, height);
		jframe.getContentPane().setBackground(Color.WHITE);
		jframe.add(this);
		jframe.setVisible(true);
	}
	
	public void draw(int width,int height,int psize) {
		this.psize = psize;
		draw(width,height);
	}
	
	public void draw(DrawConfig drawcfg) {
		this.psize = drawcfg.pointsize;
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(drawcfg.x,drawcfg.y,drawcfg.width,drawcfg.length);
		jframe.getContentPane().setBackground(Color.WHITE);
		jframe.add(this);
		jframe.setVisible(true);
	}
	
}
