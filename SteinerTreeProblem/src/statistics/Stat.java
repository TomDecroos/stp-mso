package statistics;
import java.util.Arrays;

public class Stat {
	public static double mean(double[] data) {
		double total = 0;
		for(double x : data) total += x;
		return total / data.length;
	}
	public static double median(double[] data) {
		double[] dataClone = data.clone();
		Arrays.sort(dataClone);
		if(dataClone.length % 2 == 0) {
			return (dataClone[dataClone.length/2 - 1] + dataClone[dataClone.length/2]) / 2;
		} else {
			return dataClone[dataClone.length/2-1];
		}
	}
	
	public static double max(double[] data) {
		double max = data[0];
		for(double x: data) if(x>max) max = x;
		return max;
	}
	
	public static double min(double[] data) {
		double min = data[0];
		for(double x: data) if(x<min) min = x;
		return min;
	}
	
	public static double variance(double[] data) {
		double mean = mean(data);
		double sum = 0;
		for(double x : data) sum += (x-mean)*(x-mean);
		return sum / (data.length-1);
	}
	
}
