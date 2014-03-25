package old.visual;

public class DrawConfig {
	public int x;
	public int y;
	public int width;
	public int length;
	public int pointsize;
	
	public DrawConfig(int width, int length, int pointsize ) {
		this(0,0,width,length,pointsize);
	}
	
	public DrawConfig(int x, int y, int width, int length, int pointsize ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.pointsize = pointsize;
	}
	
}
