package input;

import java.io.IOException;

import steiner.STP;

public class STPConfig {
	public static String BENCHMARKLOCATION = "src/benchmarks/";
	public static String BENCHMARKNAME = "estein";
	public int size;
	public int index;
	public int k;
	
	public STPConfig(int size, int index, int k) {
		this.size = size;
		this.index = index;
		this.k = k;
	}
	
	public STP getSTP() throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData(BENCHMARKLOCATION + BENCHMARKNAME + size +".txt");
		return dr.getSTP(index, k);
	}
}
