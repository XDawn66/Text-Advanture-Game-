/****************************************************************************************** 
Name: Zhenyu Jiang 
Course: CS170-01 
Lab #: Fall Project
Submission Date: 10pm, Thu(12/08)
Description:  This is a class storing different type of font and a method to generate sound
*********************************************************************************************/ 
package Game;

import java.awt.Font;
import java.awt.MediaTracker;
import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Fonts_and_Music
{
	protected Font title_font = new Font("Times New Roman", Font.PLAIN,40); //Three different types of font for different situation
	protected Font normal_font = new Font("Times New Roman", Font.PLAIN,30);
	protected Font normal_font2 = new Font("Times New Roman", Font.PLAIN,20);
	protected Clip clip;

	public void PlaySound(String soundLocation) {
		
		   try {
			   File file = new File(soundLocation); //read a file from the given location
			   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);//input audio
			   clip = AudioSystem.getClip( );
			   clip.open(audioInputStream);
			   clip.start();//start playing the music
			   }
			   catch(Exception e){
			     System.out.println("Error with playing sound.");
			     e.printStackTrace( ); //print the error message
			   }
			 }
}