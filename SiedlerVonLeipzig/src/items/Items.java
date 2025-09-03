package items;

import java.util.ArrayList;

import logic.Vector2D;

public abstract class Items implements IItems {
	
		//protected static ArrayList<Items> Itemlist = new ArrayList<Items>(); //vielleicht eher in main
		
		protected Vector2D position;
		//protected Vector2D start_pos;
		
		protected boolean held; //gibt an, ob ein Item im Inventar eines Siedlers ist
		
		protected String name;
		
		public Items(Vector2D pos) {
			this.held = false;
			position = pos;
			
		}
		
		public static IItems createItem(String item, Vector2D pos) {
			
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
		

		@Override
		public Vector2D getPosition() {
			return this.position;
		}
		
		@Override
		public void setPosition(Vector2D position) {
			this.position = position;
		}
		
		@Override
		public boolean isHeld() {
			return held;
		}
		
		@Override
		public void setHeld(boolean held) {
			this.held = held;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * @brief löscht ein spezifisches Item aus der itemList
		 * @param target Item welches gelöscht werden soll
		 * @param itemList
		 */
		public static void dellItemFromList(IItems target,ArrayList<IItems> itemList) {
			for(int i = 0; i < itemList.size();i++) {
				if(target != itemList.get(i)) {
					continue;
				}
				itemList.remove(i);
				break;
			}
			return;
		}
}
