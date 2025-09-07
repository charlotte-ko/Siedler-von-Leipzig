package buildings;

import java.util.ArrayList;
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
}
