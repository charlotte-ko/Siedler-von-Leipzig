package logic;

import java.util.ArrayList;

public class Items {
	
		protected static ArrayList<Items> Itemlist = new ArrayList<Items>(); //vielleicht eher in main
		
		protected float pos_x;
		protected float pos_y;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		
		protected String name;
		
		public Items() {
			this.held = false;
			pos_y = 0;
			pos_x = 0; //für die positionen braucht man koordinaten an welcher der konstruktor aufgerufen wurde
		}
		
		public static Items createItem(String item, float x, float y) {
			
			switch(item) {
			
			case "Hammer": return new Hammer(); //vielleicht position als paramter
			case "Saw": return new Saw();
			case "Log": return new Log();
			case "Plank": return new Plank();
			case "Tree": return new Tree(x, y);
			default: throw new IllegalArgumentException("Unexpected Value: " + item);
			}
		}

		public static ArrayList<Items> getItemlist() {
			return Itemlist;
		}

		public static void setItemlist(ArrayList<Items> itemlist) {
			Itemlist = itemlist;
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
