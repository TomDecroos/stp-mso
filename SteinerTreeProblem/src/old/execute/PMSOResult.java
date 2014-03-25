package old.execute;

import java.io.IOException;

import old.input.STPConfig;
import old.pmso.PMSO;


public class PMSOResult {

	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			0,		// index of data
			50		// Nb of Steiner points
			);
	
	static int swarmsize = 1000;	// swarmsize
	static int cycles = 1000;	// cycles
	static double w1 =	0.95;		// w1
	static double w2 = 0.25;	// w2
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		PMSO pmso = new PMSO(stpcfg.getSTP(), swarmsize,w1,w2);
		long start = System.nanoTime();
		for(int i=1;i<=100;i++) {
			pmso.evolve(cycles/100);
			System.out.println("Calculations " + i + "% complete... Temp result: " + pmso.getBottleneck());
		}
		
		long end = System.nanoTime();
		
		System.out.println("Result: " + pmso.getBottleneck());
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
	}

}
