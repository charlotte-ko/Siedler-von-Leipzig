package presenter;

import gui.Exit;
import gui.Game;
import gui.GameInv;
import gui.GameShop;
import logic.GameModel;
import logic.Vector2D;

public class GamePresenter {
	public GameModel model;
	public Game view;
	public boolean waiting;
	
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
		ShopPresenter presenter = new ShopPresenter(model, shop, this, view);
		
		presenter.start();
	}
	
	public void openInventory() {
		GameInv inventory = new GameInv();
		InventoryPresenter presenter = new InventoryPresenter(inventory, model);
		presenter.start();
	}
	
	public void openExit() {
		Exit exit = new Exit();
		ExitPresenter presenter = new ExitPresenter(exit, model);
		presenter.start();
	}
	
	public void savePos(Vector2D pos) {
		model.Position = pos;
	}
	
	public void changeMode() {
		view.placementmode = true;
		
	}
	
	public void isWaiting() {
		if(view.placementmode) {
			waiting = true;
		}
	}
 	
	public void place(String building) {
		
		model.constructBuilding(building, model.Position);
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
