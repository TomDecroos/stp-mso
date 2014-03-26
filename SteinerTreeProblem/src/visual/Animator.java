package visual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import stpmso.Particle;
import stpmso.MultiSwarmOptimizer;
import basic.Line;
import basic.Point;

public class Animator {
	MultiSwarmOptimizer solver;
	int cycles;
	AnimatorConfig cfg;
	
	public Animator(MultiSwarmOptimizer solver, int cycles, AnimatorConfig cfg) {
		this.solver = solver;
		this.cycles = cycles;
		this.cfg = cfg;
	}

	public void play() {
		 JFrame frame=new JFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setBounds(cfg.x,cfg.y,cfg.width,cfg.length);
         DrawPanel draw=new DrawPanel();
         frame.getContentPane().add(draw);
         frame.setVisible(true);
         for(int i=0;i<cycles;i++) {
        	 solver.evolve();
        	 draw.counter++;
        	 draw.repaint();
        	 try{Thread.sleep(cfg.sleep); } catch(Exception e) {}
         }
	}

	@SuppressWarnings("serial")
	class DrawPanel extends JPanel{
		/**
		 * SETUP
		 */
		private double maxX;
		private double maxY;
		private int counter;
		public DrawPanel() {
			this.maxX = getMaxX(solver.getSteinerTree().getPoints());
			this.maxY = getMaxY(solver.getSteinerTree().getPoints());
			this.counter = 0;
		}
		
		private double getMaxY(Point[] points) {
			double h = points[0].getY();
			for(Point point : points) {
				if(point.getY() > h) h = point.getY();
			}
			return h;
		}
		private double getMaxX(Point[] points) {
			double w = points[0].getX();
			for(Point point : points) {
				if(point.getX() > w) w = point.getX();
			}
			return w;
		}
		
		/**
		 * PAINT
		 */
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
		    g.fillRect(0,0,this.getWidth(),this.getHeight());
		    if(cfg.particles) paintParticles(g);
		    paintTree(g);
			if(cfg.steinerEdges) paintSteinerStuff(g);
			if(cfg.bottleneck) paintBottleNeck(g);
			paintText(g);
		}

		private void paintText(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(50, 10, 500, 50);
		    g.setColor(Color.BLACK);
		    g.drawString("Cycle: " + counter + "/" + cycles, 50, 25);
		    g.drawString("#Steiner Points: " + solver.getSteinerTree().getSteinerPoints().length, 150, 25);
		    g.drawString("Bottleneck: " + solver.getSteinerTree().getBottleneck().getLength() , 50, 50);
		    g.drawString("Total length: " + solver.getSteinerTree().getLength() , 250, 50);
			
		}

		private void paintTree(Graphics g) {
			g.setColor(Color.BLACK);
			paintPoints(g);
			paintLines(g);
		}
		
		private void paintPoints(Graphics g) {
			Point[] points = solver.getSteinerTree().getPoints();
			int psize = cfg.graphpointsize;
			for(Point point : points) {
				paintPoint(g,point,psize);
			}
		}

		private void paintPoint(Graphics g, Point point,int psize) {
			Point p = convertPointToDrawScale(point);
			g.fillOval( (int) p.getX()-psize/2, (int) p.getY()-psize/2, psize, psize);
		}

		private Point convertPointToDrawScale(Point point) {
			double x = (0.1 + 0.8 * (point.getX()/maxX)) * this.getWidth();
			double y = (0.1 + 0.8 * (point.getY()/maxY)) * this.getHeight();
			return new Point(x,y);
		}

		private void paintLines(Graphics g) {
			Line[] lines = solver.getSteinerTree().getLines();
			for(Line line : lines) {
				paintLine(g,line);
			}
		}

		private void paintLine(Graphics g,Line line) {
			Point a = convertPointToDrawScale(line.getA());
			Point b = convertPointToDrawScale(line.getB());
			g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());
		}

		private void paintSteinerStuff(Graphics g) {
			SteinerTree steinerTree = solver.getSteinerTree();
			g.setColor(Color.red);
			for(Point p : steinerTree.getSteinerPoints()) {
				paintPoint(g,p,cfg.graphpointsize);
			}
			
			for(Line l : steinerTree.getLines()) {
				if(isSteinerLine(steinerTree,l)) {
					paintLine(g,l);
				}
			}
			
		}

		private boolean isSteinerLine(SteinerTree steinerTree, Line line) {
			for(Point p : steinerTree.getSteinerPoints()) {
				if( p == line.getA() || p == line.getB()) return true;
			}
			return false;
		}

		private void paintBottleNeck(Graphics g) {
			
			g.setColor(Color.GREEN);
			paintLine(g,solver.getSteinerTree().getBottleneck());
			
		}
		
		private void paintParticles(Graphics g) {
			Particle[][] swarms = solver.getParticles();
			int psize = cfg.particlepointsize;
			int i=0;
			for(Particle[] swarm : swarms) {
				g.setColor(cfg.colors[i++ % cfg.colors.length]);
				for(Particle p : swarm) {
				paintPoint(g, new Point(p.getX(),p.getY()),psize);;
				}
			}
		}
	}

}
