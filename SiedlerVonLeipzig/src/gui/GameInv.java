package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameInv extends JFrame {
	
	public GameInv() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setTitle("Inventar");
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
}
