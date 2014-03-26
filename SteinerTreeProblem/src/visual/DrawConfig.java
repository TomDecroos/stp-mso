package visual;

public class DrawConfig {

	public int x;
	public int y;
	public int width;
	public int length;
	public boolean steinerEdges;
	public boolean bottleneck;
	public int graphpointsize;
	
	public static DrawConfig getBottleneckConfig() {
		return new DrawConfig();
	}
	
	public static DrawConfig getTreeLengthConfig() {
		return new DrawConfig(true,false);
	}
	
	public DrawConfig() {
		this(650,0,650,650,true,true,4);
	}
	public DrawConfig(boolean steinerEdges, boolean bottleneck) {
		this();
		this.steinerEdges = steinerEdges;
		this.bottleneck = bottleneck;
	}

	public DrawConfig(int x, int y, int width, int length,
			boolean steinerEdges, boolean bottleneck, int graphpointsize) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.steinerEdges = steinerEdges;
		this.bottleneck = bottleneck;
		this.graphpointsize = graphpointsize;
	}
	
}
