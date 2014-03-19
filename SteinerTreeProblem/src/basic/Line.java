package basic;

public interface Line extends Comparable<Line> {
	
	public Point getA();
	public Point getB();
	public double getLength();
	
	public String toString();
	public double getSquaredLength();
}
