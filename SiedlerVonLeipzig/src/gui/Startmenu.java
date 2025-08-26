package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Startmenu extends JFrame{

	private static final long serialVersionUID = 1L;
	JButton button1 = new JButton("Neues Spiel");
	JButton button2 = new JButton("Weiterspielen");
	JButton button3 = new JButton("Debug Modus");
	JButton button4 = new JButton("Einstellungen");
	JButton button5 = new JButton("Beenden");
	
	Color foresty = new Color(0, 153, 76);
	Color pine = new Color(0, 51, 25);
	
	public Startmenu() {
		
		super("Startmenue");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(42, 158, 58));
		this.setLayout(null);
		
		button1.setBounds(100, 250, 600, 50);
		button1.setBackground(pine);
		button1.setForeground(foresty);
		button1.setOpaque(true);
		button1.setBorderPainted(false);
		this.add(button1);
		
		button2.setBounds(100, 325, 600, 50);
		button2.setBackground(pine);
		button2.setForeground(foresty);
		button2.setOpaque(true);
		button2.setBorderPainted(false);
		this.add(button2);
		
		button3.setBounds(100, 400, 600, 50);
		button3.setBackground(pine);
		button3.setForeground(foresty);
		button3.setOpaque(true);
		button3.setBorderPainted(false);
		this.add(button3);
		
		button4.setBounds(100, 475, 290, 50);
		button4.setBackground(pine);
		button4.setForeground(foresty);
		button4.setOpaque(true);
		button4.setBorderPainted(false);
		this.add(button4);
		
		button5.setBounds(410, 475, 290, 50);
		button5.setBackground(pine);
		button5.setForeground(foresty);
		button5.setOpaque(true);
		button5.setBorderPainted(false);
		this.add(button5);
		
		setVisible(true);
	}
}
