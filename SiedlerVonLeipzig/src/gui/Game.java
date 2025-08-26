package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
		this.setVisible(true);
		
		//Einzelnen Komponenten
		field = createField();
		menubar = createMenuBar();

		
		this.add(field, BorderLayout.CENTER);
		this.add(menubar, BorderLayout.SOUTH);
	}
	
	private JPanel createField() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		//hier muss dann halt das Spiel eingefügt werden
		return panel;
	}
	
	private JPanel createMenuBar() {
		
		JPanel complete = new JPanel();
		complete.setOpaque(false);
		complete.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(brown);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setPreferredSize(new Dimension(800, 90));
		setButtons();
		panel.add(shop);
		panel.add(delete);
		panel.add(inventory);
		panel.add(end);
		
		complete.add(panel, BorderLayout.CENTER);
		
		return complete;
	}
	
	private void setButtons() {
		
		shop.setPreferredSize(new Dimension(50, 50));
		delete.setPreferredSize(new Dimension(50, 50));
		inventory.setPreferredSize(new Dimension(50, 50));
		end.setPreferredSize(new Dimension(50, 50));
		
	}
}
