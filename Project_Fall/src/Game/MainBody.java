/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is the main body of the prorgam. In this class, all the game scenarios will
will be set up by modify preset labels, buttons, panels from the UI class. It will set up different
even handling for different buttons. Base on the player's choice, they will rather move into the
next scenarios or they will end their day(trip) early. If they pass all the scenarios, they will
get to the final ending. In the end, no matter what kinds of ending they will receive, their score
and name will be recored 
*********************************************************************************************/ 
package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Game.MainBody.TitleScreenButtonHandler;

import javax.imageio.*;

public class MainBody 
{
	protected TitleScreenButtonHandler tHandler = new TitleScreenButtonHandler(); //setting up the event for each buttons
	protected ChoiceHandler cHandler = new ChoiceHandler();
	protected ViewScoreButtonHandler sHandelr = new ViewScoreButtonHandler();
	protected BackToTitleHandler bHandelr = new BackToTitleHandler();
	Player user = new Player(); //creating a player object to record score
	UI myUI = new UI(); //creating a UI object to access UI class functions and variables
	public void start() 
	{
		
		myUI.setWindow(); //set up the window
		myUI.set_Starting_Page();
		myUI.scorePanel.setVisible(false); //this will hide the unnecessary panels 
		myUI.backtoTitlePanel.setVisible(false);
		myUI.scoreList.setVisible(false);
		myUI.myfont.PlaySound("audio/opening.wav"); //this will play the music for the starting page
		myUI.startButton.addActionListener(tHandler);
		myUI.ViewScoreButton.addActionListener(sHandelr);
		myUI.backToTitle.addActionListener(bHandelr);
		myUI.choice1.addActionListener(cHandler);
		myUI.choice2.addActionListener(cHandler);
		myUI.choice3.addActionListener(cHandler);
		user.getName();
		try {
			ScoreRead(); //load the score from local file
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void CreateGameScreen() //this is the first function to be executed after the player click start at the starting page
	{
		myUI.background.setVisible(false);
		myUI.title.setVisible(false); //this will hide the unnecessary panels 
		myUI.start.setVisible(false);	
		myUI.setMainText_and_ChoicePanel();
		myUI.set_choice_Buttons();	
		myUI.setPlayerInfoPanel();
		playerSetUp(); //in the end, it will call the function to setup the player info
	}
	
	public void playerSetUp()
	{
		myUI.hpNumLabel.setText(""+user.playerHP);
		myUI.scoreNumLabel.setText(""+user.score);
		home();	//it will jump to the first page	
	}
	
	
	public void home() // a function to set up the first page, "when the user wake up at home" 
	{
		myUI.myfont.clip.stop(); //stop the currently playing sound
		myUI.myfont.PlaySound("audio/1.wav"); //load sounds to read title
		myUI.myfont.clip.start(); //start the sound
		user.position = "home"; //recording the position
		myUI.mainText.setText("Beep Beep Beep!!! It is 7 am in the morning and you wake up, and you\nwant to leave your:"); //setting up the question
		myUI.choice1.setText("Bed"); //setting up the choice for the player to pick
		myUI.choice2.setText("Bird");
		myUI.choice3.setText("Ball");	
	}
	
	public void preparation()  // a function to set up the second page, "when the user do its preparation"
	{
		myUI.myfont.clip.stop();//stop the currently playing sound
		myUI.myfont.PlaySound("audio/2.wav"); //load sounds to read title
		myUI.myfont.clip.start(); //start the sound
		myUI.picture.setIcon(new ImageIcon("img/bag.gif")); //set up the picture to show
		user.position = "preparation"; //recording the position
		myUI.mainText.setText("You brush your teeth and have breakfast, and you want to take your:");//setting up the question
		myUI.choice1.setText("Bed");//setting up the choice for the player to pick
		myUI.choice2.setText("Bag");
		myUI.choice3.setText("Dog");
	}
	
	public void OutsideHouse() //a function to set up the third page, "when the user leave his or her house", and so on for the next couple function
	{
		myUI.myfont.clip.stop();
		myUI.myfont.PlaySound("audio/3.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/bus.gif")); //set up the picture to show
		user.position = "OutsideHouse";  //recording the position
		myUI.mainText.setText("When you leave your house, you see something coming to you:");
		myUI.choice1.setText("Bus"); //setting up the choice for the player to pick
		myUI.choice2.setText("Car");
		myUI.choice3.setText("Boat");
	}
	
	public void InSchool()
	{
		myUI.myfont.clip.stop();
		myUI.myfont.PlaySound("audio/4.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/math.gif"));
		user.position = "InSchool";
		myUI.mainText.setText("Alright! You are on the school bus and you arrive at the school later, and \nyou start your first class:");
		myUI.choice1.setText("Math");
		myUI.choice2.setText("Play");
		myUI.choice3.setText("English");
	}
	
