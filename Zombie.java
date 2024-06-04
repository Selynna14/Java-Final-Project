import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Zombie extends Monster
{
	Random randObject = new Random();
	Rectangle monRect = new Rectangle();

//	int monsterHealth = 10;

	int x, y;
	int screenX = 0, screenY = 0;
	//
	
	GamePanel gp;
	
	public Zombie()
	{
		monsterHealth = 10;
		monsterSpeed = 2;
	}

	public Zombie(GamePanel gp)
	{
		this.gp = gp;
		//x = gp.p.screenX + gp.tileSize*3 ;
		//y = gp.p.screenY + gp.tileSize*3;
		monsterHealth = 10;
		int monsterSpeed = 2;
		int monsterDamage = randObject.nextInt(0,5); //random integer from 0-4 
		int monWorldX = 30*gp.tileSize;
		int monWorldY = 14*gp.tileSize;
		screenX = 30*gp.tileSize - (gp.worldX + gp.p.screenX);
		screenY = 14*gp.tileSize - (gp.worldY + gp.p.screenY);
	}
	
	public int getMonsterHealth()
	{
		return monsterHealth; 
	}
	
	public void update()
	{
			
		if (screenY > gp.worldY)
		{
			screenY -= 2;
		}
		else if (screenY < gp.worldY)
		{
			screenY += 2;
		}
		else if ( screenX > gp.worldX)
		{
			screenX -= 2;
		}
		else if (screenX < gp.worldX)
		{
			screenX += 2;
		}
		
		/*while()
		{
		}*/
		
	}
	
	public void draw(Graphics2D g)
    {
		try
		{
			ImageIcon icon = new ImageIcon("Zombie.png");
			Image image = icon.getImage();
			
			g.drawImage(image, screenX, screenY,gp.tileSize, gp.tileSize, null);
			monRect.setRect(screenX , screenY , gp.tileSize, gp.tileSize);
			g.draw(monRect);
		}
		catch(Exception e)
		{
			System.out.println("Zombie is not printing");
		}
	}
	
}
