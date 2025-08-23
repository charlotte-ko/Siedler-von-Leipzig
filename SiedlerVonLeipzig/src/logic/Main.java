package logic;

import java.util.Vector;

import buildings.Buildable;
import buildings.Buildings;
import buildings.House;
import items.IItems;

public class Main {

	/**
	 * Vektor, der erstellte Gebäude speichert, im Moment von Typ Buildable, weil das Interface ja Gebäude erstellt
	 */
	private static Vector<Buildable> placed = new Vector<>();
	
	public static void main(String[] args) {
		
		House build1 = new House(null);
		build1.makeSettlers();
		Buildable build = Buildings.createBuilding("House", null);
		build.makeSettlers();
		placed.add(build);
		placed.add(build1);
		
		for(Buildable p : placed) {
			p.produce();
		}
	}

}
