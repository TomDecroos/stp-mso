package test;

import input.DataReader;

import java.io.IOException;

import pso.FitnessEvaluator;
import pso.ParticleSwarmOptimizer;
import pso.Swarm;
import steiner.STP;
import steiner.ParticleConverter;
import steiner.SwarmGenerator;
import steiner.TreeLengthEvaluator;
import visual.SteinerTreeDrawer;

public class TreeLengthEvaluatorTester {

	static int problems = 50;
	static int index = 1;
	static int psize = 4;
	static int k = 2;
	static int swarmsize = 200;
	static double w1 = 1;
	static double w2 = 0.5;
	static double w3 = 0.5;
	
	static int results = 3;
	static int cyclesPerResult = 20;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein"+problems+".txt");
		STP bstp =  dr.getSTP(index, k);
		
		SwarmGenerator sg = new SwarmGenerator(bstp);
		Swarm swarm = sg.getSwarm(swarmsize);
		FitnessEvaluator feval = new TreeLengthEvaluator(bstp);
		ParticleSwarmOptimizer pso = new ParticleSwarmOptimizer(swarm,feval);
		pso.w1 = w1; pso.w2 = w2; pso.w3 = w3;
		ParticleConverter pc = new ParticleConverter(bstp);
		
		System.out.println("before evolution: " + pso.getBestParticle().fitness);
		for(int i=0;i<results; i++) {
			pso.fly(cyclesPerResult);
			System.out.println("new best: " + pso.getBestParticle().fitness);
			SteinerTreeDrawer std = new SteinerTreeDrawer(pc.getSteinerTree(pso.getBestParticle()),psize);
			std.draw(500, 500);
		}
	}

}
