package presenter;

import gui.Game;
import gui.GameShop;

import logic.GameModel;

public class ShopPresenter {

	GameShop view;
	Game game;
	GameModel model;
	GamePresenter presenter;
	
	
	public ShopPresenter(GameModel model, GameShop view, GamePresenter presenter, Game game) {
		
		this.model = model;
		this.view = view;
		this.presenter = presenter;
		this.game = game;
		view.setPresenter(this);
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	/**
	 * @brief erstellt ein Feld mit der Anzahl and vorhandenen Zahlungsmaterialien
	 * @return Feld mit Anzahl der vorhandenen Währungen
	 */
	public int[] getCurrency() {
		
		int[] currency = {model.inventarCounter()[0], model.inventarCounter()[1]};
		return currency;
	}
	
	/**
	 * @param item
	 * @return Preis eines gewählten Items
	 */
	public int givePrice(String item) {
		
		int price = model.getPrice(item);
		return price;
	}
	
	/**
	 * @param item
	 * @return Währung eines gewählten Items
	 */
	public String giveCurrency(String item) {
		
		String type = model.getPriceType(item);
		return type;
	}
	
	/**
	 * @brief Wenn ein Item im Shop gewählt wird, wird geprüft, ob genug waren für Zahlung vorhanden sind,
	 * nur wenn genug Materialien da sind, kann etwas platziert werden
	 * @param placed
	 */
	public void onClick(String placed) {
		
		int price = model.getPrice(placed);
		String type = model.getPriceType(placed);
		
		int[] availableCurrency = getCurrency();
		
		if(type == "Planks" && price > availableCurrency[1] ) {
			return;
			
		}else if(type == "Logs" && price > availableCurrency[0]) {
			return;
			
		}else {
			
			view.dispose();
			presenter.toggleShop(placed);
			presenter.place(placed);	
		}
	}
}
