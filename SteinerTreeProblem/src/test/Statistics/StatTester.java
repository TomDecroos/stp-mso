package test.Statistics;

import statistics.StatData;

public class StatTester {
	static double[] data = new double[] {
		10,15,16.5,15.5,20
	};
	
	public static void main(String[] args) {
		StatData s = new StatData(data);
		System.out.println(s);
	}
}
