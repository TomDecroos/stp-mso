package execute;

import mst.Kruskall;
import mst.MinimalSpanningTree;
import disjointset.Node;
import stpmso.BottleneckComparator;
import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import visual.Animator;
import visual.AnimatorConfig;
import basic.Point;
import benchmarks.data.DataReader;

public class BottleneckSolver {
	
	/**
	 * DATA CONFIG
	 */
	public static int problemsize = 10000;
	public static int index= 0;
	
	/**
	 * MSO CONFIG
	 */
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			50, // max steiner points
			500, // swarmsize
			0.95, // w1
			0.25 // w2
			);
	/**
	 * VISUAL CONFIG
	 */
	public static AnimatorConfig animatorcfg = new AnimatorConfig(
			0, 0, 850, 650, 4, 2, 500, true, true, true);
	
	public static void main(String[] args) {
		Point[] points = new DataReader(problemsize).getPoints(index);
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(points, msoconfig, new BottleneckComparator());
		Node[] nodes = Kruskall.convertToNodes(points);
		MinimalSpanningTree original = Kruskall.constructMinimalSpanningTree(nodes);
		new Animator(mso,cycles,animatorcfg,original).play();
	}
	
}
