/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is a class storing the current player information such as HP and score. It
also has method to add score and decrease the user HP if needed.
*********************************************************************************************/ 
package Game;

import java.io.FileWriter;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

public class Player
{
	protected int playerHP, score;
	protected String position, current_position,name; //the name is used to store the current player name 
	//the position is used to store the current player position in the game, and the current_posistion is used to
	//track the current position of the player if they make mistake.
	
	public Player()
	{
		playerHP = 5; //by default, a player start with 5 HP and 0 score
		score = 0;
	}
	
	public void correct() //if correct choice is selected
	{
		score +=5;
	}
	
	public void incorrect()
	{
		if(playerHP>0)
		{
			playerHP--;
		}
	}
	
	public boolean checkHP()
	{
		if(playerHP > 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void getName() //to get the user name
	{
		name = JOptionPane.showInputDialog(null,"Enter your name: (The score will not be saved without name)");
	}
	
	
}