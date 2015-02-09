package mstheuristic;

import basic.Line;
import basic.Point;

public class Line2 implements Line{
	private Point a;
	private Point b;
	private int steinerpoints;
	
	public void addSteinerpoint() {
		steinerpoints++;
	}

	public int getSteinerpoints() {
		return steinerpoints;
	}

	public Line2(Point a, Point b) {
		this.a = a;
		this.b = b;
		this.steinerpoints = 0;
	}
	
	public double getLength() {
		return Math.sqrt(getSquaredLength());
	}
	
	public double getSquaredLength() {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		int c = steinerpoints+1;
		return (x*x + y*y)/(c*c);
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
