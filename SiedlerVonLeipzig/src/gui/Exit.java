package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import presenter.ExitPresenter;

public class Exit extends BaseGUI {
	
	JButton save = new JButton("Speichern");
	JButton end = new JButton("Beenden");
	JButton send = new JButton("Speichern & Beenden");

	ExitPresenter presenter;
	
	public Exit() {
		super("Speichern & Beenden");
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(400, 600));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		this.getContentPane().setBackground(brown);
		
		setButtons();
		buttonLayout(this);
		
		this.setVisible(true);
	}
	
	private void buttonLayout(Container e) {
		
		GridBagConstraints axis = new GridBagConstraints();
		axis.insets = new Insets(30,10,30,10);
		axis.fill = GridBagConstraints.BOTH;
		
		axis.gridx = 0;
		axis.gridy = 0;
		e.add(save, axis);
		
		axis.gridy = 1;
		e.add(send, axis);
		
		axis.gridy = 2;
		e.add(end, axis);
		
	}
	
	private void setButtons() {
		
		save.setPreferredSize(new Dimension(150, 60));
		save.setBackground(lightsalmon);
		save.setBorderPainted(false);
		
		send.setPreferredSize(new Dimension(150, 60));
		send.setBackground(lightsalmon);
		send.setBorderPainted(false);
		
		end.setPreferredSize(new Dimension(150,60));
		end.setBackground(lightsalmon);
		end.setBorderPainted(false);
		end.addActionListener(e -> presenter.exit());
	}
	
	public void setPresenter(ExitPresenter presenter) {
		this.presenter = presenter;
	}
}
