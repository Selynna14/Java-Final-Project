import java.util.*;
import java.awt.*;

public class Zombie
{
	Random randObject = new Random();
	int monsterHealth = 10;

	GamePanel gp;

	
	public void Zombie(GamePanel gp)
	{

		monsterHealth = 10;
		int monsterSpeed = 2;
		int monsterDamage = randObject.nextInt(0,5); //random integer from 0-4 
	}
	
	
	public int getMonsterHealth()
	{
		return monsterHealth; 
	}
}
