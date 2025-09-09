package logic;

import java.util.ArrayList;
import javax.swing.JFrame;
import gui.Startmenu;
import presenter.StartPresenter;

class Main {
	
	public static void main(String[] args) {
		
		GameModel model = new GameModel();
		Startmenu view = new Startmenu();
		StartPresenter presenter = new StartPresenter(model,view);
		
		presenter.start();
	}
}

