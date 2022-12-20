/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is a class to create necessary panels, labels, and buttons for all pages
in the program. Those components will be preset in this class so that they can be used in the
MainBody later.
*********************************************************************************************/ 
package Game;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

import Game.MainBody.TitleScreenButtonHandler;

public class UI
{
	protected JFrame main_window;
	protected Container con;
	protected JPanel title,start, MainTextPanel,choiceButtonPanel,playerPanel,background,picturePanel, scorePanel,backtoTitlePanel,scoreList;
	protected JLabel title_name, hpLabel, hpNumLabel, scoreLabel, scoreNumLabel,backgroundimg, picture;
	protected JLabel SCORELabel, ViewScoreTitleLabel;
	protected JButton startButton,choice1, choice2, choice3,ViewScoreButton,backToTitle; //They are Labels, buttons, and panels that used for building the window
	protected JTextArea mainText;
	protected ImageIcon choiceimg;
	protected PriorityQueue<Player> scorebase = new PriorityQueue<Player>(5, new ScoreComparator()); //this is used to store all player info
	 
	Fonts_and_Music myfont = new Fonts_and_Music(); //create a object from Fonts_and_Music to use functions.
	
	public UI()
	{
		main_window = new JFrame(); //Initialize panel, label, and buttons
		title = new JPanel();
		background = new JPanel();
		start = new JPanel();
		scorePanel = new JPanel();
		mainText = new JTextArea("This is the main text are...");
		choiceButtonPanel = new JPanel();
		choice1 = new JButton("Choice 1");	
		choice2 = new JButton("Choice 2");
		choice3 = new JButton("Choice 3");
		playerPanel = new JPanel();
		hpLabel = new JLabel("HP:");
		hpNumLabel = new JLabel();
		scoreLabel = new JLabel("Score:");
		scoreNumLabel = new JLabel();
		ViewScoreButton = new JButton("VIEW SCORE");
		backToTitle = new JButton("BACK"); 
		ViewScoreTitleLabel = new JLabel("Ranking");
		backtoTitlePanel = new JPanel(); 
		scoreList = new JPanel();
		
	}
	
	public void setWindow()
	{
		main_window.setSize(800,600); //setting the window size 
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //you can close the window by clicking X
		main_window.getContentPane().setBackground(Color.white); //set image maybe
		main_window.setLayout(null); //getting rid of the default layout
		main_window.setVisible(true); //make the window visible
		main_window.setResizable(false); //disable the user to expand the window
		con = main_window.getContentPane(); //add the window to the container.
	}
	
	public void set_Starting_Page() //this function will set the starting page and the view score page
	{
		title.setBounds(100,100,600,100); //setting up the title label
		title.setBackground(Color.blue);
		
		background.setBounds(100,125,600,600);//setting up the panel to hold the image at the starting page
		backgroundimg = new JLabel(new ImageIcon("img/cover.gif")); //load the img to the label
		backgroundimg.setBounds(0,0,575,494);
		background.setBackground(Color.white);
		background.add(backgroundimg); //adding the label to panel
		
		
		title_name = new JLabel("MY DAY AT SCHOOL"); //setting the text of the title
		title_name.setForeground(Color.yellow);
		title_name.setFont(myfont.title_font); //setting the font
		
		start.setBounds(300, 400, 200, 200);
		start.setBackground(Color.white);
		
		startButton = new JButton("START"); //creating a start button for the starting page
		startButton.setBackground(Color.black);
		startButton.setBackground(Color.white);
		startButton.setFont(myfont.normal_font);
		startButton.setFocusPainted(false); //remove the inner edge of the button 
		
		ViewScoreButton.setBackground(Color.black);
		ViewScoreButton.setBackground(Color.white);
		ViewScoreButton.setFont(myfont.normal_font);
		ViewScoreButton.setFocusPainted(false);  //remove the inner edge of the button 
		
		title.add(title_name); //adding labels to their panels
		start.add(startButton);
		start.add(ViewScoreButton);
		
		
		ViewScoreTitleLabel.setBackground(Color.white); //setting the label to display the title in the show score page
		ViewScoreTitleLabel.setBounds(200,50,400,100);
		ViewScoreTitleLabel.setFont(myfont.normal_font);
		
		scorePanel.setBounds(0,0,800,70);
		scorePanel.setBackground(Color.yellow);
		
		backtoTitlePanel.setBounds(300,500,200,100); //setting the panel to hold back to title button
		backtoTitlePanel.setBackground(Color.white);
		backToTitle.setBackground(Color.white);
				
		scoreList.setBackground(Color.cyan);
		scoreList.setBounds(100,100,600,400);
		scoreList.setLayout(new GridLayout(6,1));
			
		scorePanel.add(ViewScoreTitleLabel);
		backtoTitlePanel.add(backToTitle);
		
		con.add(start); //adding created panels to the container to show them in the window
		con.add(title);
		con.add(background);
		con.add(scorePanel);
		con.add(backtoTitlePanel);
		
		
	}
	
