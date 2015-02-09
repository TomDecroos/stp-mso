package mstheuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import mst.Edge;
import mst.Kruskall;
import mst.MinimalSpanningTree;
import visual.SteinerTree;
import basic.Line;
import basic.Point;

public class MSTH {

	public static SteinerTree getSteinerTree(Point[] points,int k) {
		MinimalSpanningTree tree = Kruskall.constructMinimalSpanningTree(Kruskall.convertToNodes(points));
		LinkedList<Line2> lines = convertToLines(tree.getEdges());
		
		for(int i=0;i<k;i++) {
			Collections.sort(lines);
			lines.getLast().addSteinerpoint();
		}
		
		ArrayList<Point> steinerPoints = new ArrayList<>();
		ArrayList<Line> newlines = new ArrayList<>();
		for(Line2 line : lines) {
			if(line.getSteinerpoints() > 0) {
				Point[] linepoints = getSteinerPoints(line.getA(),line.getB(),line.getSteinerpoints());
				for(Point point : linepoints) steinerPoints.add(point);
				Line[] linelines = getNewLines(line.getA(),line.getB(),linepoints);
				for(Line lineline : linelines) newlines.add(lineline);
			} else {
				newlines.add(line);
			}
		}
		Line[] treelines = newlines.toArray(new Line[0]);
		Point[] treepoints = steinerPoints.toArray(new Point[0]); 
		return new SteinerTree(tree.getNodes(), treelines, treepoints);
	}

	private static LinkedList<Line2> convertToLines(Edge[] edges) {
		LinkedList<Line2> lines = new LinkedList<>();
		for (Edge edge : edges) {
			lines.add(new Line2(edge.getA(),edge.getB()));
		}
		return lines;
	}

	private static Line[] getNewLines(Point a, Point b, Point[] steinerpoints) {
		Line[] lines = new Line[steinerpoints.length+1];
		lines[0] = new Line2(a,steinerpoints[0]);
		for (int i = 1; i < steinerpoints.length; i++) {
			lines[i] = new Line2(steinerpoints[i-1],steinerpoints[i]);
		}
		lines[steinerpoints.length] = new Line2(steinerpoints[steinerpoints.length-1],b);
		return lines;
	}

	private static Point[] getSteinerPoints(Point a, Point b,int k) {
		Point[] steinerpoints = new Point[k];
		for(int i=0;i<k;i++) {
			double xa = a.getX();
			double xb = b.getX();
			double ya = a.getY();
			double yb = b.getY();
			
			double x = xa + (i+1)*(xb-xa)/(k+1);
			double y = ya + (i+1)*(yb-ya)/(k+1);
			steinerpoints[i] = new Point(x,y);
		}
		return steinerpoints;
	}
	
	
}
