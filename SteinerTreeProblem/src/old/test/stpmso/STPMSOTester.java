package old.test.stpmso;

import java.io.IOException;

import benchmarks.DataConfig;


import animate.Animator;
import animate.AnimatorConfig;

import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import stpmso.TreeLengthComparator;


public class STPMSOTester {
	static DataConfig data = new DataConfig(100);
	static int index = 0;
	static MultiSwarmOptimizerConfig cfg = new MultiSwarmOptimizerConfig(
			//50, // max amount of steiner points
			500, // swarmsize
			0.95, // w1 
			0.25 // w2
	);
	static int cycles = 500;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(data.getPoints(index),cfg, new TreeLengthComparator());
		new Animator(mso,cycles,new AnimatorConfig()).play();
	}

}
