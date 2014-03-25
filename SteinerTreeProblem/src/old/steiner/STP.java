package old.steiner;

import basic.Point;

/**
 * An object of this class represents an instance of a Steiner Tree Problem with k available steiner points.
 * @author Tom
 */
public class STP {
	
	/**
	 * An array of points that have to be connected.
	 */
	private Point[] points;
	
	/**
	 * The exact amount of steiner points.
	 */
	private int k;
	
	
	public STP(int problems, int index, int k) {
		
	}
	/**
	 * Constructor
	 */
	public STP(Point[] points,int k) {
		this.points = points;
		this.k = k;
	}
	
	
	/** Getters and Setters*/
	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

}
