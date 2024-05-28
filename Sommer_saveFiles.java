import java.io.*; // imports needed for files
import java.nio.file.Files; 
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This method reads data from a file and saves it into variables
 * @author Tate Sommer
 * @version 1.0
 * date: 5/08/2024
*/

public Sommer_saveFiles()
{ // start method
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
		
} // ends method
