package execute;

import java.io.IOException;

import input.STPConfig;
import pmso.PMSO;
import pmso.PMSOAnimation;
import visual.DrawConfig;

public class AnimatePMSO {
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			7,		// index of data
			25		// Nb of Steiner points
			);
	
	static int swarmsize = 1000;	// swarmsize
	static int cycles = 50;	// cycles
	static double w1 =	0.99;		// w1
	static double w2 = 0.25;	// w2
	
	static DrawConfig drawcfg = new DrawConfig(
			650,	// width
			650,	// length
			2	// pointsize
			);
	static int sleep = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		PMSO pmso0 = new PMSO(new STPConfig(stpcfg.size,stpcfg.index,0).getSTP(),0,0,0);
		new PMSOAnimation(pmso0,0,0,new DrawConfig(drawcfg.width,0,drawcfg.width,drawcfg.length,drawcfg.pointsize)).play();
		
		PMSO pmso = new PMSO(stpcfg.getSTP(), swarmsize,w1,w2);
		new PMSOAnimation(pmso,cycles,sleep,drawcfg).play();
	}

}
