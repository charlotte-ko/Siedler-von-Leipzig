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
	public Vector2D(){
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
	
	/**
	 * @brief ermittelt aus den 2 Punkten einen Vektor 
	 * @param b punkt zu welchen der Vektor hinzeigt
	 * @return ein Vector2D
	 */
	public Vector2D getTwoPointVector(Vector2D b) {
		Vector2D twoPointVector = new Vector2D(b.x - this.x,b.y - this.y);
		return twoPointVector;
	}
	
	/**
	 * @brief Normalisiert den Vector in einen einheitsvektor
	 * @return den einheitsvektor
	 */
	public Vector2D normalize() {
		float len = this.length();
		return new Vector2D(this.x/len,this.y/len);
	}
	/**
	 * @brief gibt die länge des Vektors an
	 * @return als float
	 */
	public float length() {
		return (float)Math.abs(Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)));
	}
}
