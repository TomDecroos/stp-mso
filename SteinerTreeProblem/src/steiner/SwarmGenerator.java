package steiner;

import pso.Particle;
import pso.Swarm;
import basic.Point;

public class SwarmGenerator {
	
	double minX;
	double maxX;
	double minY;
	double maxY;
	
	int length;
	public SwarmGenerator(STP bstp) {
		minX = bstp.getPoints()[0].getX();
		maxX = bstp.getPoints()[0].getX();
		minY = bstp.getPoints()[0].getY();
		maxY = bstp.getPoints()[0].getY();
		
		for(Point point : bstp.getPoints()) {
			if(point.getX() < minX) minX = point.getX();
			if(point.getX() > maxX) maxX = point.getX();
			if(point.getY() < minY) minY = point.getY();
			if(point.getY() > maxY) maxY = point.getY();
		}
		
		length = 2*bstp.getK();
	}
	
	public Swarm getSwarm(int n) {
		Particle[] particles = new Particle[n];
		for(int i=0;i<n;i++) {
			double[] position = new double[length];
			for(int j=0;j<length;j++) {
				if(j % 2 == 0) {
					position[j] = minX + (maxX-minX) * Math.random();
				} else {
					position[j] = minY + (maxY-minY) * Math.random();
				}
			}
			particles[i] = new Particle(position);
		}
		return new Swarm(particles);
	}
}
