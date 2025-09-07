package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.Vector2D;

public abstract class Items {
	
		//protected static ArrayList<Items> Itemlist = new ArrayList<Items>(); //vielleicht eher in main
		
		protected Vector2D position;
		//protected Vector2D start_pos;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		
		protected String name;
		
		protected BufferedImage img;
		
		public Items(Vector2D pos) {
			this.held = false;
			position = pos;
			
		}
		
		public static Items createItem(String item, Vector2D pos) {
			
			switch(item) {
			
			case "Hammer": return new Hammer(pos); //vielleicht position als paramter
			case "Saw": return new Saw(pos); //Axe
			case "Axe": return new Axe(pos);
			case "Log": return new Log(pos);
			case "Plank": return new Plank(pos);
			case "Tree": return new Tree(pos);
			default: throw new IllegalArgumentException("Unexpected Value: " + item);
			}
		}
		
		//Wir müssen noch anpassen, dass wir nur Items aus output Inventaren nehmen (sonst nehmen wir aus eigenem)
		public static Items closestItem(Vector2D current_pos, ArrayList<Items> ItemList, String target) {
			
			float min_distance = Float.POSITIVE_INFINITY;
			Items finalItem = null;
		
			for(Items item : ItemList) {
				if(!item.getName().equals(target) && !item.isHeld()) {
					continue;
				}
				float distance = current_pos.getDistance(item.getPosition());
				if(distance < min_distance) {
					min_distance = distance;
					finalItem = item;
				}
			}return finalItem;
		}

		public Vector2D getPosition() {
			return this.position;
		}
		
		public void setPosition(Vector2D position) {
			this.position = position;
		}
		
		public boolean isHeld() {
			return held;
		}
		
		public void setHeld(boolean held) {
			this.held = held;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * @brief löscht ein spezifisches Item aus der itemList
		 * @param target Item welches gelöscht werden soll
		 * @param itemList
		 */
		public static void dellItemFromList(Items target,ArrayList<Items> itemList) {
			for(int i = 0; i < itemList.size();i++) {
				if(target != itemList.get(i)) {
					continue;
				}
				itemList.remove(i);
				break;
			}
			return;
		}
		
		public void draw(Graphics2D g2,int tileSize) {
			g2.drawImage(img, (int)position.x, (int)position.y, tileSize,tileSize,null);
		}
}
