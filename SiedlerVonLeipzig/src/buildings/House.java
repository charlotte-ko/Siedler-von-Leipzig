package buildings;

import java.util.ArrayList;

import items.IItems;
import settlers.ISettlers;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {

	/**
	 * Vector, welcher alle Siedler des Hauses speichert, brauchen wir nicht, weil das in der globalen Liste steht
	 */
	public ArrayList<ISettlers> sInventory = new ArrayList<>();
	
	/**
	 * Konstruktor des Wohnhauses, übergibt Namen und Preis an Super
	 */
	public House(Vector2D pos) {
		super("House", 10, pos);
	}
	
	/**
	 * Methode um Siedler zu produzieren und dem Inventar zuzufügen, gibt zuletzt produzierten Siedler zurück
	 */
	public ISettlers makeSettlers(ArrayList<ISettlers> settlerList) {
	
		if(sInventory.size() < 10) {
			settlerList.add(ASettlers.createSettler("NormalSettler", this.getPosition()));
		} 
		return settlerList.get(settlerList.size() - 1);
	}

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, String job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISettlers findSettler(ArrayList<ISettlers> settlerList, String job, IItems target) {
		// TODO Auto-generated method stub
		return null;
	}
}
