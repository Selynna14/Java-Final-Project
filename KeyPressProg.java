/**
* lets the user move the character using the keyboard
*
* @author Neysa Thota
* @version 1.0
* date: 4/22/2024
*/ 

import java.io.*;//needed to generate random values
import java.util.*;//neede for scanner
import java.io.*;//needed to generate random values
import java.util.*;//neede for scanner
import java.awt.*;//needed for graphic 
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;


public class KeyPressProg implements KeyListener
{	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public void keyTyped(KeyEvent e)
	{
	   
	}
	public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			upPressed = true;
			System.out.println("working w");
		}
		else if (code == KeyEvent.VK_S)
		{
			downPressed = true;
			System.out.println("working s");
		}
		else if (code == KeyEvent.VK_A)
		{
			leftPressed = true;
			System.out.println("working a");
		}
		else if (code == KeyEvent.VK_D)
		{
			rightPressed = true;
			System.out.println("working d");
		}
	}
    public void keyReleased(KeyEvent e) 
    {
         int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
			upPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_S)
		{
			downPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_A)
		{
			leftPressed = false;
			System.out.println("working l");
		}
		else if (code == KeyEvent.VK_D)
		{
			rightPressed = false;
			System.out.println("working l");
		}
    }
    
}

