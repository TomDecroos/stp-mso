package old.test.Statistics;

import java.io.IOException;

import old.input.STPConfig;
import old.pso.FitnessEvaluator;
import old.pso.SwarmConfig;
import old.statistics.Analyzer;
import old.statistics.ProblemSolver;
import old.statistics.StatData;
import old.steiner.BottleneckEvaluator;
import old.steiner.STPSolver;
import old.steiner.TreeLengthEvaluator;


@SuppressWarnings("unused")
public class AnalyzerTester {
	static STPConfig stpcfg = new STPConfig(
			50,		// Nb of points,
			0,		// index of data
			25		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			100,	// swarmsize
			20,	// cycles
			1,		// w1
			0.25,	// w2
			0.25	// w3
			);
	static int nbOfRuns = 20; // amount of times to solve the problems.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//FitnessEvaluator feval = new TreeLengthEvaluator(stpcfg.getSTP());
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		ProblemSolver ps = new STPSolver(stpcfg.getSTP(),swarmcfg,feval);
		Analyzer analyzer = new Analyzer(ps);
		
		long start = System.nanoTime();
		analyzer.run(nbOfRuns);
		long end = System.nanoTime();
		
		StatData statData = analyzer.getStatData();
		System.out.println(statData);
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
	}
}
