package test;

import java.io.IOException;

import visual.PointsDrawer;

import input.DataReader;

public class ReadAndDraw {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein10000.txt");
		PointsDrawer pd = new PointsDrawer(dr.getArraysOfPoints()[0]);
		pd.draw(500, 500,2);
	}

}
