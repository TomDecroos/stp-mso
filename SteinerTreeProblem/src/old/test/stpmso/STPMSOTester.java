package old.test.stpmso;

import java.io.IOException;

import old.input.DataConfig;

import animate.Animate;
import animate.AnimateConfig;

import stpmso.STPMSO;
import stpmso.STPMSOConfig;
import stpmso.TreeLengthComparator;


public class STPMSOTester {
	static DataConfig data = new DataConfig(100);
	static int index = 0;
	static STPMSOConfig cfg = new STPMSOConfig(
			//50, // max amount of steiner points
			500, // swarmsize
			0.95, // w1 
			0.25 // w2
	);
	static int cycles = 500;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		STPMSO mso = new STPMSO(data.getPoints(index),cfg, new TreeLengthComparator());
		new Animate(mso,cycles,new AnimateConfig()).play();
	}

}
