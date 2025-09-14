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
	
	private  ArrayList<String> userData = new ArrayList<>();
	
	final public Object key = new Object();
	
	public String playerName;
	public Vector2D Position;
	

	private Map<String, Integer> priceMap = new HashMap<>();
	private Map<String, String> CurrencyMap = new HashMap<>();
	
	public boolean debugMode = false;
	
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
	
	/**
	 * @brief löscht Gebäude auf Spielfeld
	 * @param pos
	 */
	public void deconstructBuilding(Vector2D pos) {
		Buildings target = Buildings.findBuildingAprox(pos, buildingList, 3.0f);
		
		if(target != null) {
			synchronized(key) {target.deconstruct(buildingList);}
		}
	}
	
	/**
	 * @brief testet ob Gebäude in der Nähe sind
	 * @param Position
	 * @return
	 */
	public boolean testAvailabelSpace(Vector2D Position) {
		
		if(Position.testPos(buildingList, itemList, 2.0f)) {
			return true;
			
		}else {
			return false;
		}
	}
	
	/**
	 * @brief setup für den Debug Modus, hier werden die Fähigkeiten der Siedlerinnen durch strategische Platzierung
	 * der Items und Gebäude aufs Äußerste beansprucht
	 */
	
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
		buildingStart.add(Buildings.createBuilding("Forge", new Vector2D(4.0f,7.0f)));
		buildingStart.add(Buildings.createBuilding("House", new Vector2D(4.0f,10.0f)));
		buildingStart.add(Buildings.createBuilding("Lodge", new Vector2D(4.0f,13.0f)));
		buildingStart.add(Buildings.createBuilding("Mine", new Vector2D(4.0f,16.0f)));
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
	
	/**
	 * @brief Methode platziert Items und Häuser auf dem Spielfeld um ein normales Spiel zu starten
	 */
	public void setupGameField() {
		ArrayList<Items> itemStart = new ArrayList<>();
		ArrayList<Buildings> buildingStart = new ArrayList<>();
		
		buildingStart.add(Buildings.createBuilding("House", new Vector2D(0.0f, 0.0f)));
		buildingStart.add(Buildings.createBuilding("Forester", new Vector2D(4.0f, 0.0f)));
		buildingStart.add(Buildings.createBuilding("Lodge", new Vector2D(-4.0f, 0.0f)));
		
		itemStart.add(Items.createItem("Saw", new Vector2D(7.0f, 0.0f)));
		itemStart.add(Items.createItem("Saw", new Vector2D(8.0f, 0.0f)));
		itemStart.add(Items.createItem("Axe", new Vector2D(9.0f, 0.0f)));
		itemStart.add(Items.createItem("Axe", new Vector2D(9.0f, -1.0f)));
		itemStart.add(Items.createItem("Hammer", new Vector2D(8.0f, -1.0f)));
		itemStart.add(Items.createItem("Pickaxe", new Vector2D(7.0f, -1.0f)));
		
		for(Buildings b : buildingStart) {
			b.isConstructed = true;
			buildingList.add(b);
		}
		
		for(Items i : itemStart) {
			itemList.add(i);
		}
	}

	public void setupDebug() {
		this.debugMode = true;
		setupGameFieldDebug3();
	}
	
	/**
	 * @brief Zählt alle vorhandenen Objekte
	 * @return zählung
	 * @note überlänge aufgrund von switch case
	 */
	public int[] inventarCounter() {
		int[] count = new int[20];
		
		for(int i = 0; i < 19; i ++) {
			count[i] = 0;
		}
		
		for(Items i : itemList) {
			switch(i.getName()) {
				case "Log": count[0]++;break;
				case "Plank": count[1]++;break;
				case "Iron": count[2]++;break;
				case "Tree": count[3]++;break;
				case "Axe": count[4]++;break;
				case "Hammer": count[5]++;break;
				case "Saw": count[6]++;break;
				case "Pickaxe": count[7]++;break;
			}
		}
		for(Buildings b : buildingList) {
			switch(b.getName()) {
				case "House": count[8]++;break;
				case "Forester": count[9]++;break;
				case "Sawmill": count[10]++;break;
				case "Lodge": count[11]++;break;
				case "Forge": count[12]++;break;
				case "Mine": count[13]++;break;
			}
		}
		for(ASettlers s : settlerList) {
			switch(s.getJobName()) {
				case JOB_NORMAL: count[14]++;break;
				case JOB_GARDENER: count[15]++;break;
				case JOB_SAWYER: count[16]++;break;
				case JOB_LUMBERJACK: count[17]++;break;
				case JOB_SMITH: count[18]++;break;
				case JOB_MINER: count[19]++;break;
			}
			
		} return count;
	}
	
	/**
	 * @brief füllt die PriceMap/CurrencyMap mit den kaufbaren Items und ihren entsprechenden Preisen und Währungen
	 */
	public void fillMap() {
		priceMap.put("Forester", Forester.getPrice());
		priceMap.put("House", House.getPrice());
		priceMap.put("Lodge", Lodge.getPrice());
		priceMap.put("Sawmill", Sawmill.getPrice());
		priceMap.put("Forge", Forge.getPrice());
		priceMap.put("Mine", Mine.getPrice());
		
		CurrencyMap.put("Forester", Forester.getPriceType());
		CurrencyMap.put("House", House.getPriceType());
		CurrencyMap.put("Lodge", Lodge.getPriceType());
		CurrencyMap.put("Sawmill", Sawmill.getPriceType());
		CurrencyMap.put("Forge", Forge.getPriceType());
		CurrencyMap.put("Mine", Mine.getPriceType());
	}
	
	/**
	 * @brief sucht angefordertes Item in Map und gibt, Preis zurück, wenn gefunden
	 * @param purchase
	 * @return Preis des gesuchten Items, oder maximalen Integer Value, wenn Item nicht in Map
	 */
	public int getPrice(String purchase) {
		
		if(priceMap.containsKey(purchase)) {
			return priceMap.get(purchase);
			
		}else {
			return Integer.MAX_VALUE;
		}
	}
	
	public String getPriceType(String purchase) {
		
		if(CurrencyMap.containsKey(purchase)) {
			return CurrencyMap.get(purchase);
			
		}else {
			return null;
		}
	}
	
	/**
	 * @brief speichert Spielstand in einer Datei
	 * @param saveFileName
	 */
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
	/**
	 * @brief lädt Spielstand aus einer Datei 
	 * @param saveFileName
	 */
	public void loadGame(String saveFileName) {
		
		Path p = Path.of("../save/"+saveFileName+".bin");
		
		if(!Files.exists(p)) return;
		
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
	
	public ArrayList<String> getUserData() {
		return userData;
	}

	public void setUserData(ArrayList<String> userData) {
		this.userData = userData;
	}
}
