package kruskall;

import java.util.Arrays;

import steiner.Edge;
import basic.Point;
import basic.Tree;
import disjointset.DisjointSet;
import disjointset.Node;

public class KruskallMethods extends DisjointSet {
	
	public static Node[] getNodes(Point[] points) {
		int n = points.length;
		Node[] nodes = new Node[n];
		for(int i=0;i<n;i++) {
			nodes[i] = new Node(points[i]);
		}
		return nodes;
	}
	
	public static Edge[] getAllEdges(Node[] nodes) {
		int n = nodes.length;
		// a complete graph has n nodes and n*(n-1)/2 edges
		int nbOfAllEdges = n * (n-1) / 2;
		Edge[] allEdges = new Edge[nbOfAllEdges];
		
		int nbOfEdges = 0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				allEdges[nbOfEdges++] = new Edge(nodes[i],nodes[j]);
			}
		}
		Arrays.sort(allEdges);
		return allEdges;
	}
	
	public static <T> T[] merge(T[] a, T[] b) {
		T[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	
	public static Tree getMST(Node[] nodes) {
		Edge[] edges = getAllEdges(nodes);
		Edge[] treeEdges = new Edge[nodes.length - 1];
		int nbOfTreeEdges = 0;
		
		for(Edge edge : edges) {
			if(noLoops(edge)) {
				union(edge.getA(), edge.getB());
				treeEdges[nbOfTreeEdges++] = edge;
				if(nbOfTreeEdges >= nodes.length - 1) {
					break;
				}
			}
		}
		return new Tree(nodes,treeEdges);
	}
	
	public static Edge[] getEdgesOfMST(Node[] nodes) {
		Edge[] edges = getAllEdges(nodes);
		Edge[] treeEdges = new Edge[nodes.length - 1];
		int nbOfTreeEdges = 0;
		
		for(Edge edge : edges) {
			if(noLoops(edge)) {
				union(edge.getA(), edge.getB());
				treeEdges[nbOfTreeEdges++] = edge;
				if(nbOfTreeEdges >= nodes.length - 1) {
					break;
				}
			}
		}
		reset(nodes);
		return treeEdges;
	}

	public static boolean noLoops(Edge edge) {
		return find(edge.getA()) != find(edge.getB());
	}
}
