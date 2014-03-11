package test.pmso;

import input.STPConfig;
import pmso.Kruskall;
import pmso.MST;
import disjointset.Node;

public class TestKruskall {
	
	static STPConfig stpcfg = new STPConfig(
			50,		// Nb of points,
			0,		// index of data
			20		// Nb of Steiner points
			);
	public static Node[] nodes = new Node[] {
				new Node(0.5,0.5),
				new Node(0.5,0.3)};
	public static Node[] extra = new Node[] {
			new Node(0.5,0.5)};
	
	public static void main(String[] arg) {
		MST mst = Kruskall.getMST(nodes);
		System.out.println(Kruskall.canImprove(mst, nodes[0]));
	}
}
