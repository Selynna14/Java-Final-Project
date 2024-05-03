
import java.io.*;//needed to generate random values
import java.util.*;//neede for scanner
import java.awt.*;//needed for graphic 
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements Runnable
{
	final int originalTileSize = 16;
	final int scale = 4;
	
	final int tileSize = scale * originalTileSize;
	
	public final int screenColums = 22;
	public final int screenRows = 12;
	public final int screenWidth = tileSize * screenColums;
	public final int screenHeight = tileSize * screenRows;
	
	final int FPS = 60;

	Thread h;
	
	Tile[] tile;

	KeyPressProg key = new KeyPressProg();
	MapBackground mp = new MapBackground();
	Player p = new Player(this, key);
	
	
	GamePanel()
	{
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		Color b = new Color(101,67,33);
		this.setBackground(b);
		this.addKeyListener(key);
		this.setFocusable(true);
		this.requestFocusInWindow();

		
		tile = new Tile[10];
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new File("Floorboard.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(new File("Wall.png"));
			tile[1].collison = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(new File("Window.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(new File("stairs.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(new File("chest.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(new File("cabinet.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(new File("door.png"));
			
		}
		catch(Exception e)
		{
		}
	}

    public void startGameThread()
    {
		h = new Thread(this);
		h.start();
	}
    
	public void run()
	{
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (h != null)
		{
			update();
			repaint();
			
			try
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0)
					remainingTime = 0;
				
				h.sleep((long) remainingTime);
				 nextDrawTime += drawInterval;
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}

	public void update()
	{
		p.update();
	}
	
	public void drawGroundFloor(Graphics2D g)
	{
		getTileImage();
		
		int col = 0;
		int row = 0;
		int t = 0;
		int f = 0;
		try
		{
			while(row == 0 || row == screenRows)
			{
				g.drawImage(tile[1].image, t, f, tileSize, tileSize, null);

				//System.out.println("do smth" + col);
				col++;
				t += tileSize;
				
				if(col == screenColums)
				{
					col = 0;
					t = 0;
					row += screenRows;
					f += (screenHeight-tileSize);//?
					//System.out.println("do" + col);
				}
			}
			
			col = 0;
			row = 0;
			t = 0;
			f = 0;
			
			for (int i = 0; i < screenColums; i++)
			{
				g.drawImage(tile[1].image, (0), (i*tileSize), tileSize, tileSize, null);
			}
			
			for (int i = 0; i < screenColums; i++)
			{
				g.drawImage(tile[1].image, (screenWidth - tileSize), (i*tileSize), tileSize, tileSize, null);
			}
				
			g.drawImage(tile[2].image, (0), (8*tileSize), tileSize, tileSize, null);
			g.drawImage(tile[3].image, (0), (7*tileSize), tileSize, tileSize, null);
			g.drawImage(tile[4].image, (20*tileSize), (8*tileSize), tileSize, tileSize, null);
			g.drawImage(tile[5].image, (5*tileSize), (5*tileSize), tileSize, tileSize, null);
			g.drawImage(tile[6].image, (6*tileSize), (6*tileSize), tileSize, tileSize, null);
				

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
		
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		drawGroundFloor(g2D);
		
		p.draw(g2D);
		
		g.dispose();
    }
  
}
