package buildings;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import settlers.ASettlers;
import settlers.Settler;
import settlers.SettlersAktion;
import settlers.SettlersJobs;
import logic.Vector2D;

public abstract class Buildings {
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
	
	
	protected Vector2D position;
	protected ArrayList<Items> input_inventory = new ArrayList<>(); 
	protected ArrayList<Items> output_inventory = new ArrayList<>();
	
	protected ASettlers employee;
	protected boolean hasEmployee;
	protected BufferedImage img;
	protected String name;
	
	
	public void draw(Graphics2D g2, int tileSize,Vector2D camPos) {
		//eins nach oben eins nach links
		int posX = (int)((position.x-camPos.x)*tileSize) - tileSize;
		int posY = (int)((position.y-camPos.y)*tileSize) - tileSize*2;
		
		//gebäude nimmt 4 tiles ein
		
		g2.drawImage(img, posX, posY, tileSize*4,tileSize*4,null);
	}
	
	public abstract void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList);
	
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
	}
	
	/**
	 * @brief Factory Methode, erstellt Buildings nach Typ
	 * @param building
	 * @param pos
	 * @return House, Forester, Lodge, Sawmill
	 */
	public static Buildings createBuilding(String building, Vector2D pos) {
		switch(building) {
		case "House" : return new House(pos);
		case "Forester" : return new Forester(pos);
		case "Lodge" : return new Lodge(pos);
		case "Sawmill" : return new Sawmill(pos);
		default: throw new IllegalArgumentException("Unexpected Value: " + building);
		}
	}
	
	/**
	 * @brief sucht nächsten freien Settler und gibt ihn zurück
	 * @param settlerList
	 * @return closest (Settler), oder null bei nicht finden
	 */
	public ASettlers closestSettler(ArrayList<ASettlers> settlerList) {
		if(settlerList.isEmpty()) return null;
		
		float min_distance = Float.POSITIVE_INFINITY;
		ASettlers closest = null;
		
		for(ASettlers s : settlerList) {
			float distance = this.getPosition().getDistance(s.getPosition());
			if(!s.getEmployed() && !s.isWorking() && distance <= min_distance) {
				closest = s;
			}
		}
		return closest;
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
	 * @brief weist einem Building einen employee zu, falls Job nicht vorhanden wird normaler Settler gesucht,
	 * 		  falls kein Settler vorhanden wird der nächste freie genommen
	 * @param settlerList
	 * @param job
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
		}
	
		return employee;
	}
	
	/**
	 * @brief Getter für Building Position
	 * @return this.position
	 */
	public Vector2D getPosition() {
		return this.position;
	}
	
	/**
	 * @brief Getter für Building output_inventory
	 * @return this.output_inventory
	 */
	public ArrayList<Items> getOutput_Inventory() {
		return this.output_inventory;
	}
	
	/**
	 * @brief Getter für Building input_inventory
	 * @return this.input_inventory
	 */
	public ArrayList<Items> getInput_Inventory() {
		return this.input_inventory;
	}
	
	/**
	 * @brief Setter für Building output_inventory
	 * @param newInventory
	 */
	public void setOutput_Inventory(ArrayList<Items> newInventory) {
		this.output_inventory = newInventory;
	}
	
	/**
	 * Setter für Building input_inventory
	 * @param newInventory
	 */
	public void setInput_Inventory(ArrayList<Items> newInventory) {
		this.input_inventory = newInventory;
	}

	/**
	 * @brief gibt Building an der Position zurück oder null
	 * @param position
	 * @param buildingList
	 * @return
	 */
	public static Buildings findBuilding(Vector2D position, ArrayList<Buildings> buildingList) {
		for(Buildings b : buildingList) {
			if(b.getPosition().same(position)) {
				return b;
			}
		}
		return null;
	}
	
	public  String getName() {
		return this.name;
	}
	
	public boolean isContructed = false;
	private int orderedForConstruction = 0;
	/**
	 * @brief baut gebäude auf
	 * @param delta
	 * @return
	 */
	protected boolean updateConstruction(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList,int price,String priceName) {
		if(isContructed)return true;
		
		img = this.contructionImg2;
		
		//check ob materialen im inventar vorhanden sind
		if(this.getInput_Inventory().size() >= price) {
			isContructed = true;
			for(int i = 0; i < this.input_inventory.size();i++) {
				Items.dellItemFromList(this.input_inventory.get(i), itemList);
			}
			this.setInput_Inventory(new ArrayList<Items>());
			return true;
		}
		
		//order mehr items
		while(orderedForConstruction < price) {
			ASettlers s = this.findEmployee(settlerList, SettlersJobs.JOB_NORMAL);
			if(s == null)break;
			Items i = Items.closestItem(s.getPosition(), itemList, priceName);
			if(i == null)break;
			s.giveSettlerTask(i.getPosition(), SettlersAktion.GO_PICKUP_LEFT, i);
			s.giveSettlerTask(this.position, SettlersAktion.GO_BRING_LEFT, i);
			orderedForConstruction++;
		}
		return false;
	}
	/**
	 * @brief stellt Passenden Siedler ein
	 * @param settlerList
	 * @param job	der gesucht wird
	 * @param noWorkerImg	Image welche angezeigt werden soll wenn kein passender Siedler da ist
	 * @return true falls has Employee == true
	 */
	protected boolean hireEmployee(ArrayList<ASettlers> settlerList,BufferedImage noWorkerImg) {
		if(!hasEmployee) {
			this.employee = this.findEmployee(settlerList, SettlersJobs.JOB_NORMAL);
			
			if(employee == null) {
				img = noWorkerImg;
				return false;
			}
			else this.hasEmployee = true;
		}
		return true;
	}
	
	/**
	 * @brief entfernt Angestellten, legt alle Items in Inventaren ab und löscht Gebäude aus Liste
	 */
	protected void deconstruct(ArrayList<Buildings> buildingsList) {
		
		if(this.isContructed) {
			this.employee.giveSettlerTask(this.employee.getPosition(), SettlersAktion.GO_BRING_LEFT, this.employee.getItem(true));
			this.employee.giveSettlerTask(this.employee.getPosition(), SettlersAktion.GO_BRING_RIGHT, this.employee.getItem(false));
			this.employee.setEmployed(false);
			this.employee.setJobName(SettlersJobs.JOB_NORMAL);
			
			for(Items i : this.input_inventory) {
				i.setInInventoryIn(false);
			}
			
			for(Items i : this.output_inventory) {
				i.setInInventory(false);
			}
			buildingsList.remove(this);
		}
		return;
	}
}
