package msth;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import old.kruskall.KruskallMethods;
import old.steiner.STP;

import animate.SteinerTree;
import basic.Line;
import basic.Point;
import basic.Tree;

public class MSTH {

	public static SteinerTree getSteinerTree(STP stp) {
		Tree tree = KruskallMethods.getMST(KruskallMethods.getNodes(stp.getPoints()));
		LinkedList<Line> lines = new LinkedList<Line>(Arrays.asList(tree.getLines()));
		Point[] steinerPoints = new Point[stp.getK()];
		Collections.sort(lines);
		for(int i=0;i<stp.getK();i++) {
			Line line = lines.removeLast();
			Point steinerPoint = getSteinerPoint(line);
			Line[] newLines = getNewLines(line,steinerPoint);
			lines.add(newLines[0]);lines.add(newLines[1]);
			steinerPoints[i] = steinerPoint;
			Collections.sort(lines);
		}
		
		//Point[] points = KruskallMethods.merge(tree.getPoints(),steinerPoints);
		// ERROR: WTF?
		Line[] newlines = lines.toArray(new Line[0]);
		return new SteinerTree(tree.getPoints(), newlines, steinerPoints);
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
