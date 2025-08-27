package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	
	//Alle einzelnen Container
	private JPanel field;
	private JPanel menubar;
	
	//Alle Farben
	private Color brown = new Color(182,114,103);
	
	//Alle Buttons
	JButton shop = new JButton("Shop");
	JButton delete = new JButton("Abreißen");
	JButton inventory = new JButton("Inventar");
	JButton end = new JButton("Beenden");
	
	public Game() {
		
		//Basics von jedem Window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Siedler");
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
		
		shop.setPreferredSize(new Dimension(50, 50));
		delete.setPreferredSize(new Dimension(50, 50));
		inventory.setPreferredSize(new Dimension(50, 50));
		end.setPreferredSize(new Dimension(50, 50));
		
	}
}
