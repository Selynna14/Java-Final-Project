import java.io.*; // imports needed for files
import java.nio.file.Files; 
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class reads data from a file and saves it into variables
 * @author Tate Sommer
 * @version 1.0
 * date: 5/08/2024
*/

public class Sommer_saveFiles
{ // start class 
	
	String line1;
	String line2;
	String line3;
		
	try
	{ // starts try
		File fileOne = new File("fileOne.txt"); // Go find and load file
		if (fileOne.createNewFile())
		{
			System.out.println("Good");
		}
		else
		{
			System.out.println("Wait");
			line1 = Files.readAllLines(Paths.get("fileOne.txt")).get(0);
			line2 = Files.readAllLines(Paths.get("fileOne.txt")).get(1);
			line3 = Files.readAllLines(Paths.get("fileOne.txt")).get(2);
			System.out.println(line1);
			System.out.println(line2);
			System.out.println(line3);
		}

	} // ends try
	catch(Exception e)
	{ // starts catch
		System.out.println("Error! " + e);
	} // ends catch
} // ends class
