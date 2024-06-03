/**
*This program contains the main part of our program, like the code for each of the levels, and the code to create the panel
*
* @author Neysa Thota
* @version 1.0
* @since 4/22/2024
*/ 

import java.awt.*;//needed for graphic 
import javax.swing.*;//needed for the JFrame
import java.awt.event.*;//needed for keylistener and keyevent
import java.io.*; //needed fore file reading
import java.util.*; // imports scanner class


public class MyFavProgThatILove
{	
	/**
	 *Instance Variables 
	 */
	 
	static int tileNum = 0;
	static int playerLoc;
	static int currLevel = 0;
	static boolean passedFloor;
	static boolean respawn = false;
	static boolean winGame = false;
	static int lives = 3;
	static int play = 0;
	static String test;
	static Random randObject = new Random();
	static ArrayList <String> WeponList = new ArrayList <String>();
	static ArrayList <String> inventory = new ArrayList <String>();
	
	static JFrame frame = new JFrame();
	
	GamePanel panl = new GamePanel(this);
	
	static GamePanel panel = new GamePanel();	
	
	/**
	 * The main method runs all the code for the levels and calls the methods for each level, this contains all the conditions to control if the player has won the game and which level should run
	 */ 
	public static void main (String [] args)
	{
		//JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("Haunted Haven");
		
		frame.addKeyListener(panel);
		
		frame.add(panel);
		frame.pack();

		panel.startGameThread();//starting game thread	
		
		//panel.setGroundFloor();
		//groundFloor();
		panel.setFirstFloor();
		
		WeponList.add("Hand");

		
		while (winGame == false)
		{	
			switch(currLevel)
			{
				case 0:
					panel.setGroundFloor();
					groundFloor();
					break;
				case 1:
					panel.setFirstFloor();
					firstFloor();
					break;
				case 2:
					panel.setSecondFloor();
					secondFloor();
					break;
				case 3:
					panel.setThirdFloor();
					thirdFloor();
					break;
				default:
			}
		}
		
		/*if (winGame == true)
		{
			JOptionPane.showMessageDialog(null, "YOU WON THE GAME");
			panel.h.interrupt(); //here i need to destroy the thread
			frame.setVisible(false); //visibility to off
			System.exit(0);

		}*/
	}
	
	/**
	 * This method turns all the movement booleans to false to stop the charcter from moving 
	 */ 
	public static void stopMovement()
	{
		panel.p.rightPressed = false;
		panel.p.downPressed = false;
		panel.p.upPressed = false;
		panel.p.leftPressed = false; 	
	}
	
	/**
	 * This method finds the player's location at any moment in the game and returns it to the code for the levels to see if a player is at a place where they interact with a 
	 */ 
	public static int findPlayerTileLocation()
	{
		//predicting the location
		int left = panel.worldX + 1416;
		int top = panel.worldY  + 772;
		
		int leftCol = left/panel.tileSize;
		int topRow = top/panel.tileSize;

		tileNum = panel.mapTileNum[leftCol][topRow];
		
		if (panel.p.direction.equals("up") || panel.p.direction.equals("left"))
			{
				tileNum = panel.mapTileNum[leftCol][topRow];
			}
		else if (panel.p.direction.equals("down") || panel.p.direction.equals("right"))
		{
			left = panel.worldX + 1416 + 32;
			top = panel.worldY  + 772 + 48;
			leftCol = left/panel.tileSize;
			topRow = top/panel.tileSize;
				
			tileNum = panel.mapTileNum[leftCol][topRow];
		}	
	/*if(panel.p.direction.equals("up"))
		{
			top = panel.worldY  - 20;
	
			topRow = top/panel.tileSize;
			tileNum = panel.mapTileNum[leftCol][topRow];
		}
		else if (panel.p.direction.equals("down"))
		{
			top = panel.worldY + 20;
	
			topRow = top/panel.tileSize;
			tileNum = panel.mapTileNum[leftCol][topRow];
		}
		else if(panel.p.direction.equals("right"))
		{
			left = panel.worldX + 50;
			top = panel.worldY + 20;

			 //("The right coordinate is printing out");
			leftCol = left/panel.tileSize;
			topRow = top/panel.tileSize;
			tileNum = panel.mapTileNum[leftCol][topRow];
		}
		/*else if (panel.p.direction.equals("left"))
		{
			left = panel.worldX  + 772 - 30;
	
			leftCol = left/panel.tileSize;
			tileNum = panel.mapTileNum[leftCol][topRow];
		}*/	
	
		return tileNum;
	}
	
