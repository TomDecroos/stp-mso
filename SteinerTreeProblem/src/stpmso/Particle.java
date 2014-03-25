/**
 * Steiner Tree Problem - Multi Swarm Optimization
 */
package stpmso;

/**
 * Class representing a particle in a swarm.
 * A particle has a location and a velocity.
 * @author Tom
 *
 */
public class Particle {
	double px;
	double py;
	double vx;
	double vy;
	
	public Particle(double x,double y) {
		this.px = x;
		this.py = y;
		this.vx = 0;
		this.vy = 0;
	}
	
	public double getX() {
		return px;
	}
	
	public double getY() {
		return py;
	}
	
	/**
	 * Updates the velocity of the particle.
	 * @param w The relative weight of the old velocity in the new velocity.
	 * @param x
	 * @param y
	 */
	public void updateVelocity(double w, double x,double y) {
		this.vx = w*vx + x;
		this.vy = w*vy + y;
	}
	
	/**
	 * Moves the position of the particle 1 unit of the velocity.
	 */
	public void fly() {
		this.px += this.vx;
		this.py += this.vy;
	}

}
