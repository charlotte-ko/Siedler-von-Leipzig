package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameShop extends BaseGUI {

	ImageIcon log = createIcon("Log.png", 40, 40);
	ImageIcon plank = createIcon("Plank.png", 40, 40);
	
	public GameShop() {
		super("Shop");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new BorderLayout());
		
		JPanel menubar = createTopBar();
		
		this.add(menubar, BorderLayout.NORTH);
		setLayout();
		
		this.setVisible(true);
	}
	
	private void setLayout() {
		
	}

	private JLabel createLabel(String name, int width, int height) {
		JLabel label = new JLabel(name, SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(width, height));
		label.setOpaque(true);
		label.setBackground(pine);
		label.setForeground(foresty);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return label;
	}
	
	private JPanel createTopBar() {
		
		JPanel topBar = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JPanel container = new JPanel(new GridBagLayout());
		container.setOpaque(false);
		
		g.insets = new Insets(0,10,0,10);
		g.fill = GridBagConstraints.BOTH;
		
		JLabel label1 = new JLabel(log);
		JLabel label2 = createLabel("Baumstämme:", 100, 30);
		JLabel label3 = new JLabel("0");
		JLabel label4 = new JLabel(plank);
		JLabel label5 = createLabel("Bretter:", 100, 30);
		JLabel label6 = new JLabel("0");
		
		JLabel[] labels = {label1, label2, label3, label4, label5, label6};
		
		for(int i = 0; i < labels.length; i++) {
			g.gridx = i;
			g.weightx = 1.0;
			container.add(labels[i], g);
		}
	
		GridBagConstraints center = new GridBagConstraints();
		center.gridx = 0;
		center.gridy = 0;
		center.anchor = GridBagConstraints.CENTER;
		
		topBar.add(container);
		topBar.setBorder(BorderFactory.createLineBorder(pine, 3));
		topBar.setBackground(foresty);
		topBar.setOpaque(true);
		topBar.setPreferredSize(new Dimension(500, 55));
		
		return topBar;
	}
}
