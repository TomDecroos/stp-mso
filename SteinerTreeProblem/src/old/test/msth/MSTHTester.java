package old.test.msth;

import java.io.IOException;

import old.input.STPConfig;
import old.visual.DrawConfig;
import old.visual.SteinerTreeDrawer;

import animate.SteinerTree;

import msth.MSTH;

public class MSTHTester {

	static STPConfig stpcfg = new STPConfig(
			10,		// Nb of points,
			1,		// index of data
			100		// Nb of Steiner points
			);
	static DrawConfig drawcfg = new DrawConfig(
			650,	// width
			650,	// length
			4		// pointsize
			);
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		SteinerTree tree = MSTH.getSteinerTree(stpcfg.getSTP());
		new SteinerTreeDrawer(tree).draw(drawcfg);

	}

}
