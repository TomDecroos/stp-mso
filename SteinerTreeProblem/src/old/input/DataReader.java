package old.input;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import old.steiner.STP;


import basic.Point;


public class DataReader {
	
	private Point[][] arraysOfPoints;

	public Point[][] getArraysOfPoints() {
		return arraysOfPoints;
	}

	public void setArraysOfPoints(Point[][] arraysOfPoints) {
		this.arraysOfPoints = arraysOfPoints;
	}

	public void readData(String file) throws NumberFormatException, IOException {
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		int nbOfProblems = Integer.valueOf(br.readLine().trim());
		arraysOfPoints = new Point[nbOfProblems][];
		for(int i=0;i<nbOfProblems;i++) {
			int nbOfPoints = Integer.valueOf(br.readLine().trim());
			arraysOfPoints[i] = new Point[nbOfPoints];
			for(int j=0;j<nbOfPoints;j++) {	
				String[] coordinates=  br.readLine().trim().split(" ");
				//System.out.println(coordinates[0]);
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				arraysOfPoints[i][j] = new Point(x,y);
			}
		}
		br.close();
	}
	
	public STP[] getSTPs(int k) {
		int l = arraysOfPoints.length;
		STP[] problems = new STP[l];
		for(int i=0;i<l;i++) {
			problems[i] = new STP(arraysOfPoints[i],k);
		}
		return problems;
	}
	
	public STP getSTP(int index, int nbOfSteinerPoints) {
		return new STP(arraysOfPoints[index],nbOfSteinerPoints);
	}
	
	public STP getSTP(int index, int nbOfSteinerPoints, int nbOfPoints) {
		Point[] points = Arrays.copyOf(arraysOfPoints[index], nbOfPoints);
		return new STP(points,nbOfSteinerPoints);
	}
	
	public void printpoints(int i) {
		for(Point p : arraysOfPoints[i]) {
			System.out.println(p.toString());
		}
	}
}
