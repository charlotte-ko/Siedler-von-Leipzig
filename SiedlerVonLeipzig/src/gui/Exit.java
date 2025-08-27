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

public class Exit extends JFrame {
	
	JButton save = new JButton("Speichern");
	JButton end = new JButton("Beenden");
	JButton send = new JButton("Speichern & Beenden");
	
	Color lightbrown = new Color(251, 216, 193);
	
	public Exit() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Speichern & Beenden");
		this.setSize(new Dimension(400, 600));
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		this.getContentPane().setBackground(new Color(247, 181, 138));
		
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
		save.setBackground(lightbrown);
		
		send.setPreferredSize(new Dimension(150, 60));
		send.setBackground(lightbrown);
		
		end.setPreferredSize(new Dimension(150,60));
		end.setBackground(lightbrown);
		end.addActionListener(e -> System.exit(0));
	}
}
