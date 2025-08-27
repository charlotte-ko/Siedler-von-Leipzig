package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		
		setButtons();
		
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		
		setVisible(true);
	}
	
	private void openNewGame() {
		
		JFrame NewFrame = new JFrame("Neues Spiel");
		NewFrame.setSize(600,200);
		NewFrame.setLocationRelativeTo(null);
		NewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		NewFrame.setLayout(new GridBagLayout());
		
		JLabel name = new JLabel("Name:");
		JTextField field_name = new JTextField(20);
		JButton confirm = new JButton("Bestätigen");
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(5, 5, 5, 5);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		axis.gridy = 0;
		axis.gridx = 0;
		axis.weightx = 0.2;
		NewFrame.add(name, axis);
		
		axis.gridx = 1;
		axis.weightx = 0.8;
		//axis.ipady = 10;
		field_name.setPreferredSize(new Dimension(100, 30));
		NewFrame.add(field_name, axis);
		
		axis.gridx = 1;
		axis.gridy = 1;
		//axis.gridwidth = 2;
		//axis.weightx = 1.0;
		NewFrame.add(confirm, axis);
	
		NewFrame.setVisible(true);
	}
	
	private void setButtons() {
		
		button1.setBounds(100, 250, 600, 50);
		button1.setBackground(pine);
		button1.setForeground(foresty);
		button1.setOpaque(true);
		button1.setBorderPainted(false);
		button1.addActionListener(e -> openNewGame());
		
		button2.setBounds(100, 325, 600, 50);
		button2.setBackground(pine);
		button2.setForeground(foresty);
		button2.setOpaque(true);
		button2.setBorderPainted(false);
		
		button3.setBounds(100, 400, 600, 50);
		button3.setBackground(pine);
		button3.setForeground(foresty);
		button3.setOpaque(true);
		button3.setBorderPainted(false);
		
		button4.setBounds(100, 475, 290, 50);
		button4.setBackground(pine);
		button4.setForeground(foresty);
		button4.setOpaque(true);
		button4.setBorderPainted(false);
		
		button5.setBounds(410, 475, 290, 50);
		button5.setBackground(pine);
		button5.setForeground(foresty);
		button5.setOpaque(true);
		button5.setBorderPainted(false);
		this.add(button5);
	}
}
