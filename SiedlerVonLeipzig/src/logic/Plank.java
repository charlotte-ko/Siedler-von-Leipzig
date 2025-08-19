package logic;

public class Plank extends Items{
	
	public Plank(Vector2D pos) {
		this.name = "Plank";
		this.start_pos.x = pos.x;
		this.start_pos.y = pos.y;
	}
}
