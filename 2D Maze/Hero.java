import java.awt.Rectangle;
import java.util.ArrayList;
public class Hero
{
	private int col;
	private int row;
	private int width;
	private int height;
	private int shift;

	public Hero(int col, int row, int width, int height, int shift) {
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
	public Rectangle getRectangle(int col,int row) {
			Rectangle her = new Rectangle( col, row,width, height); //cant add shift, only allows four parameters
			return her;
	}

	public Rectangle getRectangle() {
			Rectangle her = new Rectangle(row, col, width, height); //cant add shift, only allows four parameters
			return her;
	}
	public void move(int dir, ArrayList<Wall> arr) {
		boolean canMove = true;
		if(dir==-1) {//down
			for(Wall wall:arr)
			{
				if(getRectangle(col,row+height).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				row+=height;
		}
		if(dir==0)  {//left
			for(Wall wall:arr)
			{
				if(getRectangle(col-width,row).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				col-=width;
		}
		if(dir==1) {//up
			for(Wall wall:arr)
			{
				if(getRectangle(col,row-height).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				row-=height;
		}
		if(dir==2)  {//right
			for(Wall wall:arr)
			{
				if(getRectangle(col+width,row).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				col+=width;
		}
	}

}