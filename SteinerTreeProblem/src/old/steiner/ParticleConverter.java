package old.steiner;

import java.util.Arrays;

import old.kruskall.Kruskall;
import old.pso.Particle;
import visual.SteinerTree;

import basic.Point;
import disjointset.Node;

public class ParticleConverter {
	
	private STP bstp;
	
	public ParticleConverter(STP bstp) {
		this.bstp = bstp;
	}
	
	public Point[] getSteinerPoints( Particle particle) {
		Point[] steinerPoints = new Point[bstp.getK()];
		int j = 0;
		for (int i=0; i<bstp.getK();i++) {
			steinerPoints[i] = new Point(particle.position[j++],particle.position[j++]);
		}
		return steinerPoints;
	}
	
	public SteinerTree getSteinerTree(Particle particle) {
		Node[] steinerNodes = Kruskall.getNodes(getSteinerPoints(particle));
		Node[] nodes = Kruskall.getNodes(bstp.getPoints());
		Kruskall kruskall = new Kruskall( (Node[]) merge(nodes,steinerNodes));
		kruskall.execute();
		return new SteinerTree(kruskall.getMST(),steinerNodes);
		
	}
	
	private Object[] merge(Object[] a, Object[] b) {
		Object[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
}
