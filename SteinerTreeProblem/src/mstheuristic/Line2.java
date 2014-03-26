package mstheuristic;

import basic.Line;
import basic.Point;

public class Line2 implements Line{
	private Point a;
	private Point b;
	
	public Line2(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public double getLength() {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return Math.sqrt(x*x + y*y);
	}
	
	public double getSquaredLength() {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return x*x + y*y;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}
	
	@Override
	public int compareTo(Line other) {
		return Double.compare(this.getSquaredLength(), other.getSquaredLength());
	}
	
	@Override
	public String toString() {
		return a.toString() + " ---> " + b.toString() + " length: " + getLength();
	}

}
