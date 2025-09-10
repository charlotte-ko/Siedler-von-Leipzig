package presenter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gui.GameShop;
import logic.GameModel;

public class ShopPresenter {

	GameShop view;
	GameModel model;
	
	ShopPresenter(GameModel model, GameShop view) {
		this.model = model;
		this.view = view;
		view.setPresenter(this);
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	public void onClick() {
		
		System.out.println(view.active);
		view.active = !view.active;
		System.out.println(view.active);
	}
		/*
		if(view.active) {
			view.attach();
		}
		else { view.detach(); }
		view.active = !view.active;
	}*/
}
