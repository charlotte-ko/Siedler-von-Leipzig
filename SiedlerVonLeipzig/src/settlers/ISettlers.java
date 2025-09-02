package settlers;

import logic.Vector2D;

import java.util.ArrayList;

import buildings.IBuildings;
import items.IItems;
import items.Items;

public interface ISettlers {
	public Vector2D getPosition();
	public void setPosition(Vector2D position);
	
	public float getSpeed();
	public void setSpeed(float speed);
	
	public boolean isWorking();
	
	/**
	 * @brief direktes abfragen welches Item in den Händen ist
	 * @param leftHand true=linke Hand, false= rechte Hand
	 * @return das Item oder null
	 * @note nicht sicher, bitte getItemHand etc. verwenden
	 */
	public IItems getItem(boolean leftHand);
	/**
	 * @brief gibt Item direkt, ohne check ob Siedler sich "verwandeln" muss
	 * @param leftHand true=links, false=rechts
	 * @param tem
	 * @note nicht sicher, bitte setItemHand etc. verwenden
	 */
	public void setItem(boolean leftHand,IItems tem);
	
	/**
	 * @brief gibt Item zurück welches zurzeit vom Siedler in einer Hand getragen wird
	 * @param leftHand true linkeHand, false rechteHand
	 * @return IItem aus der angegebenen Hand
	 */
	public IItems getItemHand(boolean leftHand);
	/**
	 * @brief setzt Item welches in einer Hand gehalten werden soll und wechselt Siedler Job falls nötig
	 * @param item
	 * @param leftHand true linkeHand, false rechteHand
	 */
	public void setItemHand(IItems item,boolean leftHand,ArrayList<ISettlers> settlersList);
	/**
	 * @brief nimmt dem Siedler das Item aus seiner Hand und wechselt Siedler Job fals nötig
	 * @param leftHand
	 * @return Item welches gerade noch gehalten wurde
	 */
	public IItems takeItemHand(boolean leftHand,ArrayList<ISettlers> settlersList);
	
	public String getJob();
	
	public void setJob(String job);
	
	/**
	 * @brief gibt einem Siedler eine weitere Aufgabe
	 * @param punkt wo der Siedler die Aufgabe machen soll
	 * @param aufgabe was er machen soll (siehe SettlersAktion)
	 * @param target mit welchem Item er interagieren soll
	 * @note 
	 * Wenn mit einem Gebäude Interagiert werden soll muss die position mit dem Gebäude übereinstimmen.
	 * Manche SettlerAktion benötigen kein Item, in dem fall einfach null verwenden
	 */
	public void giveSettlerTask(Vector2D punkt,SettlersAktion aufgabe,IItems target);
	
	/**
	 * @brief update funktion die jeden frame ausgeführt werden soll
	 * @param delta zeit
	 * @param itemList
	 * @param buildingList
	 * @param settlerList
	 */
	public void update(float delta,ArrayList<IItems> itemList,ArrayList<IBuildings> buildingList,ArrayList<ISettlers> settlerList);
	
	public IItems closestItem(Vector2D current_pos, ArrayList<IItems> ItemList, String target);
}
