package old.pso;

public class ParticleSwarmOptimizer {
	
	private Swarm swarm;
	private FitnessEvaluator fitnessEvaluator;
	public double w1 = 0.25;
	public double w2 = 0.25;
	public double w3 = 0.25;
	
	public ParticleSwarmOptimizer(Swarm swarm, FitnessEvaluator fitnessEvaluator) {
		this.swarm = swarm;
		this.fitnessEvaluator = fitnessEvaluator;
		setup();
	}
	
	public ParticleSwarmOptimizer(Swarm swarm, FitnessEvaluator fitnessEvaluator,
			double w1, double w2, double w3) {
		this.swarm = swarm;
		this.fitnessEvaluator = fitnessEvaluator;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
		setup();
	}
	
	private void setup() {
		// initialize particles to be ready to act in the optimisation
		for(Particle particle : swarm.particles) {
			initialize(particle);
		}
		// select the best particle 
		swarm.gbest = swarm.particles[0];
		for(Particle particle : swarm.particles) {
			if(particle.fitness < swarm.gbest.fitness) swarm.gbest = particle.clone();
		}
	}

	/**
	 * Make all particles move n times.
	 */
	public void fly(int n) {
		for(int i=0;i<n;i++) {
			for(Particle particle : swarm.particles) {
				move(particle);
				update(particle);
			}
		}
	}
	
	public Particle getBestParticle() {
		return swarm.gbest;
	}
	
	public Swarm getSwarm() {
		return this.swarm;
	}
	/**
	 * Initialise the particle with the necessary information
	 */
	private void initialize(Particle particle) {
		particle.fitness = fitnessEvaluator.getFitness(particle);
		particle.pbest = particle.clone();
	}


	private void move(Particle particle) {
		// v[] = w1* rand() * v[] + w2 * rand() * (pbest-present) + w3 * rand() * (gbest-present)
		double[] ofactor = multiply(particle.velocity,w1);
		double[] pfactor = multiply(min(particle.pbest.position,particle.position) , w2*rand());
		double[] gfactor = multiply(min(swarm.gbest.position,particle.position) , w3*rand());
		particle.velocity = plus(ofactor,plus(pfactor,gfactor));
		// p[] = p[] + v[]
		particle.position = plus(particle.position,particle.velocity);
	}
	
	private void update(Particle particle) {
		particle.fitness = fitnessEvaluator.getFitness(particle);
		if(particle.fitness < particle.pbest.fitness) particle.pbest = particle.clone();
		if(particle.fitness < swarm.gbest.fitness) swarm.gbest = particle.clone();
	}


	private double[] plus(double[] x, double[] y) {
		double[] z = x.clone();
		for(int i=0;i<x.length;i++) {
			z[i] += y[i];
		}
		return z;
	}
	
	private double[] min(double[] x, double[] y) {
		double[] z = x.clone();
		for(int i=0;i<x.length;i++) {
			z[i] -= y[i];
		}
		return z;
	}
	
	private double[] multiply(double[] x, double a) {
		double[] z = x.clone();
		for(int i=0;i<x.length;i++) {
			z[i] *=a;
		}
		return z;
	}
	
	private double rand() {
		return Math.random();
	}
}
