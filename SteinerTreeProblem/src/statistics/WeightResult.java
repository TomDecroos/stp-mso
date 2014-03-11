package statistics;

public class WeightResult implements Comparable<WeightResult>{
	public double w2;
	public double w3;
	public double result;
	public WeightResult(double w2, double w3, double result) {
		this.w2 = w2;
		this.w3 = w3;
		this.result = result;
	}
	@Override
	public int compareTo(WeightResult other) {
		return this.result < other.result ? -1: (this.result == other.result? 0 : 1);
	}
	
	@Override
	public String toString() {
		return "w2: " + w2 + " w3: " + w3 + " result: " + result;
	}
	
}
