package settlers;

import logic.Vector2D;

import java.util.ArrayList;

import items.IItems;

public interface ISettlers {
	public Vector2D getPosition();
	public void setPosition(Vector2D position);
	
	public float getSpeed();
	public void setSpeed(float speed);
	
	public boolean isWorking();
	
	/**
	 * @brief gibt Item zurück welches zurzeit vom Siedler in einer Hand getragen wird
	 * @param leftHand true linkeHand, false rechteHand
	 * @return IItem aus der angegebenen Hand
	 */
	public IItems getItemHand(boolean leftHand);
	/**
	 * @brief setzt Item welches in einer Hand gehalten werden soll
	 * @param item
	 * @param leftHand true linkeHand, false rechteHand
	 */
	public void setItemHand(IItems item,boolean leftHand,ArrayList<ISettlers> settlersList);
	/**
	 * @brief nimmt dem Siedler das Item aus seiner Hand
	 * @param leftHand
	 * @return Item welches gerade noch gehalten wurde
	 */
	public IItems takeItemHand(boolean leftHand,ArrayList<ISettlers> settlersList);
	
	/**
	 * @brief gebe einen Siedler eine Aufgabe in seine Aufgabenliste
	 * @param aufgabe welche als nächstes gemacht werden soll
	 */
	public void giveSettlerTask(Vector2D punkt,SettlersAktion aufgabe);
}
