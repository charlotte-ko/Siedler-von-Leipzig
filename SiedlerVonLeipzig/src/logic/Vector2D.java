package logic;

import java.util.ArrayList;
import java.util.Random;

import buildings.Buildings;
import items.Items;

public class Vector2D {
	public float x,y;
	
	public Vector2D(Vector2D old) {
		this.x = old.x;
		this.y = old.y;
	}
	
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
	
	public static boolean isInRadius(Vector2D point, Vector2D centre, float radius) {
		
		if((point.x - centre.x)*(point.x - centre.x) + (point.y - centre.y)*(point.y - centre.y) <= radius*radius) {
			return true;
		}
		return false;
	}
	
	public Vector2D genPos(float radius) {
		
		Vector2D centre = this;
		Random rnd = new Random();
		
		Vector2D temporary = new Vector2D(centre.x + ((rnd.nextFloat() %1.0f) * radius), centre.y);
		int angle = rnd.nextInt(360);
	
		float newX = (float) (temporary.x * Math.cos(angle) - temporary.y * Math.sin(angle));
		float newY = (float) (temporary.x * Math.sin(angle) + temporary.y * Math.cos(angle));
		
		Vector2D finalPoint = new Vector2D (newX, newY);
		
		return finalPoint;
	}
	
	public boolean testPos(ArrayList<Buildings> BuildingList, ArrayList<Items> ItemList) {
		
		for(Buildings b : BuildingList) {
			if(this.getDistance(b.getPosition()) < 2.0f) {return false;}
		}
		for(Items i : ItemList) {
			if(this.getDistance(i.getPosition()) < 2.0f) {return false;}
		}
		return true;
	}
	/**
	 * @brief gibt true zurück wenn x und y gleich sind
	 * @param b
	 * @return
	 */
	public boolean same(Vector2D o) {
	    return o != null && x == o.x && y == o.y;
	}

}
