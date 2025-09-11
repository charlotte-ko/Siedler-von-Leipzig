package presenter;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gui.Game;
import gui.GameShop;
import logic.GameModel;
import logic.Vector2D;

public class ShopPresenter {

	GameShop view;
	Game game;
	GameModel model;
	GamePresenter presenter;
	
	
	ShopPresenter(GameModel model, GameShop view, GamePresenter presenter, Game game) {
		this.model = model;
		this.view = view;
		this.presenter = presenter;
		this.game = game;
		view.setPresenter(this);
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	public void onClick(String building) {
		
		view.dispose();
		System.out.println(building);
		presenter.place(building);
		/*
		presenter.changeMode();
		
		while(game.placementmode) {
			if(!presenter.waiting) {
				presenter.place(building);
				game.placementmode = false;
			}
		}*/
	}
		/*
		if(view.active) {
			view.attach();
		}
		else { view.detach(); }
		view.active = !view.active;
	}*/
}
