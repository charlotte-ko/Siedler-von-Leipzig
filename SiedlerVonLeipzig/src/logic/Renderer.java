package logic;

import java.util.ArrayList;

import buildings.Buildings;
import items.Items;
import presenter.GamePresenter;
import settlers.ASettlers;

public class Renderer implements Runnable{
	int FPS = 60;
	double drawIntervalNanos = 1_000_000_000.0 /FPS;//Fps in nanosec
	
	Thread gameThread;
	Object key;
	
	ArrayList<Items> itemList;
	ArrayList<Buildings> buildingList;
	ArrayList<ASettlers> settlerList;
	
	private GamePresenter presenter;
	
	
	//private double gameLoopDebugInformationTimer = 0.0;
	private long gameLoopDebugInformationNanoTimer = 0;
	
	
	public Renderer(GamePresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long currentTime;
		double frameAcc = 0;
		int drawCount = 0;
		long lastFrame = System.nanoTime();
		double delta = 0.0;
		
		//game loop
		while(gameThread != null) {
			currentTime = System.nanoTime();
			long frameNanos = currentTime - lastTime;
			lastTime = currentTime;			
			frameAcc += frameNanos / drawIntervalNanos;

			if(frameAcc >= 1) {
				long currentFrame = System.nanoTime();
				delta = (currentFrame - lastFrame)/1_000_000_000.0;
				lastFrame = currentFrame;
				update(delta);
				this.presenter.view.gPanel.repaint();
				frameAcc -= 1.0;
				drawCount++;
			}
			if(this.presenter.model.debugMode)
				drawCount = gameLoopDebugInformation(drawCount,frameNanos,delta);
		}
	}
	/**
	 * @brief gibt nach gegebener Zeit Debug Informationen aus
	 * @param drawCount
	 * @param frameNanos
	 * @param delta
	 * @return
	 */
	private int gameLoopDebugInformation(int drawCount,long frameNanos,double delta){
		gameLoopDebugInformationNanoTimer += frameNanos;
		if(gameLoopDebugInformationNanoTimer >= 1_000_000_000) {
			System.out.println("FPS:"+drawCount+"dt(ms):"+(delta*1000.0));
			//System.out.println(camPos.x + "/" + camPos.y);
			drawCount = 0;
			gameLoopDebugInformationNanoTimer = 0;
		}
		return drawCount;
	}
	
	/**
	 * @brief berechnet das Spielgeschehen
	 * @param delta
	 * @note Neben Spiel Logik werden auch Animationen und Kamera aktualisiert
	 */
	public void update(double delta) {
		this.presenter.view.gPanel.zoomManager();
		this.presenter.view.gPanel.cameraManager(delta);
		
		presenter.giveCamera(this.presenter.view.gPanel.camPos,this.presenter.view.gPanel.tileSize);
		this.presenter.view.gPanel.updateBackGroundImg(delta);
		
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
	
	/**
	 * @brief läd die Spiel Daten und startet den GameThread
	 */
	public void startGameThread() {
		itemList = presenter.model.itemList;
		buildingList = presenter.model.buildingList;
		settlerList = presenter.model.settlerList;
		this.key = presenter.model.key;
		this.presenter.view.gPanel.setPresenter(presenter);
		gameThread = new Thread(this);
		gameThread.start();
	}
}
