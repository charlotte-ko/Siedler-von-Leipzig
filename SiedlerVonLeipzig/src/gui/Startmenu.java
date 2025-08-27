package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
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
	
	String player_name; //soll dann key für unser Speichersystem sein, weiß nicht, ob das dann in Main gehört
	
	public Startmenu() {
		
		super("Startmenue");
		
		//Basics für Fenster
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(42, 158, 58));
		this.setLayout(new GridBagLayout());
		
		setButtons();
		ButtonLayout(this);
		
		setVisible(true);
	}
	
	private void openNewGame() {
		
		JFrame NewFrame = new JFrame("Neues Spiel");
		
		NewFrame.setSize(600,200);
		NewFrame.setLocationRelativeTo(null);
		NewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		NewFrame.setLayout(new GridBagLayout());
		NewFrame.getContentPane().setBackground(new Color(42, 158, 58));
		
		JLabel name = new JLabel("Name:");
		name.setForeground(pine);
		name.setFont(new Font("Arial", Font.BOLD, 18 ));
		
		JTextField field_name = new JTextField(20);
		field_name.setBackground(pine);
		field_name.setForeground(foresty);
		
		JButton confirm = new JButton("Bestätigen");
		confirm.addActionListener(e -> {
			
			player_name = field_name.getText();
			new Game();
			NewFrame.dispose();
			this.dispose();
		});
		
		confirm.setBackground(pine);
		confirm.setForeground(foresty);
		
		NewGameLayout(NewFrame, name, field_name, confirm);
		
		NewFrame.setVisible(true);
		
	}
	
	private void setButtons() {
		
		button1.setBackground(pine);
		button1.setForeground(foresty);
		button1.setOpaque(true);
		button1.setBorderPainted(false);
		button1.addActionListener(e -> openNewGame());
		
		button2.setBackground(pine);
		button2.setForeground(foresty);
		button2.setOpaque(true);
		button2.setBorderPainted(false);
		
		button3.setBackground(pine);
		button3.setForeground(foresty);
		button3.setOpaque(true);
		button3.setBorderPainted(false);
		
		button4.setBackground(pine);
		button4.setForeground(foresty);
		button4.setOpaque(true);
		button4.setBorderPainted(false);
		
		button5.setBackground(pine);
		button5.setForeground(foresty);
		button5.setOpaque(true);
		button5.setBorderPainted(false);
		button5.addActionListener(e -> System.exit(0));
	}
	
	
	private void ButtonLayout(Container e) {
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.insets = new Insets(10, 5, 10, 5);
		grid.ipady = 10;
		
		grid.gridx = 0;
		grid.gridy = 1;
		grid.gridwidth = 1;
		grid.weightx = 0.3;
		//grid.ipady = 0;
		e.add(Box.createHorizontalGlue(), grid);
		
		grid.gridx = 1;
		grid.gridwidth = 2;
		grid.weightx = 0.4;
		e.add(button1, grid);
		
		grid.gridy = 2;
		e.add(button2, grid);
		
		grid.gridy = 3;
		e.add(button3, grid);
		
		grid.gridy = 4;
		grid.gridwidth = 1;
		grid.weightx = 0.5;
		e.add(button4, grid);
		
		grid.gridx = 2;
		e.add(button5, grid);
		
		grid.gridx = 3;
		grid.gridy = 1;
		grid.weightx = 0.3;
		e.add(Box.createHorizontalGlue(), grid);
		
		grid.gridx = 0;
	    grid.gridy = 0;
	    grid.gridwidth = 2;
	    grid.weighty = 1.0;
	    grid.weightx = 1.0;
	    grid.ipady = 0;
	    grid.insets = new Insets(0, 0, 0, 0);
	    e.add(Box.createGlue(), grid);
		
	}
	
	private void NewGameLayout(Container e, JLabel name, JTextField field, JButton confirm) {
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(20, 20, 20, 20);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		axis.gridy = 0;
		axis.gridx = 0;
		axis.weightx = 0.2;
		e.add(name, axis);
		
		axis.gridx = 1;
		axis.weightx = 0.6;
		//axis.anchor = GridBagConstraints.EAST;
		//axis.ipady = 10;
		field.setPreferredSize(new Dimension(100, 30));
		e.add(field, axis);
		
		axis.gridx = 1;
		axis.gridy = 1;
		//axis.gridwidth = 2;
		axis.weightx = 0.6;
		e.add(confirm, axis);
	
	}
	
	public String getName() {
		return this.player_name;
	}
}
