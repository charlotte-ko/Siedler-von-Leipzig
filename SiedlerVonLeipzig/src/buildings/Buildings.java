package buildings;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;
import logic.Vector2D;

public abstract class Buildings implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected Vector2D position;
	protected ArrayList<Items> input_inventory = new ArrayList<>(); 
	protected ArrayList<Items> output_inventory = new ArrayList<>();
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	public boolean isConstructed = false;
	
	private int orderedForConstruction = 0;
	
	protected transient BufferedImage img;
	protected static BufferedImage contructionImg;
	protected static BufferedImage contructionImg2;
	
	
	
	static {
		try {
			contructionImg = ImageIO.read(Buildings.class.getResourceAsStream("/resources/ConstructionBuilding.png"));
			contructionImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/Plot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2, int tileSize,Vector2D camPos) {
		
		//eins nach oben eins nach links
		int posX = (int)((position.x-camPos.x)*tileSize) - tileSize;
		int posY = (int)((position.y-camPos.y)*tileSize) - tileSize*2;
		
		//gebäude nimmt 4 tiles ein
		g2.drawImage(img, posX, posY, tileSize*4,tileSize*4,null);
	}
	
	
	/**
	 * @brief Konstruktor
	 * @param name
	 * @param price
	 * @param position
	 */
	public Buildings(String name, Vector2D pos) {
		
		this.name = name;	
		this.position = pos;
		this.hasEmployee = false;
		this.isConstructed = false;
		
	}
	
	
	/**
	 * @brief Factory Methode, erstellt Buildings nach Typ
	 * @param building, der Buildingtyp
	 * @param pos
	 * @return House, Forester, Lodge, Sawmill, Mine, Forge
	 */
	public static Buildings createBuilding(String building, Vector2D pos) {
		switch(building) {
		case "House" : return new House(pos);
		case "Forester" : return new Forester(pos);
		case "Lodge" : return new Lodge(pos);
		case "Sawmill" : return new Sawmill(pos);
		case "Mine" : return new Mine(pos);
		case "Forge" : return new Forge(pos);
		default: throw new IllegalArgumentException("Unexpected Value: " + building);
		}
	}
	
	
	/**
	 * @brief sucht nächsten freien Settler und gibt ihn zurück
	 * @param settlerList, globale Liste aller Siedler
	 * @return closest (Settler), oder null bei nicht finden
	 */
	public ASettlers closestSettler(ArrayList<ASettlers> settlerList) {
		
		if(settlerList.isEmpty()) return null;
		
		float min_distance = Float.POSITIVE_INFINITY;
		ASettlers closest = null;
		
		for(ASettlers s : settlerList) {
			
			float distance = this.getPosition().getDistance(s.getPosition());
			if(!s.isEmployed() && !s.isWorking() && distance <= min_distance) {
				closest = s;
			}
			
		}return closest;	
	}
	
	/**
	 * @brief sucht nächsten Settler mit passendem Job
	 * @param settlerList
	 * @param job (gesuchter)
	 * @return Settler mit Job, oder null
	 * @note verwendet closestSettler()
	 */
	public ASettlers findSettler(ArrayList<ASettlers> settlerList, SettlersJobs job) {
		
		ArrayList<ASettlers> wanted = new ArrayList<>();
		
		for(ASettlers s : settlerList) {
			if(s.getJobName().equals(job)) {
				wanted.add(s);
			}
		}
		
		if(!wanted.isEmpty()) {
			return this.closestSettler(wanted);
		} 
		return null;
	}
	
	/**
	 * @brief weist einem Building einen employee zu, falls Job nicht vorhanden, wird normaler Settler gesucht,
	 * 		  falls kein Settler vorhanden wird der nächste freie genommen
	 * @param settlerList
	 * @param job (gesuchter)
	 * @return employee (Settler)
	 * @note nutzt closestSettler() und findSettler()
	 */
	public ASettlers findEmployee(ArrayList<ASettlers> settlerList, SettlersJobs job) {
		
		this.employee = this.findSettler(settlerList, job);
		
		if(this.employee == null) {
			this.employee = this.findSettler(settlerList, SettlersJobs.JOB_NORMAL);
		}
		
		if(this.employee == null) {
			this.employee = this.closestSettler(settlerList);
			
		}return employee;
	}
	
	/**
	 * @brief gibt Building an der übergebenen Position zurück oder null
	 * @param position
	 * @param buildingList
	 * @return Building an Position
	 */
	public static Buildings findBuilding(Vector2D position, ArrayList<Buildings> buildingList) {
		
		for(Buildings b : buildingList) {
			
			if(b.getPosition().same(position)) {
				return b;
			}
			
		}return null;
	}
	
	/**
	 * @brief findet Building welches ungefähr an der position ist
	 * @param position
	 * @param buildingList
	 * @param deviation 				erlaubte abweichung
	 * @return Building ungefähr bei Position
	 */
	public static Buildings findBuildingAprox(Vector2D position, ArrayList<Buildings> buildingList, float deviation) {
		
		for(Buildings b : buildingList) {
			
		    if(Math.abs(b.getPosition().x - position.x) <= deviation && Math.abs(b.getPosition().y - position.y) <= deviation) {
		    	return b;
		    }
		    
		}return null;
	}
	
	/**
	 * @brief baut gebäude auf
	 * @param delta
	 * @param settlerList
	 * @param BuildingsList
	 * @param itemList
	 * @param price (des zu bauendne Gebäudes)
	 * @param priceName, Materialien für Aufbau
	 * @return
	 */
	protected boolean updateConstruction(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList, int price, String priceName) {
		
		if(isConstructed) return true;
		
		img = contructionImg2;
		
		//check ob materialen im inventar vorhanden sind
		if(this.getInput_Inventory().size() >= price) {
			
			isConstructed = true;
			
			for(int i = 0; i < this.input_inventory.size(); i++) {
				Items.deleteItemFromList(this.input_inventory.get(i), itemList);
			}
			this.setInput_Inventory(new ArrayList<Items>());
			return true;
		}
		
		//order mehr items werden benötigt
		while(orderedForConstruction < price) {
			
			ASettlers s = this.findEmployee(settlerList, SettlersJobs.JOB_NORMAL);
			if(s == null) break;
			Items i = Items.closestItem(s.getPosition(), itemList, priceName);
			if(i == null) break;
			s.giveSettlerTask(i.getPosition(), SettlersAktion.GO_PICKUP_LEFT, i);
			s.giveSettlerTask(this.position, SettlersAktion.GO_BRING_LEFT, i);
			orderedForConstruction++;
			
		}return false;
	}
	
	/**
	 * @brief stellt Passenden Siedler ein
	 * @param settlerList
	 * @param job (der gesucht wird)
	 * @param noWorkerImg	Image welches angezeigt werden soll, wenn kein passender Siedler da ist
	 * @return true falls has Employee == true
	 */
	protected boolean hireEmployee(ArrayList<ASettlers> settlerList, BufferedImage noWorkerImg) {
		
		if(!hasEmployee) {
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_NORMAL);
			
			if(employee == null) {
				img = noWorkerImg;
				return false;
				
			}else{
			
				this.employee.setEmployed(true);
				this.hasEmployee = true;
			}
			
		}return true;
	}
	
	/**
	 * @brief sucht passendes Werkzeug und übergibt den Beruf dem Employee
	 * @param itemList
	 * @param itemType	(welches item Benötigt wird)
	 * @param job	(job, den der Employee bekommen soll)
	 * @note hat der Employee schon einen Job, so legt er sein Werkzeug auf den Boden
	 * @note wird kein Item benötigt, wird einfach null übergeben
	 */
	protected void getJobItemAndJob(ArrayList<Items> itemList, String itemType, SettlersJobs job) {
		
		if(employee == null) return;
		
		Items targetItem;
		
		if(itemType == null) {
			
			targetItem = Items.createItem("Nothing", employee.getPosition());
			
		}else {
			
			targetItem = Items.closestItem(employee.getPosition(), itemList, itemType);
		}
		
		if (targetItem == null) return;
		
		if (this.employee.getJobName().equals(SettlersJobs.JOB_NORMAL)){
			employee.setJobName(job);
			employee.giveSettlerTask(targetItem.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, targetItem);
			
		}else {
		
			employee.setJobName(job);
			employee.giveSettlerTask(employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, employee.getItem(false));
			employee.giveSettlerTask(targetItem.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, targetItem);
			
		} employee.giveSettlerTask(position, SettlersAktion.GO_FREE, null);
	}
	
	/**
	 * @brief checkt die Standardsachen für ein Gebäude (employee,job)
	 * @param delta
	 * @param settlerList
	 * @param itemList
	 * @param buildingList
	 * @param price				zum Bauen
	 * @param priceType			was benötigt wird zum Bauen
	 * @param constructed		Bild, welches nach dem Bauen angezeigt werden soll
	 * @param waiting			Bild, wenn Haus wartet
	 * @param job				Job vom employee
	 * @param jobItem			Item welches der Employee benötigt (kann auch null sein)
	 * @return true falls Gebäude aufgebaut ist, ein Employee mit richtigem Job und Item und gerade nicht arbeitet.
	 */
	protected boolean updateStartCheck(double delta,ArrayList<ASettlers>settlerList, ArrayList<Items>itemList, ArrayList<Buildings>buildingList, int price, String priceType, BufferedImage constructed, BufferedImage waiting, SettlersJobs job, String jobItem) {
		
		if(this.updateConstruction(delta, settlerList, buildingList, itemList, price, priceType)){
			
			img = constructed;
			
		}else {return false;}
		
		if(!this.hireEmployee(settlerList, waiting)) return false;
		
		if(employee.isWorking()) return false;
		
		if(!this.employee.getJobName().equals(job)) {
			
			this.getJobItemAndJob(itemList, jobItem, job);
			return false;
			
		}return true;
	}
	
	/**
	 * @brief entfernt Angestellten, legt alle Items in Inventaren ab und löscht Gebäude aus Liste
	 * @note House muss noch abgefangen werden
	 */
	public void deconstruct(ArrayList<Buildings> buildingsList) {
		
		if(this.isConstructed && !(this instanceof House)) {
			
			this.employee.deleteAllTasks();
			this.employee.giveSettlerTask(this.employee.getPosition(), SettlersAktion.GO_BRING_LEFT, this.employee.getItem(true));
			this.employee.giveSettlerTask(this.employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, this.employee.getItem(false));
			this.employee.setEmployed(false);
			this.employee.setJobName(SettlersJobs.JOB_NORMAL);
			
			for(Items i : this.input_inventory) {
				i.setInInventoryInput(false);
			}
			
			for(Items i : this.output_inventory) {
				i.setInInventoryOutput(false);
			}
		}
		
		if(this.isConstructed) {
			
			buildingsList.remove(this);
			
		}return;
	}
	
	public abstract void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList);
	
	
	public  String getName() {
		return this.name;
	}
	
	public Vector2D getPosition() {
		return this.position;
	}
	
	public ArrayList<Items> getOutput_Inventory() {
		return this.output_inventory;
	}
	
	public ArrayList<Items> getInput_Inventory() {
		return this.input_inventory;
	}
	
	public void setOutput_Inventory(ArrayList<Items> newInventory) {
		this.output_inventory = newInventory;
	}
	
	public void setInput_Inventory(ArrayList<Items> newInventory) {
		this.input_inventory = newInventory;
	}	
}
