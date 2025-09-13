package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.Vector2D;

public abstract class Items implements java.io.Serializable{
		private static final long serialVersionUID = 1L;
		
		protected Vector2D position;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		protected boolean targeted;	//wenn es als Ziel an ein Siedler übergeben wurde
		protected boolean inInventory;//wenn es in einem Building ist (output inventar)
		protected boolean inInventoryIn;//wenn es in einem Building ist (input inventar)
		
		protected String name;
		protected BufferedImage img;
		
		public Items(Vector2D pos) {
			this.held = false;
			this.targeted = false;
			this.inInventory = false;
			this.inInventoryIn = false;
			position = new Vector2D(pos);
		}
		
		public static Items createItem(String item, Vector2D pos) {
			
			switch(item) {
				case "Hammer": return new Hammer(pos);
				case "Saw": return new Saw(pos);
				case "Axe": return new Axe(pos);
				case "Log": return new Log(pos);
				case "Plank": return new Plank(pos);
				case "Tree": return new Tree(pos);
				case "Nothing":return new Nothing(pos);
				case "Iron":return new Iron(pos);
				case "Pickaxe":return new Pickaxe(pos);
			default: throw new IllegalArgumentException("Unexpected Value: " + item);
			}
		}
		
		/**
		 * @brief sucht das nähstgelegene Item, ignoriert alle nicht benutzbaren Items
		 * @param current_pos
		 * @param ItemList
		 * @param target
		 * @return Item oder null wenn es nichts findet
		 */
		public static Items closestItem(Vector2D current_pos, ArrayList<Items> ItemList, String target) {
			
			float min_distance = Float.POSITIVE_INFINITY;
			Items finalItem = null;
		
			for(Items item : ItemList) {
				if(!item.getName().equals(target) || item.isHeld() || item.isTargeted() || item.isInInventoryIn()) {
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
		
		public boolean isTargeted() {
			return targeted;
		}
		
		public void setTargeted(boolean targeted) {
			this.targeted = targeted;
		}
		
		public boolean isInInventory() {
			return inInventory;
		}
		
		public void setInInventory(boolean inInventory) {
			this.inInventory = inInventory;
		}
		
		public boolean isInInventoryIn() {
			return inInventoryIn;
		}
		
		public void setInInventoryIn(boolean inInventoryIn) {
			this.inInventoryIn = inInventoryIn;
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
		
		public void draw(Graphics2D g2,int tileSize,Vector2D camPos) {
			//0.5 groß
			int posX = (int)((position.x)*(float)tileSize) - (int)((camPos.x)*(float)tileSize) +(tileSize/2);
			int posY = (int)((position.y)*(float)tileSize) - (int)((camPos.y)*(float)tileSize) +(tileSize/2);
			
			g2.drawImage(img, posX, posY, tileSize/2,tileSize/2,null);
		}
		
}
