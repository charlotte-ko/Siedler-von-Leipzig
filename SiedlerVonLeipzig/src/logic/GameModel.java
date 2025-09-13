package logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import buildings.Buildings;
import buildings.Forester;
import buildings.Forge;
import buildings.House;
import buildings.Lodge;
import buildings.Mine;
import buildings.Sawmill;
import items.*;
import settlers.ASettlers;
import settlers.SettlersAktion;
import settlers.SettlersJobs;

public class GameModel {
	public ArrayList<Items> itemList = new ArrayList<>();
	public ArrayList<Buildings> buildingList = new ArrayList<>();
	public ArrayList<ASettlers> settlerList = new ArrayList<>();
	
	public ArrayList<String> userData = new ArrayList<>();
	
	final public Object key = new Object();
	
	public String playerName;
	public Vector2D Position;
	

	private Map<String, Integer> priceMap = new HashMap<>();
	
	/**
	 * @brief erstellt Gebäude auf dem Spielfeld
	 * @param buildingName
	 * @param position
	 */
	public void constructBuilding(String buildingName,Vector2D position) {
		synchronized(key){buildingList.add(Buildings.createBuilding(buildingName, position));}
	}
	/**
	 * @brief erstellt Item auf Spielfeld
	 * @param itemName
	 * @param position
	 */

	public void constructItem(String itemName,Vector2D position) {
		synchronized(key) {itemList.add(Items.createItem(itemName, position));}
	}
	/**
	 * @brief erstellt Settler auf Spielfeld
	 * @param position
	 */
	public void contructSettler(Vector2D position) {
		synchronized(key) {settlerList.add(ASettlers.createSettler(position));}
	}

	public void deconstructBuilding(Vector2D pos) {
		Buildings target = Buildings.findBuildingAprox(pos, buildingList, 1.0f);
		if(target != null) {
			synchronized(key) {target.deconstruct(buildingList);}
		}
	}
	
