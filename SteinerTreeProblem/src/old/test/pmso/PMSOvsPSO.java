package old.test.pmso;

import java.io.IOException;

import old.input.STPConfig;
import old.pmso.PMSO;
import old.pso.FitnessEvaluator;
import old.pso.SwarmConfig;
import old.statistics.ProblemSolver;
import old.steiner.BottleneckEvaluator;
import old.steiner.STPSolver;


public class PMSOvsPSO {
	
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			0,		// index of data
			20		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			200,	// swarmsize
			20,	// cycles
			1,		// w1
			0.25,	// w2
			0.25	// w3
			);
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.out.println("PSO");
		long start = System.nanoTime();
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		ProblemSolver ps = new STPSolver(stpcfg.getSTP(),swarmcfg,feval);
		double res = ps.solve();
		long end = System.nanoTime();
		System.out.println("Bottleneck:" + res);
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
		
		System.out.println("PMSO");
		long start2 = System.nanoTime();
		PMSO pmso = new PMSO(stpcfg.getSTP(),
						swarmcfg.swarmsize,
						swarmcfg.w1,
						swarmcfg.w2);
		pmso.evolve(swarmcfg.cycles);
		double res2 = pmso.getBottleneck();
		long end2 = System.nanoTime();
		System.out.println("Bottleneck:" + res2);
		double duration2 = (end2-start2);
		System.out.println("Execution time: " + (duration2/1000000000) + " s");
		
	}

}
