package buildings;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings{
	
	protected static int price = 20;
	private static String name = "House";
	protected static String priceType = "Plank"; 
	
	private double spawntime = 0.0;
	private int counter = 0;
	
	private static BufferedImage houseImg1;

	static {
		try {
			
			houseImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/House.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Konstruktor für House, erstellt Settler
	 * @param pos
	 */
	public House(Vector2D pos) {
		super(name, pos);
		img = houseImg1;
		
	}
	
	/**
	 * @brief erzeugt neuen Settler am House Standort und fügt ihn in Liste ein
	 * @param settlerList
	 */
	public void makeSettlers(ArrayList<ASettlers> settlerList) {
	
		settlerList.add(ASettlers.createSettler(this.getPosition()));
		spawntime = 0.0;
	}
	
	/**
	 * @brief Updatemethode, erstellt pro Haus 10 Settler und fügt sie Liste hinzu
	 * @param settlerList
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList){
		
		if(this.updateConstruction(delta, settlerList, BuildingsList, itemList, price, "Log")){
			img = houseImg1;
			
		}else {return;}
		
		if(this.counter < 10) {
			
			spawntime += delta;
			if(spawntime >= 20.0) {
				
				makeSettlers(settlerList);
				this.counter++;
				spawntime = 0.0;
			}	
		}
	}
	
	public static int getPrice() {
		return price;
	}
}
