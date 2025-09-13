package gui;

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
		this.setLayout(new FlowLayout());
	}

	public void setPresenter(LoadPresenter presenter) {
		this.presenter = presenter;
		userData = presenter.getUserData();
		JPanel buttons = setButtons();
		buttons.setSize(new Dimension(700, 700));
		this.add(buttons);
	}
	
	public JPanel setButtons() {
		
		JPanel buttons = new JPanel();
		for(String u : userData) {
			JButton name = new JButton(u);
			name.setSize(new Dimension(200, 200));
			buttons.add(name);
		}
		return buttons;
	}
}
