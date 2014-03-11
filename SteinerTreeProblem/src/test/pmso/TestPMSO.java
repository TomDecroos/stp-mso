package test.pmso;

import java.io.IOException;

import input.STPConfig;
import pmso.PMSO;
import pmso.PMSOAnimation;
import visual.DrawConfig;

public class TestPMSO {
	static STPConfig stpcfg = new STPConfig(
			100,		// Nb of points,
			5,		// index of data
			25		// Nb of Steiner points
			);
	
	static int swarmsize = 1000;	// swarmsize
	static int cycles = 500;	// cycles
	static double w1 =	0.99;		// w1
	static double w2 = 0.25;	// w2
	
	static DrawConfig drawcfg = new DrawConfig(
			600,	// width
			600,	// length
			2	// pointsize
			);
	static int sleep = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		PMSO pmso = new PMSO(stpcfg.getSTP(),
							 swarmsize,
							 w1,
							 w2);
		new PMSOAnimation(pmso,cycles,sleep,drawcfg).play();
	}

}