	/**
	 * Test ob Siedler:
	 * 1.) ein Log holen und bringen können
	 */
	public void setupGameFieldDebug1Settler() {
		ASettlers settler1 = ASettlers.createSettler(new Vector2D(0.0f,0.0f));
		Items item1 = Items.createItem("Log", new Vector2D(5.0f,0.0f));
		Items item2 = Items.createItem("Axe", new Vector2D(10.0f,0.0f));
		Buildings building1 = Buildings.createBuilding("Forester", new Vector2D(0.0f,5.0f));
		Buildings building2 = Buildings.createBuilding("House", new Vector2D(5.0f,5.0f));
		Buildings building3 = Buildings.createBuilding("Forester", new Vector2D(5.0f,10.0f));
		
		settler1.setSpeed(6.0f);
		
		settlerList.add(settler1);
		itemList.add(item1);
		itemList.add(item2);
		buildingList.add(building1);
		buildingList.add(building2);
		buildingList.add(building3);
		
		settler1.giveSettlerTask(item1.getPosition(), SettlersAktion.GO_PICKUP_LEFT, item1);
		settler1.giveSettlerTask(settler1.getPosition(), SettlersAktion.GO_BRING_LEFT, item1);
		settler1.giveSettlerTask(item2.getPosition(), SettlersAktion.GO_PICKUP_RIGHT, item2);
		settler1.giveSettlerTask(settler1.getPosition(), SettlersAktion.GO_BRING_RIGHT, item2);
	}
	public void setupGameFieldDebug2() {
		ArrayList<ASettlers> settlerStart = new ArrayList<>();
		ArrayList<Items> itemStart = new ArrayList<>();
		ArrayList<Buildings> buildingStart = new ArrayList<>();
		
		settlerStart.add(ASettlers.createSettler(new Vector2D()));
		settlerStart.add(ASettlers.createSettler(new Vector2D()));
		for(int i = 0; i < 100;i++) {
			itemStart.add(Items.createItem("Log", new Vector2D(-1.0f,i/4.0f)));
		}
		itemStart.add(Items.createItem("Tree", new Vector2D(0.0f,1.0f)));
		itemStart.add(Items.createItem("Tree", new Vector2D(0.0f,2.0f)));
		itemStart.add(Items.createItem("Tree", new Vector2D(0.0f,3.0f)));
		itemStart.add(Items.createItem("Axe", new Vector2D(5.0f,5.0f)));
		itemStart.add(Items.createItem("Saw", new Vector2D(6.0f,5.0f)));
		buildingStart.add(Buildings.createBuilding("Lodge", new Vector2D(5.0f,0.0f)));
		buildingStart.add(Buildings.createBuilding("House", new Vector2D(-5.0f,-5.0f)));
		buildingStart.add(Buildings.createBuilding("Forester", new Vector2D(-8.0f,-8.0f)));
		buildingStart.add(Buildings.createBuilding("Sawmill", new Vector2D(0.0f,-8.0f)));
		buildingStart.get(1).isContructed = true;
		settlerStart.get(0).setSpeed(6.0f);
		settlerStart.get(1).setSpeed(6.0f);
		
		for(ASettlers s : settlerStart)settlerList.add(s);
		for(Items i : itemStart)itemList.add(i);
		for(Buildings b : buildingStart)buildingList.add(b);
	}
	public void setupGameFieldDebug3() {
		ArrayList<ASettlers> settlerStart = new ArrayList<>();
		ArrayList<Items> itemStart = new ArrayList<>();
		ArrayList<Buildings> buildingStart = new ArrayList<>();
		
		for(int i = 0; i < 6;i++) {
			settlerStart.add(ASettlers.createSettler(new Vector2D()));
			settlerStart.get(i).setSpeed(5.0f);
		}
		
		itemStart.add(Items.createItem("Axe", new Vector2D(0.0f,1.0f)));
		itemStart.add(Items.createItem("Saw", new Vector2D(0.0f,2.0f)));
		itemStart.add(Items.createItem("Hammer", new Vector2D(0.0f,3.0f)));
		itemStart.add(Items.createItem("Pickaxe", new Vector2D(0.0f,4.0f)));
		itemStart.add(Items.createItem("Tree", new Vector2D(0.0f,5.0f)));
		itemStart.add(Items.createItem("Log", new Vector2D(0.0f,6.0f)));
		itemStart.add(Items.createItem("Plank", new Vector2D(0.0f,7.0f)));
		itemStart.add(Items.createItem("Iron", new Vector2D(0.0f,8.0f)));
		itemStart.add(Items.createItem("Nothing", new Vector2D(0.0f,9.0f)));
		
		buildingStart.add(Buildings.createBuilding("Forester", new Vector2D(4.0f,4.0f)));
		//buildingStart.add(Buildings.createBuilding("Forge", new Vector2D(4.0f,7.0f)));
		buildingStart.add(Buildings.createBuilding("House", new Vector2D(4.0f,10.0f)));
		buildingStart.add(Buildings.createBuilding("Lodge", new Vector2D(4.0f,13.0f)));
		//buildingStart.add(Buildings.createBuilding("Mine", new Vector2D(4.0f,16.0f)));
		buildingStart.add(Buildings.createBuilding("Sawmill", new Vector2D(4.0f,19.0f)));
		
		for(int i = 0; i < 20;i++) {
			for(int j = 0; j < 20;j++) {
				itemStart.add(Items.createItem("Log", new Vector2D(-i,-j)));
			}
		}
		for(int i = 0;i < 20;i++) {
			itemStart.add(Items.createItem("Plank", new Vector2D(-i-20,-20)));
		}
		
		for(ASettlers s : settlerStart)settlerList.add(s);
		for(Items i : itemStart)itemList.add(i);
		for(Buildings b : buildingStart)buildingList.add(b);
	}	
	public void setupGameField() {
		setupGameFieldDebug1Settler();
	}
	
//Diese Methode brauchen wir wahrscheinlich nicht
	public <T> void changeList(ArrayList<T> oldList, ArrayList<T> newList) {
		oldList = newList;
	}
	public void setupDebug() {
		setupGameFieldDebug3();
		//setupGameFieldDebug2();
		//setupGameFieldDebug1Settler();
		//setupDebugBigTest();
	}
	public void setupDebugBigTest() {
		//prepare
		
		//System.out.println("Aufruf von: " + java.util.Arrays.toString(Thread.currentThread().getStackTrace())); -> ganz nützliche Ausgabe, bitte drin lassen fürs debugging
		
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
		
		for(int i = 0; i < 13; i ++) {
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
	
	public void fillMap() {
		priceMap.put("Forester", Forester.getPrice());
		priceMap.put("House", House.getPrice());
		priceMap.put("Lodge", Lodge.getPrice());
		priceMap.put("Sawmill", Sawmill.getPrice());
		priceMap.put("Forge", Forge.getPrice());
		priceMap.put("Mine", Mine.getPrice());
		
	}
	
	public int getPrice(String purchase) {
		
		if(priceMap.containsKey(purchase)) {
			return priceMap.get(purchase);
			
		}else {
			return 1000000;//wie wäre es mit naf oder so ?
		}
	}
	
	public void saveGame(String saveFileName) {
		Path p = Path.of("../save/"+ saveFileName +".bin");
		
		try {
			if (p.getParent() != null) Files.createDirectories(p.getParent());
			
			ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(p));
			
			ArrayList<Buildings> b; ArrayList<ASettlers> s; ArrayList<Items> i;
	        synchronized (key) {
	            b = new ArrayList<>(buildingList);
	            s = new ArrayList<>(settlerList);
	            i = new ArrayList<>(itemList);
	        }
			oos.writeObject(new SaveState(b,s,i));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadGame(String saveFileName) {
		Path p = Path.of("../save/"+saveFileName+".bin");
		
		if(!Files.exists(p))return;
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(p))) {
			SaveState s = (SaveState) ois.readObject();
			synchronized (key) {
				buildingList.clear();
				buildingList.addAll(s.buildings);
				settlerList.clear();
				settlerList.addAll(s.settlers);
				itemList.clear();
				itemList.addAll(s.items);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		synchronized(key) {
			for(Items i : itemList) {
				i.reloadImg();
			}
		}
	}
}
