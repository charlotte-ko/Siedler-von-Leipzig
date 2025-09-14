package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import buildings.Buildings;
import items.Items;
import presenter.GamePresenter;
import settlers.ASettlers;

public class GamePanel extends JPanel{
	//Screen settings
	
	public int tileSize = 16 * 3; //48x48
	public int grid_zoom = 8;
	public Vector2D camPos = new Vector2D();
	KeyHandler keyH = new KeyHandler();
	
	int FPS = 60;
	double drawIntervalNanos = 1_000_000_000.0 /FPS;
	
	Thread gameThread;
	Object key;
	
	ArrayList<Items> itemList;
	ArrayList<Buildings> buildingList;
	ArrayList<ASettlers> settlerList;
	
	private  static BufferedImage backgroundImg1;
	private static BufferedImage backgroundImg2;
	private BufferedImage backgroundImg;
	
	private boolean zoomInBlock = false;
	private boolean zoomOutBlock = false;
	float cameraSpeed = 4.0f;
	private double backgroundTimer = 0.0;
	
	private ArrayList<Items> itemListSnap;
	private ArrayList<Buildings> buildingListSnap;
	private ArrayList<ASettlers> settlerListSnap;
	
	private GamePresenter presenter;
	
	static {
		try {
			
			backgroundImg1 = ImageIO.read(GamePanel.class.getResourceAsStream("/resources/Grass_1.png"));
			backgroundImg2 = ImageIO.read(GamePanel.class.getResourceAsStream("/resources/Grass_2.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GamePanel() {
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setPresenter(GamePresenter presenter) {
		this.presenter = presenter;
		this.key = presenter.model.key;
		this.itemList = presenter.model.itemList;
		this.buildingList = presenter.model.buildingList;
		this.settlerList = presenter.model.settlerList;
	}
	
	/**
	 * @brief steuert das Zoomen der Kamera
	 */
	public void zoomManager() {
		
		if(this.keyH.zoomIn && !zoomInBlock) {
			this.grid_zoom--;
			zoomInBlock = true;
		}
		if(this.keyH.zoomOut && !zoomOutBlock) {
			this.grid_zoom++;
			zoomOutBlock = true;
		}
		
		if(this.grid_zoom < 1)this.grid_zoom = 1;
		if(this.grid_zoom > 30)this.grid_zoom = 30;
	
		if(!this.keyH.zoomIn)zoomInBlock = false;
		if(!this.keyH.zoomOut)zoomOutBlock = false;
	}
	
	/**
	 * @brief updatet die Kamera
	 * @param delta
	 */
	public void cameraManager(double delta) {
		
		if(this.keyH.upPressed)camPos.y -= delta * cameraSpeed;
		if(this.keyH.downPressed)camPos.y += delta * cameraSpeed;
		if(this.keyH.rightPressed)camPos.x += delta * cameraSpeed;
		if(this.keyH.leftPressed)camPos.x -= delta * cameraSpeed;
	}
	
	/**
	 * @brief macht kopie um keine Race Condition zu erzeugen
	 */
	private void doSnap() {
		
		synchronized(key) {
			
			itemListSnap = new ArrayList<>(itemList);
			buildingListSnap = new ArrayList<>(buildingList);
			settlerListSnap = new ArrayList<>(settlerList);
		}
	}
	
	/**
	 * @brief zeichnet alle objekte nach y koordinate
	 * @param g2
	 * @note Alle listen sind schon sortiert
	 */
	private void renderInOrder(Graphics2D g2) {
		
		int i = 0,j = 0, k = 0;
		
		for(int count = itemListSnap.size()+buildingListSnap.size()+settlerListSnap.size(); count > 0;count--){
			float bPos = Float.POSITIVE_INFINITY;
			float sPos = Float.POSITIVE_INFINITY;
			float iPos = Float.POSITIVE_INFINITY;
			
			if(i < buildingListSnap.size())bPos = buildingListSnap.get(i).getPosition().y;
			if(j < settlerListSnap.size())sPos = settlerListSnap.get(j).getPosition().y;
			if(k < itemListSnap.size())iPos = itemListSnap.get(k).getPosition().y;
			
			int m = (bPos<=sPos) ? ((bPos<=iPos) ? (0):(2)) : ((sPos<=iPos)?(1):(2));
			
			switch(m) {
				case 0:
					buildingListSnap.get(i).draw(g2, tileSize, camPos);
					i++;
					break;
				case 1:
					settlerListSnap.get(j).draw(g2, tileSize, camPos);
					j++;
					break;
				case 2:
					itemListSnap.get(k).draw(g2, tileSize, camPos);
					k++;
					break;
			}
		}
	}
	
	/**
	 * @brief Wird durch repaint in Renderer 60 mal pro sec aufgerufen
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileSize = (int)((float)Math.min(this.getWidth(), this.getHeight())/(float)grid_zoom);
		
		doSnap();
		
		this.drawBackGround(g2, tileSize, camPos, 1);
		
		renderInOrder(g2);
	}
	
	/**
	 * @brief berechnet animation für background
	 * @param delta
	 */
	public void updateBackGroundImg(double delta) {
		
		backgroundTimer += delta;
		
		if(backgroundTimer > 0.5) {
			backgroundImg = backgroundImg1;
		}else {
			backgroundImg = backgroundImg2;
			
		}backgroundTimer %= 1.0;
	}
	
	/**
	 * @brief Hintergrund zeichnen
	 * @param g2
	 * @param tileSize
	 * @param camPos
	 * @param delta
	 */
	private void drawBackGround(Graphics2D g2,int tileSize,Vector2D camPos,double delta) {
		
		for(int i = -1; i < this.getWidth()/tileSize+2;i++) {
			for(int j = -1; j < this.getHeight()/tileSize+2;j++) {
				
				int posX = (int)((i-camPos.x%1.0)*(float)tileSize);
				int posY = (int)((j-camPos.y%1.0)*(float)tileSize);
				g2.drawImage(this.backgroundImg, posX, posY, tileSize,tileSize,null);
			}
		}
	}
}