/**
*add purpose of program
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
	String name;
	GamePanel gp;
	KeyPressProg key;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	double screenHeight;
	double screenWidth;
	
	public Player(GamePanel gp, KeyPressProg key)
	{
		this.gp = gp;
		this.key = key;
		//this.gp.addKeyListener(this);
		//gp.addKeyListener(this);	
	}
	
	int x = 100;
	int y = 100;
	int speed = 6;

	
	public void update()
	{
		if (key.upPressed == true && y > 0)
		{
			y-= speed;
		}
		else if (key.downPressed == true &&  y <( gp.screenHeight - gp.tileSize))
		{
			y += speed;
			
		}
		else if (key.leftPressed == true && x > 0)
		{
			x -= speed;
		}
		else if (key.rightPressed == true  && x < (gp.screenWidth-gp.tileSize))
		{
			x += speed;
		}
	}
	
	public String getName ()
	{
		Scanner x = new Scanner(System.in);
		System.out.println("What is your name");
		name = x.nextLine();
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
		g.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		//g.drawImage(image, x, y, null);
	}
    
	

}
