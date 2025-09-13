package presenter;

import java.io.File;
import java.util.ArrayList;

import gui.Game;
import gui.LoadGame;
import logic.GameModel;

public class LoadPresenter {
	
	GameModel model;
	LoadGame view;
	
	public LoadPresenter(GameModel model, LoadGame view) {
		this.model = model;
		this.view = view;
		view.setPresenter(this);
	}
	
	/**
	 * @brief Methode um aus dem lokalen Ordner mit Spielerdaten die Spielernamen in ein Array zu laden 
	 * @return
	 */
	public ArrayList<String> getUserData() {
		File ordner = new File("../save/");
		String[] fileNames = ordner.list();
			
		if(ordner != null) {
			for(String f : fileNames) {
				if(f.endsWith(".bin")) {
					String playerName = f.substring(0, f.length() -4);
					model.userData.add(playerName);	
				}
			}
			return model.userData;
		}
		return null;
	}
	
	public void loadUserData(String name) {
		model.loadGame(name);
	}
	
	public void openSavedGame() {
		view.dispose();
		model.fillMap();
		Game save = new Game();
		GamePresenter gPresenter = new GamePresenter(model, save);
		gPresenter.start();
	}
	
	public void start() {
		view.setVisible(true);
	}
}
