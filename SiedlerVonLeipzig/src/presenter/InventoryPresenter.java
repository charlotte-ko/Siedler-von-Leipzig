package presenter;

import gui.GameInv;
import logic.GameModel;

public class InventoryPresenter {

	GameInv view;
	GameModel model;
	
	InventoryPresenter(GameInv view, GameModel model) {
		this.view = view;
		this.model = model;
	}
	
	public void getCounter() {

	}
}