	public void ClassPrepare()
	{
		myUI.myfont.clip.stop();
		myUI.myfont.PlaySound("audio/5.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/boook.gif"));
		user.position = "ClassPrepare";
		myUI.mainText.setText("In class, your teacher wants you to take out:");
		myUI.choice1.setText("Book");
		myUI.choice2.setText("Pen");
		myUI.choice3.setText("Ball");
	}
	
	public void ClassDone()
	{
		myUI.myfont.clip.stop();
		myUI.myfont.PlaySound("audio/6.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/egg.gif"));
		user.position = "ClassDone";
		myUI.mainText.setText("You had fun in Math and the rest of your classes. You start to eat lunch,\nand you are eating:");
		myUI.choice1.setText("Apple");
		myUI.choice2.setText("Carrot");
		myUI.choice3.setText("Egg");
	}
	
	public void AfterLunch()
	{
		myUI.myfont.PlaySound("audio/7.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/ball.gif"));
		user.position = "AfterLunch";
		myUI.mainText.setText("After lunch, you got some free time, and you decide to play:");
		myUI.choice1.setText("Ball");
		myUI.choice2.setText("Cat");
		myUI.choice3.setText("Eye");
	}
	
	public void GoHome()
	{
		myUI.myfont.clip.stop();
		myUI.myfont.PlaySound("audio/8.wav");
		myUI.myfont.clip.start();
		myUI.picture.setIcon(new ImageIcon("img/home.gif"));
		user.position = "GoHome";
		myUI.mainText.setText("Ohh, it has been a long time. The school is over, and you decide \nto go:");
		myUI.choice1.setText("Home");
		myUI.choice2.setText("Bar");
		myUI.choice3.setText("Airplane");
	}
	
	public void CorrectChoice() // this function will be called when the user select the correct choice
	{
		myUI.myfont.PlaySound("audio/cheer.wav"); //play a cheer sound
		myUI.myfont.clip.start(); 
		user.correct();//call a function from the player class to add score
	}
	
	public void WrongChoice()
	{
		myUI.myfont.PlaySound("audio/error.wav");  //play a sad sound
		myUI.myfont.clip.start();
		if(!user.checkHP()) //if the user's HP is not> 1
		{
			myUI.hpNumLabel.setText("0"); 
			Ending2(); //sent the user to a not really good ending
		}
		else //the user's HP is > 1
		{
			user.incorrect(); //call function from the player class to minus HP
			user.position = "Wrong"; 
			myUI.hpNumLabel.setText(""+user.playerHP); //update the hp number
			myUI.mainText.setText("Ops, you may want to think again! (HP -1)");
			myUI.choice1.setText("->");
			myUI.choice2.setText("");
			myUI.choice3.setText("");
		}
	}
	
	public void Ending1() //a good ending
	{
		
		myUI.myfont.PlaySound("audio/9.wav");
		myUI.myfont.clip.start();
		myUI.mainText.setText("Great job, you arrive home and go to sleep after dinner. You did \nvery well in school and your parents are proud of you.");
		myUI.choiceButtonPanel.setVisible(false);//stop showing the choice picking panel
		myUI.picture.setIcon(new ImageIcon("img/good_student.gif"));
		SaveScore(); //save the current score
	}
	
	public void Ending2() // a not really good ending
	{
		
		myUI.myfont.PlaySound("audio/11.wav");
		myUI.myfont.clip.start();
		myUI.choiceButtonPanel.setVisible(false);//stop showing the choice picking panel
		myUI.picturePanel.setBounds(125,120,525,600);
		myUI.mainText.setText("It seems like you got some troubles today, but you can always learn from your loss and try again.");
		myUI.picture.setIcon(new ImageIcon("img/fail.gif"));
		SaveScore(); //save the current score
	}
	
	public void back() // a function to put the user back to the current page if they make mistake
	{
		myUI.myfont.clip.stop(); //stop the ongoing music
		switch(user.current_position)
		{
			case"home":home();break;
			case"preparation": preparation();break;
			case"OutsideHouse":OutsideHouse();break;
			case"InSchool":InSchool();break;
			case"ClassPrepare":ClassPrepare();break;
			case"ClassDone":ClassDone();break;
			case"AfterLunch":AfterLunch();break;
			case"GoHome":GoHome();break;
			case"end2": Ending2();break;
			
		}
	}
	
	public void ShowScoreList() // it will be called when the user click show score button
	{
		myUI.background.setVisible(false);
		myUI.title.setVisible(false); // hide the unnecessary panel
		myUI.start.setVisible(false);
		myUI.scorePanel.setVisible(true);
		myUI.backtoTitlePanel.setVisible(true); // show the necessary panel
		myUI.scoreList.setVisible(true); 
	}
	
	public void setupScoreList() //this function is used to set up the score list
	{
		int rank = 1; //create a variable to record rank
		JLabel scoreListTitle = new JLabel("Rank                Name               Score"); 
		scoreListTitle.setFont(myUI.myfont.normal_font);
		myUI.scoreList.add(scoreListTitle);
		while(myUI.scorebase.size()>0 && rank < 6) //if the the score(Priority Queue) still has objects and there are less than 5 player to be displayed
		{
			
				Player out = myUI.scorebase.poll(); //getting the player with higherest score in the current queue
				JLabel ViewScoreTitleLabel = new JLabel(); //create five labels for displaying the score
				ViewScoreTitleLabel.setText(rank+"                         "+out.name + "                 "+out.score);
				ViewScoreTitleLabel.setFont(myUI.myfont.normal_font);
				myUI.scoreList.add(ViewScoreTitleLabel);
				rank++;
		}
		myUI.con.add(myUI.scoreList);//add the panel to the container
	}
	
	public void back_To_Title() //when the user click back button
	{
		myUI.background.setVisible(true);
		myUI.title.setVisible(true); 
		myUI.start.setVisible(true);//this will show the necessary panels 
		myUI.scorePanel.setVisible(false);
		myUI.backtoTitlePanel.setVisible(false); //this will hide the unnecessary panels 
		myUI.scoreList.setVisible(false);
	}

	public void SaveScore() // this function is used to save the current score to the file
	{
		try {
			
			FileWriter out = new FileWriter("scores.txt",true);//read file from the local file and not override it
			Player score_recording  = user; 
				out.write(score_recording.name); //write info to the file
				out.write('\n');
				out.write(Integer.toString(score_recording.score));
				out.write('\n');
				out.close(); //close the file
		}
		catch (IOException e) {
			System.out.println(e);
		}

	}
	
	public void ScoreRead() throws IOException //this function is used to read file and transfer the info to the Priority Queue
	{
		int currentScore;
		String line,line2;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
			while((line = reader.readLine())!=null) //when there is still lines
			{
				Player score_reading = new Player(); //create an object to hold info
				score_reading.name = line;
				line2 = reader.readLine();//read the second line
				currentScore = Integer.parseInt(line2);
				score_reading.score = currentScore;
				myUI.scorebase.add(score_reading);
			}
			//System.out.println(myUI.scorebase);
			setupScoreList(); //call the function to set up score list
			//System.out.println(myUI.scorebase.poll().name);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
	
	public class BackToTitleHandler implements ActionListener{ //for back button

		public void actionPerformed(ActionEvent e) {
			back_To_Title();
		}
		
	}
	
	public class TitleScreenButtonHandler implements ActionListener{ //for start button

		public void actionPerformed(ActionEvent e) {
			CreateGameScreen();	//the function to create the first page
			myUI.scorePanel.setVisible(false);
			myUI.backtoTitlePanel.setVisible(false);
			myUI.scoreList.setVisible(false);
		}
		
	}
	
	public class ViewScoreButtonHandler implements ActionListener{ //for the show score button

		public void actionPerformed(ActionEvent e) {
			ShowScoreList();	
		}
		
	}
	
	public class ChoiceHandler implements ActionListener{ //for jumping in between different pages of the game(scenario)

		public void actionPerformed(ActionEvent e) {
			String yourChoice = e.getActionCommand(); //get the choice signal from a button
			
			switch(user.position) //call different action base on the player position and choice they make
			{
				case "home":
					switch(yourChoice)
					{
						case "c1":{ preparation(); CorrectChoice(); myUI.scoreNumLabel.setText(""+user.score);}break; //right choice
						case "c2": WrongChoice(); user.current_position = "home"; break; //wrong choice
					    case "c3":WrongChoice();user.current_position = "home"; break;	//wrong choice		
					}break;
				case "preparation":
					switch(yourChoice)
					{
						case "c1":WrongChoice();user.current_position = "preparation";break; //wrong choice
						case "c2":{ OutsideHouse(); CorrectChoice();myUI.scoreNumLabel.setText(""+user.score);}break;  //right choice
					    case "c3":WrongChoice();user.current_position = "preparation";break;	//wrong choice		
					}break;
				case "OutsideHouse": //and so on
					switch(yourChoice)
					{
						case "c1":{ InSchool(); CorrectChoice();myUI.scoreNumLabel.setText(""+user.score);}break;
						case "c2":WrongChoice();user.current_position = "OutsideHouse";break;		
					    case "c3":WrongChoice();user.current_position = "OutsideHouse";break;			
					}break;
				case "InSchool":
					switch(yourChoice)
					{
					case "c1":{ ClassPrepare(); CorrectChoice(); myUI.scoreNumLabel.setText(""+user.score);}break;
					case "c2": WrongChoice(); user.current_position = "InSchool";break;
				    case "c3":WrongChoice();user.current_position = "InSchool";break;			
					}break;
				case "ClassPrepare":
					switch(yourChoice)
					{
						case "c1":{ ClassDone(); CorrectChoice();myUI.scoreNumLabel.setText(""+user.score);}break;
						case "c2":WrongChoice();user.current_position = "ClassPrepare";break;		
					    case "c3":WrongChoice();user.current_position = "ClassPrepare";break;			
					}break;
				case "ClassDone":
					switch(yourChoice)
					{
						case "c1":WrongChoice();user.current_position = "ClassDone";break;
						case "c2":WrongChoice();user.current_position = "ClassDone";break;
					    case "c3":{ AfterLunch(); CorrectChoice();myUI.scoreNumLabel.setText(""+user.score);}break;			
					}break;
				case "AfterLunch":
					switch(yourChoice)
					{
						case "c1":{ GoHome(); CorrectChoice();myUI.scoreNumLabel.setText(""+user.score);}break;
						case "c2":WrongChoice();user.current_position = "AfterLunch";break;
					    case "c3":WrongChoice();user.current_position = "AfterLunch";break;			
					}break;
				case "GoHome":
					switch(yourChoice)
					{
						case "c1":{  CorrectChoice(); myUI.scoreNumLabel.setText(""+user.score);Ending1(); }break; //reach the good ending
						case "c2":WrongChoice();user.current_position = "GoHome";break;		
					    case "c3":WrongChoice();user.current_position = "GoHome";break;			
					}break;
				case "Wrong": //if the user make a mistake
					switch(yourChoice)
					{
						case "c1": back();break;	//put the user back to the current page		
					}break;
			} 
		}
	}
}