	public void setMainText_and_ChoicePanel() //this function will set up the maintext and choice panel
	{
		MainTextPanel = new JPanel();
		MainTextPanel.setBounds(100,65,600,215);
		MainTextPanel.setBackground(Color.white);
		
		mainText.setBounds(100,65,600,215);
		mainText.setBackground(Color.white);
		mainText.setForeground(Color.black);
		mainText.setFont(myfont.normal_font2);
		mainText.setLineWrap(true); //adjust the text if it is too long
		mainText.setHighlighter(null);//disable user from typing the textarea
		mainText.setEditable(false);
		
		choiceButtonPanel.setBounds(150,400,400,100);
		choiceButtonPanel.setBackground(Color.orange);
		choiceButtonPanel.setLayout(new GridLayout(3,1));
		MainTextPanel.add(mainText);
		
		picturePanel = new JPanel();
		picturePanel.setBounds(125,120,525,270);
		picturePanel.setBackground(Color.white);
		picture = new JLabel(new ImageIcon("img/bed.gif"));
		picturePanel.add(picture);
		
		
		con.add(choiceButtonPanel);
		con.add(picturePanel);
		con.add(MainTextPanel); //this will add those panels to the container
		
	}
	
	public void set_choice_Buttons() //this function is used to set up three buttons for the answer choosing 
	{
		choice1.setBackground(Color.white); 
		choice1.setForeground(Color.black);
		choice1.setFont(myfont.normal_font);
		choice1.setActionCommand("c1"); //setting up the button's command signal so that we can read it later 
		
		choice2.setBackground(Color.white);
		choice2.setForeground(Color.black);
		choice2.setFont(myfont.normal_font);
		choice2.setActionCommand("c2"); //setting up the button's command signal so that we can read it later 
		
		choice3.setBackground(Color.white);
		choice3.setForeground(Color.black);
		choice3.setFont(myfont.normal_font);
		choice3.setActionCommand("c3"); //setting up the button's command signal so that we can read it later 
		
		choice1.setFocusPainted(false);
		choice2.setFocusPainted(false);
		choice3.setFocusPainted(false);
		
		choiceButtonPanel.add(choice1);
		choiceButtonPanel.add(choice2);
		choiceButtonPanel.add(choice3);
	}
	
	public void setPlayerInfoPanel() //this function is used to set up the HP and Score BAR on the top
	{
		
		playerPanel.setBounds(100, 15, 600, 50);
		playerPanel.setBackground(Color.blue);
		playerPanel.setLayout(new GridLayout(1,4));
		
		hpLabel.setFont(myfont.normal_font);
		hpLabel.setForeground(Color.white);
		playerPanel.add(hpLabel);
		
		hpNumLabel.setFont(myfont.normal_font);
		hpNumLabel.setForeground(Color.white);
		playerPanel.add(hpNumLabel);
		
		scoreLabel.setFont(myfont.normal_font);
		scoreLabel.setForeground(Color.white);
		playerPanel.add(scoreLabel);
		
		scoreNumLabel.setFont(myfont.normal_font);
		scoreNumLabel.setForeground(Color.white);
		playerPanel.add(scoreNumLabel);
		
		con.add(playerPanel);
		
	}
	
	public void drawString(Graphics g) //try to use graphic to draw string
	{
		String goodbye = "hello";
		g.drawString(goodbye, 100, 200);
	}

	

	
}