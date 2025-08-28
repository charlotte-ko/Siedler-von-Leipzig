package settlers;

import logic.Vector2D;

public class Sawyer extends ASettlers{
	Sawyer (Vector2D position){
		this.setSpeed(4.0f);
		this.setPosition(position);
		this.setIsWorking(false);
		this.job = "Sawyer";
	}
	@Override
	protected void work(float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vector2D closestItem(Vector2D position) {
		// TODO Auto-generated method stub
		return null;
	}

}
