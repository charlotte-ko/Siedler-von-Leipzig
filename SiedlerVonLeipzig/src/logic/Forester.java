package logic;

public class Forester extends Buildings {

	public Forester() {
		super("Forester", 10);
	}

	//Methode um Baumstämme zu produzieren und Inventar zuzufügen -> wie kommt man an das Inventar der Siedler??
	private Logs makeLogs() {
		for(Items item : inventory) {
			if(item.equals("Axe")) {
				inventory.add(new Logs());
			}
		}
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);};
 }
