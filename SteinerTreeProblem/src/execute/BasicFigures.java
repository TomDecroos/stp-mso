package execute;

import pso.FitnessEvaluator;
import pso.Particle;
import pso.ParticleSwarmOptimizer;
import pso.Swarm;
import steiner.STP;
import steiner.BottleneckEvaluator;
import steiner.ParticleConverter;
import steiner.SwarmGenerator;
import steiner.TreeLengthEvaluator;
import visual.SteinerTreeDrawer;
import basic.Point;

public class BasicFigures {
	static int psize = 10;
	static int k = 1;
	static int swarmsize = 10;
	static double w1 = 1;
	static double w2 = 0;
	static double w3 = 0.25;

	static Point[] points = new Point[] {
			 new Point(0.1,0.1)
			,new Point(0.1,0.9)
			,new Point(0.8,0.1)
			,new Point(0.5,0.5)
			,new Point(0.8,0.9)
	};
	
	static int results = 1;
	static int cyclesPerResult = 200;
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		STP bstp = new STP(points, k);
		
		SwarmGenerator sg = new SwarmGenerator(bstp);
		Swarm swarm = sg.getSwarm(swarmsize);
		BottleneckEvaluator beval = new BottleneckEvaluator(bstp);
		FitnessEvaluator feval = new TreeLengthEvaluator(bstp);
		ParticleSwarmOptimizer pso = new ParticleSwarmOptimizer(swarm,beval);
		pso.w1 = w1; pso.w2 = w2; pso.w3 = w3;
		ParticleConverter pc = new ParticleConverter(bstp);
		
		System.out.println("before evolution: " + pso.getBestParticle().fitness);
		for(int i=0;i<results; i++) {
			pso.fly(cyclesPerResult);
			System.out.println("new best: " + pso.getBestParticle().fitness);
			//System.out.println(pc.getSteinerTree(pso.getBestParticle()).getLength());
			SteinerTreeDrawer std = new SteinerTreeDrawer(pc.getSteinerTree(pso.getBestParticle()),psize);
			std.draw(500, 500);
		}
		
		for(Particle p : pso.getSwarm().particles) {
			SteinerTreeDrawer std = new SteinerTreeDrawer(pc.getSteinerTree(p),psize);
			std.draw(200, 200);
		}
		
	}
}
