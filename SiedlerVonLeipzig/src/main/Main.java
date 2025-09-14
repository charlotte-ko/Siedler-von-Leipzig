package main;

import gui.Startmenu;
import logic.GameModel;
import presenter.StartPresenter;

class Main {
	
	public static void main(String[] args) {
		
		GameModel model = new GameModel();
		Startmenu view = new Startmenu();
		StartPresenter presenter = new StartPresenter(model,view);
		
		presenter.start();
	}
}

