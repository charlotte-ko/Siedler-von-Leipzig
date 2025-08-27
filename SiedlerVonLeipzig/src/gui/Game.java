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
	private Color brown = new Color(247, 181, 138);
	Color lightbrown = new Color(251, 216, 193);
	
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
		
		shop.setPreferredSize(new Dimension(50, 50));
		shop.setBackground(lightbrown);
		delete.setPreferredSize(new Dimension(50, 50));
		delete.setBackground(lightbrown);
		inventory.setPreferredSize(new Dimension(50, 50));
		inventory.setBackground(lightbrown);
		
		end.setPreferredSize(new Dimension(50, 50));
		end.setBackground(lightbrown);
		end.addActionListener(e -> new Exit());
		
	}
}
