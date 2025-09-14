package items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logic.Vector2D;

public abstract class Items implements java.io.Serializable {
	
		private static final long serialVersionUID = 1L;
		
		protected Vector2D position;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		protected boolean targeted;	//wenn es als Ziel an ein Siedler übergeben wurde
		protected boolean inInventoryOutput;//wenn es in einem Building ist (output inventar)
		protected boolean inInventoryInput;//wenn es in einem Building ist (input inventar)
		
		protected String name;
		
		protected transient BufferedImage img;
		
		/**
		 * @brief Konstuktor für Items
		 * @param pos
		 */
		public Items(String itemType, Vector2D pos) {
			
			this.name = itemType;
			this.held = false;
			this.targeted = false;
			this.inInventoryOutput = false;
			this.inInventoryInput = false;
			position = new Vector2D(pos);
		}
		
		/**
		 * @brief Factory Methode für Itemerstellung
		 * @param item
		 * @param pos
		 * @return
		 */
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
		 * @brief sucht das nächstgelegene Item, ignoriert alle nicht benutzbaren Items
		 * @param current_pos
		 * @param ItemList
		 * @param target
		 * @return Item oder null, wenn es nichts findet
		 */
		public static Items closestItem(Vector2D current_pos, ArrayList<Items> ItemList, String target) {
			
			float min_distance = Float.POSITIVE_INFINITY;
			Items finalItem = null;
		
			for(Items item : ItemList) {
				
				if(!item.getName().equals(target) || item.isHeld() || item.isTargeted() || item.isInInventoryInput()) {
					continue;
				}
				
				float distance = current_pos.getDistance(item.getPosition());
				if(distance < min_distance) {
					min_distance = distance;
					finalItem = item;
				}
				
			} return finalItem;
		}
		
		
		/**
		 * @brief löscht ein spezifisches Item aus der itemList
		 * @param target Item, welches gelöscht werden soll
		 * @param itemList
		 */
		public static void deleteItemFromList(Items target, ArrayList<Items> itemList) {
			
			for(int i = 0; i < itemList.size(); i++) {
				
				if(target != itemList.get(i)) {
					continue;
				}
				
				itemList.remove(i);
				break;
				
			} return;
		}
		
		public void draw(Graphics2D g2,int tileSize,Vector2D camPos) {
			
			
			float offsetX = 0;
			float offsetY = 0;
			
			if(this.isInInventoryOutput()) {
				offsetX = 0.6f;
				offsetY = 0.6f;
			}
			
			if(this.isInInventoryInput()) {
				offsetX = -0.6f;
				offsetY = 0.6f;
			}
			
			int posX = (int)((position.x+offsetX)*(float)tileSize) - (int)((camPos.x)*(float)tileSize) +(tileSize/2);
			int posY = (int)((position.y+offsetY)*(float)tileSize) - (int)((camPos.y)*(float)tileSize) +(tileSize/2);
			
			g2.drawImage(img, posX, posY, tileSize/2,tileSize/2,null);
		}
		
		public abstract void reloadImg();

		
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
		
		public boolean isInInventoryOutput() {
			return inInventoryOutput;
		}
		
		public void setInInventoryOutput(boolean inInventory) {
			this.inInventoryOutput = inInventory;
		}
		
		public boolean isInInventoryInput() {
			return inInventoryInput;
		}
		
		public void setInInventoryInput(boolean inInventory) {
			this.inInventoryInput = inInventory;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}		
}
