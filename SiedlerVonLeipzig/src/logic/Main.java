package logic;

import java.util.ArrayList;
import javax.swing.JFrame;
import gui.Startmenu;

class Main {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("AOPSettlers");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();//Window get resized to preferred size from gamePanel
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}

