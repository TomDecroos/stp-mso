package old.steiner;

import basic.Line;
import disjointset.Node;


public class Edge implements Line {
	
	private Node a;
	private Node b;

	public Edge(Node a, Node b) {
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

	public Node getA() {
		return a;
	}

	public Node getB() {
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
