/**
*This file contains all the code for updating and printing the game and screen, and redrwaing each frame of the screen
*
* @author Neysa Thota
* @version 1.0
* date: 4/22/2024
*/ 

import java.awt.*;//needed for graphics and color
import javax.swing.*;//needed for the JPanel 
import java.util.*;//needed for scanner
import javax.imageio.ImageIO;//needed to use images
import java.io.*;//needed to generate random values
import java.awt.event.*;//needed for keylistener and keyevent

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	/**
	 *Instance Variables 
	 */
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
	int mapTileNum[][];
	Player p = new Player(this);
	
	public boolean gameRuns = true;
	static int keyPress;
	static boolean jPressed =  false;
	
	//world vars
	public int worldX, worldY;
	public final int maxWorldCol = 64;
	public final int maxWorldRow = 56;
	public final int worldWidth = tileSize  * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	/**
	 * This method turns all the movement booleans to false to stop the charcter from moving 
	 */ 
	GamePanel()
	{
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		
		Color b = new Color(101,67,33);
		this.setBackground(b);
		this.setDoubleBuffered(true);
		this.addKeyListener(this);
		this.setFocusable(true);
	
		tile = new Tile[40];
		mapTileNum = new int [maxWorldCol][maxWorldRow];
	}
	
	/**
	 * This method draws the map based on the array values after the load map method is called	 
	 * @param g is the graphics object needed for the program to draw an obejct in the jpanel
	 */ 
	public void drawMap(Graphics2D g)
	{
		try
		{
			getTileImage();
			loadMap("Map01Floor1.txt");
			
			int worldCol = 0;
			int worldRow = 0;
			
			
			while (worldCol <  maxWorldCol && worldRow <  maxWorldRow)
			{
				
				int tileNum = mapTileNum[worldCol][worldRow];
				
				int worlX = worldCol * tileSize;
				int worlY = worldRow * tileSize;
				int screenX = worlX - (worldX + p.screenX);
				int screenY = worlY - (worldY + p.screenY);
				
				if (tileNum == 0)
				{
				}
				else
				{
					g.drawImage(tile[tileNum].image, screenX, screenY,  tileSize,  tileSize, null);
				}
				
				worldCol++;
				
				
				if (worldCol ==  maxWorldCol)
				{
					worldCol = 0;
					worldRow++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e + "jfdkljf");
		}

	}	
    
    /**
	 * This method starts the game thread and the game
	 */ 
    public void startGameThread()
    {
		h = new Thread(this);
		h.start();
	}
    
    /**
	 * This method controls the game thread and also repaints the game screen and updates each time the game thread runs
	 */ 	
    public void run()
	{
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (h != null)
		{
			p.update();
			repaint();
			Thread.State state = h.getState();
			while(state.equals("RUNNABLE"))
			{
				 gameRuns = true;
			}
		
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

	/**
	 * This method updates the player's x and y locations
	 */ 
	public void update()
	{
		p.update();
	}
	
	/**
	 * This method paints the screen and the player
	 */ 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		drawMap(g2D);
		
		p.draw(g2D);
		
		
		
		g.dispose();
    }
	
	/**
	 * This method intializes the tile[] array to allow the corresponding images in the map file to be drawn onto the panel
	 */ 
	public void getTileImage()
	{
		try
		{
			//tile[0] = new Tile();
			//tile[0].image = ImageIO.read(new File("Floorboard.png"));
			for (int i = 0; i<tile.length; i++)
			{
				tile[i] = new Tile();
				tile[i].image = ImageIO.read(new File("Gray_Floor.png"));
			}
			
			tile[2].collison = true;

			
			tile[2].image = ImageIO.read(new File("Wall.png"));
	
			tile[3].image = ImageIO.read(new File("stairs.png"));
			
			//tile[4] = new Tile();
			//tile[4].image = ImageIO.read(new File("Improved_stairs.png"));
			
		
			tile[5].image = ImageIO.read(new File("Window.png"));
			
			
			tile[6].image = ImageIO.read(new File("Door.png"));
			
			//furniture
			
			tile[10].image = ImageIO.read(new File("Chair.png"));

		
			tile[11].image = ImageIO.read(new File("table.png"));

			
			tile[12].image = ImageIO.read(new File("cabinet.png"));

			
			tile[13].image = ImageIO.read(new File("chest.png"));

			tile[15].image = ImageIO.read(new File("Closed_scroll.png"));
			
			// monsters

			tile[20].image = ImageIO.read(new File("Zombie.png"));


			tile[21].image = ImageIO.read(new File("Skeleton.png"));

			tile[22].image = ImageIO.read(new File("Witch.png"));

			//tile[23].image = ImageIO.read(new File("Pixies.png"));

			//tile[24].image = ImageIO.read(new File("Soul_Orb.png"));
			//weapons

			tile[30].image = ImageIO.read(new File("woodAxe.png"));
	
			tile[31].image = ImageIO.read(new File("IronSword.png"));
			
		}
		catch(Exception e)
		{
			System.out.println(e + "jhskadhsk");
		}
	}
	
	/**
	 * This method stores the numbers in the Map file in an array to use them later to draw the map
	 * @param map is the map file from which the map is loaded
	 */ 
	public void loadMap(String map)
	{
		
		try 
		{
				
				File file = new File(map);
				Scanner br = new Scanner(file);
				
				int col = 0;
				int row = 0;
				
				while (col <  maxWorldCol && row <  maxWorldRow)
				{
					String line = br.nextLine();
					
					while(col <  maxWorldCol)
					{						
						String numbers[] = line.split(" ");
						
						int num = Integer.parseInt(numbers[col]);
						
						mapTileNum[col][row] = num;
						
						col++;
					}
					
					if (col ==  maxWorldCol)
					{
						col = 0;
						row++;
					}
					//br.close();
				}
		
		}
		catch (Exception e)
		{
			System.out.println(e + "d");
		}
	}
	
	//to move character
	public void keyTyped(KeyEvent e)
	{
	   int code = e.getKeyCode();
	}
	
	public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			p.upPressed = true;
	
		}
		else if (code == KeyEvent.VK_S)
		{
			p.downPressed = true;
		
		}
		else if (code == KeyEvent.VK_A)
		{
			p.leftPressed = true;
		
		}
		else if (code == KeyEvent.VK_D)
		{
			p.rightPressed = true;
		}
		else if (code == KeyEvent.VK_ESCAPE)
		{
			p.rightPressed = true;
			System.out.println("working esc");
		}
		else if (code == KeyEvent.VK_J)
		{
			jPressed = true;
		}
	}
	
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			p.upPressed = false;
		}
		else if (code == KeyEvent.VK_S)
		{
			p.downPressed = false;
		}
		else if (code == KeyEvent.VK_A)
		{
			p.leftPressed = false;
		}
		else if (code == KeyEvent.VK_D)
		{
			p.rightPressed = false;
		}
		else if (code == KeyEvent.VK_J)
		{
			jPressed = false;
		}
    }
	
	//the levels
}
