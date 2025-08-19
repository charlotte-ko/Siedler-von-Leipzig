package logic;

import java.util.ArrayList;

public class Items {
	
		//protected static ArrayList<Items> Itemlist = new ArrayList<Items>(); //vielleicht eher in main
		
		protected float pos_x;
		protected float pos_y;
		//protected Vector2D start_pos;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		
		protected String name;
		
		public Items(Vector2D pos) {
			this.held = false;
			pos_y = pos.y;
			pos_x = pos.x;
			
		}
		
		public static Items createItem(String item, float x, float y, Vector2D pos) {
			
			switch(item) {
			
			case "Hammer": return new Hammer(pos); //vielleicht position als paramter
			case "Saw": return new Saw(pos); //Axe
			case "Axe": return new Axe(pos);
			case "Log": return new Log(pos);
			case "Plank": return new Plank(pos);
			case "Tree": return new Tree(x, y); //wird seine eigene methode, weil parameter
			default: throw new IllegalArgumentException("Unexpected Value: " + item);
			}
		}

		public float getPos_x() {
			return pos_x;
		}

		public void setPos_x(float pos_x) {
			this.pos_x = pos_x;
		}

		public float getPos_y() {
			return pos_y;
		}

		public void setPos_y(float pos_y) {
			this.pos_y = pos_y;
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
