package old.execute;


import java.io.IOException;

import old.input.STPConfig;
import old.pmso.PMSO;
import old.pmso.PMSOAnimation;
import old.visual.DrawConfig;
import old.visual.SteinerTreeDrawer;
import visual.SteinerTree;


import mstheuristic.MSTH;


public class PMSOvsMSTH {
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			0,		// index of data
			50		// Nb of Steiner points
			);
	
	static int swarmsize = 500;	// swarmsize
	static int cycles = 500;	// cycles
	static double w1 =	0.95;		// w1
	static double w2 = 0.25;	// w2
	
	static DrawConfig drawcfg = new DrawConfig(
			650,	// width
			650,	// length
			2	// pointsize
			);
	static int sleep = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		SteinerTree tree = MSTH.getSteinerTree(stpcfg.getSTP().getPoints(),
											   stpcfg.k);
		new SteinerTreeDrawer(tree).draw(new DrawConfig(drawcfg.width,0,drawcfg.width,drawcfg.length,drawcfg.pointsize));
		
		PMSO pmso = new PMSO(stpcfg.getSTP(), swarmsize,w1,w2);
		new PMSOAnimation(pmso,cycles,sleep,drawcfg).play();
		
	}

}
