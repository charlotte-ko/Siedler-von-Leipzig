package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends BaseGUI {
	
	//Alle einzelnen Container
	private JPanel field;
	private JPanel menubar;
	
	//Image erstellProzess muss noch eine eigene Methode werden, die die Buttons, dann überschreibt

	
	//Alle Buttons
	JButton shop = new JButton("Shop");
	JButton delete = new JButton(createIcon("Hammer.png", 40, 40));
	JButton inventory = new JButton("Inventar");
	JButton end = new JButton("Beenden");
	
	JButton[] buttons = {shop, delete, inventory, end};
	
	public Game() {
		
		super("Sielder");
		//Basics von jedem Window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		//Einzelnen Komponenten
		field = createField();
		menubar = createMenuBar();

		
		this.add(field, BorderLayout.CENTER);
		this.add(menubar, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private JPanel createField() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		//hier muss dann halt das Spiel eingefügt werden
		return panel;
	}
	
	private JPanel createMenuBar() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(brown);
		panel.setOpaque(true);
		panel.setPreferredSize(new Dimension(800, 90));
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(10, 20, 10, 20);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		setButtons();
		
		axis.gridy = 0;
		axis.gridx = 0;
		panel.add(shop, axis);
		
		axis.gridx = 1;
		panel.add(delete, axis);
		
		axis.gridx = 2;
		panel.add(inventory, axis);
		
		axis.gridx = 3;
		panel.add(end, axis);
		
		return panel;
	}
	
	private void setButtons() {
		
		for(JButton b : buttons) {
			
			b.setPreferredSize(new Dimension(50,50));
			b.setBackground(lightsalmon);
			b.setOpaque(true);
			b.setBorderPainted(false);
		}
	
		inventory.addActionListener(e -> new GameInv());
	
		end.addActionListener(e -> new Exit());
	}
}
