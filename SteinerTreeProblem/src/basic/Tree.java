package basic;

public class Tree {
	
	private Point[] points;
	private Line[] lines;
	
	public Tree(Point[] points, Line[] lines) {
		this.points = points;
		this.lines = lines;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public Line[] getLines() {
		return lines;
	}
	
	public String toString() {
		String string = "TREE \n Points:";
		for(Point p : points) {
			string += p.toString() + "\n";
		}
		string += "Lines:\n";
		for(Line l : lines) {
			string += l.toString() + "\n";
		}
		return string;
	}
	
	public double getLength() {
		double length = 0;
		for(Line line : lines) {
			length += line.getLength();
		}
		return length;
		}
	
	public Line getBottleneck() {
		Line bottleneck = lines[0];
		for(Line line: lines) {
			if (line.getLength() > bottleneck.getLength()) bottleneck = line;
		}
		return bottleneck;
	}
}
