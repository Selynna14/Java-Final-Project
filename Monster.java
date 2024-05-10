import java.util.* //for using random library.

Public class Monsters 
{//start of monsters class
  //make instance variables 
  private int monsterDamage;
  private int monsterHealth;
  private int monsterSpeed;

  Public BloodThirstyPixies()
  {//3 Pixies
	monsterHealth = 10;
	monsterSpeed=8;
    	monsterDamage = randObject.nextInt(10,13);	//random integer from 10-12
  }

  Public Witches()
  {
  //only 2 Witches
  	monsterHealth = 30;
    monsterSpeed = 4;
    monsterDamage = randObject.nextInt(5,8);//random integer from 5-7 	
  }
  
  Public Zombies()
  {
  	monsterHealth = 10;
  	monsterSpeed=2;
    monsterDamage = randObect.nextInt(0,5); //random integer from 0-4 
  }
  
  Public Skeletons()
  {
  	monsterHealth = 13;
    monsterSpeed=3;
    monsterDamage = randObect.nextInt(4,8);//random integer from 4-7  
  }
  Public House()
  {
  	monsterHealth = 75;
    monsterSpeed = null;
    monsterDamage = randObject.nextInt(10,21); //random integer from 10-20 
  }
public int getMonsterHealth()
{
	return monsterHealth;
}
public int getMonsterDamage()
{
	return monsterDamage;
}
public int getMonsterSpeed()
{
	return monsterSpeed;
}

}//end of monsters class
