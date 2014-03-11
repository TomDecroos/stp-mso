package test;

import java.io.IOException;

import pso.Particle;

import input.DataReader;
import steiner.STP;
import steiner.ParticleConverter;
import steiner.SwarmGenerator;
import visual.SteinerTreeDrawer;

public class SwarmGeneratorTester {
	static int problems = 20;
	static int index = 0;
	static int k = 3;
	static double[] steinerpoints = {0.2,0.2,0.8,0.4,0.65,0.5};
	static int psize = 10;
	static int swarmsize = 5;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein"+problems+".txt");
		STP bstp =  dr.getSTP(index, k);
		
		SwarmGenerator sg = new SwarmGenerator(bstp);
		
		for (Particle particle : sg.getSwarm(swarmsize).particles) {
			ParticleConverter pc = new ParticleConverter(bstp);
			SteinerTreeDrawer std = new SteinerTreeDrawer(pc.getSteinerTree(particle),psize);
			std.draw(400, 400);
		}

	}

}
