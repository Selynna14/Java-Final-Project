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


public class Thota_Player 
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
	String direction;
	
	boolean coll = false;
	public Rectangle solidArea;
	
	public Thota_Player(GamePanel gp)
	{
		this.gp = gp;
		this.gp.addKeyListener(gp);
		this.screenHeight = gp.screenHeight;
		this.screenWidth = gp.screenWidth;
	
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		
		solidArea = new Rectangle();
		solidArea.x = 32;
		solidArea.y = 32;
		solidArea.width = 32;
		solidArea.height = 32;
		
		gp.worldX = 636;
		gp.worldY = 3140;
		
	}
	
	public void update()
	{
		coll = false;
		gp.checkCollision();
		
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
			
	}
	
	public String getName ()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("What is your name");
		name = s.nextLine();
		return name;
	}

	/*Public void GameOver ()
	{
	If (health <= 0)
	{
	Print “Game over”
	}
	}*/
	
	//drawing character
    public void draw(Graphics2D g)
    {
		ImageIcon icon = new ImageIcon("Forward_character.png");
		Image image = icon.getImage();
		g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		//System.out.println(gp.worldX + " " +gp.worldY);
	}
    
	
}
