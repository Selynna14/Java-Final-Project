
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
	
	final int screenColums = 22;
	final int screenRows = 12;
	final int screenWidth = tileSize * screenColums;
	final int screenHeight = tileSize * screenRows;
	
	final int FPS = 60;

	Thread h;
	Player p = new Player(this);
	
	GamePanel()
	{
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
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
				remainingTime = remainingTime/10000000;
				
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
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		/*
		int t = 0;
		int d = 0;
		
		try
		{
			while (d < screenHeight)
			{
				while (t < screenWidth)
				{
					BufferedImage bufferedImage = ImageIO.read(new File("basic background.png"));
					Image image = bufferedImage.getScaledInstance(tileSize, tileSize, Image.SCALE_DEFAULT);
					g.drawImage(image, t, d, null);
					t += tileSize;
				}
				
				t = 0;
				d += tileSize;
			}
		}
		catch (Exception e)
		{
		}
		*/
		p.draw(g2D);
		
		g.dispose();
    }
  
}
