package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;

import presenter.LoadPresenter;
import presenter.StartPresenter;

public class LoadGame extends BaseGUI {

	LoadPresenter presenter;
	
	ArrayList<String> userData = presenter.getUserData();
	
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
		this.setButtons();
	}
	
	public void setButtons() {
		
		for(String u : userData) {
			JButton name = new JButton(u);
			this.add(name);
		}
	}
}
