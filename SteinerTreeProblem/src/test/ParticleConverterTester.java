package test;

import java.io.IOException;

import pso.Particle;

import steiner.STP;
import steiner.ParticleConverter;
import visual.SteinerTreeDrawer;

import input.DataReader;

public class ParticleConverterTester {
	static int problems = 1000;
	static int index = 0;
	static int k = 3;
	static double[] steinerpoints = {0.2,0.2,0.8,0.4,0.65,0.5};
	static int psize = 10;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein"+problems+".txt");

		STP bstp =  dr.getSTP(index, k);
		Particle particle = new Particle(steinerpoints);
		ParticleConverter pc = new ParticleConverter(bstp);
		SteinerTreeDrawer std = new SteinerTreeDrawer(pc.getSteinerTree(particle),psize);
		std.draw(700, 700);
	}

}
