package logic;

import java.util.Vector;

public class House extends Buildings {

	/**
	 * Vector, welcher alle Siedler des Hauses speichert
	 */
	public static Vector<Settlers> sInventory = new Vector<>();
	
	/**
	 * Konstruktor des Wohnhauses, übergibt Namen und Preis an Super
	 */
	public House() {
		super("House", 10);
	}
	
	/**
	 * Methode um Siedler zu produzieren und dem Inventar zuzufügen
	 */
	public void makeSettlers() {
		sInventory.add(new Settlers(new Vector2D()));
	}
	
	/**
	 * Kontrollausgabe um Inventar auszugeben
	 */
	public void produce() {System.out.println(inventory);}
}
