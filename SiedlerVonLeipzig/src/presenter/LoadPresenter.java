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
	
	public void start() {
		
		view.setVisible(true);
	}
	
	/**
	 * @brief Methode um aus dem lokalen Ordner mit Spielerdaten die Spielernamen in ein Array zu laden 
	 * @return Array mit allen UserNamen
	 */
	public ArrayList<String> getUserData() {
		
		File ordner = new File("../save/");
		String[] fileNames = ordner.list();
			
		if(ordner != null) {
			
			for(String f : fileNames) {
				if(f.endsWith(".bin")) {
					String playerName = f.substring(0, f.length() -4);
					model.getUserData().add(playerName);	
				}
			}
			
			
		} return model.getUserData();
	}
	
	/**
	 * @brief 
	 * @param name
	 */
	public void loadUserData(String name) {
		
		view.dispose();
		model.fillMap();
		Game save = new Game();
		GamePresenter gPresenter = new GamePresenter(model, save);
		model.playerName = name;
		gPresenter.startSave(name);
	}
}
