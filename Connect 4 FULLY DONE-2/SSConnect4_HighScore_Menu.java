// The "SSConnect4Application" class.
/*
 Name: Samandeep Singh Virdi & Shahzada
 Date: January 2016
 Description: High Score Menu for the Program GUI
 */
//import all needed files
import java.awt.*;
import java.awt.event.*; //import for action listener
import javax.swing.*; //import javax
import java.awt.Font;
import java.io.*;
public class SSConnect4_HighScore_Menu extends JFrame implements ActionListener
{
    // variables for the program being declared
    JLabel lblTitle, subTitle;
    JTextArea lblHS;
    JTextField search;
    JButton btnBack, btnAlpha, btnHighest, btnSearch, btnClear;  //buttons made for the different menus
    Container frame; //contains the frame
    int index = 0;
    String name[];
    int time[];
    String list;
    public SSConnect4_HighScore_Menu () throws IOException
    {
	super ("SS Connect 4 High Score Menu"); // Set the frame's name
	setSize (600, 450); // Set the frame's size

	//to centre the window
	Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
	int w = this.getSize ().width;
	int h = this.getSize ().height;
	int x = (dim.width / 2) - 300;
	int y = (dim.height / 2) - 225;
	setLocation (x, y);
	frame = getContentPane ();

	//create the variables
	lblHS = new JTextArea ();
	search = new JTextField ();
	btnClear = new JButton ("Clear");
	btnSearch = new JButton ("Search");
	lblTitle = new JLabel ("High Scores");
	subTitle = new JLabel ("Search for a player");
	lblTitle.setFont (new Font ("Calibri", Font.BOLD, 30));
	btnBack = new JButton ("Back");
	btnAlpha = new JButton ("Sort Alphabetically");
	btnHighest = new JButton ("Sort For Highest Score"); //says highest however it will sort it from least to greatest

	//read the file and set the JTextArea as the high scores
	FileReader file = new FileReader ("HighScores.txt");
	BufferedReader input = new BufferedReader (file);
	index = Integer.parseInt (input.readLine ());
	name = new String [index];
	time = new int [index];
	list = "";
	for (int i = 0 ; i < name.length ; i++)
	{
	    name [i] = input.readLine ();
	    time [i] = Integer.parseInt (input.readLine ());
	}
	input.close ();
	list = "Name\tTime(s)\n";
	list = list + "--------\t-------\n";
	for (int i = 0 ; i < name.length ; i++)
	{
	    list = list + (name [i] + "\t" + "   " + time [i] + "s" + "\n");
	}
	lblHS.setText (list);
	lblHS.setEditable (false);
	//setting the fonts for the labels
	lblHS.setFont (new Font ("Calibri", Font.PLAIN, 25));
	btnAlpha.setFont (new Font ("Calibri", Font.PLAIN, 11));
	btnHighest.setFont (new Font ("Calibri", Font.PLAIN, 11));
	frame.setLayout (null);

	//set bounds for the buttons, variables etc.
	lblTitle.setBounds (200, 10, 500, 35);
	btnBack.setBounds (20, 355, 125, 40);
	lblHS.setBounds (120, 90, 275, 265);
	subTitle.setBounds (400, 260, 200, 50);
	btnSearch.setBounds (400, 330, 75, 40);
	btnClear.setBounds (480, 330, 75, 40);
	search.setBounds (400, 295, 154, 25);
	btnAlpha.setBounds (425, 150, 140, 40);
	btnHighest.setBounds (425, 200, 140, 40);

	//add the variables to the frame
	frame.add (btnAlpha);
	frame.add (subTitle);
	frame.add (btnClear);
	frame.add (btnSearch);
	frame.add (search);
	frame.add (btnHighest);
	frame.setBackground (Color.WHITE);
	frame.add (lblTitle);
	frame.add (btnBack);
	frame.add (lblHS);

	//add action listener to all buttons
	btnBack.addActionListener (this);
	btnAlpha.addActionListener (this);
	btnHighest.addActionListener (this);
	btnSearch.addActionListener (this);
	btnClear.addActionListener (this);
	setVisible (true); // Show the frame
    } // Constructor


