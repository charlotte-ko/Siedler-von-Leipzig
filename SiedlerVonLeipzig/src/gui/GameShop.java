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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presenter.ShopPresenter;

public class GameShop extends BaseGUI {

	ShopPresenter presenter;
	
	JButton log = new JButton(createIcon("Log.png", 40, 40));
	JButton plank = new JButton(createIcon("Plank.png", 40, 40));
	JButton house = new JButton(createIcon("House.png", 80, 80));
	JButton forester = new JButton(createIcon("Forester.png", 80, 80));
	JButton lodge = new JButton(createIcon("Lodge.png", 80, 80));
	JButton sawmill = new JButton(createIcon("Sawmill.png", 80, 80));
	JButton axe = new JButton(createIcon("Axe.png", 40, 40));
	JButton saw = new JButton(createIcon("Saw.png", 40, 40));
	
	JButton[] leftbuttons = {house, forester, lodge, sawmill};
	JButton[] rightbuttons = {axe ,saw};
	
	ImageIcon logC = createIcon("Log.png", 40, 40);
	ImageIcon plankC = createIcon("Plank.png", 40, 40);
	ImageIcon houseC = createIcon("House.png", 80, 80);
	
	public boolean active = false;
	private Container glassPane = (Container) getGlassPane();
	JLabel clickable;
	
	public GameShop() {
		super("Shop");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500, 550));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new BorderLayout());
		

		setButtons(leftbuttons);
		setButtons(rightbuttons);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(active && clickable != null) {
					clickable.setLocation(e.getX() - 15, e.getY() - 15);
				}
			} 
		});
		

		JPanel menubar = createTopBar();
		
		this.add(menubar, BorderLayout.NORTH);
		this.add(chartLayout());
		this.setVisible(true);
	}
	
	public void setPresenter(ShopPresenter presenter) {
		this.presenter = presenter;
	}
	
	private JPanel chartLayout() {
		
		JLabel label1 = createLabel("Haus:", 100, 30);
		JLabel label2 = createLabel("Försterei:", 100, 30);
		JLabel label3 = createLabel("Lodge:", 100, 30); 
		JLabel label4 = createLabel("Sägemühle:", 100, 30);
		JLabel label5 = createLabel("Axt:", 100, 30);
		JLabel label6 = createLabel("Säge:", 100, 30);
		
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
			g.insets = new Insets(0, 10, 0, 100);
			if(i%2 == 0) {
				container.add(leftbuttons[index], g);
				index++;
			}
			else { 
				container.add(leftlabels[index2], g);
				index2++;
			}
		}
		
		JLabel[] rightlabels = {label5, label6};
		
		index = 0;
		index2 = 0;
		for(int i = 0; i < 4; i++) {
			g.gridx = 1;
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
		return container;
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
		JLabel label3 = new JLabel("0");
		JLabel label4 = new JLabel(plankC);
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
	
	private void setButtons(JButton[] buttons) {
		for(JButton b : buttons) {
			b.setPreferredSize(new Dimension(80,80));
			b.setBackground(brown);
			b.setOpaque(true);
			b.setBorderPainted(false);
			b.setFocusable(false); 			
		}
		
		house.addActionListener(e -> presenter.onClick("House"));
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
