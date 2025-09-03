package buildings;

import java.awt.Graphics2D;
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
	
	public void draw(Graphics2D g2,int tileSize);
	public void update(double delta);
}
