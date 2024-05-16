import java.util.* // imports for files
import java.io.*
/**
    * Saves data to file
    *
    * @author Tate Sommer
    * @version 1.0
    * date: 5/15/2024
*/ 
    
Public SaveFile(String name, String health, String floor)
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
            write.println(health);
            write.println(floor);
            write.println();
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
            write.println(health);
            write.println(floor);
            write.println();
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
            write.println(health);             
            write.println(floor);
            write.println();
            write.close();
        }
        catch(Exception e)
            System.out.println("Error");
    }
    else
        System.out.println("?");
}
  
