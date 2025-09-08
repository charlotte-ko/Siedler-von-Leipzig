package logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import buildings.Buildings;
import items.Items;
import main.KeyHandler;
import settlers.ASettlers;


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
	KeyHandler keyH = new KeyHandler();
	int FPS = 60;
	double drawIntervalNanos = 1_000_000_000.0 /FPS;
	
	//Später hinzufügen für die Kamera Steuerung
	//KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//init Siedler,Häuser,Items etc.
	//Player player = new Player(this,keyH);
	ArrayList<Items> itemList = new ArrayList<>();
	ArrayList<Buildings> buildingList = new ArrayList<>();
	ArrayList<ASettlers> settlerList = new ArrayList<>();
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		double frameAcc = 0;
		long secAccNanos = 0;
		int drawCount = 0;
		
		//prepare
		for(int i = 0; i < 30;i++) {
			for(int j = 0; j < 30;j++) {
				itemList.add(Items.createItem("Axe", new Vector2D(i,j)));
			}
		}
		/*
		buildingList.add(Buildings.createBuilding("Forester",new Vector2D(0,0)));
		itemList.add(Items.createItem("Axe", new Vector2D(tileSize*2,tileSize*2)));
		settlerList.add(ASettlers.createSettler(new Vector2D(tileSize*3,tileSize*2)));
		settlerList.get(0).giveSettlerTask(new Vector2D(tileSize*10,tileSize*10), SettlersAktion.GO_FREE, null);
		settlerList.get(0).giveSettlerTask(itemList.get(0).getPosition(), SettlersAktion.GO_PICKUP_LEFT, itemList.get(0));
		settlerList.get(0).giveSettlerTask(new Vector2D(tileSize*10,tileSize*10), SettlersAktion.GO_FREE, null);
		*/
		//game loop
		while(gameThread != null) {
			currentTime = System.nanoTime();
			long frameNanos = currentTime - lastTime;
			lastTime = currentTime;
			
			frameAcc += frameNanos / drawIntervalNanos;
			secAccNanos += frameNanos;
			
			double deltaSec = frameNanos / 1_000_000_000.0;
			
			if(frameAcc >= 1) {
				update(deltaSec);
				repaint(); //ruft paintComponent auf
				frameAcc -= 1.0;
				drawCount++;
			}
			if(secAccNanos >= 1_000_000_000) {
				System.out.println("FPS:"+drawCount+"dt(ms):"+(deltaSec*1000.0));
				drawCount = 0;
				secAccNanos = 0;
			}
		}
	}
	
	public void update(double delta) {
		if(this.keyH.upPressed) {
			this.grid_zoom++;
		}
		if(this.keyH.downPressed) {
			this.grid_zoom--;
		}
		if(grid_zoom <= 0) {
			grid_zoom = 1;
		}
		if(grid_zoom > 30) {
			grid_zoom = 30;
		}
		
		//Update funktion der Siedler und Gebäude durchgehen
		//player.update();
		for(Buildings b : buildingList) {
			//b.update(delta, settlerList, buildingList, itemList);
		}
		for(ASettlers s : settlerList) {
			//s.update(delta, settlerList, buildingList, itemList);
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
		synchronized(itemList) {itemListSnap = new ArrayList<>(itemList);}
		synchronized(buildingList) {buildingListSnap = new ArrayList<>(buildingList);}
		synchronized(settlerList) {settlerListSnap = new ArrayList<>(settlerList);}
		
		
		//tile size muss noch angepasst werden
		for(Items i : itemListSnap) {
			i.draw(g2,tileSize);
		}
		for(ASettlers s: settlerListSnap) {
			s.draw(g2,tileSize);
		}
		for(Buildings b : buildingListSnap) {
			
			b.draw(g2,tileSize);
		}
		
		//g2.dispose();//nicht wirklich nötig
	}
}
