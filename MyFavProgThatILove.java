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
		int health, floor;
		String name, line1, lin2;
		String[] yesNo = {"Yes","No"};
		String input = (String) JOptionPane.showInputDialog(null,"Do you want to play? ", "", JOptionPane.QUESTION_MESSAGE, null, yesNo, yesNo[1]);
		
		if (input.equals("Yes"))
		{
			panel.startGameThread();//starting game thread
   			String[] loadFile = {"1","2", "3"};
			String input2 = (String) JOptionPane.showInputDialog(null,"Which file do you want to play out of? ", "", JOptionPane.QUESTION_MESSAGE, null,loadFile, loadFile[2]);
			if (input2.equals("1"))
			{

			}
			else if (input2.equals("2"))
			{

			}
			else if (input2.equals("3"))
			{

			}
			else
				System.out.println("?");
		}
		else 
		{
			frame.setVisible(false); //you can't see me!
			frame.dispose(); //Destroy the JFrame object
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
}
