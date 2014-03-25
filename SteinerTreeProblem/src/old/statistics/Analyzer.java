package old.statistics;

public class Analyzer {
	public ProblemSolver ps;
	public StatData std;
	public Analyzer(ProblemSolver ps) {
		this.ps = ps;
	}
	
	public void run(int times) {
		double[] data = new double[times];
		for(int i=0;i<times;i++) data[i] = ps.solve();
		this.std = new StatData(data);
	}
	
	public StatData getStatData() {
		return this.std;
	}
}
