package execute;

import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import stpmso.TreeLengthComparator;
import animate.Animator;
import animate.AnimatorConfig;
import benchmarks.DataConfig;

public class TreeLengthSolver {
	
	public static DataConfig data = new DataConfig(60);
	public static int index= 5;
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			500, // cycles
			0.95, // w1
			0.25 // w2
			);
	public static AnimatorConfig animatorcfg = new AnimatorConfig(
			true, // color steiner edges red
			false, // color bottleneck green
			true // display particles
			);
	
	public static void main(String[] args) {
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(data.getPoints(index), msoconfig, new TreeLengthComparator());
		new Animator(mso,cycles,animatorcfg).play();
	}
}
