package test.visual;

import java.io.IOException;

import input.STPConfig;
import pso.FitnessEvaluator;
import pso.SwarmConfig;
import steiner.BottleneckEvaluator;
import steiner.STPStepByStepSolver;
import steiner.TreeLengthEvaluator;
import visual.DrawConfig;
import visual.PSOAnimation;

public class PSOAnimationTester {

	static STPConfig stpcfg = new STPConfig(
			1000,		// Nb of points,
			4,		// index of data
			5		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			5000,	// swarmsize
			500,	// cycles
			0.95,		// w1
			0.25,	// w2
			0.25	// w3
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
