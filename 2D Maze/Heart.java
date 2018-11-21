import java.awt.Rectangle;
public class Heart
{
	private int col;
	private int row;
	private int width;
	private int height;
	private int shift;

	public Heart(int col, int row, int width, int height, int shift) {
		this.col=col*width+shift;
		this.row=row*height+shift;
		this.width=width;
		this.height=height;
		this.shift=shift;
	}

	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setCol(int coll) {
		col=coll;
	}
	public void setRow(int rowww) {
		row=rowww;
	}
	public Rectangle getRectangle() {
		Rectangle hear = new Rectangle(row, col, width, height); //cant add shift, only allows four parameters
		return hear;
	}

}