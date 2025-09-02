package logic;

import java.util.ArrayList;

public class Vector2D {
	float x,y;
	
	public Vector2D(float startX,float startY){
		this.x = startX;
		this.y = startY;
	}
	public Vector2D(float startX){
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
	
	public Vector2D scale(float scalar) {
		return new Vector2D(this.x*scalar,this.y*scalar);
	}
	public Vector2D add(Vector2D b) {
		return new Vector2D(this.x+b.x,this.y+b.y);
	}
	
	/**
	 * 
	 * @param centre Mittelpunkt, also meistens ein Gebäude
	 * @param radius 
	 * @return eine Liste an Punkten, die sich innerhalb eines gewünschten Radius befinden
	 */
	public ArrayList<Vector2D> genPos(float radius) {
		
		ArrayList<Vector2D> pos_in_rad = new ArrayList<Vector2D>();
		Vector2D centre = this;
		for(float i = centre.x-radius; i <= (centre.x + radius); i += 1.0f) {
			for(float j = centre.y-radius; j <= (centre.y + radius); j += 1.0f) {
				
				Vector2D pos = new Vector2D(i,j);
				if(isInRadius(pos, centre, radius)) {pos_in_rad.add(pos);}
			}
		}
		return pos_in_rad;
	} 
	
	public static boolean isInRadius(Vector2D point, Vector2D centre, float radius) {
		
		if((point.x - centre.x)*(point.x - centre.x) + (point.y - centre.y)*(point.y - centre.y) <= radius*radius) {
			return true;
		}
		return false;
	}
}
