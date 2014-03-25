package old.execute;


import java.io.IOException;

import old.input.STPConfig;
import old.pso.FitnessEvaluator;
import old.pso.SwarmConfig;
import old.steiner.BottleneckEvaluator;
import old.steiner.STPStepByStepSolver;
import old.visual.DrawConfig;
import old.visual.PSOAnimation;


public class Animate {


	static STPConfig stpcfg = new STPConfig(
			50,		// Nb of points,
			4,		// index of data
			50		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			50,	// swarmsize
			50,	// cycles
			1,		// w1
			0.25,	// w2
			0	// w3
			);
	static DrawConfig drawcfg = new DrawConfig(
			600,	// width
			600,	// length
			2	// pointsize
			);
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		STPStepByStepSolver solver = new STPStepByStepSolver(stpcfg.getSTP(),swarmcfg,feval);
		
		new PSOAnimation(solver,swarmcfg.cycles,drawcfg).play();
	}

}
