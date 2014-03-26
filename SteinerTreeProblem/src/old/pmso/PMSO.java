package old.pmso;

import old.steiner.STP;
import visual.SteinerTree;
import basic.Tree;
import disjointset.Node;

/**
 * Thought experiment: try doing pso, but with multiple swarms. 1 swarm per steiner point
 * @author Tom
 *
 */
public class PMSO {
	STP stp;
	int n;
	int k;
	Particle[][] swarms;
	Node[] steinerNodes;
	MST basic;
	MST best;
	double w1;
	public double w2;
	
	public PMSO(STP problem,int swarmsize,double w1,double w2) {
		this.stp = problem;
		this.n = problem.getPoints().length;
		this.k = problem.getK();
		this.w1 = w1;
		this.w2 = w2;
		this.basic = Kruskall.getMST(Kruskall.getNodes(problem.getPoints()));
		setUpSwarms(swarmsize);
		setUpSteinerNodes();
	}
	
	public Tree getTree() {
		return new Tree(best.getNodes(),best.getEdges());
	}
	
	public SteinerTree getSteinerTree() {
		return new SteinerTree(getTree(),steinerNodes);
	}
	
	public double getBottleneck() {
		return best.getBottleneck();
	}

	/**
	 * Generates the swarms with random particles.
	 * There are k swarms that each have the given swarmsize.
	 * A swarm is nothing more than an array of particles.
	 * @param swarmsize
	 */
	private void setUpSwarms(int swarmsize) {
		swarms = new Particle[k][swarmsize];
		for(int i=0;i<this.k;i++) {
			for(int j=0;j<swarmsize;j++) {
				swarms[i][j] = new Particle(Math.random(),Math.random());
			}
		}
	}
	
	private void setUpSteinerNodes() {
		steinerNodes = new Node[k];
		for(int i=0;i<k;i++) {
			steinerNodes[i] = basic.getNodes()[0].clone();
		}
		best = Kruskall.getImprovedMST(basic, steinerNodes);
		for(int i=0;i<swarms.length;i++) {
			Node[] otherNodes = getOtherSteinerNodes(i);
			MST temp = Kruskall.getImprovedMST(basic, otherNodes);
			for(int j=0;j<swarms[i].length;j++) {
				Particle p = swarms[i][j];
				Node node = new Node(p.px,p.py);
				if(Kruskall.canImprove(temp, node)) {
					MST mst = Kruskall.getImprovedMST(temp, node);
					if(mst.getSqBottleneck() <= best.getSqBottleneck()) {
						best = mst;
						steinerNodes[i] = node;
					}
				}
			}
		}
	}
	
	/**
	 * Return an array of all the nodes,
	 * excluding the one with the given index.
	 * @param index
	 * @return
	 */
	private Node[] getOtherSteinerNodes(int index) {
		Node[] otherNodes = new Node[k-1];
		for(int i=0;i<index;i++) {
			otherNodes[i] = steinerNodes[i];
		}
		for(int i=index;i<k-1;i++) {
			otherNodes[i] = steinerNodes[i+1];
		}
		return otherNodes;
	}

	/**
	 * Evolves the swarms 1 cycle.
	 */
	public void evolve() {
		for(int i=0;i<k;i++) {
			Node[] otherNodes = getOtherSteinerNodes(i);
			MST temp = Kruskall.getImprovedMST(basic, otherNodes);
			for(int j=0;j<swarms[i].length;j++) {
				Particle p = swarms[i][j];
				// fly
				fly(p,steinerNodes[i]);
				// check if better.
				Node node = new Node(p.px,p.py);
				if(Kruskall.canImprove(temp, node)) {
					MST mst = Kruskall.getImprovedMST(temp, node);
					if(mst.getSqBottleneck() <= best.getSqBottleneck()) {
						best = mst;
						steinerNodes[i] = node;
					}
				}
			}
		}
	}
	
	private void fly(Particle p, Node node) {
		p.vx = w1*p.vx + w2*Math.random()*(node.getX()-p.px);
		p.vy = w1*p.vy + w2*Math.random()*(node.getY()-p.py);
		p.px += p.vx;
		p.py += p.vy;
	}

	public void evolve(int cycles) {
		for (int i = 0; i < cycles; i++) {
			evolve();
		}
	}
	
	public static class Particle {
		public Particle(double x,double y) {
			this.px = x;
			this.py = y;
		}
		double px;
		double py;
		double vx;
		double vy;
	}

}
