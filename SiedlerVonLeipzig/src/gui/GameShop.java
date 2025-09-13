package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presenter.ShopPresenter;

public class GameShop extends BaseGUI {

	ShopPresenter presenter;
	
	int[] currency = {0, 0};
	JButton log = new JButton(createIcon("Log.png", 40, 40));
	JButton plank = new JButton(createIcon("Plank.png", 40, 40));
	JButton house = new JButton(createIcon("House.png", 80, 80));
	JButton forester = new JButton(createIcon("Forester.png", 80, 80));
	JButton lodge = new JButton(createIcon("Lodge.png", 80, 80));
	JButton sawmill = new JButton(createIcon("Sawmill.png", 80, 80));
	JButton forge = new JButton(createIcon("Forge.png", 80, 80));
	JButton mine = new JButton(createIcon("Mine.png", 80, 80));
	
	JButton[] leftbuttons = {house, forester, lodge, sawmill};
	JButton[] rightbuttons = {forge ,mine};
	
	ImageIcon logC = createIcon("Log.png", 40, 40);
	ImageIcon plankC = createIcon("Plank.png", 40, 40);
	ImageIcon houseC = createIcon("House.png", 80, 80);
	
	public boolean active = false;
	private Container glassPane = (Container) getGlassPane();
	JLabel clickable;
	
