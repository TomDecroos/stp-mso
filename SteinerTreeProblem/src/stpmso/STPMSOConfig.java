package stpmso;

public class STPMSOConfig {
	public int max; // max steiner points
	public int swarmsize;
	public double w1; // weight of original velocity.
	public double w2; // weight of global best.
	
	/**
	 * Constructor for limited amount of steiner points
	 * @param k
	 * @param swarmsize
	 * @param cycles
	 * @param w1
	 * @param w2
	 */
	public STPMSOConfig(int max, int swarmsize, double w1, double w2) {
		this.max = max;
		this.swarmsize = swarmsize;
		this.w1 = w1;
		this.w2 = w2;
	}
	
	/**
	 * Constructor for unlimited steiner points
	 * @param swarmsize
	 * @param cycles
	 * @param w1
	 * @param w2
	 */
	public STPMSOConfig(int swarmsize, double w1, double w2) {
		this(Integer.MAX_VALUE,swarmsize,w1,w2);
	}
}
