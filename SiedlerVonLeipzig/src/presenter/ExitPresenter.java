package presenter;

import gui.Exit;
import logic.GameModel;

public class ExitPresenter {

	Exit view;
	GameModel model;
	
	ExitPresenter(Exit view, GameModel model) {
		this.view = view;
		this.model = model;
		view.setPresenter(this);
	}
	
	public String getCurrentPlayer() {
		return model.playerName;
	}
	
	public void start() {
		view.setVisible(true);
	}
	
	public void speichern() {
		model.saveGame(model.playerName);
		view.dispose();
	}

	public void sexit() {
		model.saveGame(model.playerName);
		System.exit(0);
	}
	
	public void exit() {
		System.exit(0);
	}
}
