package presenter;

import gui.Game;
import gui.GameShop;
import logic.GameModel;

public class GamePresenter {
	public GameModel model;
	public Game view;
	
	GamePresenter(GameModel model, Game view){
		this.model = model;
		this.view = view;
		view.setPresenter(this);
	}

	public void start() {
		model.setupGameField();
		view.startGameLoop();
		view.setVisible(true);
	}
	
	public void openShop() {
		GameShop shop = new GameShop();
		ShopPresenter presenter = new ShopPresenter(model, shop);
		
		presenter.start();
	}
	
	public void toggleHammer() {
		 {
				
				view.hammerActive = !view.hammerActive;
				
				if(view.hammerActive) {
					view.attachHammer();
				}else {
					view.removeHammer();
				}
			}
	}
}
