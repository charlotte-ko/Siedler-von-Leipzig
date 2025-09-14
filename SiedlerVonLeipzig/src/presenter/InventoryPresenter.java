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
	
	/**
	 * @brief lädt die Anzahl aller vorhandenen (für das Spiel relevanten) Objekte in einen Array
	 * @return Array mit Objektanzahlen
	 */
	public int[] getCounter() {
		
		int[] counter = model.inventarCounter();
		return counter;
	}
}
