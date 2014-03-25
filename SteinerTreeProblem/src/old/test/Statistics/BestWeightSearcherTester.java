package old.test.Statistics;

import java.io.IOException;

import old.input.STPConfig;
import old.pso.FitnessEvaluator;
import old.pso.SwarmConfig;
import old.statistics.Analyzer;
import old.statistics.BestWeightSearcher;
import old.statistics.ProblemSolver;
import old.steiner.BottleneckEvaluator;
import old.steiner.STPSolver;


public class BestWeightSearcherTester {
	
	static STPConfig stpcfg = new STPConfig(
			1000,		// Nb of points,
			0,		// index of data
			10		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			50,	// swarmsize
			20,	// cycles
			1,		// w1
			-1,	// w2 irrelevant
			-1	// w3 irrelevant
			);
	static int nbOfRuns = 5; // amount of times to solve the problems.
	static int weights = 20; // amount of different weights between 0 and 1 to try.

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		//FitnessEvaluator feval = new TreeLengthEvaluator(stpcfg.getSTP());
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		ProblemSolver ps = new STPSolver(stpcfg.getSTP(),swarmcfg,feval);
		Analyzer analyzer = new Analyzer(ps);
		BestWeightSearcher bws = new BestWeightSearcher(analyzer);
		
		long start = System.nanoTime();
		bws.search(weights, nbOfRuns);
		long end = System.nanoTime();
		
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
		
		System.out.println(bws.getResults(10));

	}

}
