package presenter;

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
}
