package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import presenter.LoadPresenter;

public class LoadGame extends BaseGUI {

	LoadPresenter presenter;
	
	ArrayList<String> userData = new ArrayList<>();
	
 	public LoadGame() {
 		
		super("Speicherdaten");
		
		this.setWindowBase(this, 700, 700, null, brown);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
	}

	public void setPresenter(LoadPresenter presenter) {
		this.presenter = presenter;
		userData = presenter.getUserData();
		
		if(userData.isEmpty()) {
			this.displayLabel();
			
		}else {
			this.setButtons();
		}
	}
	
	private void displayLabel() {
		
		JLabel error = new JLabel("Keine Spielstände vorhanden!");
		error.setForeground(lightsalmon);
		error.setFont(big);
		error.setPreferredSize(new Dimension(100, 100));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayout(new BorderLayout());
		this.add(error, BorderLayout.CENTER);
	}
	
	private void setButtons() {
		
		JButton[] buttons = getButtons();
		
		for(JButton b : buttons) {
			
			b.setBackground(lightsalmon);
			b.setForeground(brown);
			b.setFont(big);
			b.setOpaque(true);
			b.setBorderPainted(false);
			this.add(Box.createRigidArea(new Dimension(10, 0)));
			this.add(b);
			
			b.addActionListener(e -> presenter.loadUserData(b.getText()));
		}
	}

	private JButton[] getButtons() {
		JButton[] buttons = new JButton[userData.size()];
		
		for(int i = 0; i < userData.size(); i++) {
			JButton name = new JButton(userData.get(i));
			buttons[i] = name;
		}
		return buttons;
	}
}
