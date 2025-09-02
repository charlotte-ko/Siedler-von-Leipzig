package buildings;

import java.util.ArrayList;

import items.IItems;
import settlers.ISettlers;
import logic.Vector2D;

public interface IBuildings {

	public void createBuilding();
	
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList);

	/**
	 * Methode um nächsten Siedler mit bestimmten Item zu finden
	 */
	ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList, String name);

	/**
	 * Methode um nächsten Siedler mit gewünschtem Item zu finden
	 */
	ISettlers findSettler(ArrayList<ISettlers> settlerList, String job);

	/**
	 * Methode um nächsten Siedler mit gewünschtem Item zu finden
	 */
	ISettlers findSettler(ArrayList<ISettlers> settlerList, String job, IItems target);
}
