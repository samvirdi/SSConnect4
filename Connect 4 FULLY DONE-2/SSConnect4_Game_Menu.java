// The "SSConnect4_Game_Menu" class.
/* Authors: Shahzada Gulfam, Samandeep Virdi
   Date: Jan, 2016
   Description: This program takes the user input on the column clicked and adds
		the correct chip needed to two different arrays. A JLabel array is
		used to set the graphics for the UI and the other array is used to
		correctly check wins, check if full etc...
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SSConnect4_Game_Menu extends JFrame implements ActionListener, MouseListener
{
    //Checking screen size
    Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
    int w = this.getSize ().width;
    int h = this.getSize ().height;

    //Creating JLables, buttons and JLabel array for chips
    JLabel lblConnectPic, lblBackground, lblTitle, lblTurn, lblRed, lblBlack, black[] [], red[] [], lblColumn[];
    JButton btnExit;

    //Declaring array for checking wins etc and variables
    int table[] [], tries = 0, column = 0, x, y;
    String colour, type;

    Container frame;

    public SSConnect4_Game_Menu ()
    {
	//Setting frame name, setting background color
	super ("SSConnect4_Game_Menu");
	frame = getContentPane ();
	frame.setBackground (Color.WHITE);

	//Creating table array to check win, and other arrays for visuals
	table = new int [6] [7];
	table = SetZero (table); //Setting all elements in array to 0 (indicates empty)
	black = new JLabel [6] [7];
	red = new JLabel [6] [7];
	lblColumn = new JLabel [7];

	//Declaring Labels
	lblConnectPic = new JLabel (new ImageIcon ("Connect4Pic.png"));
	lblRed = new JLabel (new ImageIcon ("RedChip.png"));
	lblBlack = new JLabel (new ImageIcon ("BlackChip.png"));
	lblBackground = new JLabel (new ImageIcon ("Background.jpg"));

	//Creating Fonts and foreground for labels
	lblTitle = new JLabel ("SS Connect 4");
	lblTitle.setFont (new Font ("Calibri", Font.PLAIN, 30));
	lblTitle.setForeground (Color.RED);
	lblTurn = new JLabel ("Turn: ");
	lblTurn.setFont (new Font ("Calibri", Font.PLAIN, 17));

	//Filling and giving location to JLabel arrays
	x = 110;
	y = 48;
	for (int i = 0 ; i < black.length ; i++)
	{
	    for (int j = 0 ; j < black [0].length ; j++)
	    {
		//Declaring JLabel
		black [i] [j] = new JLabel (new ImageIcon ("black.png"));
		red [i] [j] = new JLabel (new ImageIcon ("red.png"));

		//Setting Location
		black [i] [j].setBounds (x, y, 46, 46);
		red [i] [j].setBounds (x, y, 46, 46);

		//Adding to frame
		frame.add (black [i] [j]);
		frame.add (red [i] [j]);

		//Making invisible
		black [i] [j].setVisible (false);
		red [i] [j].setVisible (false);

		//Changing y location
		x = x + 56;
	    }
	    //Resetting y to original and changing
	    y = y + 50;
	    x = 110;
	}

	//Adding, setting, bounds for lblColumn
	x = 100;
	for (int i = 0 ; i < lblColumn.length ; i++)
	{
	    lblColumn [i] = new JLabel ("");
	    lblColumn [i].setBounds (x, 45, 57, 300);
	    frame.add (lblColumn [i]);
	    lblColumn [i].addMouseListener (this);
	    x = x + 57;
	}

	//JButoon
	btnExit = new JButton ("Back");

	frame.setLayout (null);

	//Setting size and location
	//To put frame in the center of the screen
	setSize (600, 450);
	x = (dim.width / 2) - 300;
	y = (dim.height / 2) - 225;
	setLocation (x, y);

	//Setting bounds of labels
	lblConnectPic.setBounds (600 / 2 - 200, 450 / 2 - 180, 400, 300);
	lblTitle.setBounds (600 / 2 - 80, 450 / 2 - 250, 160, 100);
	lblTurn.setBounds (10, 320, 100, 100);
	btnExit.setBounds (600 / 2 - 50, 360, 100, 30);
	lblRed.setBounds (50, 345, 46, 46);
	lblBlack.setBounds (50, 345, 46, 46);
	lblBackground.setBounds (0, 0, 600, 450);

	//Adding labels to frame
	frame.add (lblConnectPic);
	frame.add (lblTitle);
	frame.add (lblTurn);
	frame.add (btnExit);
	frame.add (lblBlack);
	frame.add (lblRed);
	frame.add (lblBackground);

	//Adding mouse and action listener to labels
	btnExit.addActionListener (this);

	//Showing frame
	setVisible (true);

    } // Constructor


    public static void main (String[] args)
    {

	new SSConnect4_Game_Menu ();    // Create a SSConnect4_Game_Menu frame

    } // main method


    //Checking the button clicked
    public void actionPerformed (ActionEvent e)
    {
	//If the exit button is pressed
	if (e.getSource () == btnExit)
	{
	    //Yes or No JOptionDialog, asking for confirmation
	    int choice = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION); //Code retrived from Java Documentation

	    if (choice == 0)
	    {
		dispose ();
	    }
	    else
	    {
	    }
	}
    }


    //If the mouse is clicked
    public void mouseClicked (MouseEvent e)
    {
	//Checking which column is clicked
	for (int i = 0 ; i < lblColumn.length ; i++)
	{
	    if (e.getSource () == lblColumn [i])
	    {
		column = i;
	    }
	}

	//If space in the column
	if (table [0] [column] == 0)
	{
	    tries++;

	    //Checking wheter even or odd
	    type = EvenOrOdd (tries);

	    //Method to add chips to screen and an array
	    table = calculate (table, black, red, type, column);

	    //Checking horiz win
	    String winType = "horiz";
	    String win = checkWin (table, type, winType);

	    //Checking vertical win
	    winType = "vert";
	    String win2 = checkWin (table, type, winType);

	    //Checking right diagonal win
	    int num = 1;
	    String win3 = checkWin2 (table, type, num);

	    //Checking left diagonal win
	    num = -1;
	    String win4 = checkWin2 (table, type, num);

	    //If any of the wins are true...
	    if (win.equals ("true") || win2.equals ("true") || win3.equals ("true") || win4.equals ("true"))
	    {
		//Checking which player won
		if (type.equals ("even"))
		{
		    JOptionPane.showMessageDialog (null, "Red Won!");
		    //Delay for few seconds to add effect
		    delay ();
		    //to close the window
		    dispose ();
		}
		else
		{
		    JOptionPane.showMessageDialog (null, "Black Won!");
		    delay ();
		    dispose ();
		}
	    }
	    //Method to check if the game is tied
	    checkFull (table);

	}
	//If column is full
	else
	{
	    JOptionPane.showMessageDialog (null, "Error full column");
	}
    }


    //Method to check whether the array is full
    public void checkFull (int array[] [])
    {
	int counter = 0;

	//Loop to run through array to check if full
	for (int i = 0 ; i < array [0].length ; i++)
	{
	    if (array [0] [i] == 0)
	    {
		counter = 0;
	    }
	    else
	    {
		counter++;
	    }
	    if (counter >= 7) //If all arrays all full
	    {
		JOptionPane.showMessageDialog (null, "Tie Game"); //Tie game, delay and dispose
		delay ();
		dispose ();
	    }
	}

    }


    //Delay
    public void delay ()
    {
	//Dummy to waste time
	for (int i = 0 ; i < 10000000 ; i++)
	{
	    int x = 0;
	    Math.pow (x, 2);
	    Math.sqrt (x);
	    Math.pow (x, 2);
	}
    }



    //Method to check diagonal wins
    public String checkWin2 (int array[] [], String type, int num)
    {
	//Declaring variables
	int a;
	String win = "";
	int connect = 4;
	int count = 0;

	//Checking which chip for array
	if (type.equals ("even"))
	{
	    a = 2;
	}


	else
	{
	    a = 1;
	}


	//Loop to see if there is a diagonal win, loop goes through whole array
	for (int i = 0 ; i < array.length ; i++)
	{
	    count = 0;
	    for (int j = 0 ; j < array [0].length ; j++)
	    {
		count = 0;
		for (int x = 0 ; x < 5 ; x++)
		{
		    try
		    {
			if (array [i + x] [j + num * x] == a) //Checking if number in diagonal are equal to chip
			{
			    count++;

			    if (count >= connect) //When count is equal to 4
			    {
				win = "true";
				break;
			    }
			}
			else
			{
			    count = 0; //Resetting count to 0 if number in diagonal are not in sequence
			}
		    }
		    //Catching exception for out of bounds
		    catch (Exception e)
		    {
			count = 0;
		    }
		}

	    }
	}


	return win;
    }


    //Method to check vertical and horiz wins
    public String checkWin (int array[] [], String type, String winType)
    {
	//Declaring variables
	int a;
	String win = "";
	int connect = 4;
	int count = 0;

	//Checking whose turn
	if (type.equals ("even"))
	{
	    a = 2;
	}


	else
	{
	    a = 1;
	}


	//If checking horiz wins, loop to go through whole array
	if (winType.equals ("horiz"))
	{
	    for (int i = 0 ; i < array.length ; i++)
	    {
		count = 0;
		for (int j = 0 ; j < array [0].length ; j++)
		{
		    if (array [i] [j] == a) //If the number at location is equal to chip
		    {
			count++;
			if (count >= connect) //Once number is equal to four
			{
			    win = "true";
			    break;
			}
		    }
		    else
		    {
			count = 0; //If sequence is broken
		    }

		}
	    }
	}


	//If checking vertical wins, same as horiz except opposite loop check
	else
	{
	    for (int i = 0 ; i < array [0].length ; i++)
	    {
		count = 0;
		for (int j = 0 ; j < array.length ; j++)
		{
		    if (array [j] [i] == a)
		    {
			count++;
			if (count >= connect)
			{
			    win = "true";
			    break;
			}
		    }
		    else
		    {
			count = 0;
		    }

		}
	    }
	}


	return win;
    }


    //Method to check odd or even and show turn
    public String EvenOrOdd (int num)
    {
	String evenOrOdd = "";

	//Displaying black's turn
	if ((num % 2) == 0)
	{
	    evenOrOdd = "even";
	    lblRed.setVisible (false);
	    lblBlack.setVisible (true);
	}


	//Displaying red's turn
	else
	{
	    evenOrOdd = "odd";
	    lblRed.setVisible (true);
	    lblBlack.setVisible (false);
	}


	return evenOrOdd;
    }


    //Method to set number array to empty
    public static int[] [] SetZero (int array[] [])
    {
	//Filling grid with 0's (empty)
	for (int i = 0 ; i < array.length ; i++)
	{
	    for (int j = 0 ; j < array [0].length ; j++)
	    {
		array [i] [j] = 0;
	    }
	}


	return array;
    }


    //Method to add area clicked to Jlabel array and int array as well as screen
    public static int[] [] calculate (int array[] [], JLabel black[] [], JLabel red[] [], String num, int loc)
    {
	//Declaring variables
	int zero = -1;
	int a = 5;
	int col;

	//Checking which number to add to array
	if (num == "even")
	{
	    col = 2;
	}


	else
	{
	    col = 1;
	}


	//Checking if bottom is empty
	if (array [a] [loc] == 0)
	{
	    array [a] [loc] = col;

	    if (col == 1)
	    {
		black [a] [loc].setVisible (true); //Setting black to visible if bottom in array is empty
	    }
	    else
	    {
		red [a] [loc].setVisible (true); //Setting red to visible if bottom in array is empty
	    }
	}


	//If the bottom is not empty
	else
	{
	    do
	    {
		zero = zero + 1;

		if (array [zero] [loc] != 0)
		{
		    array [zero - 1] [loc] = col;

		    if (col == 1)
		    {
			black [zero - 1] [loc].setVisible (true);
		    }
		    else
		    {
			red [zero - 1] [loc].setVisible (true);
		    }
		}
	    }
	    //Loop to go through whole array to correctly insert column clicked onto array
	    while (array [zero] [loc] == 0);
	}


	return array;
    }


    //Not in use
    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }
} // SSConnect4_Game_Menu class
