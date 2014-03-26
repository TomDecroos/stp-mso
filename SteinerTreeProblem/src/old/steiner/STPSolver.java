package old.steiner;

import old.pso.FitnessEvaluator;
import old.pso.Particle;
import old.pso.ParticleSwarmOptimizer;
import old.pso.SwarmConfig;
import old.statistics.ProblemSolver;
import visual.SteinerTree;

public class STPSolver implements ProblemSolver {
	
	public STP stp;
	public SwarmGenerator swarmgen;
	public SwarmConfig swarmcfg;
	public FitnessEvaluator feval;

	public STPSolver(STP stp, SwarmConfig swarmcfg, FitnessEvaluator feval) {
		this.stp = stp;
		this.swarmgen = new SwarmGenerator(stp);
		this.swarmcfg = swarmcfg;
		this.feval = feval;
	}

	@Override
	public double solve() {
		Particle p = execute();
		return p.fitness;
	}
	
	public SteinerTree getSteinerTree() {
		Particle p = execute();
		ParticleConverter pc = new ParticleConverter(stp);
		return pc.getSteinerTree(p);
	}
	
	private Particle execute() {
		ParticleSwarmOptimizer pso = new ParticleSwarmOptimizer(
				swarmgen.getSwarm(swarmcfg.swarmsize),
				feval,
				swarmcfg.w1,
				swarmcfg.w2,
				swarmcfg.w3
				);
		pso.fly(swarmcfg.cycles);
		return pso.getBestParticle();
	}

}
