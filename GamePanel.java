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
	//MapBackground mp = new MapBackground(this);
	Player p = new Player(this);
	
	String currLevel = "0";
	
	//world vars
	public int worldX, worldY;
	public final int maxWorldCol = 64;
	public final int maxWorldRow = 67;
	public final int worldWidth = tileSize  * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	GamePanel()
	{
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		
		Color b = new Color(101,67,33);
		this.setBackground(b);
		this.setDoubleBuffered(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		tile = new Tile[37];
		mapTileNum = new int [maxWorldCol][maxWorldRow];
		
	}
	
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new File("Floorboard.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(new File("Gray_Floor.png"));
			tile[1].collison = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(new File("Wall.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(new File("stairs.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(new File("Improved_stairs.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(new File("Window.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(new File("Door.png"));
			//furniture
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(new File("Chair.png"));

			tile[11] = new Tile();
			tile[11].image = ImageIO.read(new File("table.png"));

			tile[12] = new Tile();
			tile[12].image = ImageIO.read(new File("cabinet.png"));

			tile[13] = new Tile();
			tile[13].image = ImageIO.read(new File("chest.png"));

			tile[14] = new Tile();
			tile[14].image = ImageIO.read(new File("Pile_of_clothes.png"));

			tile[15] = new Tile();
			tile[15].image = ImageIO.read(new File("Closed_scroll.png"));
			// monsters
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(new File("Zombie.png"));

			tile[21] = new Tile();
			tile[21].image = ImageIO.read(new File("Skeleton.png"));

			tile[22] = new Tile();
			tile[22].image = ImageIO.read(new File("Witch.png"));

			tile[23] = new Tile();
			tile[23].image = ImageIO.read(new File("Pixies.png"));

			tile[24] = new Tile();
			tile[24].image = ImageIO.read(new File("Soul_Orb.png"));
			//weapons
			tile[30] = new Tile();
			tile[30].image = ImageIO.read(new File("woodAxe.png"));
			
			tile[31] = new Tile();
			tile[31].image = ImageIO.read(new File("IronSword.png"));
			
		}
		catch(Exception e)
		{
			System.out.println(e + " ");
		}
	}
	
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
			
			p.update();
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
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		drawMap(g2D);
		
		p.draw(g2D);
		
		g.dispose();
    }
  
	//to move character
	public void keyTyped(KeyEvent e)
	{
	   
	}
	
	public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			p.upPressed = true;
			System.out.println("working w");
		}
		else if (code == KeyEvent.VK_S)
		{
			p.downPressed = true;
			System.out.println("working s");
		}
		else if (code == KeyEvent.VK_A)
		{
			p.leftPressed = true;
			System.out.println("working a");
		}
		else if (code == KeyEvent.VK_D)
		{
			p.rightPressed = true;
			System.out.println("working d");
		}
		else if (code == KeyEvent.VK_ESCAPE)
		{
			p.rightPressed = true;
			System.out.println("working esc");
			SaveFile(name, health, floor);
		}
	}
	
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			p.upPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_S)
		{
			p.downPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_A)
		{
			p.leftPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_D)
		{
			p.rightPressed = false;
			System.out.println("working l");
		}
    }
	
	
	public void checkCollision()
	{
		//predicting how to do collsions
		int predictLeft = worldX + p.solidArea.x;
		int predictRight = worldX + p.solidArea.x + p.solidArea.width;
		int predictTop = worldY + p.solidArea.y;
		int predictBottom = worldY + p.solidArea.y + p.solidArea.height;
		
		int leftCol = predictLeft/tileSize;
		int rightCol = predictRight/tileSize;
		int topCol = predictTop/tileSize;
		int bottomCol = predictBottom/tileSize;
		
		//System.out.println(leftCol);
		//System.out.println(rightCol);
		//System.out.println(topCol);
		//System.out.println(bottomCol);
		
		
		int tileNum1, tileNum2;
		
		if (p.direction == "up")
		{
			//System.out.println("u");
		}
		else if (p.direction == "down")
		{
			//System.out.println("d");
		}
		else if (p.direction == "left")
		{
			//System.out.println("l");
		}
		else if (p.direction == "right")
		{
			//System.out.println("r");
		}
		
	}
}
