package presenter;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gui.Game;
import gui.GameShop;
import items.Items;
import items.Log;
import items.Plank;
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
	

	public int[] getCurrency() {
		int[] currency = {model.inventarCounter()[0], model.inventarCounter()[1]};
		return currency;
	}
	
	public void onClick(String placed) {
		
		int price = model.getPrice(placed);
		int[] availableCurrency = getCurrency();
		
		if(placed == "House" && price > availableCurrency[1] ) {
			System.out.println("" + price);
			return;
			
		}else if(price > availableCurrency[0]) {
			System.out.println("" + price);
			return;
			
		}else {
			
			view.dispose();
			presenter.place(placed);
			updateList(model.itemList, placed, price);	
		}
	}
	
	private static String chooseCurrency(String purchase) {
		
		String wanted;
		
		switch(purchase) {
		
		case "House": wanted = "Plank";
			break;
		default: wanted = "Log";
		}
		
		return wanted;
	}
		
		
	private void updateList(ArrayList<Items> List, String purchase, int price) {
		
		Iterator<Items> index = List.iterator();
		String wanted = chooseCurrency(purchase);
		
		Items target = index.next();
		
		while(price != 0 && index.hasNext()) {
			
			if(!(target.getName().equals(wanted))) {
				target = index.next();
				
			}else {
				index.remove();
				if(index.hasNext()) {target = index.next();}
				price--;
			}
		}
	}
		
		/*
		presenter.changeMode();
		
		while(game.placementmode) {
			if(!presenter.waiting) {
				presenter.place(building);
				game.placementmode = false;
			}
		}*/
	
		/*
		if(view.active) {
			view.attach();
		}
		else { view.detach(); }
		view.active = !view.active;
	}*/
}
