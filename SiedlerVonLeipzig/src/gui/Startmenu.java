package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presenter.StartPresenter;

public class Startmenu extends BaseGUI{

	private static final long serialVersionUID = 1L;
	
	JButton button1 = new JButton("Neues Spiel");
	JButton button2 = new JButton("Weiterspielen");
	JButton button3 = new JButton("Debug Modus");
	JButton button4 = new JButton("Einstellungen");
	JButton button5 = new JButton("Beenden");
	
	JButton[] buttons = {button1, button2, button3, button4, button5};
	
	String player_name; //soll dann key für unser Speichersystem sein, weiß nicht, ob das dann in Main gehört
	
	private StartPresenter presenter;
	
	public Startmenu() {
		super("Startmenue");
		
		setWindowBase(this,800, 600, null, foresty);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		setButtons();
		ButtonLayout(this);
	}
	public void setPresenter(StartPresenter presenter) {
		this.presenter = presenter;
	}
	
	private void setButtons() {
		
		for(JButton b : buttons) {
			
			b.setBackground(pine);
			b.setForeground(foresty);
			b.setOpaque(true);
			b.setBorderPainted(false);
		}
	
		button1.addActionListener(e -> presenter.buttonStartNewGame());
		
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
	
	public void openNewGame() {
		
		JFrame NewFrame = new JFrame("Neues Spiel");
		
		setWindowBase(NewFrame, 600, 200, this, foresty);
		
		NewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		NewFrame.setLayout(new GridBagLayout());

		
		JLabel name = new JLabel("Name:");
		
		name.setForeground(pine);
		name.setFont(new Font("Arial", Font.BOLD, 18 ));
		
		JTextField field_name = new JTextField(20);
		
		field_name.setBackground(pine);
		field_name.setForeground(foresty);
		
		JButton confirm = new JButton("Bestätigen");
		
		confirm.setBackground(pine);
		confirm.setForeground(foresty);
		
		//Bestetigen !!!
		/*
		confirm.addActionListener(e -> {
			player_name = field_name.getText();
			new Game();
			NewFrame.dispose();
			this.dispose();
		});
		*/
		confirm.addActionListener(e -> {
			presenter.setPlayerName(player_name);
			NewFrame.dispose();
			presenter.makeNewGame();
		});
		
		NewGameLayout(NewFrame, name, field_name, confirm);
		
		NewFrame.setVisible(true);
		
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
		
		axis.gridx = 2;
		axis.weightx = 0.2;
		e.add(Box.createHorizontalGlue(), axis);
		
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
