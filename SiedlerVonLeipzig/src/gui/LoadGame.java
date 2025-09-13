package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import presenter.LoadPresenter;
import presenter.StartPresenter;

public class LoadGame extends BaseGUI {

	LoadPresenter presenter;
	
	ArrayList<String> userData = new ArrayList<>();
	
 	public LoadGame() {
		super("Speicherdaten");
		

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(700, 700));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(brown);
		this.setLayout(new GridBagLayout());
	}

	public void setPresenter(LoadPresenter presenter) {
		this.presenter = presenter;
		userData = presenter.getUserData();
		this.setButtons();
	}
	
	public JButton[] getButtons() {
		JButton[] buttons = new JButton[userData.size()];
		
		for(int i = 0; i < userData.size(); i++) {
			JButton name = new JButton(userData.get(i));
			buttons[i] = name;
		}
		
		return buttons;
	}
	
	public void setButtons() {
		JButton[] buttons = getButtons();
		
		for(JButton b : buttons) {
			b.setBackground(lightsalmon);
			b.setForeground(brown);
			b.setOpaque(true);
			b.setBorderPainted(false);
			this.add(b);
			
			
			b.addActionListener(e -> presenter.loadUserData(b.getText()));
		}
	}
}
