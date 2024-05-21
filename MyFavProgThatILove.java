/**
* main program
*
* @author Neysa Thota
* @version 1.0
* date: 4/22/2024
*/ 
import java.awt.*;//needed for graphic 
import javax.swing.*;//needed for the JFrame
import java.io.*; // imports needed for files
import java.nio.file.Files; 
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFavProgThatILove
{	
	static int tileNum = 0;
	static int playerLoc;
	static int currLevel = 0;
	static boolean passedFloor;
	static boolean respawn = false;
	static boolean combat = false;
	static boolean winGame = false;
	static Random randObject = new Random();
	static String[] WeponList = {"Hand"};
	//static ArrayList < String[] > WeponList = new ArrayList < String[] >();
	static ArrayList < String[] > inventory = new ArrayList < String[] > ();
	
	static JFrame frame = new JFrame();
	
	static GamePanel panel = new GamePanel();
	
	public static void main (String [] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("Haunted Haven");
		
		GamePanel panel = new GamePanel();
		
		frame.add(panel);
		frame.pack();


		panel.startGameThread();//starting game thread
		int health;
		String name,floor, lin2;
		String[] yesNo = {"Yes","No"};
		String input = (String) JOptionPane.showInputDialog(null,"Do you want to play? ", "", JOptionPane.QUESTION_MESSAGE, null, yesNo, yesNo[1]);
		
		if (input.equals("Yes"))
		{
			panel.startGameThread();//starting game thread
   			String[] loadFile = {"1","2", "3"};
			String input2 = (String) JOptionPane.showInputDialog(null,"Which file do you want to play out of? ", "", JOptionPane.QUESTION_MESSAGE, null,loadFile, loadFile[2]);
			if (input2.equals("1"))
			{
				try
				{ // starts try
					File fileOne = new File("fileOne.txt"); // Go find and load file
					if (fileOne.createNewFile())
					{
						System.out.println("Good");
					}
					/*else
					{
						name = Files.readAllLines(Paths.get("fileOne.txt")).get(0);
						line2 = Files.readAllLines(Paths.get("fileOne.txt")).get(1);
						floor = Files.readAllLines(Paths.get("fileOne.txt")).get(2);
						health = Integer.parseInt(line2);
					}
			*/
				} // ends try
				catch(Exception e)
				{ // starts catch
					System.out.println("Error! " + e);
				} // ends catch
	
			}
			else if (input2.equals("2"))
			{
				try
				{ // starts try
					File fileTwo = new File("fileTwo.txt"); // Go find and load file
					if (fileTwo.createNewFile())
					{
						System.out.println("Good");
					}
					/*else
					{
						name = Files.readAllLines(Paths.get("fileTwo.txt")).get(0);
						line2 = Files.readAllLines(Paths.get("fileTwo.txt")).get(1);
						floor = Files.readAllLines(Paths.get("fileTwo.txt")).get(2);
						health = Integer.parseInt(line2);
					}
			*/
				} // ends try
				catch(Exception e)
				{ // starts catch
					System.out.println("Error! " + e);
				} // ends catch

			}
			else if (input2.equals("3"))
			{
				try
				{ // starts try
					File fileThree = new File("fileThree.txt"); // Go find and load file
					if (fileThree.createNewFile())
					{
						System.out.println("Good");
					}
					//else
					//{
						/* 
						 * name = Files.readAllLines(Paths.get("fileThree.txt")).get(0);
						line2 = Files.readAllLines(Paths.get("fileThree.txt")).get(1);
						floor = Files.readAllLines(Paths.get("fileThree.txt")).get(2);
						health = Integer.parseInt(line2);
						*/
					//}
			
				} // ends try
				catch(Exception e)
				{ // starts catch
					System.out.println("Error! " + e);
				} // ends catch

			}
			else
				System.out.println("?");
		}
		else 
		{
			frame.setVisible(false); //you can't see me!
			frame.dispose(); //Destroy the JFrame object
		}
		while (winGame == false)
		{
			switch(currLevel)
			{
				case 0:
					groundFloor();
					break;
				case 1:
					firstFloor();
					break;
				case 2:
					secondFloor();
					break;
				case 3:
					thirdFloor();
					break;
				default:
			}
		}
		
		if (winGame == true)
		{
			JOptionPane.showMessageDialog(null, "YOU WON THE GAME");
			panel.h.interrupt(); //here i need to destroy the thread
			frame.setVisible(false); //visibility to off
			System.exit(0);
			break;

		}
		
		/*int dialogButton = JOptionPane.YES_NO_OPTION;
        dialogButton = JOptionPane.showConfirmDialog (null, "DO You want to proceed?","WAING", dialogButton);
        if(dialogButton == JOptionPane.NO_OPTION) 
        {
			frame.setVisible(false);
			frame.dispose(); //Destroys the JFrame object
			System.exit(0);
        }
        else if (dialogButton == JOptionPane.YES_OPTION) 
        {
			panel.startGameThread();//starting game thread
		}*/
	}
		
	static GamePanel panel = new GamePanel();
	
		public static void stopMovement()
	{
		panel.p.rightPressed = false;
		panel.p.downPressed = false;
		panel.p.upPressed = false;
		panel.p.leftPressed = false; 	
	}
	
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
	
	public static void groundFloor()
	{
		
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
			 System.out.println(playerLoc);		
			 			
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
					panel.worldX = 642;
					panel.worldY = 1728;
					currLevel = 1;
					passedFloor = true;
				}
			}
		}
	}
		
	public static void firstFloor()
	{		
		 // = true;	
		
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
			panel.tile[3].collison = true;	
						
			playerLoc = findPlayerTileLocation();
			 //(playerLoc);		

			if (currLevel == 1)
			{
				if (panel.worldY >= 1992)
				{
					panel.worldY -= 20;
				}
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
							panel.worldX = 642;
							panel.worldY = 1728;
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
			
			if (playerLoc == 15 && answeredRiddle == false)//scroll
			{
				stopMovement();
				String answer;
				JOptionPane.showMessageDialog(null, "You have to answer a riddle to move on");
				JOptionPane.showMessageDialog(null, "This place has hardly any lights, but a lot of creaking floors. There are all kinds of strange noises and some random slamming doors. What is it?");
				answer = JOptionPane.showInputDialog(null, "Input your answer:");
				
				if (answer.equalsIgnoreCase("haunted house"))
				{
					JOptionPane.showMessageDialog(null,"You can move on");
					answeredRiddle = true;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"You answered the riddle wrong, try again");	
				}
			}
			
			if (playerLoc == 15 && answeredRiddle == true)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null,"Just go to the next floor, :(");	
				panel.worldY -= 20;
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
					panel.worldX = 640;
					panel.worldY = 1024;
					passedFloor = true;
				}
			}
			else if (playerLoc == 3 && (answeredRiddle == false || comlpetedLeveltoAnswerRiddle < 2 ))//stairs without completing level
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "You cant go up yet");
				panel.worldY += panel.tileSize;
			}
			
			if (playerLoc == 13 && interactedWithChest == false)//chest
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
			else if  (playerLoc == 13 && interactedWithChest == true)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "You might wanna use the key to open the doors");
				panel.worldY += 40;
				panel.worldY += 20;
			}
			
			if (playerLoc == 6 && hasKey == true && openDoor == false)
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				stopMovement();
				dialogButton = JOptionPane.showConfirmDialog (null, "Do You want to use the key to open the door?","Oh no, You are stuck inside", dialogButton);
				
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
	
			if (playerLoc == 30)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null, "You found an axe, adding this to inventory");
				comlpetedLeveltoAnswerRiddle++;
				panel.worldX -= 20;
				//add axe to inventory
			//	WeponList.add("Axe");
			//	inventory.add("Axe");
				removeIcon(30);
			}	
			
			if (playerLoc == 20)
			{
			combat = true;
			}
			if (combat == true)
			{
				if (playerLoc == 20)
				{
					JOptionPane.showMessageDialog(null," There’s a zombie!" );
				} 
					JOptionPane.showMessageDialog(null," You are in combat! Don’t die!");
				//combat();
			}

		}
	}
	
	public static void secondFloor()
	{	
		boolean answeredRiddle = false;
		passedFloor = false;
		
		while(panel.gameRuns = true && passedFloor == false)
		{
			if (currLevel == 2)//making sure the player cant go down
			{
				if (panel.worldY >= 1288)
				{
					panel.worldY -= 20;
				}
			}
			if (playerLoc == 15 && answeredRiddle == false)//scroll
			{
				stopMovement();
				String answer;
				JOptionPane.showMessageDialog(null, "You have to answer a riddle to move on");
				JOptionPane.showMessageDialog(null, "People are scared of me. When you have more of me, you will see less. What am I?");
?
");
				answer = JOptionPane.showInputDialog(null, "Input your answer:");
				
				if (answer.equalsIgnoreCase("darkness"))
				{
					JOptionPane.showMessageDialog(null,"You can move on");
					answeredRiddle = true;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"You answered the riddle wrong, try again");	
				}
			}
			
			if (playerLoc == 15 && answeredRiddle == true)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null,"Just go to the next floor, :)");	
				panel.worldY -= 20;
			}
				
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
							panel.worldX = 642;
							panel.worldY = 1728;
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
				combat = true;
			}
			
			if (combat == true)//fighitng section
			{
				if (playerLoc == 22)
				{
					stopMovement();
					JOptionPane.showMessageDialog(null," There’s a witch!" );
				} 
					stopMovement();
					JOptionPane.showMessageDialog(null," You are in combat! Don’t die!");
					//combat();
					
				if (combat == false)
				{
					removeIcon(22);
					stopMovement();
					JOptionPane.showMessageDialog(null,"The witches dropped 3 potions, I'll add them to your inventory ");
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
				JOptionPane.showMessageDialog(null, "Answer the cipher before you move on");

					
			}
			
			if (currLevel == 2)//making sure the player cant go down
			{
				if (panel.worldY >= 584)
				{
					panel.worldY -= 20;
				}
			}
			if (playerLoc == 15 && answeredRiddle == false)//scroll
			{
				stopMovement();
				String answer;
				JOptionPane.showMessageDialog(null, "You have to answer a cipher to move on");
				JOptionPane.showMessageDialog(null, "ZCUYPC-MD-RFC-FMSQC - Hint: y=a.");
				answer = JOptionPane.showInputDialog(null, "ZCUYPC-MD-RFC-FMSQC - Input your answer:");
				
				if (answer.equalsIgnoreCase("beware of the house"))
				{
					JOptionPane.showMessageDialog(null,"You can move on");
					answeredRiddle = true;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"You answered the riddle wrong, try again");	
				}
			}
			
			if (playerLoc == 15 && answeredRiddle == true)
			{
				stopMovement();
				JOptionPane.showMessageDialog(null,"Just go to the next floor, :)");	
				panel.worldY -= 20;
			}
		}	
		
	}
	
	public static void fourthFloor()
	{
		 // = true;	
		panel.tile[3].collison = true;	
		
		passedFloor = false;
		
		while(panel.gameRuns = true && passedFloor == false)
		if (playerLoc == 3)
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				stopMovement();
				dialogButton = JOptionPane.showConfirmDialog (null, "Do You want to proceed, you cant go back?","u might not expect smth, be ready", dialogButton);
				
				if(dialogButton == JOptionPane.NO_OPTION) 
				{
					stopMovement();
					panel.worldY -= panel.tileSize;
				}
				else if (dialogButton == JOptionPane.YES_OPTION) 
				{
		
					currLevel = 2;
					passedFloor = true;
				}
		}	
		/*if (combat = true)
		{
			if (player encountered zombie)
			{
			JOptionPane.ShowMesageDialog(null," There’s a zombie!" );
			} 
			JOptionPane.ShowMesageDialog(null," You are in combat! Don’t die!");
			Call combat class.
		}
		if (player encountered scroll)
		{
		JOptionPane.ShowMesageDialog(null, "You have found a scroll. On the scroll, there is a riddle on it. Answer it correctly to move on to the next floor.");
		}
		if (player collides with chest)
		{
		JOptionPane.ShowMesageDialog(null, "You have encountered a chest! What are you going to do with it? ");
		}
		*/
	}
	
	public static void removeIcon(int weaponnum)
	{
		try
		{ // Starts try
			ArrayList<String[]> wholeText = new ArrayList<String[]>(); // create arrayList
			
			File myFile = new File("Map01Floor1.txt"); // finds the file
			
			Scanner iR = new Scanner(myFile); // creates scanner to read file
			
			while(iR.hasNextLine()) // creates a loop while there is more data
			{ // Starts while loop
				String theLine = iR.nextLine();
				wholeText.add(theLine.split(" "));
			} // Ends while loop
			
			iR.close(); // closes the input reader
			for(int i = 0; i < wholeText.size(); i++)
			{ // Starts for
				String[] currLine = wholeText.get(i);
				for(int j = 0; j < currLine.length; j++)
				{ // Starts for
					
					String word = currLine[j];
					if(word.equals("weaponnum")) // Looks for the word God and changes it to Bob
					{ // Start if
						System.out.print("0 ");
					} // Ends if
					else
					{
						System.out.print(word + " "); // Prints every other word
					}
					
				} // ends for
				
				System.out.println();
			
			} // ends for
				
				System.out.println();
			//} // end file loop
		} // end try
		catch(Exception e) // stops the program from breaking
		{ // start catch
			System.out.println("Error!" + e);
		} // end catch
	}
	
	/*public static void combat(int tileNum)
	{
		int MonsterHealth;
		boolean combat = true; // creates flag variable
		while(combat == true)
		{
			int Damge = 0; 
			if (tileNum == 20) //need colitions
			{
				Monster Zombie = new Zombie();//make a house!
				MonsterHealth = Zombie.getMonsterHealth();
				destroyMonster(MonsterHealth);
				
				//Player health = Player health - Damage; //need player health // player is INVINCIBLE - RAHHHHHH
			}

			if (tileNum == 21) //need colitions
			{
				Monster Skeletons = new Skeletons();//make a house!
				MonsterHealth = Skeleton.getMonsterHealth();
				//call destroy monster
				destroyMonster(MonsterHealth);

			}
			
			if (tileNum == 22) //need colitions
			{
				Monster Witch = new Witch();//make a house!
				MonsterHealth = Witch.getMonsterHealth;
			    //call destory monster
				destroyMonster(MonsterHealth);
				//Player health = Player health - Damage; //need player health // player is INVINCIBLE - RAHHHHHHH
			}

			if (tileNum == 23) //need colitions
			{
				Monsters Pixies = new Pixies();//make a house!
				MonsterHealth = Pixies.getMonsterHealth;
				//call destory monster
				destroyMonster(MonsterHealth);

			}

			if (tileNum == 24) //need colitions
			{
				Monster SoulOrb = new SoulOrb();//make a house!
				MonsterHealth = SoulOrb.getMonsterHealth;
				destroyMonster(MonsterHealth);
			}

			combat = false;
		}
	}
	
	public static void destroyMonster(int MonsterHealth, int Damage)
	{
		while (MonsterHealth > 0) // loops while the monster is alive
		{
					String input = (String) JOptionPane.showInputDialog(null,"How would you like to attack?", "", JOptionPane.QUESTION_MESSAGE, null, WeponList, WeponList[0]);
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
	}*/
}

}
