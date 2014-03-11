package steiner;

import pso.FitnessEvaluator;
import pso.Particle;
import pso.ParticleSwarmOptimizer;
import pso.SwarmConfig;

public class STPStepByStepSolver {
	public STP stp;
	private SwarmGenerator swarmgen;
	private SwarmConfig swarmcfg;
	private FitnessEvaluator feval;
	public ParticleSwarmOptimizer pso; 
	
	public STPStepByStepSolver(STP stp, SwarmConfig swarmcfg, FitnessEvaluator feval) {
		this.stp = stp;
		this.swarmgen = new SwarmGenerator(stp);
		this.swarmcfg = swarmcfg;
		this.feval = feval;
		initializePSO();
	}
	
	public void initializePSO() {
		this.pso = new ParticleSwarmOptimizer(
				swarmgen.getSwarm(swarmcfg.swarmsize),
				feval,
				swarmcfg.w1,
				swarmcfg.w2,
				swarmcfg.w3
				);
	}
	
	/**
	 * Evolve once
	 */
	public void next() {
		pso.fly(1);
	}
	
	public SteinerTree getSteinerTree() {
		ParticleConverter pc = new ParticleConverter(stp);
		return pc.getSteinerTree(pso.getBestParticle());
	}
	
	public Particle[] getParticles() {
		return pso.getSwarm().particles;
	}

}
