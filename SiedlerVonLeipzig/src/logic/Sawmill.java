package logic;

public class Sawmill extends Buildings {

	/**
	 * Konstruktor, übergibt Namen und Preis
	 */
	public Sawmill() {
		super("Sawmill", 10);
	}

	/**
	 * Methode um Bretter herzustellen, Zuweisung zu Siedler fehlt noch
	 */
	public void makePlanks() {
		inventory.add(new Plank(new Vector2D()));	
	}
	
	/**
	 * Kontrollausgabe um Inventar auszugeben
	 */
	public void produce() {System.out.println(inventory);};
}
