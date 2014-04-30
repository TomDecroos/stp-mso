package execute;

import mst.Kruskall;
import mst.MinimalSpanningTree;
import disjointset.Node;
import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import stpmso.TreeLengthComparator;
import visual.Animator;
import visual.AnimatorConfig;
import benchmarks.data.DataReader;

public class TreeLengthSolver {
	
	public static DataReader data = new DataReader(500);
	public static int index= 0;
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			500, // swarmsize
			0.95, // w1
			0.25 // w2
			);
	public static AnimatorConfig animatorcfg = new AnimatorConfig(
			0, 0, 650, 650, 4, 2, 0, true, false, false);
	
	public static void main(String[] args) {
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(data.getPoints(index), msoconfig, new TreeLengthComparator());
		Node[] nodes = Kruskall.convertToNodes(data.getPoints(index));
		MinimalSpanningTree original = Kruskall.constructMinimalSpanningTree(nodes);
		new Animator(mso,cycles,animatorcfg,original).play();
	}
}