	/**
	 * This method contains all the code for the ground floor, it first contians the instructions that should only appear once and then a loop that runs everytime the gameloop runs and until the conditions and the boolean for passing the floor = true
	 */
	public static void groundFloor()
	{
		
		WeponList.add("Hand");
		
		while (panel.p.name == null)
		{
			panel.p.name = JOptionPane.showInputDialog(null,"Please enter your name:");
		}

		JOptionPane.showMessageDialog(null, "Hello " + panel.p.name  +"! Finally, you woke up. You are stuck in this haunted house and i will be your assistant to escape this miserable place.");
		JOptionPane.showMessageDialog(null, " Btw, press \"esc\" on your keyboard if you want to quit this game. I do not think that is a good idea, especially because we just started.");
		JOptionPane.showMessageDialog(null, "  FYI, use wasd to move around. I suggest that you go up to the first floor. ");
		JOptionPane.showMessageDialog(null, " Also you can jump out the window on each floor, but I do not know if that is a good idea. ");
		
		while(panel.gameRuns == true && passedFloor == false)
		{
			playerLoc = findPlayerTileLocation();
			System.out.println(playerLoc + "The ground floor is running");		 			
			
			play = panel.findObjNum();
			test = "chair";
			
			if (play != 10)
			{
					String item = panel.obj[play].name;
					System.out.println(item);
			
				if (item.equalsIgnoreCase(test))
				{
					stopMovement();	
					panel.worldX -= panel.tileSize/4;
					JOptionPane.showMessageDialog(null, " Bruh its just a chair ");
				}
			}
			else if (play == 10)
			{
			}
			
			//panel.setObjectAttributes(2, 10, 10, null, "");
			panel.obj[2] = null;
						
			if (playerLoc == 6)
			{
				stopMovement();	
				panel.worldY -= panel.tileSize;
				JOptionPane.showMessageDialog(null, " You thought you can escape by going through the door? The door is locked! ");
			}
					
			if (playerLoc == 10 || playerLoc == 11)
			{
				
				panel.worldY -= panel.tileSize;
				panel.worldX -= panel.tileSize;
				stopMovement();
				JOptionPane.showMessageDialog(null, "Just a table and chair. (I wonder why)");
			}
			

			if (playerLoc == 3)
			{
				stopMovement();
				int dialogButton = JOptionPane.YES_NO_OPTION;
				dialogButton = JOptionPane.showConfirmDialog (null, "Do You want to proceed, you cant go back?","help", dialogButton);
				
				if(dialogButton == JOptionPane.NO_OPTION) 
				{
					stopMovement();
					panel.worldY -= panel.tileSize;
				}
				else if (dialogButton == JOptionPane.YES_OPTION) 
				{
					panel.worldX = 648;
					panel.worldY = 318;
					currLevel = 1;
					passedFloor = true;
				}
			}
		}
	}
		
