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


public class Player implements KeyListener
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
	
	int speed = 7;
	
	public Player(GamePanel gp)
	{
		this.gp = gp;
		this.gp.addKeyListener(this);
		this.screenHeight = gp.screenHeight;
		this.screenWidth = gp.screenWidth;
	
		screenX = gp.screenWidth/2;
		screenY = gp.screenHeight/2;
		
		gp.worldX = -700;
		gp.worldY = 100;
		
	}
	
	public void update()
	{
		if (upPressed == true)
		{
			gp.worldY-= speed;
		}
		else if (downPressed == true )
		{
			gp.worldY += speed;
		}
		else if (leftPressed == true )
		{
			gp.worldX -= speed;
		}
		else if (rightPressed == true )
		{
			gp.worldX += speed;
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
			upPressed = true;
			System.out.println("working w");
		}
		else if (code == KeyEvent.VK_S)
		{
			downPressed = true;
			System.out.println("working s");
		}
		else if (code == KeyEvent.VK_A)
		{
			leftPressed = true;
			System.out.println("working a");
		}
		else if (code == KeyEvent.VK_D)
		{
			rightPressed = true;
			System.out.println("working d");
		}
	}
	
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			upPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_S)
		{
			downPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_A)
		{
			leftPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_D)
		{
			rightPressed = false;
			System.out.println("working l");
		}
    }

}
