package logic;

public class House extends Buildings {

	public House() {
		super("House", 10);
	}
	
	//Methode um Siedler zu produzieren, Bedingung fehlt noch
	public Settlers makeSettlers() {
		sInventory.add(new Settlers());
	}
	
	//Kontrollausgabe
	public void produce() {System.out.println(inventory);}
}
