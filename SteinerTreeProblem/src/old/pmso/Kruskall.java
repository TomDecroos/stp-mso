package old.pmso;

import java.util.ArrayList;
import java.util.Arrays;

import old.steiner.Edge;

import basic.Point;
import basic.Tree;
import disjointset.DisjointSet;
import disjointset.Node;

public class Kruskall {
	
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
	
	public static MST getMST(Node[] nodes) {
		Edge[] edges = getAllEdges(nodes);
		Edge[] treeEdges = new Edge[nodes.length - 1];
		int nbOfTreeEdges = 0;
		
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				treeEdges[nbOfTreeEdges++] = edge;
				if(nbOfTreeEdges >= nodes.length - 1) {
					break;
				}
			}
		}
		DisjointSet.reset(nodes);
		return new MST(nodes,treeEdges);
	}

	private static void union(Edge edge) {
		DisjointSet.union(edge.getA(), edge.getB());
		
	}

	private static boolean noloops(Edge edge) {
		return !DisjointSet.sameSet(edge.getA(), edge.getB());
	}
	
	public static boolean canImprove(MST mst, Node[] nodes) {
		Edge[] edges =  merge(getExtraShorterEdges(mst,nodes),mst.getEdges());
		Arrays.sort(edges);
		
		int cnt = 0;
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				cnt++;
			}
		}
		DisjointSet.reset(mst.getNodes());
		DisjointSet.reset(nodes);
		return cnt == mst.getNodes().length + nodes.length - 1;
	}
	
	public static boolean canImprove(MST mst, Node node) {
		return canImprove(mst,new Node[] {node});
	}
	
	public static MST getImprovedMST(MST mst,Node[] nodes) {
		Edge[] extra = getExtraShorterEdges(mst,nodes);
		Edge[] edges =  merge(extra,mst.getEdges());
		Arrays.sort(edges);
		
		int n = mst.getNodes().length + nodes.length; // nb of nodes
		Edge[] treeEdges = new Edge[n-1];
		int i = 0;
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				treeEdges[i++] = edge;
				if(i == n-1) {
					break;
				}
			}
		}
		Node[] newNodes = merge(mst.getNodes(),nodes);
		DisjointSet.reset(newNodes);
		return new MST(newNodes,treeEdges);
	}
	
	public static MST getImprovedMST(MST mst, Node node) {
		return getImprovedMST(mst,new Node[] {node});
	}

	private static Edge[] getExtraShorterEdges(MST mst,Node[] nodes) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		double max = mst.getSqBottleneck();
		for(int i=0;i<nodes.length;i++) {
			for(int j=i+1;j<nodes.length;j++){
				Edge edge = new Edge(nodes[i],nodes[j]);
				if(edge.getSquaredLength() < max) edges.add(edge);
			}
			for(int j=0;j<mst.getNodes().length;j++) {
				Edge edge = new Edge(nodes[i],mst.getNodes()[j]);
				if(edge.getSquaredLength() < max) edges.add(edge);
			}
		}
		return edges.toArray(new Edge[0]);
	}
	
	public static <T> T[] merge(T[] a, T[] b) {
		T[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	
	/*************************************************
	 * STUFF FOR CALCULATING THE STEINER TREE
	 * INSTEAD OF THE K-BOTTLENECK
	 *************************************************/
	
	/**
	 * Method for extending an MST without checking for a better bottleneck.
	 * @param mst
	 * @param nodes
	 * @return
	 */
	
	public static MST getExtendedMST(MST mst,Node[] nodes) {
		Edge[] extra = getExtraMSTEdges(mst,nodes);
		Edge[] edges =  merge(extra,mst.getEdges());
		Arrays.sort(edges);
		
		int n = mst.getNodes().length + nodes.length; // nb of nodes
		Edge[] treeEdges = new Edge[n-1];
		int i = 0;
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				treeEdges[i++] = edge;
				if(i == n-1) {
					break;
				}
			}
		}
		Node[] newNodes = merge(mst.getNodes(),nodes);
		DisjointSet.reset(newNodes);
		return new MST(newNodes,treeEdges);
	}

	private static Edge[] getExtraMSTEdges(MST mst, Node[] nodes) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i=0;i<nodes.length;i++) {
			SteinerEdgesList lst = new SteinerEdgesList(nodes[i]);
			for(int j=i+1;j<nodes.length;j++){
				lst.add(nodes[j]);
			}
			for(int j=0;j<mst.getNodes().length;j++) {
				lst.add(mst.getNodes()[j]);
			}
			for(Edge edge : lst.getEdges()) {
				edges.add(edge);
			}
		}
		return edges.toArray(new Edge[0]);
	}
	
	/**
	 * Class for storing the maximum 5 edges to use for constructing a new MST
	 * @author Tom
	 *
	 */
	private static class SteinerEdgesList {
		ArrayList<Edge> edges;
		Node node;
		public SteinerEdgesList(Node node) {
			this.edges = new ArrayList<Edge>();
			this.node = node;
		}

		public ArrayList<Edge> getEdges() {
			return edges;
		}

		public void add(Node node) {
			Edge edge = new Edge(this.node,node);
			if(edges.size() < 5) {
				edges.add(edge);
			} else {
				Edge bottleneck = getBottleneck();
				if(edge.getSquaredLength() < bottleneck.getSquaredLength()) {
					edges.remove(bottleneck);
					edges.add(edge);
				}
			}
		}

		private Edge getBottleneck() {
			Edge bottleneck = edges.get(0);
			for(Edge edge : edges) {
				if( edge.getSquaredLength() > bottleneck.getSquaredLength()) bottleneck = edge;
			}
			return bottleneck;
		}
		
	}
}
