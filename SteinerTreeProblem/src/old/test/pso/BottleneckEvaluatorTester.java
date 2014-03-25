package old.test.pso;

import java.io.IOException;

import old.input.DataReader;
import old.pso.Particle;
import old.steiner.BottleneckEvaluator;
import old.steiner.ParticleConverter;
import old.steiner.STP;



public class BottleneckEvaluatorTester {

	static int problems = 1000;
	static int index = 0;
	static int k = 3;
	static double[] steinerpoints = {0.2,0.2,0.8,0.4,0.65,0.5};
	static int psize = 10;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein"+problems+".txt");
		STP bstp =  dr.getSTP(index, k);
		
		BottleneckEvaluator be = new BottleneckEvaluator(bstp);
		Particle particle = new Particle(steinerpoints);
		System.out.println("specialkruskall: " + be.getFitness(particle));
		ParticleConverter pc = new ParticleConverter(bstp);
		System.out.println(pc.getSteinerTree(particle).getBottleneck());

	}

}
