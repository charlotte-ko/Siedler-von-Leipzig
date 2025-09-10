package logic;

import java.util.ArrayList;

import buildings.Buildings;
import items.*;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class GameModel {
	public ArrayList<Items> itemList = new ArrayList<>();
	public ArrayList<Buildings> buildingList = new ArrayList<>();
	public ArrayList<ASettlers> settlerList = new ArrayList<>();
	
	public String playerName;
	public Vector2D Position;
	
	GameModel(){
		this.setupGameField();
	}
	
	/**
	 * @brief erstellt Gebäude auf dem Spielfeld
	 * @param buildingName
	 * @param position
	 */
	public void constructBuilding(String buildingName,Vector2D position) {
		synchronized(buildingList){buildingList.add(Buildings.createBuilding(buildingName, position));}
	}
	/**
	 * @brief erstellt Item auf Spielfeld
	 * @param itemName
	 * @param position
	 */
	public void contructItem(String itemName,Vector2D position) {
		synchronized(itemList) {itemList.add(Items.createItem(itemName, position));}
	}
	/**
	 * @brief erstellt Settler auf Spielfeld
	 * @param position
	 */
	public void contructSettler(Vector2D position) {
		synchronized(settlerList) {settlerList.add(ASettlers.createSettler(position));}
	}
	
	public void setupGameField() {
		//prepare
				for(int i = 0; i < 10;i++) {
					for(int j = 0; j < 10;j++) {
						itemList.add(Items.createItem("Axe", new Vector2D(i,j)));
						itemList.add(Items.createItem("Hammer", new Vector2D(i+10,j)));
						itemList.add(Items.createItem("Log", new Vector2D(i,j+10)));
						//itemList.add(Items.createItem("Nothing", new Vector2D(i*2,j*2)));
						itemList.add(Items.createItem("Plank", new Vector2D(i+10,j+10)));
						itemList.add(Items.createItem("Saw", new Vector2D(i+20,j)));
						itemList.add(Items.createItem("Tree", new Vector2D(i+20,j+10)));
					}
				}
				settlerList.add(ASettlers.createSettler(new Vector2D()));
				settlerList.get(0).giveSettlerTask(new Vector2D(200,200), SettlersAktion.GO_FREE, null);
				settlerList.add(ASettlers.createSettler(new Vector2D()));
				settlerList.get(1).setJobName(SettlersJobs.JOB_SAWYER);
				settlerList.get(1).giveSettlerTask(new Vector2D(-200,-200), SettlersAktion.GO_FREE, null);
				
				buildingList.add(Buildings.createBuilding("Forester",new Vector2D(0.0f,0.0f)));
				buildingList.add(Buildings.createBuilding("Forester",new Vector2D(3.0f,0.0f)));
				buildingList.add(Buildings.createBuilding("Forester",new Vector2D(-3.0f,0.0f)));
				
				buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(0.0f,2.5f)));
				buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(3.0f,2.5f)));
				buildingList.add(Buildings.createBuilding("Sawmill",new Vector2D(-3.0f,2.5f)));
				
				buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(0.0f,-2.5f)));
				buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(3.0f,-2.5f)));
				buildingList.add(Buildings.createBuilding("Lodge",new Vector2D(-3.0f,-2.5f)));
				
				buildingList.add(Buildings.createBuilding("House",new Vector2D(0.0f,5.0f)));
				buildingList.add(Buildings.createBuilding("House",new Vector2D(3.0f,5.0f)));
				buildingList.add(Buildings.createBuilding("House",new Vector2D(-3.0f,5.0f)));
	}
	
	/**
	 * @brief Zählt alle vorhandenen Objekte
	 * @return zählung
	 * @note überlänge aufgrund von switch case
	 */
	public int[] inventarCounter() {
		int[] count = new int[14];
		
		for(int i = 0; i < 14; i ++) {
			count[i] = 0;
		}
		for(Items i : itemList) {
			switch(i.getName()) {
				case "Log": count[0]++;break;
				case "Plank": count[1]++;break;
				case "Tree": count[2]++;break;
				case "Axe": count[3]++;break;
				case "Hammer": count[4]++;break;
				case "Saw": count[5]++;break;
			}
		}
		for(Buildings b : buildingList) {
			switch(b.getName()) {
				case "Forester": count[6]++;break;
				case "House": count[7]++;break;
				case "Sawmill": count[8]++;break;
				case "Lodge": count[9]++;break;
			}
		}
		for(ASettlers s : settlerList) {
			switch(s.getJobName()) {
				case JOB_NORMAL: count[10]++;break;
				case JOB_GARDENER: count[11]++;break;
				case JOB_LUMBERJACK: count[12]++;break;
				case JOB_SAWYER: count[13]++;break;
			}
		}
		return count;
	}
}
