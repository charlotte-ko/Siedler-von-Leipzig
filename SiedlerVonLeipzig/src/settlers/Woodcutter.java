package settlers;

import logic.Vector2D;

public class Woodcutter extends ASettlers{
	Woodcutter (Vector2D position){
		this.setSpeed(4.0f);
		this.setPosition(position);
		this.setIsWorking(false);
		this.setJob("Lumberjack");
	}
	@Override
	protected void work(float delta) {
		// TODO Auto-generated method stub
		
	}
}
