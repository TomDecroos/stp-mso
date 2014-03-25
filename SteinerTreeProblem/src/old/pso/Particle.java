package old.pso;

public class Particle {
	public double[] position;
	public double[] velocity;
	public double fitness;
	
	/** The best location in this particle's history. */
	public Particle pbest;
	
	
	public Particle(double[] position) {
		this.position = position;
		this.velocity = new double[position.length];
	}
	
	public Particle clone() {
		Particle clone = new Particle(position.clone());
		clone.fitness = fitness;
		return clone;
	}
	
}
