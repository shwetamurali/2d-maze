import java.awt.Rectangle;
import java.util.ArrayList;
public class Monster
{
	private int col;
	private int row;
	private int width;
	private int height;
	private int shift;

	public Monster(int col, int row, int width, int height, int shift) {
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
	public Rectangle getRectangle(int col,int row) {
				Rectangle monst = new Rectangle( col, row,width, height); //cant add shift, only allows four parameters
				return monst;
		}

		public Rectangle getRectangle() {
				Rectangle monst = new Rectangle(row, col, width, height); //cant add shift, only allows four parameters
				return monst;
	}
	public void move(ArrayList<Wall> arr) {
	int direc = (int)(Math.random()*4)-1;
		//try to move closer to the hero - by row first and then by column (or vice versa)

		boolean canMove = true;
		if(direc==-1) {//down
			for(Wall wall:arr)
			{
				if(getRectangle(col,row+height).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				row+=height;
		}
		if(direc==0)  {//left
			for(Wall wall:arr)
			{
				if(getRectangle(col-width,row).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				col-=width;
		}
		if(direc==1) {//up
			for(Wall wall:arr)
			{
				if(getRectangle(col,row-height).intersects(wall.getRectangle()))
					canMove=false;
			}
			if(canMove)
				row-=height;
		}
		if(direc==2)  {//right
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