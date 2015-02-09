package execute;

import java.util.Arrays;

import disjointset.Node;
import mst.Kruskall;
import mst.MinimalSpanningTree;
import mst.UsableEdges;
import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import stpmso.TreeLengthComparator;
import visual.Animator;
import visual.AnimatorConfig;
import visual.DrawConfig;
import visual.Drawer;
import visual.SteinerTree;
import basic.Point;
import benchmarks.data.DataReader;
import benchmarks.optimalsolutions.SolutionReader;

public class MSOvsOptimalSolution {
	/**
	 * DATA CONFIG
	 */
	public static int problemsize = 90;
	public static int index= 0;
	
	/**
	 * MSO CONFIG
	 */
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			50, // swarmsize
			0.95, // w1
			0.25 // w2
			);
	public static int usableEdges = 10; // amount of edges to process per steiner point to add.
	
	/**
	 * VISUAL CONFIG
	 */
	public static AnimatorConfig animatorcfg = AnimatorConfig.getTreeLengthConfig();
	public static DrawConfig drawcfg = DrawConfig.getTreeLengthConfig();
	
	/**
	 * MAIN
	 */
	public static void main(String[] args) {
		UsableEdges.MAX = usableEdges;
		//drawOptimalSolution();
		animateMSO();
	}
	
	public static void drawOptimalSolution() {
		Node[] basicNodes = Kruskall.convertToNodes(new DataReader(problemsize).getPoints(index));
		Node[] steinerNodes = Kruskall.convertToNodes(new SolutionReader(problemsize).getSteinerPoints(index));
		Node[] nodes = merge(basicNodes,steinerNodes);
		MinimalSpanningTree original = Kruskall.constructMinimalSpanningTree(basicNodes);
		MinimalSpanningTree tree = Kruskall.constructMinimalSpanningTree(nodes);
		SteinerTree steinerTree = new SteinerTree(tree.getNodes(), tree.getEdges(), steinerNodes);
		new Drawer(steinerTree,drawcfg,original).draw();
	}
	
	private static <T> T[] merge(T[] a, T[] b) {
		T[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	private static void animateMSO() {
		Point[] points = new DataReader(problemsize).getPoints(index);
		Node[] nodes = Kruskall.convertToNodes(points);
		MinimalSpanningTree original = Kruskall.constructMinimalSpanningTree(nodes);
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(points, msoconfig, new TreeLengthComparator());
		new Animator(mso,cycles,animatorcfg,original).play();
	}

}
