package mstheuristic;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import mst.Kruskall;
import mst.MinimalSpanningTree;

import visual.SteinerTree;

import basic.Line;
import basic.Point;

public class MSTH {

	public static SteinerTree getSteinerTree(Point[] points,int k) {
		MinimalSpanningTree tree = Kruskall.constructMinimalSpanningTree(Kruskall.convertToNodes(points));
		LinkedList<Line> lines = new LinkedList<Line>(Arrays.asList(tree.getEdges()));
		Point[] steinerPoints = new Point[k];
		Collections.sort(lines);
		for(int i=0;i<k;i++) {
			Line line = lines.removeLast();
			Point steinerPoint = getSteinerPoint(line);
			Line[] newLines = getNewLines(line,steinerPoint);
			lines.add(newLines[0]);lines.add(newLines[1]);
			steinerPoints[i] = steinerPoint;
			Collections.sort(lines);
		}
		
		//Point[] points = KruskallMethods.merge(tree.getPoints(),steinerPoints);
		// ERROR: edges and line2s cannot be merged with system.arraycopy()
		Line[] newlines = lines.toArray(new Line[0]);
		return new SteinerTree(tree.getNodes(), newlines, steinerPoints);
	}

	private static Line[] getNewLines(Line line, Point steinerPoint) {
		Line l1 = new Line2(line.getA(),steinerPoint);
		Line l2 = new Line2(line.getB(),steinerPoint);
		return new Line[] {l1,l2};
	}

	private static Point getSteinerPoint(Line line) {
		double x = (line.getA().getX() + line.getB().getX())/2;
		double y = (line.getA().getY() + line.getB().getY())/2;
		return new Point(x,y);
	}
}
