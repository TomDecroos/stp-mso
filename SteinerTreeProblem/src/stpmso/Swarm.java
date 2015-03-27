package stpmso;

import disjointset.Node;

/**
 * Represents a swarm for a steiner point.
 * @author Tom
 *
 */
public class Swarm {
	
	private Particle[] particles;
	private Node node;
	
	public Swarm(Particle[] swarm, Node node) {
		this.particles = swarm;
		this.node = node;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the swarm
	 */
	public Particle[] getParticles() {
		return particles;
	}
	
	public void removeParticles() {
		particles = new Particle[]{};
	}
	
}
