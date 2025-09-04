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
	 * Methode um nächsten Siedler mit gewünschtem Item zu finden
	 */
	ISettlers findSettler(ArrayList<ISettlers> settlerList, ArrayList<IItems> itemList, String job, IItems target);
	

	public void draw(Graphics2D g2,int tileSize);
	//public void update(double delta);

	public String getName();
	
	public void getLogs(ISettlers worker, ArrayList<IItems> itemList);
	
	public ArrayList<IItems> getOutput_Inventory();
	
	public ArrayList<IItems> getInput_Inventory();
	
	public Vector2D getPosition();
	
	public void update(ArrayList<ISettlers> settlerList, ArrayList<IItems> itemList);
	
}
