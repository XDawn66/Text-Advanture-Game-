/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is a compare class that uses for comparing different player base on their
score. It will be used to sort player in the Priority queue, which is used to store all player
info
*********************************************************************************************/ 
package Game;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Player>
{
	public int compare(Player one, Player two)
	{
		if(one.score < two.score) //if the player one has lower score compare to the player two
		{
			return 1;
		}
		else if(one.score > two.score) //if the player two has lower score compare to the player one
		{
			return -1;
		}
		return 0;
	}
}