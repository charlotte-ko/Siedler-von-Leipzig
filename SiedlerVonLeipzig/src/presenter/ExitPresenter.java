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
	
	public void speichern(String PlayerName) {
		model.saveGame(PlayerName);
		view.dispose();
	}

	
	public void exit() {
		System.exit(0);
	}
}
