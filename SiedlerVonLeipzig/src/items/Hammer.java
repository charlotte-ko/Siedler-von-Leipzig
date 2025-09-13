package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Hammer extends Items {
	static BufferedImage hammerImg;
	static {
		try {
			hammerImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Hammer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int price = 5;
	public static String priceType = "Log";
	
	public Hammer(Vector2D pos) {
		super(pos);
		this.name = "Hammer";
		img = hammerImg;
	}
	
	public static int getPrice() {
		return price;
	}
	public static String getPriceType() {
		return priceType;
	}
}
