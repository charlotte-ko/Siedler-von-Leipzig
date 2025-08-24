package buildings;

import java.util.ArrayList;

import settlers.ISettlers;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {
	
	private static Vector2D spawn = new Vector2D();

	/**
	 * Vector, welcher alle Siedler des Hauses speichert
	 */
	public ArrayList<ISettlers> sInventory = new ArrayList<>();
	
	/**
	 * Konstruktor des Wohnhauses, übergibt Namen und Preis an Super
	 */
	public House(Vector2D pos) {
		super("House", 10, spawn);
	}
	
	/**
	 * Methode um Siedler zu produzieren und dem Inventar zuzufügen
	 */
	public void makeSettlers() {
		//sInventory.add(new ISettlers(new Vector2D()));
		/**
		 * Position muss prezieser angegeben werden !!!
		 */
		sInventory.add(ASettlers.createSettler("NormalSettler",new Vector2D()));
	}
	
	/**
	 * Kontrollausgabe um Inventar auszugeben
	 */
	public void produce() {
		if(sInventory.isEmpty()) {
			System.out.println("Nix im Vektor");
			return;
		}
		
		System.out.println("Inventar: " + this.sInventory.toString() + " Position: " + spawn.toString());}

	@Override
	public void createBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISettlers findSettler(Vector2D current_pos, ArrayList<ISettlers> settlerList) {
		// TODO Auto-generated method stub
		return null;
	}
}
