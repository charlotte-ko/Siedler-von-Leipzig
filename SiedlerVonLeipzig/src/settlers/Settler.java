package settlers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import buildings.IBuildings;
import items.IItems;
import logic.Vector2D;

public class Settler extends ASettlers{
	Settler(Vector2D position){
		this.setSpeed(0.4f);
		this.setPosition(position);
		this.setIsWorking(false);
		this.setJob("Normal");
	}


	@Override
	public void draw(Graphics2D g2, int tileSize) {
		g2.setColor(Color.red);
		g2.fillRect((int)this.getPosition().x, (int)this.getPosition().y, tileSize,tileSize);
	}
}
