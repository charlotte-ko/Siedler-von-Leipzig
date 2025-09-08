package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GameShop extends BaseGUI {

	public GameShop() {
		super("Shop");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new BorderLayout());
		
		JPanel menubar = createBar();
		
		this.add(menubar, BorderLayout.NORTH);
		setLayout();
		
		this.setVisible(true);
	}
	
	private void setLayout() {
		
	}

	private JPanel createBar() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.BLACK);
		panel.setOpaque(true);
		panel.setPreferredSize(new Dimension(500, 50));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		c.weightx = 1;
		
		return panel;
	}
}
