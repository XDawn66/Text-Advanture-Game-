/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is a app driver class to call the app building function from other class
*********************************************************************************************/ 
import java.io.FileWriter;
import java.io.IOException;

import Game.*;

public class AppRunner
{
	public static void main(String arg[])
	{
		MainBody game = new MainBody(); //create the app building function
		game.start(); //start the app
	}
}    