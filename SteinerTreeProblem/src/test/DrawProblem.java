package test;


import javax.swing.JFrame;

import basic.Point;

import visual.PointsDrawer;

public class DrawProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		Point[] points = {new Point(2.5,3.6), new Point(10,9.8), new Point(9,4.3),new Point(0,0),
				new Point(5,12.6),new Point(50,100)};
		PointsDrawer dp = new PointsDrawer(points);
		jframe.add(dp);
		jframe.setVisible(true);
	}

}
