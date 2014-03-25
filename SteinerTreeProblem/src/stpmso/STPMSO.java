package stpmso;

import java.util.ArrayList;
import java.util.Comparator;

import disjointset.Node;

import animate.SteinerTree;
import basic.Point;

/**
 * This class solves a Steiner Tree Problem using Multi Swarm Optimisation.
 * @author Tom
 *
 */
public class STPMSO {
	/**
	 * The basic minimal spanning tree of the given points,
	 * without any steiner points.
	 */
	private MinimalSpanningTree basic;
	/**
	 * The current best solution.
	 */
	private MinimalSpanningTree best;
	/**
	 * The maximum amount of steiner points to add.
	 */
	private int max;
	
	/**
	 * The relative weight of an old particle velocity in a new particle velocity.
	 */
	private double w1;
	
	/**
	 * The relative weight of the distance between a particle and the current best position
	 * for that particle in the new particle velocity.
	 */
	private double w2;
	
	/**
	 * Decides what type of steiner tree we are searching for.
	 * Ex. smallest bottleneck, smallest total length, smallest average length, ...
	 */
	private Comparator<MinimalSpanningTree> comparator;
	
	/**
	 * The list of swarms
	 */
	private ArrayList<STPSwarm> swarms;
	
	/**
	 * The swarm size of each swarm per steiner point
	 */
	private int swarmsize;
	
	/**
	 * Constructor for executing multi swarm optimization for a limited amount of steiner nodes.
	 * @param points
	 * @param comparator
	 */
	public STPMSO(Point[] points, int max, int swarmsize,
			double w1, double w2, Comparator<MinimalSpanningTree> comparator) {
		setup(points);
		this.max = max;
		this.swarmsize = swarmsize;
		this.w1 = w1;
		this.w2 = w2;
		this.comparator = comparator;

	}
	private void setup(Point[] points) {
		Node[] nodes = Kruskall.convertToNodes(points);
		this.basic = Kruskall.constructMinimalSpannningTree(nodes);
		this.best = basic;
		this.swarms = new ArrayList<STPSwarm>();
	}
	
	public STPMSO(Point[] points, STPMSOConfig cfg,
			Comparator<MinimalSpanningTree> comparator) {
		this(points,cfg.max,cfg.swarmsize,cfg.w1,cfg.w2,comparator);
	}

	/**
	 * Evolves the swarm for the given amount of cycles.
	 * @param amount
	 */
	public void evolve(int amount) {
		for(int i=0;i<amount;i++) {
			evolve();
		}
	}
	
	/**
	 * Evolves the swarms 1 cycle
	 */
	public void evolve() {
		if(swarms.size() <= max) {
			tryNewSwarm();
		}
		fly();
	}
	
	public SteinerTree getSteinerTree() {
		return new SteinerTree(basic.getNodes(), best.getEdges(), getSteinerNodes());
	}

	private void tryNewSwarm() {
		Particle[] particles = generateParticles();
		for(Particle p : particles) {
			Node node = new Node(p.px,p.py);
			MinimalSpanningTree temp = Kruskall.extendMinimalSpanningTree(best,node);
			if(comparator.compare(temp, best) < 0) {
				best = temp;
				swarms.add(new STPSwarm(particles,node));
				break;
			}
		}
	}
	
	private void fly() {
		for(STPSwarm swarm : swarms) {
			Node[] nodes = getOtherSteinerNodes(swarm.getNode());
			MinimalSpanningTree temp = Kruskall.extendMinimalSpanningTree(basic,nodes);
			for(Particle p : swarm.getParticles()) {
				updateParticle(p,swarm.getNode());
				Node node = new Node(p.px,p.py);
				MinimalSpanningTree candidate = Kruskall.extendMinimalSpanningTree(temp,node);
				if(comparator.compare(candidate, best) <= 0) {
					best = candidate;
					swarm.setNode(node);
				}
			}
		}
	}
	

	private void updateParticle(Particle p, Node node) {
		double x = Math.random() * w2 * (node.getX() - p.px);
		double y = Math.random() * w2 * (node.getY() - p.py);
		p.updateVelocity(w1, x, y);
		p.fly();
	}

	private Node[] getOtherSteinerNodes(Node node) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		for(STPSwarm swarm : swarms) {
			if(swarm.getNode() != node) nodes.add(swarm.getNode());
		}
		return nodes.toArray(new Node[0]);
	}
	
	private Node[] getSteinerNodes() {
		Node[] nodes = new Node[swarms.size()];
		int i=0;
		for(STPSwarm swarm : swarms) {
			nodes[i++] = swarm.getNode();
		}
		return nodes;
	}

	private Particle[] generateParticles() {
		Particle[] particles = new Particle[swarmsize];
		for(int i=0;i<swarmsize;i++) {
			particles[i] = new Particle(Math.random(),Math.random());
		}
		return particles;
	}

	public Particle[][] getParticles() {
		Particle[][] particles = new Particle[swarms.size()][];
		int i=0;
		for(STPSwarm swarm: swarms) {
			particles[i++] = swarm.getParticles();
		}
		return particles;
	}
}
