package gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;

public class GameShop extends BaseGUI {

	public GameShop() {
		super("Shop");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new GridBagLayout());
		
		setLayout();
		
		this.setVisible(true);
	}
	
	private void setLayout() {
		
	}

	private void createBar() {
		
	}
}
