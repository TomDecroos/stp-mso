package old.test.pso;


import java.io.IOException;

import old.input.DataReader;
import old.kruskall.Kruskall;
import old.visual.TreeDrawer;

import disjointset.Node;

import basic.Tree;




public class KruskallTester {
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader dr = new DataReader();
		dr.readData("src/benchmarks/estein40.txt");
		Node[] nodes = Kruskall.getNodes(dr.getArraysOfPoints()[1]);
		Kruskall krus = new Kruskall(nodes);
		krus.execute();
		Tree tree = krus.getMST();
		TreeDrawer td = new TreeDrawer(tree,8);
		td.draw(500, 500);
		System.out.print(tree.toString());
	}

}