	/**
	 * This method contians the code for the first floor, and uses a loop to test the player's location until they complete everything in the level and move on to the next one
	 */ 
	public static void firstFloor()
	{		
		
		int comlpetedLeveltoAnswerRiddle = 0;
		boolean introWindow = true;
		boolean answeredRiddle = false;
		boolean interactedWithChest = false;
		boolean openDoor = false;
	    boolean hasKey = false;
		
		passedFloor = false;
		 
		JOptionPane.showMessageDialog(null, "You might find a key sowewhere in this room for the doors");
		JOptionPane.showMessageDialog(null, "You might want to explore the rooms");
		
		while(panel.gameRuns = true && passedFloor == false)
		{
			if(panel.showInventory == true)
			{
				JOptionPane.showMessageDialog(null, WeponList);
				panel.showInventory = false;
			}
											
			playerLoc = findPlayerTileLocation();
			System.out.println(playerLoc + " First floor is running");		
			
			play = panel.findObjNum();
			test = "";
			
			if (play != 10)
			{
					String item = panel.obj[play].name;
					//System.out.println(item);
			
				test = "axe";
				if (item.equalsIgnoreCase(test))
				{
					stopMovement();
					JOptionPane.showMessageDialog(null, "You found an axe, adding this to inventory");
					comlpetedLeveltoAnswerRiddle++;
					panel.worldX -= 20;
					//add axe to inventory
					WeponList.add("Axe");
					inventory.add("Axe");
					panel.obj[7] = null;
				}	
			
				test = "chest";
				if (item.equalsIgnoreCase(test) && interactedWithChest == false)//chest
				{
					stopMovement();
					JOptionPane.showMessageDialog(null, "You have encountered a chest");
					JOptionPane.showMessageDialog(null, "You found a key inside, add it to your inventory");
					panel.worldY += 40;
					panel.worldY += 20;
					interactedWithChest = true;
					comlpetedLeveltoAnswerRiddle++;
					hasKey = true;
				}
				else if  (item.equalsIgnoreCase(test) && interactedWithChest == true)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null, "You might wanna use the key to open the doors");
					switch(panel.p.direction)
					{
						case "up":
							panel.worldY += 40;
							break;
						case "down":
							panel.worldY -= 40;
							break;
						case "right":
							panel.worldX -= 40;
							break;
						case "left":
							panel.worldX += 40;
							break;
					}
				
				}
				
				test = "scroll";
				if (item.equalsIgnoreCase(test) && answeredRiddle == false)//scroll
				{
					stopMovement();
					String answer;
					JOptionPane.showMessageDialog(null, "You have to answer a riddle to move on");
					JOptionPane.showMessageDialog(null, "This place has hardly any lights, but a lot of creaking floors. There are all kinds of strange noises and some random slamming doors. What is it?");
					answer = JOptionPane.showInputDialog(null, "Input your answer:");
					
					if (answer.equalsIgnoreCase("haunted house"))
					{
						JOptionPane.showMessageDialog(null,"you can move on");
						answeredRiddle = true;
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"you answered the riddle wrong, try again");	
					}
				}
				
