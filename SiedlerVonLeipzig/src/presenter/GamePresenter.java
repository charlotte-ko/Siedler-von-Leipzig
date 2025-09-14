package presenter;

import gui.Exit;
import gui.Game;
import gui.GameInv;
import gui.GameShop;
import logic.GameModel;
import logic.Renderer;
import logic.Vector2D;

public class GamePresenter {
	
	public GameModel model;
	public Game view;
	public boolean waiting;
	
	boolean placeBuilding = false;
	String placeBuildingName;
	
	private Vector2D camPos;
	private int tileSize;
	
	Renderer renderer;
	
	GamePresenter(GameModel model, Game view){
		this.model = model;
		this.view = view;
		view.setPresenter(this);
		renderer = new Renderer(this);
	}
	
	/**
	 * @brief startet ein neues Spiel
	 */
	public void start() {
		
		model.setupGameField();
		renderer.startGameThread();
		view.setVisible(true);
	}
	
	/**
	 * @brief lädt vorhandenen Spielstand
	 * @param name
	 */
	public void startSave(String name) {
		
		model.loadGame(name);
		renderer.startGameThread();
		view.setVisible(true);
	}
	
	/**
	 * @brief startet den Debug Modus
	 */
	public void startDebug() {
		
		model.setupDebug();
		model.playerName = "Debug";
		renderer.startGameThread();
		view.setVisible(true);
	}
	
	/**
	 * @brief öffnet das ShopFenster
	 */
	public void openShop() {
		
		GameShop shop = new GameShop();
		ShopPresenter presenter = new ShopPresenter(model, shop, this, view);
		disableMouseIcon();
		presenter.start();
	}
	
	/**
	 * @brief öffnet InventarFenster
	 */
	public void openInventory() {
		
		GameInv inventory = new GameInv();
		InventoryPresenter presenter = new InventoryPresenter(inventory, model);
		disableMouseIcon();
		presenter.start();
	}
	
	/**
	 * @brief öffnet das Exit Fenster
	 */
	public void openExit() {
		
		Exit exit = new Exit();
		ExitPresenter presenter = new ExitPresenter(exit, model);
		disableMouseIcon();
		presenter.start();
	}
	
	/**
	 * @brief speichert eine Position und platziert dort ein ausgewähltes Gebäude
	 * @param pos
	 */
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
			case "Forge": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			case "Mine": 
				model.constructBuilding(placeBuildingName,screenToWorld(pos));
				break;
			default: model.constructItem(null, pos);
			}
			placeBuilding = false;
		}
	}
	
	public boolean keepPosition(Vector2D pos) {
		
		return model.testAvailabelSpace(screenToWorld(pos));
	}
	
	/**
	 * @brief rechnet die Mausposition in geeignete floats um
	 * @param mouse
	 * @return Vector mit umgerechneten Werten
	 */
	private Vector2D screenToWorld(Vector2D mouse) {
		float x = mouse.x / (float)tileSize;
		float y = mouse.y / (float)tileSize;
		x += camPos.x;
		y += camPos.y;
		return new Vector2D(x,y);
	}
 	
	/**
	 * @brief aktiviert den Platzierungsmodus und speichert ausgewähltes Gebäude
	 * @param placed
	 */
	public void place(String placed) {
		
		placeBuilding = true;
		placeBuildingName = placed;
	}
	
	/**
	 * @brief prüft ob HammerIcon bereits aktiviert und hängt ein Icon an wenn nicht bzw. löst Icon wenn ja
	 */
	public void toggleHammer() {
		 {
				view.ShopItemActive = false;
				view.removeShopItem();
				
				view.hammerActive = !view.hammerActive;
				
				if(view.hammerActive) {
					view.attachHammer();
				}else {
					view.removeHammer();
				}
			}
	}
	
	/**
	 * @brief Hängt bereits ein Icon an der Maus, wird dieses nun gelöst
	 */
	private void disableMouseIcon() {
		
		if(view.hammerActive || view.ShopItemActive) {

			view.ShopItemActive = false;
			view.hammerActive = false;
			
			view.removeHammer();
			view.removeShopItem();
		}
	}
	
	/**
	 * @brief gibt die Positon weiter, an welcher ein Gebäude gelöscht werden soll
	 * @param pos
	 */
	public void toggleDeconstruction(Vector2D pos) {
		
		model.deconstructBuilding(screenToWorld(pos));
	}
	
	/**
	 * @return in Model gespeicherte Position
	 */
	public Vector2D giveModelPos() {
		return model.Position;
	}
	
	/**
	 * @brief fordert GUI auf Icon eines gewählten Gebäudes an die Maus zu binden
	 * @param purchase
	 */
	public void toggleShop(String purchase) {
		
		view.ShopItemActive = true;
		view.attachShopItem(purchase);
	
	}
	
	public void giveCamera(Vector2D camPos,int tileSize) {
		
		this.camPos = camPos;
		this.tileSize = tileSize;
	}
}
