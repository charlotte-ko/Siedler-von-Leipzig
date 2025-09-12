package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.GamePanel;
import logic.Vector2D;
import presenter.GamePresenter;

public class Game extends BaseGUI {
	
	private ImageIcon hammerIcon = createIcon("Hammer.png", 40, 40);
	public boolean hammerActive = false;
	private JLabel hammerLabel;
	public boolean ShopItemActive = false;
	private JLabel ShopItemLabel;
	private Container glassPane = (Container) getGlassPane();
	
	//Alle Buttons
	JButton shop = new JButton(createIcon("Coin.png", 40, 40));
	JButton delete = new JButton(createIcon("Hammer.png", 40, 40));
	JButton inventory = new JButton(createIcon("Bag.png", 40, 40));
	JButton end = new JButton(createIcon("Cross.png", 40, 40));
	
	JButton[] buttons = {shop, delete, inventory, end};
	GamePresenter presenter;
	
	public boolean placementmode = false;

	public Game() {
		
		super("Siedler von Leipzig");
		
		setWindowBase(this, 800, 600, null, brown);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e ) {
				float x = e.getX();
				float y = e.getY();
				
				presenter.savePos(new Vector2D(x,y));
				presenter.waiting = false;
				if(ShopItemActive && ShopItemLabel != null) {
					removeShopItem();
				}
				
				if(hammerActive && hammerLabel != null) {
					Vector2D modelPos = presenter.giveModelPos();
					
					presenter.toggleDeconstruction(modelPos);
				}
				System.out.println("x:" + x + "y" +y);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(hammerActive && hammerLabel != null) {
					hammerLabel.setLocation(e.getX() - 15, e.getY() - 15);
				}
				if(ShopItemActive && ShopItemLabel != null) {
					ShopItemLabel.setLocation(e.getX() -15,  e.getY() - 15);
				}
			}
		});
		
		//Einzelnen Komponenten
		JPanel field = createField();
		JPanel menubar = createMenuBar();
		

		
		this.add(field, BorderLayout.CENTER);
		this.add(menubar, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		//gibt den focus wieder dem field
		addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override public void windowGainedFocus(java.awt.event.WindowEvent e) {
                field.requestFocusInWindow();
            }
        });
	}
	
	public void setPresenter(GamePresenter presenter) {
		this.presenter = presenter;
		gPanel.setPresenter(presenter);
	}
	
	GamePanel gPanel;
	private JPanel createField() {
		gPanel = new GamePanel();
		//gPanel.startGameThread();
		return gPanel;
	}
	
	public void startGameLoop() {
		gPanel.startGameThread();
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
			b.setFocusable(false); 			//Ich hoffe das macht kein problem
		}
	
		shop.addActionListener(e -> presenter.openShop());
		inventory.addActionListener(e -> presenter.openInventory());
		
		delete.addActionListener(e -> presenter.toggleHammer());
		end.addActionListener(e -> presenter.openExit());
	}
	
	public void attachHammer() {
		
		if(hammerLabel != null) {
			glassPane.remove(hammerLabel);
		}
		
		hammerLabel = new JLabel(hammerIcon);
		hammerLabel.setSize(hammerIcon.getIconWidth(), hammerIcon.getIconWidth());
		
		glassPane.setLayout(null);
	    glassPane.add(hammerLabel);
	    glassPane.setVisible(true);
	}
	
	public void removeHammer() {
	    if (hammerLabel != null) {
	        glassPane.remove(hammerLabel);
	        glassPane.setVisible(false);
	        hammerLabel = null;
	    }
	}
	
	public void attachShopItem(String item) {
		
		if(ShopItemLabel != null) {
			glassPane.remove(ShopItemLabel);
		}
		ImageIcon icon = createIcon(item + ".png", 60, 60);
		ShopItemLabel = new JLabel(icon);
		ShopItemLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
		glassPane.setLayout(null);
		glassPane.add(ShopItemLabel);
		glassPane.setVisible(true);
	}
	
	public void removeShopItem() {
		
		if(ShopItemLabel != null) {
			glassPane.remove(ShopItemLabel);
			glassPane.setVisible(false);
			ShopItemLabel = null;
		}
	}
}