				if (item.equalsIgnoreCase(test) && answeredRiddle == true)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null,"Just go to the next floor, :(");	
					panel.worldY += panel.tileSize;
				}
					
				test = "zombie";
				
				if(item.equalsIgnoreCase(test))
				{
					stopMovement();
					panel.combat = true;
					stopMovement();
					combat(test);
					switch(panel.p.direction)
					{
						case "up":
							panel.worldY += 40;
							break;
						case "down":
							panel.worldY -= 40;
							break;
						case "right":
							panel.worldX -= 40;
							break;
						case "left":
							panel.worldX += 40;
							break;
					}
					panel.obj[8] = null;
					panel.obj[9] = null;
				}
			}
			else if (play == 10)
			{
			}
			
			if(playerLoc == 5 && introWindow == true)//window
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "You can press J to jump");
				panel.worldX -= panel.tileSize;
				introWindow = false;
				
			}
						
			if (playerLoc == 5 && introWindow == false)//window
			{
				
				if(panel.jPressed == true)
				{
					stopMovement();
					int yesno = JOptionPane.YES_NO_OPTION;
					yesno = JOptionPane.showConfirmDialog (null, "Would you like to jump?","JUMP", yesno);
					
					if(yesno == JOptionPane.YES_OPTION) 
					{
						JOptionPane.showMessageDialog(null, "You died");
						int dialogButton = JOptionPane.YES_NO_OPTION;
						dialogButton = JOptionPane.showConfirmDialog (null, "Would you like to respawn?","Respawn", dialogButton);
						
						if(dialogButton == JOptionPane.YES_OPTION) 
						{
							panel.worldX = 648;
							panel.worldY = 318;
							currLevel = 1;
							stopMovement();
							lives--;
							JOptionPane.showMessageDialog(null, "You lost a life");

						}
						else if (dialogButton == JOptionPane.NO_OPTION) 
						{
							JOptionPane.showMessageDialog(null, "GAME OVER");
							panel.h.interrupt(); //here i need to destroy the thread
							frame.setVisible(false); //visibility to off
							System.exit(0);
							break;
							
						}
					}
					else 
					{
						panel.worldX -= panel.p.speed*4;
					}
				}
			}
			
			if (playerLoc == 6 && hasKey == false && openDoor == false)
			{
				
				stopMovement();
				if (panel.p.direction.equals("left"))
				{
					panel.worldX += panel.tileSize;
				}
				
				if (panel.p.direction.equals("right"))
				{
					panel.worldX -= panel.tileSize;
				}
				JOptionPane.showMessageDialog(null, "Find the key");
				
			}
			
			if (playerLoc == 3 && answeredRiddle == true && comlpetedLeveltoAnswerRiddle >= 2)//stairs
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				stopMovement();
				dialogButton = JOptionPane.showConfirmDialog (null, "Do You want to proceed, you cant go back?","u might not expect smth, be ready", dialogButton);
				
				if(dialogButton == JOptionPane.NO_OPTION) 
				{
					stopMovement();
					panel.worldY += panel.tileSize;
				}
				else if (dialogButton == JOptionPane.YES_OPTION) 
				{
					currLevel = 2;
					panel.worldX = 684;
					panel.worldY = 318;
					passedFloor = true;
				}
			}
			else if (playerLoc == 3 && (answeredRiddle == false || comlpetedLeveltoAnswerRiddle < 2 ))//stairs without completing level
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "You cant go up yet");
				panel.worldY += panel.tileSize;
			}
					
			if (playerLoc == 6 && hasKey == true && openDoor == false)
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				stopMovement();
				dialogButton = JOptionPane.showConfirmDialog (null, "Do you want to use the key to open the door?","Oh no, You are stuck inside", dialogButton);
				
				if(dialogButton == JOptionPane.NO_OPTION) 
				{
					stopMovement();
					panel.worldY -= panel.tileSize;
				}
				else if (dialogButton == JOptionPane.YES_OPTION) 
				{
					stopMovement();
					openDoor = true;
				}
			} 
			
			if(playerLoc == 6 && openDoor == true)
			{
				 // = false;	
			}	
	
		}
	}
	
	/**
	 * This method contians the code for the second floor, and uses a loop to test the player's location until they complete everything in the level and move on to the next one
	 */ 
	public static void secondFloor()
	{	
		boolean answeredRiddle = false;
		passedFloor = false;
		boolean potionsAddToInventory = false;
		
		while(panel.gameRuns = true && passedFloor == false)
		{
			playerLoc = findPlayerTileLocation();
			System.out.println(playerLoc);
			
			/*if (currLevel == 2)//making sure the player cant go down
			{
				if (panel.worldY >= 1288)
				{
					panel.worldY -= 20;
				}
			}*/
			if (playerLoc == 3 && answeredRiddle == true)//stairs
			{
					int dialogButton = JOptionPane.YES_NO_OPTION;
					stopMovement();
					dialogButton = JOptionPane.showConfirmDialog (null, "Do You want to proceed, you cant go back?","Last Level, yayyy", dialogButton);
					
					if(dialogButton == JOptionPane.NO_OPTION) 
					{
						stopMovement();
						panel.worldY -= panel.tileSize;
					}
					else if (dialogButton == JOptionPane.YES_OPTION) 
					{
						panel.worldX = 648;
						panel.worldY = 318;
						currLevel = 3;
						passedFloor = true;
					}
			}	
			else if (playerLoc == 3 && answeredRiddle == false)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "Answer the riddle before you move on");

			}
			if (playerLoc == 5)//window
			{
				
				if(panel.jPressed == true)
				{
					stopMovement();
					int yesno = JOptionPane.YES_NO_OPTION;
					yesno = JOptionPane.showConfirmDialog (null, "Would you like to jump?","JUMP", yesno);
					
					if(yesno == JOptionPane.YES_OPTION) 
					{
						JOptionPane.showMessageDialog(null, "You died");
						int dialogButton = JOptionPane.YES_NO_OPTION;
						dialogButton = JOptionPane.showConfirmDialog (null, "Would you like to respawn?","Respawn", dialogButton);
						
						if(dialogButton == JOptionPane.YES_OPTION) 
						{
							panel.worldX = 648;
							panel.worldY = 318;
							currLevel = 1;
						}
						else if (dialogButton == JOptionPane.NO_OPTION) 
						{
							JOptionPane.showMessageDialog(null, "GAME OVER");
							panel.h.interrupt(); //here i need to destroy the thread
							frame.setVisible(false); //visibility to off
							System.exit(0);
							break;
						}
					}
					else 
					{
						panel.worldX -= panel.p.speed*4;
					}
				}
			}
			if  (playerLoc == 13)//chest with hint
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "Hint hint");
				panel.worldY += 40;
				panel.worldX += 20;
			}
			
			if (playerLoc == 20 || playerLoc == 22)//fighting in level 2
			{
				panel.worldY += 20;
				panel.combat = true;
			}
			
			if (panel.combat == true)//fighitng section
			{
				if (playerLoc == 22)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null," There’s a witch!" );
				} 
					stopMovement();
					JOptionPane.showMessageDialog(null," You are in combat! Don’t die!");
					panel.combat = false;
					//combat();
					
				if (panel.combat == false && potionsAddToInventory ==  false)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null,"The witches dropped 3 potions, I'll add them to your inventory ");
					//add potions to inventory
				}
				
				if (playerLoc == 20)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null," There’s a zombie!" );
				} 
					stopMovement();
					JOptionPane.showMessageDialog(null," You are in combat! Don’t die!");
					//combat();
			}
			
		}
	}
	
	/**
	 * This method contians the code for the third floor, and uses a loop to test the player's location until they complete everything in the level and move on to the next one
	 */ 
	public static void thirdFloor()
	{
		 // = true;	
		panel.tile[3].collison = true;	
		
		int levCompleted = 0;
		passedFloor = false;
		
		
		while(panel.gameRuns = true && passedFloor == false)
		{
			if (levCompleted <= 3)
			{
				stopMovement();
				winGame = true;
				JOptionPane.showMessageDialog(null, "Answer the riddle before you move on");

					
			}
			
			if (currLevel == 2)//making sure the player cant go down
			{
				if (panel.worldY >= 584)
				{
					panel.worldY -= 20;
				}
			}
		}	
		
	}

	/**
	 * This method turns all the movement booleans to false to stop the charcter from moving 
	 * @param tileNum is the player's location showing which monster the player is fighting, so that the method can get the right values for the right monsters
	 */ 
	public static void combat(String monster)
	{
		try
		{
			int MonsterHealth;
			
			while(panel.combat == true)
			{
				int Damge = 0; 
				if (monster.equals("zombie")) //zombie
				{

					/*Zombie[] zom = new Zombie[3];
					Zombie z = new Zombie(panel);
					zom[0] = new Zombie(panel);
					MonsterHealth = z.getMonsterHealth();*/
					MonsterHealth = 10;
					System.out.println(MonsterHealth);
					Object[] weapons = WeponList.toArray();
					while (MonsterHealth > 0) // loops while the monster is alive
					{
						String input = (String) JOptionPane.showInputDialog(null,"How would you like to attack?", "", JOptionPane.QUESTION_MESSAGE, null, weapons, weapons[0]);
						switch(input) 
						{
							case "Hand": // code block 
								Damge = randObject.nextInt(0,5); // does a randObject.nextInt amount of damage from 0 to 4
								break;
							case "Axe": // code block 
								Damge = randObject.nextInt(5,10); 
								break;
							case "Sword": // code block 
								Damge = randObject.nextInt(8,12);
								break;
							case "Poison Sword": // code block 
								Damge = randObject.nextInt(10,14);
								break;
							case "Poison Axe": // code block 
								Damge = randObject.nextInt(7,12);
								break;
							default: // code block 
						} 
						
						MonsterHealth -= Damge;
					}
					
					panel.combat = false;
					JOptionPane.showMessageDialog(null, "You beat the Monster. Yayy");
					
					//Player health = Player health - Damage; //need player health // player is INVINCIBLE - RAHHHHHH
				}

				/*if (tileNum == 21) //need colitions
				{
					Zombie[] zom = new Zombie[3];
					Zombie z = new Zombie(panel);
					zom[0] = new Zombie(panel);
					MonsterHealth = z.getMonsterHealth();
					System.out.println(MonsterHealth);	
					destroyMonster(MonsterHealth);	

				}
				
				if (tileNum == 22) //need colitions
				{
					Zombie[] zom = new Zombie[3];
					Zombie z = new Zombie(panel);
					zom[0] = new Zombie(panel);
					MonsterHealth = z.getMonsterHealth();		
					//Player health = Player health - Damage; //need player health // player is INVINCIBLE - RAHHHHHHH
				}

				if (tileNum == 23) //need colitions
				{
					Zombie[] zom = new Zombie[3];
					Zombie z = new Zombie(panel);
					zom[0] = new Zombie(panel);
					MonsterHealth = z.getMonsterHealth();		
					destroyMonster(MonsterHealth);

				}

				if (tileNum == 24) //need colitions
				{
					Zombie[] zom = new Zombie[3];
					Zombie z = new Zombie(panel);
					zom[0] = new Zombie(panel);
					MonsterHealth = z.getMonsterHealth();		
				}*/

			}
		}
		catch(Exception e)
		{
			System.out.println("Error! in combat class: " + e);
		}

	}
	
	/**
	 * This method does returns the damage done to the monster each hit, depending on what the player used. 
	 * @param MonsterHealth is the amount of health the monster has to decide how many times the player needs to hit to kill the monster. 
	 */ 
	public static int destroyMonster(int MonsterHealth)
	{
		int Damage = 0;
		Object[] weapons = WeponList.toArray();
		try
		{		
		while (MonsterHealth > 0) // loops while the monster is alive
		{
					String input = (String) JOptionPane.showInputDialog(null,"How would you like to attack?", "", JOptionPane.QUESTION_MESSAGE, null, weapons, weapons[0]);
					switch(input) 
					{
						case "Hand": // code block 
							Damage = randObject.nextInt(0,5); // does a randObject.nextInt amount of damage from 0 to 4
							break;
						case "Axe": // code block 
							Damage = randObject.nextInt(5,10); 
							break;
						case "Sword": // code block 
							Damage = randObject.nextInt(8,12);
							break;
						case "Poison Sword": // code block 
							Damage = randObject.nextInt(10,14);
							break;
						case "Poison Axe": // code block 
							Damage = randObject.nextInt(7,12);
							break;
						default: // code block 
					} 
		}
				
		}
		catch(Exception e)
		{
			System.out.println("Error! in dmamage class: " + e);
		}
		
		return Damage;

	}
	/**
	 * Saves data to file
	 * @param name is the players name
         * @param lives number of lives the player has
	 * @param floor the floor number the player is on
	 */ 
	/**  
	Public SaveFile(String name, int lives, int floor)
	{
	    String[] saveFile = {"1","2", "3"}; 
	    String input = (String) JOptionPane.showInputDialog(null,"Which file do you want to save your game in? ", "", JOptionPane.QUESTION_MESSAGE, null,loadFile, loadFile[2]);
	    if (input.equals("1"))
	    {
	        try
	        {
	            File fileOne = new File("fileOne.txt"); // loads file
	            PrintWriter write = new PrintWriter(fileOne); // creates file writer 
	            write.println(name); // writes important data into file
	            write.println(lives);
	            write.println(floor);
	            write.close();
	        }
	        catch(Exception e)
	            System.out.println("Error");
	           
	        
	    }
	    else if (input.equals("2"))
	    {
	        try
	        {
	            File fileTwo = new File("fileTwo.txt");
	            PrintWriter write = new PrintWriter(fileTwo);
	            write.println(name);
	            write.println(lives);
	            write.println(floor);
	            write.close();
	        }
	        catch(Exception e)
	            System.out.println("Error");
	    }
	    else if (input.equals("3"))
	    {
	        try
	        {
	            File fileOne = new File("fileOne.txt");
	            PrintWriter write = new PrintWriter(fileOne);
	            write.println(name);
	            write.println(lives);             
	            write.println(floor);
	            write.close();
	        }
	        catch(Exception e)
	            System.out.println("Error");
	    }
	    else
	        System.out.println("?");
	}
	*/
}

