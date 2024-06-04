/**
*This is the tile class to create an class to store tiles(images) needed for the game
*
* @author Neysa Thota
* @version 1.0
* @since 5/7/2024
*/ 

import java.awt.*;
import javax.imageio.ImageIO;//needed to use images
import java.awt.image.BufferedImage;//needed to make an image variable
import java.io.*;//needed to generate random values

public class Obj
{	
	/**
	 *Instance Variables 
	 */	
	public BufferedImage image;
	public String name;
	Rectangle objRect = new Rectangle();
	public boolean collison = false;
	public int ObjWorldX, ObjWorldY;
	
	int worldCol = 0;
	int worldRow = 0;
	
	public void draw(Graphics2D g, GamePanel gp)
	{
		int screenX = ObjWorldX - (gp.worldX + gp.p.screenX);
		int screenY = ObjWorldY - (gp.worldY + gp.p.screenY);
		
		objRect.setRect(screenX , screenY , gp.tileSize, gp.tileSize);
		g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		g.draw(objRect);
	}

}
