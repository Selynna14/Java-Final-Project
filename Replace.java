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
				} // end file loop
	
				
		} // end try
		catch(Exception e) // stops the program from breaking
		{ // start catch
			System.out.println("Error!");
		} // end catch
	} // ends replaceWeapons
} // ends
