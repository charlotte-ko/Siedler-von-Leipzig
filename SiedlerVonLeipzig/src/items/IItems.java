package items;

import java.awt.Graphics2D;

import logic.Vector2D;

public interface IItems {
	
	public Vector2D getPosition();

	public void setPosition(Vector2D position);

	public boolean isHeld();

	public void setHeld(boolean held);

	public String getName();

	public void setName(String name);
	
	public void draw(Graphics2D g2,int tileSize);
}
