package presenter;

import gui.GameInv;
import logic.GameModel;

public class InventoryPresenter {

	GameInv view;
	GameModel model;
	
	InventoryPresenter(GameInv view, GameModel model) {
		this.view = view;
		this.model = model;
		view.setPresenter(this);
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	public int[] getCounter() {
		
		int[] counter = model.inventarCounter();
		for(int i = 0; i < model.buildingList.size(); i++) {
			System.out.println("" + model.buildingList.get(i).getPosition().x + "," + model.buildingList.get(i).getPosition().y);
		}
		
		return counter;
	}
}
