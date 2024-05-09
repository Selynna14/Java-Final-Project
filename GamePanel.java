/**
*This file contains all the code for updating and printing the game and screen 
*
* @author Neysa Thota
* @version 1.0
* date: 4/22/2024
*/ 

import java.awt.*;//needed for graphic s and color
import javax.swing.*;//needed for the JPanel 
import java.util.*;//needed for scanner
import javax.imageio.ImageIO;//needed to use images
import java.io.*;//needed to generate random values

public class GamePanel extends JPanel implements Runnable
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
	
	//world vars
	public int worldX, worldY;
	public final int maxWorldCol = 22;
	public final int maxWorldRow = 56;
	public final int worldWidth = tileSize  * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	GamePanel()
	{
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		
		Color b = new Color(101,67,33);
		this.setBackground(b);
		this.setDoubleBuffered(true);
		this.addKeyListener(p);
		this.setFocusable(true);
		
		tile = new Tile[10];
		mapTileNum = new int [maxWorldCol][maxWorldRow];
		
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
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(new File("table.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(new File("Chair.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(new File("Pile_of_clothes.png"));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
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
	
	public void drawA(Graphics2D g)
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
		
		drawA(g2D);
		
		p.draw(g2D);
		
		g.dispose();
    }
  
}
