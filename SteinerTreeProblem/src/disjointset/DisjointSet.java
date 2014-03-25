package disjointset;



public class DisjointSet {
	

	/**
	 * Altered find method to decrease the amortized cost.
	 * http://en.wikipedia.org/wiki/Disjoint-set_data_structure
	 */
	public static Node find(Node x) {
		if (x.parent != x)
			x.parent = find(x.parent);
		return x.parent;
	}
	
	/**
	 * x and y are not already in same set. Merge them.
	 * http://en.wikipedia.org/wiki/Disjoint-set_data_structure
	 */
	public static void union(Node x, Node y) {
		Node xRoot = find(x);
		Node yRoot = find(y);
		
		if (xRoot.rank < yRoot.rank) {
			xRoot.parent = yRoot;
		} else if (xRoot.rank > yRoot.rank) {
			yRoot.parent = xRoot;
		} else {
			yRoot.parent = xRoot;
			xRoot.rank++;
		}
	}
	
	public static boolean sameSet(Node x, Node y) {
		return find(x) == find(y);
	}
	
	public static void reset(Node[] nodes) {
		for(Node node : nodes) {
			node.parent = node;
			node.rank = 0;
		}
	}

}
