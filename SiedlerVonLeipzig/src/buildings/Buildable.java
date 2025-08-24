package buildings;

import java.util.ArrayList;

import settlers.ISettlers;
import logic.Vector2D;

public interface Buildable {

	public void createBuilding();
	
	public void produce();
	
	public void makeSettlers();

	/**
	 * Methode um passenden Siedler zu finden der Bäume fällen kann
	 */
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList);
}
