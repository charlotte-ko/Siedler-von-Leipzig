package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import presenter.InventoryPresenter;

public class GameInv extends BaseGUI {
	
	ImageIcon log = createIcon("Log.png", 40,40);
	ImageIcon plank = createIcon("Plank.png", 40, 40);
	ImageIcon tree = createIcon("Tree.png", 40,40);
	ImageIcon axe = createIcon("Axe.png", 40, 40);
	ImageIcon hammer = createIcon("Hammer.png", 40, 40);
	ImageIcon saw = createIcon("Saw.png", 40, 40);
	ImageIcon pickaxe = createIcon("Pickaxe.png", 40, 40);
	ImageIcon iron = createIcon("Iron.png", 40, 40);
	
	ImageIcon forester = createIcon("Forester.png", 60, 60);
	ImageIcon house = createIcon("House.png", 60, 60);
	ImageIcon lodge = createIcon("Lodge.png", 60, 60);
	ImageIcon sawmill = createIcon("Sawmill.png", 60, 60);
	ImageIcon mine = createIcon("Mine.png", 60, 60);
	ImageIcon forge = createIcon("Forge.png", 60, 60);
	
	ImageIcon gardener = createSettlerIcon("Gardener_1.png", 40, 40);
	ImageIcon normal = createSettlerIcon("Normal_1.png", 40, 40);
	ImageIcon lumberjack = createSettlerIcon("Lumberjack_1.png", 40, 40);
	ImageIcon sawyer = createSettlerIcon("Sawyer_1.png", 40, 40);
	ImageIcon miner = createSettlerIcon("Miner_1.png", 40, 40);
	ImageIcon smith = createSettlerIcon("Smith_1.png", 40, 40);
	
	int[] counter;
	
	InventoryPresenter presenter;
	
	public GameInv() {
		
		super("Inventar");
		
		this.setWindowBase(this, 900, 800, null, brown);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
	}
	
	public void setPresenter(InventoryPresenter presenter) {
		this.presenter = presenter;
		this.counter = presenter.getCounter();
		setLayout();
		this.setVisible(true);
	}
	
	private void setLayout() {
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(20,50,10,50);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		axis.gridy = 0;
		axis.ipady = 5;
		setCategories(axis);
		
		axis.gridy = 1;
		axis.ipady = 0;
		setImages(axis);
	}
	
	private void setCategories(GridBagConstraints axis) {
		
		JLabel materials = new JLabel("Materialien");
		materials.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel buildings = new JLabel("Gebäude");
		buildings.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel settlers = new JLabel("Siedler");
		settlers.setHorizontalAlignment(SwingConstants.CENTER);
		
		axis.gridx = 0;
		axis.gridwidth = 2;
		
		materials.setFont(hehe);
		materials.setPreferredSize(new Dimension(300, 100));
		materials.setBackground(pine);
		materials.setForeground(foresty);
		materials.setOpaque(true);
		axis.gridx = 1;
		this.add(materials, axis);
		
		buildings.setFont(hehe);
		buildings.setPreferredSize(new Dimension(500, 500));
		buildings.setBackground(pine);
		buildings.setForeground(foresty);
		buildings.setOpaque(true);
		axis.gridwidth = 1;
		axis.gridx = 3;
		this.add(buildings, axis);
		
		settlers.setFont(hehe);
		settlers.setPreferredSize(new Dimension(500, 500));
		settlers.setBackground(pine);
		settlers.setForeground(foresty);
		settlers.setOpaque(true);
		axis.gridx = 4;
		this.add(settlers, axis);	
	}
	
	private void setImages(GridBagConstraints axis) {
		
		axis.gridx = 1;
		this.add(combine(log, counter[0]), axis);
		
		axis.gridy = 2;
		this.add(combine(plank, counter[1]), axis);
		
		axis.gridy = 3;
		this.add(combine(iron, counter[2]), axis);
		
		axis.gridy = 4;
		this.add(combine(tree, counter[3]), axis);
		
		axis.gridx = 2;
		axis.gridy = 1;
		this.add(combine(axe, counter[4]), axis);
		
		axis.gridy = 2;
		this.add(combine(hammer, counter[5]), axis);
		
		axis.gridy = 3;
		this.add(combine(saw, counter[6]), axis);
		
		axis.gridy = 4;
		this.add(combine(pickaxe, counter[7]), axis);
		
		axis.gridx = 3;
		axis.gridy = 1;
		this.add(combine(house, counter[8]), axis);
		
		axis.gridy = 2;
		this.add(combine(forester, counter[9]), axis);
		
		axis.gridy = 3;
		this.add(combine(sawmill, counter[10]), axis);
		
		axis.gridy = 4;
		this.add(combine(lodge, counter[11]), axis);
		
		axis.gridy = 5;
		this.add(combine(forge, counter[12]), axis);
		
		axis.gridy = 6;
		this.add(combine(mine, counter[13]), axis);
		
		axis.gridx = 4;
		axis.gridy = 1;
		this.add(combine(normal, counter[14]), axis);
		
		axis.gridy = 2;
		this.add(combine(gardener, counter[15]), axis);
		
		axis.gridy = 3;
		this.add(combine(sawyer, counter[16]), axis);
		
		axis.gridy = 4;
		this.add(combine(lumberjack, counter[17]), axis);
		
		axis.gridy = 5;
		this.add(combine(smith, counter[18]), axis);
		
		axis.gridy = 6;
		this.add(combine(miner, counter[19]), axis);
	}
	
	private JPanel combine(ImageIcon image, int counter) {
		
		JPanel panel = new JPanel();
		panel.setBackground(brown);
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
		panel.add(new JLabel(image));
		
		String counterT = String.valueOf(counter);
		JLabel counterL = new JLabel(counterT);
		
		counterL.setForeground(foresty);
		counterL.setFont(hehe);
		
		panel.setBorder(BorderFactory.createLineBorder(foresty, 3));
		panel.setBackground(pine);
		panel.add(counterL);
	
		return panel;
	}
}
