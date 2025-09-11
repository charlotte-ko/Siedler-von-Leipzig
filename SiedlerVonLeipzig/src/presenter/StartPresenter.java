package presenter;

import gui.Game;
import gui.Startmenu;
import logic.GameModel;

public class StartPresenter {
	final private GameModel model;
	final private Startmenu view;
	
	public StartPresenter(GameModel model,Startmenu view) {
		this.model = model;
		this.view = view;
		view.setPresenter(this);
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	/**
	 * @brief aufgerufen wenn Button1 Start new Game gedrückt wird
	 */
	public void buttonStartNewGame() {
		model.fillMap();
		view.openNewGame();
	}
	
	public void setPlayerName(String name) {
		model.playerName = name;
	}
	
	/*
	 * @brief wird aufgerufen wenn Debug Modus gestartet wird
	 */
	public void buttonDebugging() {
		view.dispose();
		model.fillMap();
		Game debug = new Game();
		GamePresenter gPresenter = new GamePresenter(model, debug);
		gPresenter.startDebug();
	}
	
	public void makeNewGame() {
		view.dispose();
		Game gView = new Game();
		GamePresenter gPresenter = new GamePresenter(model,gView);
		gPresenter.start();
	}
	
	public void end() {
		System.exit(0);
	}
}
