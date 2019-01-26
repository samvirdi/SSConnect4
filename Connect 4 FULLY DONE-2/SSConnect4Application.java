// The "SSConnect4Application" class.
/*
 Name: Samandeep Singh Virdi & Shahzada
 Date: January 2016
 Description: Main Menu for the Program GUI
 */
import java.awt.*;
import java.awt.event.*; //import for action listener
import javax.swing.*; //import javax
import java.awt.Font;

public class SSConnect4Application extends JFrame implements ActionListener
{
    JLabel lblTitle;    //label made for title
    JButton btnPlay, btnHelp, btnHS, btnExit; //buttons made for the different menus
    JLabel lblBackground;
    Container frame; //contains the frame
    public SSConnect4Application ()
    {
	super ("SS Connect 4"); // Set the frame's name
	setSize (600, 450); // Set the frame's size

	//Setting window to the center of the screen
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	int w = this.getSize ().width;
	int h = this.getSize ().height;
	int x = (dim.width / 2) - 300;
	int y = (dim.height / 2) - 225;
	setLocation (x, y);

	frame = getContentPane ();

	//Creating JLabels to be used
	lblBackground = new JLabel (new ImageIcon ("Background.jpg"));
	btnExit = new JButton ("Exit");
	lblTitle = new JLabel ("SS CONNECT 4"); //Label for title
	lblTitle.setFont (new Font ("Gulim", Font.BOLD, 35)); //change font and size of title
	lblTitle.setForeground (new Color (250, 0, 0)); //Setting specific colour
	btnPlay = new JButton ("Play"); //set the Play JButton as Play
	btnHelp = new JButton ("Help"); //set the Help JButton as Help
	btnHS = new JButton ("High Score"); //set the HS JButton as High Score

	frame.setLayout (null); //set the frame layout as null

	//Setting the bounds of the JLabels
	lblTitle.setBounds (150, 20, 500, 35);
	btnPlay.setBounds (225, 100, 125, 40);
	btnHelp.setBounds (225, 150, 125, 40);
	btnHS.setBounds (225, 200, 125, 40);
	btnExit.setBounds (225, 250, 125, 40);
	lblBackground.setBounds (0, 0, 600, 450);

	//Adding the labels to the frame
	frame.add (lblTitle);
	frame.add (btnPlay);
	frame.add (btnHelp);
	frame.add (btnExit);
	frame.add (btnHS);
	frame.add (lblBackground);

	//Adding action listeners to the buttons
	btnPlay.addActionListener (this);
	btnHelp.addActionListener (this);
	btnHS.addActionListener (this);
	btnExit.addActionListener (this);

	setVisible (true); // Show the frame
    } // Constructor


    public void actionPerformed (ActionEvent e)  //method is created for the actions of the buttons
    {
	//if the Play button is pressed the game menu program appears
	if (e.getSource () == btnPlay)
	{
	    try
	    {
		new SSConnect4_Game_Menu ();
	    }
	    catch (Exception x)
	    {
	    }
	}
	// if the Help button is pressed the help program appears
	else if (e.getSource () == btnHelp)
	{
	    try
	    {
		new SSConnect4_Help_Menu ();
	    }
	    catch (Exception x)
	    {
	    }
	}
	//if the High Score button is pressed the high score program appears
	else if (e.getSource () == btnHS)
	{
	    try
	    {
		new SSConnect4_HighScore_Menu ();
	    }
	    catch (Exception x)
	    {

	    }
	}
	//if exit is pressed the program quits
	else if (e.getSource () == btnExit)
	{
	    System.exit (0); //Exiting the game
	}
    }


    public static void main (String[] args)
    {
	new SSConnect4Application ();   // Create a SSConnect4Application frame
    } // main method
} // SSConnect4Application class
