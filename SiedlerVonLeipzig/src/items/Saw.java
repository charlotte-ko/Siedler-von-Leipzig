package items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.Vector2D;

public class Saw extends Items {
	static BufferedImage sawImg;
	static {
		try {
			sawImg = ImageIO.read(Items.class.getResourceAsStream("/resources/Saw.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int price = 5;
	public static String priceType = "Log";
	
	public Saw(Vector2D pos) {
		super(pos);
		this.name = "Saw";
		img = sawImg;
	}
	
	public static int getPrice() {
		return price;
	}
	public static String getPriceType() {
		return priceType;
	}

}
