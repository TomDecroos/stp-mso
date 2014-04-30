package execute;

import mst.Kruskall;
import mst.MinimalSpanningTree;
import disjointset.Node;
import stpmso.BottleneckComparator;
import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import visual.Animator;
import visual.AnimatorConfig;
import benchmarks.data.DataReader;

public class BottleneckSolver {
	
	public static DataReader data = new DataReader(10);
	public static int index= 0;
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			1, // max steiner points
			500, // cycles
			0.95, // w1
			0.25 // w2
			);
	public static AnimatorConfig animatorcfg = new AnimatorConfig();
	
	public static void main(String[] args) {
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(data.getPoints(index), msoconfig, new BottleneckComparator());
		Node[] nodes = Kruskall.convertToNodes(data.getPoints(index));
		MinimalSpanningTree original = Kruskall.constructMinimalSpanningTree(nodes);
		new Animator(mso,cycles,animatorcfg,original).play();
	}
	
}
