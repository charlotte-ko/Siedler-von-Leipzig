package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;

public abstract class BaseGUI extends JFrame {
	
	//Alle Farben
	protected final static Color foresty = new Color(0, 153, 76);
	protected final static Color pine = new Color(0, 51, 25);
	protected final static Color brown = new Color(139, 87, 66);
	protected final static Color lightsalmon = new Color(205, 129, 98);
	
	protected final static Font big = new Font("Arial", Font.BOLD, 16);
	
	public BaseGUI(String title) {
		super(title);
	}
	
	protected void setWindowBase(JFrame frame, int width, int height, Component relative, Color bg) {
		
		frame.setSize(new Dimension(width, height));
		frame.setLocationRelativeTo(relative);
		frame.getContentPane().setBackground(bg);
	}
	
	protected ImageIcon createIcon(String png, int width, int height) {
		
		URL path = this.getClass().getResource("/resources/" + png);
		ImageIcon placeholder = new ImageIcon(path);
		Image scale = placeholder.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon finishedImage = new ImageIcon(scale);
		
		return finishedImage;
	};
	
	protected ImageIcon createSettlerIcon(String png, int width, int height) {
		
		URL path = this.getClass().getResource("/Settler/" + png);
		ImageIcon placeholder = new ImageIcon(path);
		Image scale = placeholder.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon finishedImage = new ImageIcon(scale);
		
		return finishedImage;
	}
}
