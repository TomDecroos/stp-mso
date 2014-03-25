package old.test.pso;


import java.io.IOException;

import old.input.DataReader;
import old.pso.ParticleSwarmOptimizer;
import old.pso.Swarm;
import old.steiner.BottleneckEvaluator;
import old.steiner.ParticleConverter;
import old.steiner.STP;
import old.steiner.SwarmGenerator;
import old.visual.SteinerTreeDrawer;




public class PSOtester {
	static int problems = 50;
	static int index = 1;
	static int psize = 4;
	static int k = 25;
	static int swarmsize = 200;
	static double w1 = 0.25;
	static double w2 = 0.25;
	
	static int results = 3;
	static int cyclesPerResult = 20;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein"+problems+".txt");
		STP bstp =  dr.getSTP(index, k);
		
		SwarmGenerator sg = new SwarmGenerator(bstp);
		Swarm swarm = sg.getSwarm(swarmsize);
		BottleneckEvaluator beval = new BottleneckEvaluator(bstp);
		ParticleSwarmOptimizer pso = new ParticleSwarmOptimizer(swarm,beval);
		pso.w1 = w1; pso.w2 = w2;
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
