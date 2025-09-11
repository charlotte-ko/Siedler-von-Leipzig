package logic;

import java.awt.Color;
import java.awt.Dimension;
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
import settlers.SettlersAktion;
import settlers.SettlersJobs;


public class GamePanel extends JPanel implements Runnable{
	//Screen settings
	final int originalTileSize = 16; //16x16 Size
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale; //48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;//768pxl
	final int screenHeight = tileSize * maxScreenRow;//576pxl
	
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
	
	private BufferedImage backgroundImg1;
	private BufferedImage backgroundImg2;
	private BufferedImage backgroundImg;
	
	private GamePresenter presenter;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		this.loadBackgroundImg();
	}
	public void setPresenter(GamePresenter presenter) {
		this.presenter = presenter;
	}
	
	public void startGameThread() {
		itemList = presenter.model.itemList;
		buildingList = presenter.model.buildingList;
		settlerList = presenter.model.settlerList;
		this.key = presenter.model.key;
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long currentTime;
		double frameAcc = 0;
		long secAccNanos = 0;
		int drawCount = 0;
		
		long lastFrame = System.nanoTime();
		long currentFrame;
		double delta = 0.0;
		
		
		//game loop
		while(gameThread != null) {
			currentTime = System.nanoTime();
			long frameNanos = currentTime - lastTime;
			lastTime = currentTime;
			
			frameAcc += frameNanos / drawIntervalNanos;
			secAccNanos += frameNanos;
			
			
			if(frameAcc >= 1) {
				currentFrame = System.nanoTime();
				delta = (currentFrame - lastFrame)/1_000_000_000.0;
				lastFrame = currentFrame;
				update(delta);
				repaint(); //ruft paintComponent auf
				frameAcc -= 1.0;
				drawCount++;
			}
			if(secAccNanos >= 1_000_000_000) {
				System.out.println("FPS:"+drawCount+"dt(ms):"+(delta*1000.0));
				System.out.println(camPos.x + "/" + camPos.y);
				drawCount = 0;
				secAccNanos = 0;
			}
		}
	}
	
	private boolean zoomInBlock = false;
	private boolean zoomOutBlock = false;
	private void zoomManager() {
		
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
	
	float cameraSpeed = 4.0f;
	private void cameraManager(double delta) {
		if(this.keyH.upPressed)camPos.y -= delta * cameraSpeed;
		if(this.keyH.downPressed)camPos.y += delta * cameraSpeed;
		if(this.keyH.rightPressed)camPos.x += delta * cameraSpeed;
		if(this.keyH.leftPressed)camPos.x -= delta * cameraSpeed;
	}
	
	public void update(double delta) {
		zoomManager();
		cameraManager(delta);
		presenter.giveCamera(camPos,tileSize);
		updateBackGroundImg(delta);
		
		//Update funktion der Siedler und Gebäude durchgehen
		//player.update();
		synchronized(key) {
			for(Buildings b : buildingList) {
				b.update(delta, settlerList, buildingList, itemList);
			}
			for(ASettlers s : settlerList) {
				s.update(delta, settlerList, buildingList, itemList);
			}
			renderSort(itemList,buildingList,settlerList);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Tile size ermitteln
		tileSize = (int)((float)Math.min(this.getWidth(), this.getHeight())/(float)grid_zoom);
		
		//Draw funktion der Gebäude, Siedler und Items. Sowie terrain
		
		ArrayList<Items> itemListSnap;
		ArrayList<Buildings> buildingListSnap;
		ArrayList<ASettlers> settlerListSnap;
		synchronized(key) {
			itemListSnap = new ArrayList<>(itemList);
			buildingListSnap = new ArrayList<>(buildingList);
			settlerListSnap = new ArrayList<>(settlerList);
		}
		
		//hintergrund zeichnen
		this.drawbackGround(g2, tileSize, camPos, 1);
		
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
	private void loadBackgroundImg() {
		try {
			this.backgroundImg1 = ImageIO.read(getClass().getResourceAsStream("/resources/Grass_1.png"));
			this.backgroundImg2 = ImageIO.read(getClass().getResourceAsStream("/resources/Grass_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private double backgroundTimer = 0.0;
	
	private void updateBackGroundImg(double delta) {
		backgroundTimer += delta;
		if(backgroundTimer > 0.5) {
			backgroundImg = backgroundImg1;
		}else {
			backgroundImg = backgroundImg2;
		}
		backgroundTimer %= 1.0;
	}
	
	private void drawbackGround(Graphics2D g2,int tileSize,Vector2D camPos,double delta) {
		for(int i = -1; i < this.getWidth()/tileSize+2;i++) {
			for(int j = -1; j < this.getHeight()/tileSize+2;j++) {
				int posX = (int)((i-camPos.x%1.0)*(float)tileSize);
				int posY = (int)((j-camPos.y%1.0)*(float)tileSize);
				g2.drawImage(this.backgroundImg, posX, posY, tileSize,tileSize,null);
			}
		}
	}
	
	/**
	 * @brief Sortiert alle Listen nach render reinfolge
	 * @param itemList
	 * @param buildingList
	 * @param settlerList
	 * @note Insertion Sort 5-line version nach Jon Bentley, Programming Pearls
	 */
	private void renderSort(ArrayList<Items>itemList,ArrayList<Buildings>buildingList,ArrayList<ASettlers>settlerList) {
		int i,j;
		for (i = 1; i < itemList.size(); i++) {
		    Items x = itemList.get(i);
		    for (j = i; j > 0 && itemList.get(j-1).getPosition().y > x.getPosition().y; j--)
		        itemList.set(j, itemList.get(j-1));
		    itemList.set(j, x);
		}
		for (i = 1; i < buildingList.size(); i++) {
		    Buildings x = buildingList.get(i);
		    for (j = i; j > 0 && buildingList.get(j-1).getPosition().y > x.getPosition().y; j--)
		    	buildingList.set(j, buildingList.get(j-1));
		    buildingList.set(j, x);
		}
		for (i = 1; i < settlerList.size(); i++) {
		    ASettlers x = settlerList.get(i);
		    for (j = i; j > 0 && settlerList.get(j-1).getPosition().y > x.getPosition().y; j--)
		    	settlerList.set(j, settlerList.get(j-1));
		    settlerList.set(j, x);
		}

	}
	
}
