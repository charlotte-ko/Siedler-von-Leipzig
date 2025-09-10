package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameInv extends BaseGUI {
	
	ImageIcon log = createIcon("Log.png", 40,40);
	ImageIcon plank = createIcon("Plank.png", 40, 40);
	ImageIcon tree = createIcon("Tree.png", 40,40);
	ImageIcon axe = createIcon("Axe.png", 40, 40);
	ImageIcon hammer = createIcon("Hammer.png", 40, 40);
	ImageIcon saw = createIcon("Saw.png", 40, 40);
	
	ImageIcon forester = createIcon("Forester.png", 60, 60);
	ImageIcon house = createIcon("House.png", 60, 60);
	ImageIcon lodge = createIcon("Lodge.png", 60, 60);
	ImageIcon sawmill = createIcon("Sawmill.png", 60, 60);
	
	ImageIcon gardener = createSettlerIcon("Gardener_1.png", 40, 40);
	ImageIcon normal = createSettlerIcon("Normal_1.png", 40, 40);
	ImageIcon lumberjack = createSettlerIcon("Lumberjack_1.png", 40, 40);
	ImageIcon sawyer = createSettlerIcon("Sawyer_1.png", 40, 40);
	
	int[] counter = presenter.getCounter();
	
	InventoryPresenter presenter;
	
	
	public GameInv() {
		super("Inventar");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(700, 700));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new GridBagLayout());
		
		setLayout();
		
		this.setVisible(true);
	}
	
	public void setPresenter(InventoryPresenter presenter) {
		this.presenter = presenter;
	}
	
	public void setCounter(int[] inventoryCounter) {
		this.counter = inventoryCounter;
	}
	
	private void setLayout() {
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(20,60,20,60);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		axis.gridy = 0;
		axis.ipady = 10;
		setCategories(axis);
		
		axis.gridy = 1;
		axis.ipady = 0;
		setImages(axis);
	}
	
	private void setCategories(GridBagConstraints axis) {
		
		axis.gridx = 0;
		axis.gridwidth = 1;
		//this.add(Box.createHorizontalGlue(), axis);
		
		axis.gridx = 1;
		this.add(new JLabel("Materialien"), axis);
		
		axis.gridx = 2;
		this.add(new JLabel("Gebäude"), axis);
		
		axis.gridx = 3;
		this.add(new JLabel("Siedler"), axis);
		
		axis.gridx = 4;
		//this.add(Box.createHorizontalGlue(), axis);	
	}
	
	private void setImages(GridBagConstraints axis) {
		
		axis.gridx = 1;
		this.add(combine(log, counter[0]), axis);
		
		axis.gridy = 2;
		this.add(combine(plank, counter[1]), axis);
		
		axis.gridy = 3;
		this.add(combine(tree, counter[2]), axis);
		
		axis.gridy = 4;
		this.add(combine(axe, counter[2]), axis);
		
		axis.gridy = 5;
		this.add(combine(hammer, counter[3]), axis);
		
		axis.gridy = 6;
		this.add(combine(saw, counter[4]), axis);
		
		axis.gridx = 2;
		axis.gridy = 1;
		this.add(combine(forester, counter[5]), axis);
		
		axis.gridy = 2;
		this.add(combine(house, counter[6]), axis);
		
		axis.gridy = 3;
		this.add(combine(sawmill, counter[7]), axis);
		
		axis.gridy = 4;
		this.add(combine(lodge, counter[8]), axis);
		
		axis.gridx = 3;
		axis.gridy = 1;
		this.add(combine(normal, counter[9]), axis);
		
		axis.gridy = 2;
		this.add(combine(gardener, counter[10]), axis);
		
		axis.gridy = 3;
		this.add(combine(lumberjack, counter[11]), axis);
		
		axis.gridy = 4;
		this.add(combine(sawyer, counter[12]), axis);
		
	}
	
	private JPanel combine(ImageIcon image, int counter) {
		
		JPanel panel = new JPanel();
		panel.setBackground(brown);
		
		//JLabel num = new JLabel("" + counter);
		//vielleicht später noch farbe ändern
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
		
		return panel;
	}
}
