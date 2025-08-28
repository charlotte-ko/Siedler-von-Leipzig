package settlers;

import logic.Vector2D;

public class Builder extends ASettlers{
	Builder (Vector2D position){
		this.setSpeed(4.0f);
		this.setPosition(position);
		this.setIsWorking(false);
	}
	@Override
	protected void work(float delta) {
		// TODO Auto-generated method stub
		
	}

}
