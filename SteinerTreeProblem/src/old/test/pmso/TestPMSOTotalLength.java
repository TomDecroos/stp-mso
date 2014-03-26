package old.test.pmso;


import java.io.IOException;

import old.input.STPConfig;
import old.pmso.PMSOTotalLength;
import old.pmso.PMSOTotalLengthAnimation;
import old.visual.DrawConfig;



public class TestPMSOTotalLength {
	
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			0,		// index of data
			50// Nb of Steiner points
			);
	
	static int swarmsize = 5000;	// swarmsize
	static int cycles = 500;	// cycles
	static double w1 =	0.99;		// w1
	static double w2 = 0.25;	// w2
	
	static DrawConfig drawcfg = new DrawConfig(
			650,	// width
			650,	// length
			2	// pointsize
			);
	static int sleep = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		PMSOTotalLength pmso0 = new PMSOTotalLength(new STPConfig(stpcfg.size,stpcfg.index,0).getSTP(),0,0,0);
		new PMSOTotalLengthAnimation(pmso0,0,0,new DrawConfig(drawcfg.width,0,drawcfg.width,drawcfg.length,drawcfg.pointsize)).play();
		
		PMSOTotalLength pmso = new PMSOTotalLength(stpcfg.getSTP(), swarmsize,w1,w2);
		new PMSOTotalLengthAnimation(pmso,cycles,sleep,drawcfg).play();
	}
}
