package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.BorderFactory;
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
	
	ImageIcon LogIcon = createIcon("Log.png", 40, 40);
	ImageIcon PlankIcon = createIcon("Plank.png", 40, 40);
	ImageIcon HouseIcon = createIcon("House.png", 80, 80);
	
	public boolean active = false;

	JLabel clickable;
	
	public GameShop() {
		
		super("Shop");
		
		this.setWindowBase(this, 700, 650, null, brown);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
	
		setButtons(leftbuttons);
		setButtons(rightbuttons);
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
		
		JLabel price1 = createLabel("Preis: " + presenter.givePrice("House") + " " + presenter.giveCurrency("House") + "s", 20, 20);
		JLabel price2 = createLabel("Preis: " + presenter.givePrice("Forester") + " " + presenter.giveCurrency("Forester") + "s", 20, 20);
		JLabel price3 = createLabel("Preis: " + presenter.givePrice("Lodge") + " " + presenter.giveCurrency("Lodge") + "s", 20, 20);
		JLabel price4 = createLabel("Preis: " + presenter.givePrice("Sawmill") + " " + presenter.giveCurrency("Sawmill") + "s", 20, 20);
		JLabel price5 = createLabel("Preis: " + presenter.givePrice("Forge") + " " + presenter.giveCurrency("Forge") + "s", 20, 20);
		JLabel price6 = createLabel("Preis: " + presenter.givePrice("Mine") + " " + presenter.giveCurrency("Mine") + "s", 20, 20);
		
		JLabel[] prices = {price1, price2, price3, price4, price5, price6};
		
		JLabel label1 = createLabel("Haus:", 100, 30);
		JLabel label2 = createLabel("Försterei:", 100, 30);
		JLabel label3 = createLabel("Lodge:", 100, 30); 
		JLabel label4 = createLabel("Sägemühle:", 100, 30);
		JLabel label5 = createLabel("Schmiede:", 100, 30);
		JLabel label6 = createLabel("Miene:", 100, 30);
		
		JPanel subContainer = new JPanel();
		
		subContainer.setLayout(new GridBagLayout());
		subContainer.setOpaque(false);
		
		GridBagConstraints grid = new GridBagConstraints();
		//grid.insets = new Insets(0, 20, 0, 20);
		grid.insets = new Insets(0, 10, 0, 50);
		grid.fill = GridBagConstraints.HORIZONTAL;
		
		grid.weightx = 1;
		grid.gridx = 0;
		
		grid.weighty = 0;
		grid.ipady = 10;
		
		grid.gridy = 0;
		subContainer.add(house, grid);
		
		grid.gridy = 1;
		subContainer.add(label1, grid);
		
		grid.gridy = 2;
		subContainer.add(forester, grid);
		
		grid.gridy = 3;
		subContainer.add(label2, grid);
		
		grid.gridy = 4;
		subContainer.add(lodge, grid);
		
		grid.gridy = 5;
		subContainer.add(label3, grid);
		
		grid.gridy = 6;
		subContainer.add(sawmill, grid);
		
		grid.gridy = 7;
		subContainer.add(label4, grid);
	
		grid.gridx = 2;
		grid.gridy = 0;
		subContainer.add(forge, grid);
		
		grid.gridy = 1;
		subContainer.add(label5, grid);
		
		grid.gridy = 2;
		subContainer.add(mine, grid);
		
		grid.gridy = 3;
		subContainer.add(label6, grid);
		
		int index1 = 0;
		grid.gridx = 1;
		
		for( int i = 0; i < 4; i++) {
			
			grid.gridy = 2*i;
			prices[index1].setPreferredSize(new Dimension(100, 30));
			prices[index1].setBackground(brown);
			prices[index1].setForeground(lightsalmon);
			subContainer.add(prices[index1], grid);
			index1++;
		}
		
		index1 = 4;
		grid.gridx = 3;
		
		for( int i = 0; i < 2; i++) {
			
			grid.gridy = 2*i;
			prices[index1].setPreferredSize(new Dimension(100, 30));
			prices[index1].setBackground(brown);
			prices[index1].setForeground(lightsalmon);
			subContainer.add(prices[index1], grid);
			index1++;
			
		}return subContainer;
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
		GridBagConstraints grid = new GridBagConstraints();
		
		JPanel container = new JPanel(new GridBagLayout());
		container.setOpaque(false);
		
		grid.insets = new Insets(0,10,0,10);
		grid.fill = GridBagConstraints.BOTH;
		
		JLabel label1 = new JLabel(LogIcon);
		JLabel label2 = createLabel("Baumstämme:", 100, 30);
		JLabel label3 = new JLabel(String.valueOf(currency[0]));
		JLabel label4 = new JLabel(PlankIcon);
		JLabel label5 = createLabel("Bretter:", 100, 30);
		JLabel label6 = new JLabel(String.valueOf(currency[1]));
		
		JLabel[] labels = {label1, label2, label3, label4, label5, label6};
		
		for(int i = 0; i < labels.length; i++) {
			grid.gridx = i;
			grid.weightx = 1.0;
			container.add(labels[i], grid);
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
}
