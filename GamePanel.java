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
	Obj obj[] = new Obj[10];
	
	MyFavProgThatILove f;
	
	Rectangle tileRect = new Rectangle();
	
	public boolean gameRuns = true;
	public static boolean combat = false;
	boolean showInventory =  false;
	static boolean jPressed =  false;
	
	//world vars
	public int worldX, worldY;
	public final int maxWorldCol = 64;
	public final int maxWorldRow = 23;
	public final int worldWidth = tileSize  * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	/**
	 * This method intitalizes the MyFavProgVariable so that methods and variables and etc from that program can be used in this program
	 */ 
	GamePanel(MyFavProgThatILove favProg)
	{
		f = favProg;
	}
	
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
		
		getTileImage();

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
			
			tile[1].collison = true;
			tile[1].image = ImageIO.read(new File("Wall.png"));
	
			tile[3].image = ImageIO.read(new File("stairs.png"));
			
			//tile[4] = new Tile();
			//tile[4].image = ImageIO.read(new File("Improved_stairs.png"));
			
		
			tile[5].image = ImageIO.read(new File("Window.png"));
			
			
			tile[6].image = ImageIO.read(new File("Door.png"));
			
		}
		catch(Exception e)
		{
			System.out.println("Error in getTile" + e);
		}
	}

	 /**
	 * This sets the objects that should be drawn on the ground floor
	 */ 
	public void setGroundFloor() 
	{
		setObjectAttributes(0, 22, 19, "cabinet.png", "cabinet");
		setObjectAttributes(1, 22, 18, "cabinet.png", "cabinet");
		setObjectAttributes(2, 41, 17, "cabinet.png", "cabinet");
		setObjectAttributes(4, 41, 21, "cabinet.png", "cabinet");
		setObjectAttributes(5, 38, 12, "Chair.png", "chair");
		setObjectAttributes(6, 39, 12, "table.png", "table");	
	}
	/**
	 * This sets the objects and monsters that should be drawn on the FIRST floor
	 */ 
	public void setFirstFloor() 
	{
		setObjectAttributes(0, 25, 19, "chest.png", "chest");
		setObjectAttributes(1, 32, 12, "Closed_scroll.png", "scroll");
		setObjectAttributes(5, 37, 12, "Chair.png", "chair");
		setObjectAttributes(6, 38, 12, "table.png", "table");
		setObjectAttributes(7, 53, 16, "woodAxe.png", "axe");
		setObjectAttributes(8, 8, 16, "Zombie.png", "zombie");
		setObjectAttributes(9, 9, 16, "Zombie.png", "zombie");
	
	}
	
	/**
	 * This sets the objects that should be drawn on the second floor
	 */ 
	public void setSecondFloor() 
	{
		setObjectAttributes(0, 22, 19, "cabinet.png", "door");
		setObjectAttributes(1, 22, 18, "cabinet.png", "door");
		setObjectAttributes(2, 41, 17, "cabinet.png", "door");
		setObjectAttributes(4, 41, 21, "cabinet.png", "door");
		setObjectAttributes(5, 41, 12, "Chair.png", "door");
		setObjectAttributes(6, 38, 12, "table.png", "door");
		setObjectAttributes(7, 22, 18, "cabinet.png", "door");
		setObjectAttributes(8, 41, 17, "cabinet.png", "door");
		setObjectAttributes(9, 41, 21, "cabinet.png", "door");

	}
	
	/**
	 * This sets the objects that should be drawn on the third floor
	 */ 
	public void setThirdFloor() 
	{
		setObjectAttributes(0, 22, 19, "cabinet.png", "door");
		setObjectAttributes(1, 22, 18, "cabinet.png", "door");
		setObjectAttributes(2, 41, 17, "cabinet.png", "door");
		setObjectAttributes(4, 41, 21, "cabinet.png", "door");
		setObjectAttributes(5, 39, 12, "Chair.png", "door");
		setObjectAttributes(6, 38, 12, "table.png", "door");

	}
	
	
	/**
	 * This sets the attributes for an object to be drawn on the panel 
	 * @param objNum is the graphics object needed for the program to draw an obejct in the jpanel
	 * @param tileX is the x location at whihc hte object shoudl be placed
	 * @param tileY is the y location at which this should be placed
	 * @param image is the name of the image file needed
	 */ 
	public void setObjectAttributes(int objNum, int tileX, int tileY, String image, String name)
	{

		try
		{
			obj[objNum] = new Obj();
			obj[objNum].ObjWorldX = tileX*tileSize;
			obj[objNum].ObjWorldY = tileY*tileSize;
			obj[objNum].image = ImageIO.read(new File(image));
			obj[objNum].name = name;
		}
		catch (Exception e)
		{
			System.out.println("Error in setAtt" + e);
		}
	}
		

	 /**
	 * This method finds if the player is interacting with an object
	 */ 
	public int findObjNum()
	{
		int i;
		
		for (i = 0; i < obj.length; i++)
		{
			if (obj[i] != null)
			{
				if (p.x.intersects(obj[i].objRect))
				{
					return i;
				}
			}
		}
		return i;
	}
	
	/**
	 * This method draws the map based on the array values after the load map method is called	 
	 * @param g is the graphics object needed for the program to draw an obejct in the jpanel
	 * @param map is the name of the map file we want to draw the level from
	 */ 
	public void drawMap(Graphics2D g, String map)
	{
		try
		{
			loadMap(map);
			
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
				else if(tileNum != 1 && tileNum != 0)
				{
					g.drawImage(tile[tileNum].image, screenX, screenY,  tileSize,  tileSize, null);
					tileRect.setRect(screenX + 18, screenY + 32, tileSize/2, tileSize/2);
					g.draw(tileRect);	
				}
				else
				{
					g.drawImage(tile[tileNum].image, screenX, screenY,  tileSize,  tileSize, null);
				}
				
				worldCol++;
				
				if(tileRect.intersects(p.x))
				{
					System.out.println("The rects are instersecting");
				}
																
				if (worldCol ==  maxWorldCol)
				{
					worldCol = 0;
					worldRow++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println( "Error in DrawMap" + e);
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
			update();
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
	
	public void drawObjectsMonsters(Graphics2D g)
	{
		for (int i = 0; i < obj.length; i++)
		{
			if (obj[i] != null)
			{
				obj[i].draw(g, this);
			}
		}
		
		
	}
	/**
	 * This method paints the screen and the player
	 * @param Graphics g
	 */ 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		ImageIcon icon;
		Image image;
						
		switch(f.currLevel) // drawing map based on level
		{
			case 0:
				drawMap(g2D, "GroundFloor.txt");
				drawObjectsMonsters(g2D);
				break;
			case 1:
				drawMap(g2D, "FirstFloor.txt");
				drawObjectsMonsters(g2D);
				break;
			case 2: 
				drawMap(g2D, "SecondFloor.txt");
				drawObjectsMonsters(g2D);
				break;
			case 3:
				drawMap(g2D, "ThirdFloor.txt");
				drawObjectsMonsters(g2D);
				break;
		}
		
		p.draw(g2D);//drawing the player

		switch(f.lives)//drawing the amount of lives the player has
		{
			case 0:
				icon = new ImageIcon("no_hearts.png");
				image = icon.getImage();
				g.drawImage(image, 1180, 20, 240, 80, null);
				//JOptionPane.showMessageDialog(null, "u lost");
				break;
			case 1:
				icon = new ImageIcon("1_heart.png");
				image = icon.getImage();
				g.drawImage(image, 1180, 20, 240, 80, null);
				break;
			case 2: 
				icon = new ImageIcon("2_hearts.png");
				image = icon.getImage();
				g.drawImage(image, 1180, 20, 240, 80, null);
				break;
			case 3:
				icon = new ImageIcon("full_health.png");
				image = icon.getImage();
				g.drawImage(image, 1180, 20, 240, 80, null);
				break;
		}
		
		g.dispose();
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
		else if (code == KeyEvent.VK_I)
       {
			showInventory = true;
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
		else if (code == KeyEvent.VK_I)
        {
			showInventory = false;
	    }
    }

}
