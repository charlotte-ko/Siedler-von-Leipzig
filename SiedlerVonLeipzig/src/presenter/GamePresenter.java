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
	
	private Vector2D camPos;
	private int tileSize;
	
	GamePresenter(GameModel model, Game view){
		this.model = model;
		this.view = view;
		view.setPresenter(this);
	}

	public void start() {
		view.startGameLoop();
		view.setVisible(true);
	}
	
	public void startDebug() {
		model.setupDebug();
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
		if(placeBuilding) {
			switch(placeBuildingName) {
			case "House": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			case "Forester": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			case "Lodge": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			case "Sawmill": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			case "Axe": 
				model.constructItem(placeBuildingName,screenToWorld(pos));
				break;
			case "Saw": 
				model.constructItem(placeBuildingName,screenToWorld(pos));
				break;
			default: model.constructItem(null, pos);
			}
			//checken ob kein anderes Building da ist
			placeBuilding = false;
		}
	}
	
	private Vector2D screenToWorld(Vector2D mouse) {
		float x = mouse.x / (float)tileSize;
		float y = mouse.y / (float)tileSize;
		x += camPos.x;
		y += camPos.y;
		return new Vector2D(x,y);
	}
 	
	boolean placeBuilding = false;
	String placeBuildingName;
	
	public void place(String placed) {
		//warten bis geklickt wurde
		//checken ob abstand passt
		//model.constructBuilding(building, model.Position);
		placeBuilding = true;
		placeBuildingName = placed;
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
	
	public void toggleDeconstruction(Vector2D pos) {
		model.deconstructBuilding(screenToWorld(pos));
		System.out.println(screenToWorld(pos).x + " X " + screenToWorld(pos).y + " Y");
	}
	
	public Vector2D giveModelPos() {
		return model.Position;
	}
	
	public void toggleShop(String purchase) {
		
		view.ShopItemActive = true;
		if(!view.ShopItemActive) {
			System.out.println("Kaputt");
		}
		if(view.ShopItemActive) {
			view.attachShopItem(purchase);
			
		}
	}
	
	public void giveCamera(Vector2D camPos,int tileSize) {
		this.camPos = camPos;
		this.tileSize = tileSize;
	}
}
