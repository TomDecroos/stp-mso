package old.statistics;

public class StatData {
	public double mean;
	public double median;
	public double max;
	public double min;
	public double variance;
	
	public StatData(double[] data) {
		this.mean = Stat.mean(data);
		this.median = Stat.median(data);
		this.max = Stat.max(data);
		this.min = Stat.min(data);
		this.variance = Stat.variance(data);
	}
	public StatData(double mean, double median, double max, double min, double variance) {
		this.mean = mean;
		this.median = median;
		this.max = max;
		this.min = min;
		this.variance = variance;
	}
	
	@Override
	public String toString() {
		int n = 5;
		String mean = round(this.mean,n);
		String median = round(this.median,n);
		String max = round(this.max,n);
		String min = round(this.min,n);
		return "Mean: " + mean + "\n"
				+ "Median: " + median + "\n"
				+ "Max: " + max + "\n"
				+ "Min: " + min + "\n"
				+ "Variance: " + variance + "\n";
	}
	
	
	/**
	 * Returns a string representing the double with only n digits.
	 */
	public static String round(double num, int n) {
		String string = String.valueOf(num);
		
		return string.length() > n+1 ? string.substring(0, n+1) : string;
	}
}
