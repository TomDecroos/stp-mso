package execute;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import stpmso.TreeLengthComparator;
import basic.Point;

public class Main {

	/**
	 * @param 	args[0] location instance file
	 * 			args[1] w1
	 * 			args[2] w2
	 * 			args[3] swarm size
	 * 			args[4] cycles
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		String file = args[0];
		double w1 = Double.parseDouble(args[2]);
		double w2 = Double.parseDouble(args[3]);
		int swarmsize = Integer.parseInt(args[4]);
		int cycles = Integer.parseInt(args[5]);
		
		MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(swarmsize, w1, w2);
		Point[] points = readData(file);
		MultiSwarmOptimizer solver = new MultiSwarmOptimizer(points, msoconfig, new TreeLengthComparator());
		solver.evolve(cycles);
		
		System.out.println(solver.getSteinerTree().getLength());

	}
	
	private static Point[] readData(String file) throws IOException {
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		int nbOfPoints = Integer.valueOf(br.readLine().trim());
		Point[] data = new Point[nbOfPoints];
		for(int j=0;j<nbOfPoints;j++) {
			String[] coordinates=  br.readLine().trim().split(" ");
			double x = Double.parseDouble(coordinates[0]);
			double y = Double.parseDouble(coordinates[1]);
			data[j] = new Point(x,y);
		}
		br.close();
		return data;
		
	}

}
