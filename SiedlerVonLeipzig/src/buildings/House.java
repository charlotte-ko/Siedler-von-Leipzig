package buildings;

import java.awt.Graphics2D;
import java.util.ArrayList;

import items.Items;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {

	private int counter = 0;
	
	/**
	 * @brief Konstruktor für House, erstellt Settler
	 * @param pos
	 */
	public House(Vector2D pos) {
		super("House", 10, pos);
	}
	
	/**
	 * @brief macht neuen Settler am House Standort und fügt ihn in Liste ein
	 * @param settlerList
	 */
	public void makeSettlers(ArrayList<ASettlers> settlerList) {
	
		settlerList.add(ASettlers.createSettler(this.getPosition()));
	}
	
	/**
	 * @brief Updatemethode, erstellt pro Haus 10 Settler und fügt sie Liste hinzu
	 * @param settlerList
	 * @note zeitlicher Aspekt fehlt noch
	 */
	public void update(ArrayList<ASettlers> settlerList) {
		
		if(this.counter < 10) {
			makeSettlers(settlerList);
		}
	}

	/**
	 * @note muss noch angepasst werden
	 */
	@Override
	public void draw(Graphics2D g2, int tileSize) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @note muss noch angepasst werden
	 */
	@Override
	public void update(double Delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList,
			ArrayList<Items> itemList) {
		// TODO Auto-generated method stub
		
	}
}