    public void actionPerformed (ActionEvent e)  //method is created for the actions of the buttons
    { //if back button is pressed the window is disposed off and returned to the main menu
	if (e.getSource () == btnBack) //if the Play button is pressed
	{
	    dispose ();
	}
	// if the set alphabetically the sortAlphabentical method is called to sort the JTextArea alphabetically
	else if (e.getSource () == btnAlpha)
	{
	    sortAlphabetical (name, time);
	    //calls fileWrite method to write to output file
	    try
	    {
		fileWrite (name, time);
	    }
	    catch (Exception a)
	    {
	    }
	}
	//if the set highest score is pressed the sortHighest method is called to sort the JTextArea from least to greatest
	else if (e.getSource () == btnHighest)
	{
	    //method is called
	    sortHighest (name, time);
	    //list is set to the titles and list is set as the array
	    list = "Name\tTime(s)\n";
	    list = list + "--------\t-------\n";
	    for (int i = 0 ; i < name.length ; i++)
	    {
		list = list + (name [i] + "\t" + "   " + time [i] + "s" + "\n");
	    }
	    lblHS.setText (list);
	    //calls the fileWrite method to write to output file
	    try
	    {
		fileWrite (name, time);
	    }
	    catch (Exception a)
	    {
	    }
	}

	else if (e.getSource () == btnClear)
	{
	    //set the search field and jtextarea to ""
	    search.setText ("");
	    lblHS.setText ("");
	    //call the readfileandsetarea to set the JTextArea as the titles
	    try
	    {
		name = readFileAndSetArea (name);
		time = readFileAndSetAreaForTime (time);

	    }
	    catch (Exception a)
	    {
	    }

	}
	else if (e.getSource () == btnSearch)
	{
	    //make the name string equal the word that the user entered
	    String name1 = "";
	    lblHS.setText ("");
	    String info = "";
	    name1 = search.getText ();
	    //make loc equal -1
	    int loc = -1;
	    //call the findSearch method to see if the search is valid
	    loc = findInfo (name1, name);
	    //if search is Invalid
	    if (loc < 0)
	    { //the JTextArea is set as Invalid Search
		lblHS.setText ("Invalid Search! \n Player Not Found");
	    }
	    else //if valid
	    { //use the place returned by the method and use that place to output the instructions for the program
		info = name [loc] + " beat his/her opponent in ";
		info = info + time [loc] + " seconds";
		lblHS.setText (info);
		lblHS.setEditable (false);
		lblHS.setLineWrap (true);
		lblHS.setWrapStyleWord (true);
	    }
	}
    }


    //method for the searching
    public static int findInfo (String name, String array[])
    { //make place = -1
	int place = -1;

	//for statement for the array
	for (int i = 0 ; i < array.length ; i++)
	{ //if statement that checks if the entered word is equal to anything in the array
	    if (name.equalsIgnoreCase (array [i]) == true)
	    {
		place = i; //make place equal that place
	    }

	}
	return place; //return that place
    }


    //method to read the titles file and set it to the JTextArea
    public String[] readFileAndSetArea (String array1[]) throws IOException
    {

	//read the file and set the JTextArea as the high scores
	FileReader file = new FileReader ("HighScores.txt");
	BufferedReader input = new BufferedReader (file);
	index = Integer.parseInt (input.readLine ());
	name = new String [index];
	time = new int [index];
	list = "";
	for (int i = 0 ; i < name.length ; i++)
	{
	    name [i] = input.readLine ();
	    time [i] = Integer.parseInt (input.readLine ());
	}
	input.close ();
	list = "Name\tTime(s)\n";
	list = list + "--------\t-------\n";
	for (int i = 0 ; i < name.length ; i++)
	{
	    list = list + (name [i] + "\t" + "   " + time [i] + "s" + "\n");
	}
	lblHS.setText (list);
	lblHS.setEditable (false);

	//set the text area to list
	lblHS.setText (list);
	lblHS.setEditable (false);
	lblHS.setLineWrap (true);
	lblHS.setWrapStyleWord (true);

	//make list equal nothing again
	list = "";
	//return array1
	return array1;

    }


