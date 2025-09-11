package buildings;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import items.Items;
import settlers.ASettlers;
import logic.Vector2D;

public class House extends Buildings {
	private static BufferedImage houseImg1;
	private static BufferedImage houseImg2;
	public static int price = 20;
	
	static {
		try {
			houseImg1 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/House.png"));
			houseImg2 = ImageIO.read(Buildings.class.getResourceAsStream("/resources/House_!.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double spawntime = 0.0;
	private int counter = 0;
	
	/**
	 * @brief Konstruktor für House, erstellt Settler
	 * @param pos
	 */
	public House(Vector2D pos) {
		super("House", pos);
		this.name = "House";
		this.price = 20;
		img = houseImg1;
		
	}
	
	/**
	 * @brief macht neuen Settler am House Standort und fügt ihn in Liste ein
	 * @param settlerList
	 */
	public void makeSettlers(ArrayList<ASettlers> settlerList) {
	
		settlerList.add(ASettlers.createSettler(this.getPosition()));
		spawntime = 0.0;
	}
	
	/**
	 * @brief Updatemethode, erstellt pro Haus 10 Settler und fügt sie Liste hinzu
	 * @param settlerList
	 * @note zeitlicher Aspekt fehlt noch
	 */
	public void update(double delta, ArrayList<ASettlers> settlerList, ArrayList<Buildings> BuildingsList, ArrayList<Items> itemList){
		
		while(this.counter < 10) {
			spawntime += delta;
			if(spawntime >= 20.0) {
				makeSettlers(settlerList);
				this.counter++;
			}
			
		}
	}
}
