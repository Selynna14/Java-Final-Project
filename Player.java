/**
*This is where all the code for the player exists. 
*
* @author Neysa Thota
* @version 1.0
* date: 4/27/2024
*/ 
import java.awt.*;//needed for graphic 
import javax.swing.*;
import java.util.*;//needed for scanner
import java.awt.event.*;//needed for keylistener and keyevent


public class Player 
{
	/*make all instance variables
	String userName
	Make health bar variable
	Make axe variable
	Make hand variable
	Make sword variable
	*/
	
	/**
	 *Instance Variables 
	 */
	
	String name;
	GamePanel gp;
	
	double screenHeight;
	double screenWidth;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public final int screenX;
	public final int screenY;
	
	int speed = 4;
	String direction = "";
	
	boolean coll = false;
	
	/**
	 * This method intializes some variables 
	 * @param gp this allows the player class to use variables from the GamePanel file without breaking the program
	 */ 	
	public Player(GamePanel gp)
	{
		this.gp = gp;
		this.gp.addKeyListener(gp);
		this.screenHeight = gp.screenHeight;
		this.screenWidth = gp.screenWidth;
	
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		
		gp.worldX = 644;
		gp.worldY = 2436;
		
	}
	
	/**
	 * This method finds the player's location in the map, and the tile they are on to test if they are colliding with anything
	 */ 
	public int findPlayerTileLocation()
	{
		int tileNum;
		//predicting the location
		int left = gp.worldX + 1416;
		int top = gp.worldY  + 772;
		
		int leftCol = left/gp.tileSize;
		int topRow = top/gp.tileSize;

		tileNum = gp.mapTileNum[leftCol][topRow];
		
		if(gp.p.direction.equals("up"))
		{
			top = gp.worldY  + 772 - 50;
	
			topRow = top/gp.tileSize;
			tileNum = gp.mapTileNum[leftCol][topRow];
		}
		else if (gp.p.direction.equals("down"))
		{
			top = gp.worldY  + 772 + 50;
	
			topRow = top/gp.tileSize;
			tileNum = gp.mapTileNum[leftCol][topRow];
		}
		else if(gp.p.direction.equals("right"))
		{
			left = gp.worldX + 772 + 30;
	
			leftCol = left/gp.tileSize;
			tileNum = gp.mapTileNum[leftCol][topRow];
		}
		/*else if (gp.p.direction.equals("left"))
		{
			left = gp.worldX  + 772 - 30;
	
			leftCol = left/gp.tileSize;
			tileNum = gp.mapTileNum[leftCol][topRow];
		}*/
		
		return tileNum;
	}
	
	/**
	 * This method updates the player's x ro y values depending on if the user is pressing the keys to make the player move through the map. It also checks if a playe is able to move and moves thme back by a bit if they can't
	 */ 
	public void update()
	{
		coll = false;
		checkCollision();
		
		int playerLoc = findPlayerTileLocation();
		 
		if (coll == false)
		{
			if (upPressed == true)
			{
				direction = "up";
				gp.worldY-= speed;
			}
			else if (downPressed == true )
			{
				direction = "down";
				gp.worldY += speed;
			}
			else if (leftPressed == true )
			{
				direction = "left";
				gp.worldX -= speed;
			}
			else if (rightPressed == true )
			{
				direction = "right";
				gp.worldX += speed;
			}
		}
		else if (coll == true)
		{
			switch(direction) 
			{
				case "up":
					gp.worldY += speed;
					break;
				case "down":
					gp.worldY-= speed;
					break;
				case "left":
					gp.worldX += speed;
					break;
				case "right":
					gp.worldX -= speed;
					break;
			}
		}
		else if (coll == true && playerLoc != 2)
		{
			rightPressed = false;
			downPressed = false;
			upPressed = false;
			leftPressed = false; 	
			//System.out.println("The player is encounerting smth tthast not a wall");
		}
		
	}
	
	/**
	 * This method finds the players location and determines if they are collinding with an object and are unable to move forward
	 */ 
	public void checkCollision()
	{
		//predicting the location
		int left = gp.worldX + 1416;
		int top = gp.worldY  + 772;
		
		int leftCol = left/gp.tileSize;
		int topRow = top/gp.tileSize;

		int tileNum;
		
		try
		{
			if (direction.equals("up") || direction.equals("left"))
			{
				tileNum = gp.mapTileNum[leftCol][topRow];

				if (gp.tile[tileNum].collison == true)
				{
					coll=true;
				}
			}
			else if (direction.equals("down") || direction.equals("right"))
			{
				left = gp.worldX + 1416 + 32;
				top = gp.worldY  + 772 + 48;
		
				leftCol = left/gp.tileSize;
				topRow = top/gp.tileSize;
				
				tileNum = gp.mapTileNum[leftCol][topRow];

				if (gp.tile[tileNum].collison == true)
				{
					coll=true;
				}
			}
		}
		catch(Exception e)
		{
		}
		
	}
	
	//drawing character
	/**
	 * This method draws the player in the jpanel
	 * @param g is the graphics object needed for the program to draw an obejct in the jpanel
	 */ 
    public void draw(Graphics2D g)
    {
		ImageIcon icon = new ImageIcon("Forward_character.png");
		Image image = icon.getImage();
		
		g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	
    
	
}
