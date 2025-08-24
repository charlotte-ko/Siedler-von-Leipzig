package settlers;

import items.IItems;
import logic.Vector2D;

public class NormalSettler extends ASettlers{
	NormalSettler(Vector2D position){
		this.setSpeed(4.0f);
		this.setPosition(position);
		this.setIsWorking(false);
	}

	@Override
	protected void work(float delta) {
		//tut noch nicht wirklich etwas
	}
}
