package old.statistics;

import java.util.Arrays;

import old.pso.SwarmConfig;
import old.steiner.STPSolver;

public class BestWeightSearcher {
	
	public Analyzer analyzer;
	public WeightResult[] results;
	public BestWeightSearcher(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	/**
	 * 
	 * @param weights The amount of different weights between 0 and 1 to try for w2 and w3.
	 * @param runs the amount of runs the analyzer should do per choice of weights.
	 */
	public void search(int weights, int runs) {
		results = new WeightResult[weights*weights];
		int k = 0;
		SwarmConfig swarmcfg = ((STPSolver) analyzer.ps).swarmcfg;
		double gap = 1.0/weights;
		for(int i=0;i<weights;i++) {
			double w2 = i*gap;
			swarmcfg.w2 = w2;
			for(int j=0;j<weights;j++) {
				double w3 = j*gap;
				swarmcfg.w3 = w3;
				analyzer.run(runs);
				results[k++] = new WeightResult(w2,w3,analyzer.getStatData().median);				
			}
		}
		Arrays.sort(results);
	}
	
	public String getResults(int n) {
		String string = "";
		for(int i=0;i<n;i++) string += results[i].toString() + "\n";
		return string;
	}
}
