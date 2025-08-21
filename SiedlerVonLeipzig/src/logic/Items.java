package logic;

import java.util.ArrayList;

public class Items {
	
		//protected static ArrayList<Items> Itemlist = new ArrayList<Items>(); //vielleicht eher in main
		
		protected Vector2D position;
		//protected Vector2D start_pos;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		
		protected String name;
		
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
		
		public Items closestItem(Vector2D current_pos, Items item_1, Items item_2) {
			if(current_pos.getDistance(item_1.position) <= current_pos.getDistance(item_2.position)) {
				return item_1;
			}
			return item_2;
		}


		public Vector2D getPosition() {
			return position;
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
}
