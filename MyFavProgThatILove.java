/**
* Creates game panel
*
* @author Neysa Thota
* @version 1.0
* date: 4/22/2024
*/ 

import java.io.*;//needed to generate random values
import java.util.*;//neede for scanner
import java.awt.*;//needed for graphic 
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;


public class MyFavProgThatILove
{	
	public static void main (String [] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("A Creative Title");
		
		GamePanel panel = new GamePanel();
		
		frame.add(panel);
		frame.pack();
		
		panel.startGameThread();//starting game thread
	}
}
