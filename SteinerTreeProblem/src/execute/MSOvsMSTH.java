package execute;

import mstheuristic.MSTH;
import stpmso.BottleneckComparator;
import stpmso.MultiSwarmOptimizer;
import stpmso.MultiSwarmOptimizerConfig;
import visual.Animator;
import visual.AnimatorConfig;
import visual.DrawConfig;
import visual.Drawer;
import visual.SteinerTree;
import basic.Point;
import benchmarks.data.DataReader;

public class MSOvsMSTH {
	
	/**
	 * DATA CONFIG
	 */
	public static int problemsize = 1000;
	public static int index= 0;
	/**
	 * MSO CONFIG
	 */
	public static int cycles = 500;
	public static MultiSwarmOptimizerConfig msoconfig = new MultiSwarmOptimizerConfig(
			100, // max steiner points
			500, // swarmsize
			0.95, // w1
			0.25 // w2
			);
	
	/**
	 * VISUAL CONFIG
	 */
	public static AnimatorConfig animatorcfg = AnimatorConfig.getBottleneckConfig();
	public static DrawConfig drawcfg = DrawConfig.getBottleneckConfig();
	public static void main(String[] args) {
		Point[] points = new DataReader(problemsize).getPoints(index);
		
		SteinerTree tree = MSTH.getSteinerTree(points,msoconfig.max);
		new Drawer(tree,drawcfg).draw();
		
		MultiSwarmOptimizer mso = new MultiSwarmOptimizer(points,msoconfig,new BottleneckComparator());
		new Animator(mso,cycles,animatorcfg).play();
	}

}
