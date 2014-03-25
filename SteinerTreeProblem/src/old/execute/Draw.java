package old.execute;

import java.io.IOException;

import old.input.STPConfig;
import old.pso.FitnessEvaluator;
import old.pso.SwarmConfig;
import old.steiner.BottleneckEvaluator;
import old.steiner.STPSolver;
import old.steiner.TreeLengthEvaluator;
import old.visual.DrawConfig;
import old.visual.SteinerTreeDrawer;

import animate.SteinerTree;


@SuppressWarnings("unused")
public class Draw {
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			1,		// index of data
			10		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			200,	// swarmsize
			200,	// cycles
			1,		// w1
			0.25,	// w2
			0.25	// w3
			);
	static DrawConfig drawcfg = new DrawConfig(
			500,	// width
			500,	// length
			4		// pointsize
			);
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//FitnessEvaluator feval = new TreeLengthEvaluator(stpcfg.getSTP());
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		STPSolver ps = new STPSolver(stpcfg.getSTP(),swarmcfg,feval);
		
		long start = System.nanoTime();
		SteinerTree tree = ps.getSteinerTree();
		long end = System.nanoTime();
		
		new SteinerTreeDrawer(tree).draw(drawcfg);
		
		System.out.println("Length: " + tree.getLength());
		System.out.println("Bottleneck: " + tree.getBottleneck().getLength());
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
	}
}
