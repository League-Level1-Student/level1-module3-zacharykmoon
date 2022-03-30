package _03_jars._2_jukebox;
/*
 *    Copyright (c) The League of Amazing Programmers 2013-2019
 *    Level 1
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.EventObject;

import javax.print.DocFlavor.STRING;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*   If you don't have javazoom.jar in your project, you can download it from here: http://bit.ly/javazoom
 *   Right click your project and add it as a JAR (Under Java Build Path > Libraries).*/
import java.awt.geom.Area;

public class Jukebox<Song> implements Runnable, ActionListener {
JButton button1 = new JButton();
JButton button2 = new JButton();
JButton button3 = new JButton();
JButton button4 = new JButton();
JButton pause = new JButton ();
Song grasswalk = new Song("20. Grasswalk IN-GAME.mp3");
Song Mario = new Song("01 - Super Mario Bros.mp3");
Song Minecraft = new Song("08. Minecraft.mp3");
Song Brainiac = new Song("14. Brainiac Maniac.mp3");


    public void run() {

		// 1. Find an mp3 on your computer or on the Internet.
		// 2. Create a Song object for that mp3

		// 3. Play the Song

		/*
		 * 4. Create a user interface for your Jukebox so that the user can to
		 * choose which song to play. You can use can use a different button for
		 * each song, or a picture of the album cover. When the button or album
		 * cover is clicked, stop the currently playing song, and play the one
		 * that was selected.
		 */
    	
    	JFrame frame = new JFrame();
        JPanel song = new  JPanel();
        frame.add(song);
        frame.setVisible(true);
        button1.setSize(100,100);
        button2.setSize(100,100);
        button3.setSize(100,100);
        button4.setSize(100,100);
        pause.setSize(100,100);
        button1.setText("grasswalk Plants VS Zombies");
        button2.setText("Super Mario Bros");
        button3.setText("Minecraft");
        button4.setText("Brainiac Maniac");
        pause.setText("Pause");
        frame.setTitle("Video Game soundtracks");
        song.add(button1);
        song.add(button2);
        song.add(button3);
        song.add(button4);
        song.add(pause);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        frame.pack();
    	
    }
    
    
	/* Use this method to add album covers to your Panel. */
	private JLabel loadImage(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		 
		// TODO Auto-generated method stub
		
		JButton buttonclicked = (JButton) arg0.getSource();
		if(arg0.getSource()== button1) {
			grasswalk.play();
			Mario.stop();
			Minecraft.stop();
			Brainiac.stop();
		}
	 
		if(arg0.getSource()== button2) {
			Mario.play();
			grasswalk.stop();
			Minecraft.stop();
			Brainiac.stop();
		}
		 
		if(arg0.getSource()== button3) {
			Minecraft.play();
			grasswalk.stop();
			Mario.stop();
			Brainiac.stop();
		}
		if(arg0.getSource()== button4) {
			Brainiac.play();
			Minecraft.stop();
			grasswalk.stop();
			Mario.stop();
		}

	}


class Song {

	private int duration;
	private String songAddress;
	private AdvancedPlayer mp3Player;
	private InputStream songStream;

	/**
	 * Songs can be constructed from files on your computer or Internet
	 * addresses.
	 * 
	 * Examples: <code> 
	 * 		new Song("everywhere.mp3"); 	//from default package 
	 * 		new Song("/Users/joonspoon/music/Vampire Weekend - Modern Vampires of the City/03 Step.mp3"); 
	 * 		new	Song("http://freedownloads.last.fm/download/569264057/Get%2BGot.mp3"); 
	 * </code>
	 */
	public Song(String songAddress) {
		this.songAddress = songAddress;
	}

	public void play() {
		loadFile();
		if (songStream != null) {
			loadPlayer();
			startSong();
		} else
			System.err.println("Unable to load file: " + songAddress);
	}

	public void setDuration(int seconds) {
		this.duration = seconds * 100;
	}

	public void stop() {
		if (mp3Player != null)
			mp3Player.close();
	}

	private void startSong() {
		Thread t = new Thread() {
			public void run() {
				try {
					if (duration > 0)
						mp3Player.play(duration);
					else
						mp3Player.play();
				} catch (Exception e) {
				}
			}
		};
		t.start();
	}

	private void loadPlayer() {
		try {
			this.mp3Player = new AdvancedPlayer(songStream);
		} catch (Exception e) {
		}
	}

	private void loadFile() {
		if (songAddress.contains("http"))
			this.songStream = loadStreamFromInternet();
		else
			this.songStream = loadStreamFromComputer();
	}

	private InputStream loadStreamFromInternet() {
		try {
			return new URL(songAddress).openStream();
		} catch (Exception e) {
			return null;
		}
	}

	private InputStream loadStreamFromComputer() {
		try {
			return new FileInputStream(songAddress);
		} catch (FileNotFoundException e) {
			return this.getClass().getResourceAsStream(songAddress);
		}
	}
}
}
