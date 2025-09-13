package presenter;

import java.util.ArrayList;

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
	
	public ArrayList<String> getUserData() {
		return model.userData;
	}
	
	public void openLoad() {
		view.setVisible(true);
	}
}
