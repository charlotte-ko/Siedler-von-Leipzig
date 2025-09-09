package presenter;

import gui.Game;
import logic.GameModel;

public class GamePresenter {
	public GameModel model;
	public Game view;
	
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
	
	
}
