package buildings;

import java.util.ArrayList;

import logic.Settlers;
import logic.Vector2D;

public interface Buildable {

	public void createBuilding();
	
	public void produce();
	
	public void makeSettlers();

	/**
	 * Methode um passenden Siedler zu finden der Bäume fällen kann
	 */
	public Settlers findSettler(Vector2D current_pos, ArrayList<Settlers> settlerList);
}
