package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presenter.StartPresenter;

public class Startmenu extends BaseGUI{

	private static final long serialVersionUID = 1L;
	
	JButton button1 = new JButton("Neues Spiel");
	JButton button2 = new JButton("Weiterspielen");
	JButton button3 = new JButton("Debug Modus");
	JButton button4 = new JButton("Beenden");
	
	JButton[] buttons = {button1, button2, button3, button4};
	
	private Image background;
	
	private StartPresenter presenter;
	
	public Startmenu() {
		
		super("Startmenue");
		
		setWindowBase(this, 800, 600, null, foresty);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		background = loadImage("Background.png");
		
		setContentPane(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(background != null) {
					g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
				}
			}
		});
		
		getContentPane().setLayout(new GridBagLayout());
		setButtons();
		ButtonLayout(getContentPane());
	}
	
	public void setPresenter(StartPresenter presenter) {
		this.presenter = presenter;
	}
	
	private Image loadImage(String png) {
		URL path = this.getClass().getResource("/resources/" + png);
		return new ImageIcon(path).getImage();
	}
	
	private void setButtons() {
		
		for(JButton b : buttons) {
			
			b.setBackground(pine);
			b.setForeground(foresty);
			b.setOpaque(true);
			b.setBorderPainted(false);
		}
	
		button1.addActionListener(e -> presenter.buttonStartNewGame());
		button2.addActionListener(e -> presenter.buttonLoad());
		button3.addActionListener(e -> presenter.buttonDebugging());
		button4.addActionListener(e -> presenter.end());
	}
	
	private void ButtonLayout(Container e) {
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.insets = new Insets(10, 5, 10, 5);
		grid.ipady = 10;
		
		grid.gridx = 0;
		grid.gridy = 1;
		grid.gridwidth = 1;
		grid.weightx = 0.3;
		e.add(Box.createHorizontalGlue(), grid);
		
		grid.gridx = 1;
		grid.gridwidth = 2;
		grid.weightx = 0.4;
		e.add(button1, grid);
		
		grid.gridy = 2;
		e.add(button2, grid);
		
		grid.gridy = 3;
		e.add(button3, grid);
		
		grid.gridy = 4;
		grid.gridwidth = 1;
		grid.weightx = 0.5;
		e.add(button4, grid);
		
		grid.gridx = 3;
		grid.gridy = 1;
		grid.weightx = 0.3;
		e.add(Box.createHorizontalGlue(), grid);
		
		grid.gridx = 0;
	    grid.gridy = 0;
	    grid.gridwidth = 2;
	    grid.weighty = 1.0;
	    grid.weightx = 1.0;
	    grid.ipady = 0;
	    grid.insets = new Insets(0, 0, 0, 0);
	    e.add(Box.createGlue(), grid);
		
	}
	
	public void openNewGame() {
		
		JFrame NewFrame = new JFrame("Neues Spiel");
		
		setWindowBase(NewFrame, 600, 200, this, foresty);
		
		NewFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		NewFrame.setLayout(new GridBagLayout());

		JLabel name = new JLabel("Name:");
		
		name.setForeground(pine);
		name.setFont(new Font("Arial", Font.BOLD, 20));
		
		JTextField field_name = new JTextField(20);
		
		field_name.setBackground(pine);
		field_name.setForeground(foresty);
		
		JButton confirm = new JButton("Bestätigen");
		
		confirm.setBackground(pine);
		confirm.setForeground(foresty);
		
		confirm.addActionListener(e -> {
			
			if(!(field_name.getText().isEmpty())) {
				
				presenter.setPlayerName(field_name.getText());
				NewFrame.dispose();
				presenter.makeNewGame();
			}
		});
		
		NewGameLayout(NewFrame, name, field_name, confirm);
		NewFrame.setVisible(true);
	}
	
	public void openDebugging() {
		
		this.dispose();
		presenter.buttonDebugging();
	}
	
	private void NewGameLayout(Container e, JLabel name, JTextField field, JButton confirm) {
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(20, 20, 20, 20);
		axis.fill = GridBagConstraints.HORIZONTAL;
		
		axis.gridy = 0;
		axis.gridx = 0;
		axis.weightx = 0.2;
		e.add(name, axis);
		
		axis.gridx = 1;
		axis.weightx = 0.6;
		field.setPreferredSize(new Dimension(100, 30));
		e.add(field, axis);
		
		axis.gridx = 2;
		axis.weightx = 0.2;
		e.add(Box.createHorizontalGlue(), axis);
		
		axis.gridx = 1;
		axis.gridy = 1;
		axis.weightx = 0.6;
		e.add(confirm, axis);
	
	}
}
