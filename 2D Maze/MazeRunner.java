import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import javax.sound.sampled.*;

public class MazeRunner extends JPanel implements KeyListener, Runnable
{
	JFrame frame;
	int x = 100;
	Thread thread;
	int movDig = -5;
	boolean gameOn = true;
	ArrayList <Wall> list = new ArrayList<Wall>();
	BufferedImage heroImg;
	Hero heroo;
	BufferedImage finishImg;
	Hero finish;
	BufferedImage monsterImg;
	Monster monst;
	BufferedImage heartImg;
	BufferedImage hearttImg;
	BufferedImage heartttImg;
	BufferedImage monstImg;
	BufferedImage monstttImg;
	Heart hea;
	Heart hea2;
	Heart hea3;
	Monster monst2;
	Monster monst3;
	Clip clip;
	int lives = 3;
	boolean right=false,left=false,up=false,down=false;
	public MazeRunner () {
		createMaze();
		frame = new JFrame("Maze Runner");
		frame.add(this);
		frame.setSize(1350,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("game.wav"));
				clip = AudioSystem.getClip();
				clip.open(audioStream);
			}
		catch(LineUnavailableException lue){}
		catch(UnsupportedAudioFileException uafe){}
		catch(IOException ioe){System.out.println("Hello?");}
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		frame.setVisible(true);
		frame.addKeyListener(this);
		thread = new Thread(this);
		heroo = new Hero(1,0,30,30,100);
		finish = new Hero(25,14,50,50,0);
		monst = new Monster(10,10,30,30,100);
		monst2 = new Monster(30,4,30,30,100);
		monst3 = new Monster(4,14,30,30,100);
		hea = new Heart(15,0,40,40,40);
		hea2= new Heart(20,0,40,40,40);
		hea3= new Heart(25,0,40,40,40);
		thread.start(); //starts run method

		try
		{
			heroImg=ImageIO.read(new File("hero.png"));
			monsterImg=ImageIO.read(new File("monster.png"));
			monstImg=ImageIO.read(new File("monstertwo.png"));
			heartImg = ImageIO.read(new File("heart.png"));
			hearttImg = ImageIO.read(new File("heart1.png"));
			heartttImg = ImageIO.read(new File("heart2.png"));
			finishImg = ImageIO.read(new File("finish.png"));
		    monstttImg=ImageIO.read(new File("monsterthree.png"));

		}catch(IOException e)
		{
		}
	}

		public void createMaze()
		{
			int r = 0;
			File name = new File("Maze1.txt");
			try
			{
				BufferedReader input = new BufferedReader(new FileReader(name));
				String text;

				while( (text=input.readLine())!=null)
				{
					for(int i=0;i<text.length();i++) {
						if((int)(text.charAt(i))==42)
							list.add(new Wall(r, i, 30,30,100) );
					}
					r++;
				}
			}
			catch (IOException io)
			{
				System.err.println("File does not exist");
			}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,1350,1000);
		//g2.setColor(new Color(100,121,0));
		//g2.setFont(new Font("Elephant",Font.PLAIN,70));
		//g2.drawString("I am here!",100,400);
		//g2.setColor(Color.CYAN);
		//g2.fillOval(x,100,150,120);
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10));
		//g2.drawOval(300,100,150,120);
		for(Wall w:list) {
			g2.fill(w.getRectangle());
		}
		g2.drawImage(heroImg,heroo.getCol(),heroo.getRow(),heroo.getWidth(),heroo.getHeight(),this);
		g2.drawImage(monsterImg,monst.getCol(),monst.getRow(),monst.getWidth(),monst.getHeight(),this);
		g2.drawImage(monstImg,monst2.getCol(),monst2.getRow(),monst2.getWidth(),monst2.getHeight(),this);
		g2.drawImage(monstttImg,monst3.getCol(),monst3.getRow(),monst3.getWidth(),monst3.getHeight(),this);
		g2.drawImage(heartImg,hea.getCol(),hea.getRow(),hea.getWidth(),hea.getHeight(),this);
		g2.drawImage(hearttImg,hea2.getCol(),hea2.getRow(),hea2.getWidth(),hea2.getHeight(),this);
		g2.drawImage(heartttImg,hea3.getCol(),hea3.getRow(),hea3.getWidth(),hea3.getHeight(),this);
		g2.drawImage(finishImg,finish.getCol(),finish.getRow(),finish.getWidth(),finish.getHeight(),this);
		//g2.setColor(Color.CYAN);
		//g2.fillRect(1240,740,50,50);
	}
	public void run() {
		while(true)
		{
			monst.move(list);
			monst2.move(list);
			monst3.move(list);
			if(right)
				heroo.move(2,list);
			if(left)
				heroo.move(0,list);
			if(up)
				heroo.move(1,list);
			if(down)
				heroo.move(-1,list);
			if( finish.getRectangle().intersects(heroo.getRectangle())) {
					System.out.println("Congrats!");
					System.exit(0);
				}
			if( monst.getRectangle().intersects(heroo.getRectangle()) || monst2.getRectangle().intersects(heroo.getRectangle()) || monst3.getRectangle().intersects(heroo.getRectangle())) {
				lives--;
				if(lives==2) {
					heroo.setCol(130);
					heroo.setRow(100);
					hea3.setCol(2000);
					hea3.setRow(2000);
				}
				if(lives==1) {
					heroo.setCol(130);
					heroo.setRow(100);
					hea2.setCol(2000);
					hea2.setRow(2000);
				}
				if(lives==0) {
					System.exit(0);
					System.out.println("Oops! You ran out of lives!");
				}
			}
			try
			{
				thread.sleep(100);//stops every 10 milliseconds
			}catch(InterruptedException e)
			{
			}
			repaint();
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==39) //right
			right=false;
		if(e.getKeyCode()==37)//left
			left=false;
		if(e.getKeyCode()==38)//up
			up=false;
		if(e.getKeyCode()==40)//down
			down=false;


	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==39) //right
			right=true;
		if(e.getKeyCode()==37)//left
			left=true;
		if(e.getKeyCode()==38)//up
			up=true;
		if(e.getKeyCode()==40)//down
			down=true;


	}
	public void keyTyped(KeyEvent e) {
		//never going to be used
	}


	public static void main(String args[])
	{
		MazeRunner app=new MazeRunner();
	}





}