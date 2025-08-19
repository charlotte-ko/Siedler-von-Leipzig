package logic;

public class Vector2D {
	float x,y;
	
	Vector2D(float startX,float startY){
		this.x = startX;
		this.y = startY;
	}
	Vector2D(float startX){
		this.x = startX;
		this.y = 0.0f;
	}
	Vector2D(){
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	/**
	 * @brief gibt die Distanz zu einem anderen Punkt als float an
	 * @param b zweiter Punkt
	 * @return float mit der Distanz
	 */
	public float getDistance(Vector2D b) {
		return (float) Math.sqrt(Math.pow(b.x-this.x,2)+Math.pow(b.y-this.y,2));
	}
}
