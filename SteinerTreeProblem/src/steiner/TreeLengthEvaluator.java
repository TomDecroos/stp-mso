package steiner;

import java.util.ArrayList;
import java.util.Arrays;

import kruskall.KruskallMethods;
import pso.FitnessEvaluator;
import pso.Particle;
import disjointset.Node;

public class TreeLengthEvaluator extends KruskallMethods implements FitnessEvaluator {
	
	private Node[] nodes;
	private Edge[] edges;
	private double maxSqLength;
	private int k;
	
	public TreeLengthEvaluator(STP problem) {
		this.nodes = getNodes(problem.getPoints());
		this.edges = getEdgesOfMST(nodes);
		this.maxSqLength = edges[edges.length-1].getSquaredLength();
		this.k = problem.getK();
	}

	@Override
	public double getFitness(Particle particle) {
		Node[] steinerNodes = getSteinerNodes(particle);
		Edge[] possibleEdges =  merge(getSteinerEdges(steinerNodes),this.edges);
		Arrays.sort(possibleEdges);
		
		int nbOfEdgesToAdd = nodes.length + k - 1;
		double length = 0;
		boolean validTree = false;
		for(Edge edge : possibleEdges) {
			if(noLoops(edge)) {
				union(edge.getA(),edge.getB());
				length += edge.getLength();
				if(--nbOfEdgesToAdd <= 0) {
					validTree = true;
					break;
				}
			}
		}
		reset(nodes);
		//if(!validTree) System.out.println("invalid tree");
		return (validTree)? length : Double.MAX_VALUE;
	}
	
	private Edge[] getSteinerEdges(Node[] steinerNodes) {
		ArrayList<Edge> steinerEdges = new ArrayList<Edge>();
		for(int i=0;i<k;i++) {
			for(int j=i+1;j<k;j++){
				Edge edge = new Edge(steinerNodes[i],steinerNodes[j]);
				if(edge.getSquaredLength() < maxSqLength) steinerEdges.add(edge);
			}
			for(int j=0;j<nodes.length;j++) {
				Edge edge = new Edge(steinerNodes[i],nodes[j]);
				if(edge.getSquaredLength() < maxSqLength) steinerEdges.add(edge);
			}
		}
		
		return steinerEdges.toArray(new Edge[0]);
	}

	/**
	 * Convert the particle to an array of k steiner nodes.
	 */
	private Node[] getSteinerNodes(Particle particle) {
		Node[] steinerNodes = new Node[k];
		int j = 0;
		for (int i=0; i<k;i++) {
			steinerNodes[i] = new Node(particle.position[j++],particle.position[j++]);
		}
		return steinerNodes;
	}
	
}
