// The "SSConnect4Application" class.
/*
 Name: Samandeep Singh Virdi & Shahzada
 Date: January 2016
 Description: Help Menu for the Program GUI
 */
import java.awt.*;
import java.awt.event.*; //import for action listener
import javax.swing.*; //import javax
import java.awt.Font;
import java.io.*;

public class SSConnect4_Help_Menu extends JFrame implements ActionListener
{
    //declare the variables for reading the file, for the titles, for buttons, the search field and the textArea
    String title[], list = "", instructions[];
    int index;
    JLabel lblTitle, subTitle, lblQuestion;      //label made for title
    JButton btnBack, btnSearch, btnClear; //buttons made for the different menus
    JTextArea helpMenu;
    JTextField search;
    Container frame; //contains the frame

    public SSConnect4_Help_Menu () throws IOException
    {
	super ("SS Connect 4 Help Menu"); // Set the frame's name
	setSize (600, 450); // Set the frame's size

	//to centre the window
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	int w = this.getSize ().width;
	int h = this.getSize ().height;
	int x = (dim.width / 2) - 300;
	int y = (dim.height / 2) - 225;
	setLocation (x, y);

	//create the variables
	frame = getContentPane ();
	helpMenu = new JTextArea ();
	title = readFileAndSetArea (title);
	instructions = readInstruction (instructions);
	search = new JTextField ();
	btnSearch = new JButton ("Search");
	subTitle = new JLabel ("Search Title For Explaination");
	lblQuestion = new JLabel (new ImageIcon ("question.jpg"));
	subTitle.setFont (new Font ("Times New Roman", Font.PLAIN, 18));
	helpMenu.setFont (new Font ("Times New Roman", Font.PLAIN, 18));
	lblTitle = new JLabel ("Help Menu");
	lblTitle.setFont (new Font ("Calibri", Font.BOLD, 30));
	subTitle.setFont (new Font ("Calibri", Font.PLAIN, 15));
	btnBack = new JButton ("Back");
	btnClear = new JButton ("Clear");

	//set the bounds for all of the variables
	frame.setLayout (null);
	btnClear.setBounds (472, 180, 75, 40);
	search.setBounds (393, 150, 154, 25);
	helpMenu.setBounds (75, 135, 250, 200);
	lblTitle.setBounds (200, 10, 500, 35);
	btnBack.setBounds (20, 355, 125, 40);
	subTitle.setBounds (380, 110, 200, 50);
	btnSearch.setBounds (393, 180, 75, 40);
	lblQuestion.setBounds (390, 215, 200, 200);
	frame.setBackground (Color.WHITE);

	//add all variables to the frame
	frame.add (lblTitle);
	frame.add (btnBack);
	frame.add (helpMenu);
	frame.add (search);
	frame.add (btnSearch);
	frame.add (subTitle);
	frame.add (lblQuestion);
	frame.add (btnClear);

	//add button listener for all buttons
	btnBack.addActionListener (this);
	btnSearch.addActionListener (this);
	btnClear.addActionListener (this);

	setVisible (true); // Show the frame
    } // Constructor

    
    //method to read the instruction file and to fill an array
    public String[] readInstruction (String array[]) throws IOException 
    {   
	//open the instructions file 
	FileReader file = new FileReader ("Help Menu Instructions.txt");
	BufferedReader input = new BufferedReader (file);
	//make the array the size of index (taken from readFileAndSetArea method
	array = new String [index];
	//fill the array with all of the contents of the text file
	for (int i = 0 ; i < array.length ; i++)
	{
	    array [i] = input.readLine ();
	}
	//close the file
	input.close ();
	
	//return the filled array
	return array;
    }
    
    //method to read the titles file and set it to the JTextArea
    public String[] readFileAndSetArea (String array1[]) throws IOException
    {   
	//open the file
	FileReader file = new FileReader ("Help Menu Titles.txt");
	BufferedReader input = new BufferedReader (file);
	
	//read the firtst line to get the size of array
	index = Integer.parseInt (input.readLine ());
	
	//set the size of the array
	array1 = new String [index];
	
	//fill the array with the contents of with file
	for (int i = 0 ; i < array1.length ; i++)
	{
	    array1 [i] = input.readLine ();
	}
	//close the file
	input.close ();
	
	//use list to set the JTextArea
	list = "Titles: " + "\n";
	//make list equal all of the contents of the array
	for (int i = 0 ; i < array1.length ; i++)
	{
	    list = list + (array1 [i] + "\n");
	}
	
	//set the text area to list
	helpMenu.setText (list);
	helpMenu.setEditable (false);
	helpMenu.setLineWrap (true);
	helpMenu.setWrapStyleWord (true);
	
	//make list equal nothing again
	list = "";
	//return array1
	return array1;

    }
    
    //method for the searching
    public static int findInfo (String name, String array[])
    {   //make place = -1
	int place = -1;
	
	//for statement for the array
	for (int i = 0 ; i < array.length ; i++)
	{   //if statement that checks if the entered word is equal to anything in the array
	    if (name.equalsIgnoreCase (array [i]) == true)
	    {
		place = i;//make place equal that place
	    }

	}
	return place;//return that place
    }


    public void actionPerformed (ActionEvent e)  //method is created for the actions of the buttons
    {   
	//if user presses the back button
	if (e.getSource () == btnBack)
	{   //dispose the windows and go back to application
	    dispose ();
	}
	//if the user presses the clear button
	else if (e.getSource () == btnClear)
	{   
	    //set the search field and jtextarea to ""
	    search.setText ("");
	    helpMenu.setText ("");
	    //call the readfileandsetarea to set the JTextArea as the titles
	    try
	    {
		title = readFileAndSetArea (title);
	    }
	    catch (Exception a)
	    {
	    }
	}
	//if the user presses the search button
	else if (e.getSource () == btnSearch)
	{   
	    //make the name string equal the word that the user entered
	    String name = "";
	    helpMenu.setText ("");
	    String info = "";
	    name = search.getText ();
	    //make loc equal -1
	    int loc = -1;
	    //call the findSearch method to see if the search is valid
	    loc = findInfo (name, title);
	    //if search is Invalid
	    if (loc < 0)
	    {   //the JTextArea is set as Invalid Search
		helpMenu.setText ("Invalid Search! \n Please Search Again: \n How To Play \n Rules \n Creators");
	    }
	    else //if valid
	    {   //use the place returned by the method and use that place to output the instructions for the program
		info = title [loc] + "\n";
		info = info + instructions [loc] + "\n";
		helpMenu.setText (info);
		helpMenu.setEditable (false);
		helpMenu.setLineWrap (true);
		helpMenu.setWrapStyleWord (true);
	    }
	}

    }


    public static void main (String[] args)
    {
    } // main method
} // SSConnect4_Help_Menu class
