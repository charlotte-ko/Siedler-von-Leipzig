package logic;

public class Sawmill extends Buildings {

	public Sawmill() {
		super("Sawmill", 10);
	}

	//Methode um Planken herzustellen und ins Inventar zu packen -> Siedler Inventar überprüfen wie??
	public Planks makePlanks() {
		for(Items item : inventory) {
			if(item.equals("Saw")) {
				inventory.add(new Planks());
			}
		}
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);};
}
