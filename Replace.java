import java.io.*; 
import java.util.*; // imports scanner class
/**
* replaces axe or sword
*
* @author Tate Sommer
* @version 1.0
* @since 4/22/2024
*/ 
public class replace
{ // starts 
	public replaceWeapons(int weaponnum)
	{ // starts replaceWeapons
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
						if(word.equals("weaponnum")) // Looks weapon number and replaces it with 0
						{ // Start if
							System.out.print("0 ");
						} // Ends if
						else
						{
							System.out.print(word + " "); // Prints every other word
						}
						
					} // ends for
					
					System.out.println();
				} // end file loop
	
				
		} // end try
		catch(Exception e) // stops the program from breaking
		{ // start catch
			System.out.println("Error!");
		} // end catch
	} // ends replaceWeapons

	public replaceMonster(int monsternum, int currLevel)
	{ // starts replaceMonster
		if (currLevel == 2)
		{
			try
			{ // Starts try
				ArrayList<String[]> wholeText = new ArrayList<String[]>(); // create arrayList
				File newFile = new File("Map01Floor1.txt"); // finds the file
				Scanner iR = new Scanner(newFile); // creates scanner to read file
				while(iR.hasNextLine()) // creates a loop while there is more data
				{ // Starts while loop
					String theLine = iR.nextLine();
					wholeText.add(theLine.split(" "));
				} // Ends while loop
				iR.close(); // closes the input reader
				for(int i = 0; i < wholeText.size(); i++)
				{ // Starts for
					if (i > blank)
					{
						String[] currLine = wholeText.get(i);
						for(int j = 0; j < currLine.length; j++)
						{ // Starts for
							
							String word = currLine[j];
							if(word.equals("monsternum")) // Looks weapon number and replaces it with 0
							{ // Start if
								System.out.print("0 ");
							} // Ends if
							else
							{
								System.out.print(word + " "); // Prints every other word
							}
						} // ends for	
					} // ends if
					else
					{
						String[] currLine = wholeText.get(i);
						for(int j = 0; j < currLine.length; j++)
						{ // Starts for
							String word = currLine[j];
							System.out.print(word + " "); // Prints every other word
						}
					} // ends else
					
				System.out.println();
				} // end file loop
	
				
			} // end try
			catch(Exception e) // stops the program from breaking
			{ // start catch
				System.out.println("Error!");
			} // end catch
		}
		if (currLevel == 3)
		{
		}
	} // ends replaceMonster
} // ends
