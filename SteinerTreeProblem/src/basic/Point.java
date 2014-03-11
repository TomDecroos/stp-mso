package basic;

/**
 * This class represents a point in the 2-dimensional euclidean space.
 * @author Tom
 *
 */
public class Point {
	private double x;
	private double y;
	
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		return "x: " + x + " y: " + y ;
	}
	
}