    public int[] readFileAndSetAreaForTime (int array1[]) throws IOException
    {

	//read the file and set the JTextArea as the high scores
	FileReader file = new FileReader ("HighScores.txt");
	BufferedReader input = new BufferedReader (file);
	index = Integer.parseInt (input.readLine ());
	name = new String [index];
	time = new int [index];
	list = "";
	for (int i = 0 ; i < name.length ; i++)
	{
	    name [i] = input.readLine ();
	    time [i] = Integer.parseInt (input.readLine ());
	}
	input.close ();
	list = "Name\tTime(s)\n";
	list = list + "--------\t-------\n";
	for (int i = 0 ; i < name.length ; i++)
	{
	    list = list + (name [i] + "\t" + "   " + time [i] + "s" + "\n");
	}
	lblHS.setText (list);
	lblHS.setEditable (false);

	//set the text area to list
	lblHS.setText (list);
	lblHS.setEditable (false);
	lblHS.setLineWrap (true);
	lblHS.setWrapStyleWord (true);

	//make list equal nothing again
	list = "";
	//return array1
	return array1;

    }


    //sortAlphabetial takes in the arrays and sorts it
    public void sortAlphabetical (String array[], int array2[])  //method for sorting the array in Alphabetical order
    {
	//for statement for the array
	for (int i = 0 ; i < array.length - 1 ; i++)
	{ //for statement for the sorting process to be looped
	    for (int j = 0 ; j < array.length - 1 ; j++)
	    { // if to compare the words and then set it alphabetically if not already set
		if (array [j + 1].compareTo (array [j]) < 0) //compared too used for comparing strings
		{
		    int temp1 = array2 [j];
		    array2 [j] = array2 [j + 1];
		    array2 [j + 1] = temp1;
		    String temp2 = array [j];
		    array [j] = array [j + 1];
		    array [j + 1] = temp2;

		}
	    }
	}
	//make list equal the titles and make list equal the array
	list = "Name\tTime(s)\n";
	list = list + "--------\t-------\n";
	for (int i = 0 ; i < name.length ; i++)
	{
	    list = list + (name [i] + "\t" + "   " + time [i] + "s" + "\n");
	}
	//set the text area as list
	lblHS.setText (list);

    }


    //method for sorting the scores least to greatest
    public void sortHighest (String array[], int array2[])
    { //for statement for the array
	for (int i = 0 ; i < array.length ; i++)
	{ //for statement for looping the sort
	    for (int j = 0 ; j < array.length - 1 ; j++)
	    { //check if the numbers are sorted if not it sorts them
		if (array2 [j] > array2 [j + 1])
		{
		    int scoreTemp = array2 [j];
		    array2 [j] = array2 [j + 1];
		    array2 [j + 1] = scoreTemp;
		    String nameTemp = array [j];
		    array [j] = array [j + 1];
		    array [j + 1] = nameTemp;
		}

	    }


	}

    }


    //method for the fileWrite to output the arrays into a file
    public void fileWrite (String array[], int array2[]) throws IOException
    {
	//file writer for the array to be outputed to the file
	FileWriter fileOutput = new FileWriter ("HighScores Output.txt");
	PrintWriter output = new PrintWriter (fileOutput);

	//for statement for the size of array
	for (int i = 0 ; i < array.length ; i++)
	{
	    //output the arrays
	    output.println (array [i]);
	    output.println (array2 [i]);
	}
	//close the file
	output.close ();
    }


    public static void main (String[] args) throws IOException
    {
	new SSConnect4_HighScore_Menu ();
    } // main method
} // SSConnect4_HighScore_Menu class
