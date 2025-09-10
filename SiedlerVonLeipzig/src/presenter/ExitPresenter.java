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
	
	public void start() {
		view.setVisible(true);
	}
	
	public void exit() {
		System.exit(0);
	}
}