	public GameShop() {
		super("Shop");
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(550, 550));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new BorderLayout());
		

		setButtons(leftbuttons);
		setButtons(rightbuttons);
		/*
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(active && clickable != null) {
					clickable.setLocation(e.getX() - 15, e.getY() - 15);
				}
			} 
		});*/
	}
	
	public void setPresenter(ShopPresenter presenter) {
		this.presenter = presenter;
		currency = presenter.getCurrency();
		

		JPanel menubar = createTopBar();
		
		this.add(menubar, BorderLayout.NORTH);
		this.add(chartLayout());
		this.setVisible(true);
	}
	
	private JPanel chartLayout() {
		
		JLabel price1 = createLabel("Preis: " + presenter.givePrice("House") + " Planks", 20, 20);
		JLabel price2 = createLabel("Preis: " + presenter.givePrice("Forester") + " Logs", 20, 20);
		JLabel price3 = createLabel("Preis: " + presenter.givePrice("Lodge") + " Logs", 20, 20);
		JLabel price4 = createLabel("Preis: " + presenter.givePrice("Sawmill") + " Logs", 20, 20);
		JLabel price5 = createLabel("Preis: " + presenter.givePrice("Forge") + " Logs", 20, 20);
		JLabel price6 = createLabel("Preis: " + presenter.givePrice("Mine") + " Logs", 20, 20);
		
		JLabel[] prices = {price1, price2, price3, price4, price5, price6};
		
		JLabel label1 = createLabel("Haus:", 100, 30);
		JLabel label2 = createLabel("Försterei:", 100, 30);
		JLabel label3 = createLabel("Lodge:", 100, 30); 
		JLabel label4 = createLabel("Sägemühle:", 100, 30);
		JLabel label5 = createLabel("Schmiede:", 100, 30);
		JLabel label6 = createLabel("Miene:", 100, 30);
		
		JLabel[] leftlabels = {label1, label2, label3, label4};
		JLabel[] rightlabels = {label5, label6};
		
		JPanel subContainer1 = new JPanel();
		subContainer1.setLayout(new GridBagLayout());
		subContainer1.setOpaque(false);
		GridBagConstraints grid = new GridBagConstraints();
		grid.insets = new Insets(0, 20, 0, 20);
		grid.fill = GridBagConstraints.HORIZONTAL;
		
		grid.weightx = 1;
		grid.gridx = 0;
		grid.weighty = 0;
		//grid.weightx = 0.2;
		int index1 = 0;
		int index2 = 0;
		for( int i = 0; i < 8; i++) {
			grid.gridy = i;
			grid.ipady = 10;
			grid.insets = new Insets(0, 10, 0, 50);
			if(i%2 == 0) {
				subContainer1.add(leftbuttons[index1], grid);
				//grid.insets = new Insets(0, 20, 0, 20);
				index1++;
			}
			else {
				subContainer1.add(leftlabels[index2], grid);
				//grid.insets = new Insets(0, 20, 0, 20);
				index2++;
			}
			
		}
		index1 = 0;
		index2 = 0;
		
		grid.gridx = 1;
		for( int i = 0; i < 4; i++) {
			grid.gridy = 2*i;
			prices[index1].setPreferredSize(new Dimension(100, 30));
			prices[index1].setBackground(brown);
			prices[index1].setForeground(lightsalmon);
			subContainer1.add(prices[index1], grid);
			index1++;
		
		}
		

		
		index1 = 0;
		for(int i = 0; i < 4; i++) {
			grid.gridx = 2;
			grid.gridy = i;
			grid.weighty = 1.0;
			grid.insets = new Insets(0, 10, 0, 50);
			//grid.insets = new Insets(5, 50, 5, 20);
			if(i%2 == 0) {
				subContainer1.add(rightbuttons[index1], grid);
				//grid.insets = new Insets(0, 20, 0, 20);
				index1++;
			}
			else { 
				subContainer1.add(rightlabels[index2], grid); 
				//grid.insets = new Insets(0, 20, 0, 20);
				index2++;
			}
		}
		
		index1 = 4;
		grid.gridx = 3;
		for( int i = 0; i < 2; i++) {
			grid.gridy = 2*i;
			prices[index1].setPreferredSize(new Dimension(100, 30));
			prices[index1].setBackground(brown);
			prices[index1].setForeground(lightsalmon);
			subContainer1.add(prices[index1], grid);
			index1++;
		
		}
		
		return subContainer1;
		/*
		JPanel container = new JPanel(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		container.setOpaque(false);
		
		int index = 0;
		int index2 = 0;
		JLabel[] leftlabels = {label1, label2, label3, label4};
		for(int i = 0; i < 8; i++) {
			g.gridx = 0;
			g.gridy = i;
			g.weighty = 1.0;
			g.insets = new Insets(0, 10, 0, 50);
			if(i%2 == 0) {
				container.add(leftbuttons[index], g);
				index++;
			}
			else { 
				container.add(leftlabels[index2], g);
				index2++;
			}
		}
		
		g.insets = new Insets(0,10,0,50);
		g.gridx = 1;
		g.gridy = 0;
		container.add(new JLabel("" + presenter.givePrice("House")));
		
		g.gridy = 2;
		container.add(new JLabel("" + presenter.givePrice("Forester")));
		
		g.gridy = 4;
		container.add(new JLabel("" + presenter.givePrice("Lodge")));
		
		g.gridy = 6;
		container.add(new JLabel("" + presenter.givePrice("Sawmill")));
		
		JLabel[] rightlabels = {label5, label6};
		
		index = 0;
		index2 = 0;
		for(int i = 0; i < 4; i++) {
			g.gridx = 2;
			g.gridy = i;
			g.weighty = 1.0;
			g.insets = new Insets(0, 100, 0, 100);
			if(i%2 == 0) {
				container.add(rightbuttons[index], g);
				index++;
			}
			else { 
				container.add(rightlabels[index2], g); 
				index2++;
			}
		}
		return container;*/
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
		
		JLabel label1 = new JLabel(logC);
		JLabel label2 = createLabel("Baumstämme:", 100, 30);
		JLabel label3 = new JLabel(String.valueOf(currency[0]));
		JLabel label4 = new JLabel(plankC);
		JLabel label5 = createLabel("Bretter:", 100, 30);
		JLabel label6 = new JLabel(String.valueOf(currency[1]));
		
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
	
	private void setButtons(JButton[] buttons) {
		for(JButton b : buttons) {
			b.setPreferredSize(new Dimension(80,80));
			b.setBackground(brown);
			b.setOpaque(true);
			b.setBorderPainted(false);
			b.setFocusable(false); 			
		}
		
		house.addActionListener(e -> presenter.onClick("House"));
		forester.addActionListener(e -> presenter.onClick("Forester"));
		lodge.addActionListener(e -> presenter.onClick("Lodge"));
		sawmill.addActionListener(e -> presenter.onClick("Sawmill"));
		forge.addActionListener(e -> presenter.onClick("Forge"));
		mine.addActionListener(e -> presenter.onClick("Mine"));
	}
	/*
	public void attach() {
		
		if(clickable != null) {
			glassPane.remove(clickable);
		}
		
		ImageIcon image = createIcon("House.png", 40,40);
		clickable = new JLabel(image);
		clickable.setSize(image.getIconWidth(), image.getIconHeight());
		
		glassPane.setLayout(null);
		glassPane.add(clickable);
		glassPane.setVisible(true);
	}
	
	public void detach() {
		if(clickable != null) {
			glassPane.remove(clickable);
			glassPane.setVisible(false);
			clickable = null;
		}
	}*/
}
