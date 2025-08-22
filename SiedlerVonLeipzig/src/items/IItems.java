package items;

import logic.Vector2D;

public interface IItems {
	
	public Vector2D getPosition();

	public void setPosition(Vector2D position);

	public boolean isHeld();

	public void setHeld(boolean held);

	public String getName();

	public void setName(String name);
}
