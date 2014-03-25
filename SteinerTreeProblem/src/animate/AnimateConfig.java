package animate;

import java.awt.Color;

public class AnimateConfig {
	public int x;
	public int y;
	public int width;
	public int length;
	public int graphpointsize;
	public int particlepointsize;
	public int sleep;
	public boolean steinerEdges;
	public boolean bottleneck;
	public boolean particles;
	public Color[] colors = new Color[] {
		Color.BLUE,
		Color.CYAN,
		Color.DARK_GRAY,
		Color.LIGHT_GRAY,
		Color.PINK,
		Color.MAGENTA,
		Color.orange,
		Color.YELLOW};
	
	public AnimateConfig() {
		this(0,0,650,650);
	}

	public AnimateConfig(int x, int y, int width, int height) {
		this(x,y,width,height,4,2);
	}

	public AnimateConfig(int x, int y, int width, int height, int graphpointsize, int particlepointsize) {
		this(x,y,width,height,graphpointsize,particlepointsize,0,true,true,true);
	}

	public AnimateConfig(int x, int y, int width, int length,
			int graphpointsize, int particlepointsize, int sleep,
			boolean steinerEdges, boolean bottleneck, boolean particles) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.graphpointsize = graphpointsize;
		this.particlepointsize = particlepointsize;
		this.sleep = sleep;
		this.steinerEdges = steinerEdges;
		this.bottleneck = bottleneck;
		this.particles = particles;
	}
	
	
}
