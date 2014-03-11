package pso;

public class SwarmConfig {
	public int swarmsize;
	public int cycles;
	public double w1; // weight of original velocity.
	public double w2; // weight of local best.
	public double w3; // weight of global best.
	
	public SwarmConfig(int swarmsize, int cycles, double w1, double w2, double w3) {
		this.swarmsize = swarmsize;
		this.cycles = cycles;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
	}
	
}
