package settlers;

import logic.Vector2D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import buildings.IBuildings;
import items.IItems;

public abstract class ASettlers implements ISettlers {
	private Vector2D position;
	private float speed;
	private boolean working;
	private IItems rightHand;
	private IItems leftHand;
	//Aufgabenspeicher (deque besser als stack)
	private Deque<Vector2D> taskWay = new ArrayDeque<>(); //Weg zur Aufgabe
	private Deque<SettlersAktion> task = new ArrayDeque<>();	//aufgabe
	
	
	private Deque<IItems> taskTarget = new ArrayDeque<>();
	
	public IItems getItem(boolean leftHand) {
		if(leftHand) {
			return this.leftHand;
		}else {
			return this.rightHand;
		}
	}
	public void setItem(boolean leftHand,IItems tem) {
		if(leftHand) {
			this.leftHand = tem;
		}else {
			this.rightHand = tem;
		}
	}
	
	/**
	 * @brief hilfs Factory Methode zum erstellen von einem Siedler
	 * @param settlerType
	 * @param position
	 * @return ISettlers
	 */
	private static ISettlers createSettler(String settlerType,Vector2D position) {
		ISettlers newSettler;
		switch(settlerType) {
			case "NormalSettler":
				newSettler = new NormalSettler(position);
				break;
			default:
				newSettler = new NormalSettler(position);
				break;
		}
		return newSettler;
	}
	/**
	 * @brief Factory Methode zum Erstellen von Siedlern, welche direkt in die globale settlerList gespeichert werden.
	 * @param settlerList
	 * @param settlerType
	 * @param position
	 * @return erstelltes Objekt wird zurückgegeben (steht aber auch in der liste)
	 * @note Es sollten nur einfache Siedler (ohne Arbeits Item) erstellt werden
	 * Siedler können durch Items ihren Job bekommen.
	 */
	public static ISettlers createSettlerInList(ArrayList<ISettlers> settlerList,String settlerType,Vector2D position) {
		ISettlers newSettler = createSettler(settlerType,position);
		settlerList.add(newSettler);
		return newSettler;
	}
	
	/**
	 * @brief ändert Siedler Type
	 * @param settlersList Liste mit allen Siedlern (um richtigen Eintrag zu ändern)
	 * @pram settlerType Type in wen er verwandelt werden soll
	 */
	protected void changeSettlerType(ArrayList<ISettlers>settlersList,String settlerType) {
		ISettlers s = null;
		for(int i = 0; i < settlersList.size();i++) {
			if(settlersList.get(i) != this) {
				continue;
			}
			settlersList.set(i, createSettler(settlerType,this.position));
			s = settlersList.get(i);
		}
		if(s == null) {
			return;
		}
		//items übertragen
		s.setItem(true, this.getItem(true));
		s.setItem(false, this.getItem(false));
		//Aufgaben übertragen
		while(!this.task.isEmpty()) {
			s.giveSettlerTask(this.taskWay.pollFirst(), this.task.pollFirst(),this.taskTarget.pollFirst());
		}
	}
	
	public Vector2D getPosition() {
		return this.position;
	}
	public void setPosition(Vector2D pos) {
		this.position = pos;
	}
	public float getSpeed() {
		return this.speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public boolean isWorking() {
		return this.working;
	}
	protected void setIsWorking(boolean working) {
		this.working = working;
	}
	public IItems getItemHand(boolean leftHand) {
		if(leftHand) {
			return this.leftHand;
		}else {
			return this.rightHand;
		}
	}
	

	public void setItemHand(IItems item,boolean leftHand,ArrayList<ISettlers> settlersList) {
		if(leftHand) {
			this.leftHand = item;
			return;
		}
		this.changeSettlerType(settlersList, ASettlers.getJobFromItem(item));
	}
	
	public IItems takeItemHand(boolean leftHand,ArrayList<ISettlers> settlersList) {
		if(leftHand) {
			IItems ret = this.leftHand;
			this.leftHand = null;
			return ret;
		}
		
		IItems ret = this.rightHand;
		this.rightHand = null;
		this.changeSettlerType(settlersList, "NormalSettler");
		return ret;
	}
	

	public void giveSettlerTask(Vector2D punkt,SettlersAktion aufgabe,IItems target) {
		this.taskWay.addLast(punkt);
		this.task.addLast(aufgabe);
		this.taskTarget.addLast(target);
	}
	
	public void update(float delta,ArrayList<IItems> itemList,ArrayList<IBuildings> buildingList,ArrayList<ISettlers> settlerList) {
		if(this.task.isEmpty()) {
			this.working = false;
			return;
		}
		if(this.position.getDistance(this.taskWay.peekFirst()) > 1.0f) {
			walk(delta,this.taskWay.peekFirst());
			return;
		}
		this.taskWay.pollFirst();
		IItems target = this.taskTarget.pollFirst();
		switch(this.task.pollFirst()) {
			case GO_PICKUP_LEFT:
				this.setItemHand(target, true, settlerList);
				break;
			case GO_PICKUP_RIGHT:
				this.setItemHand(target, false, settlerList);
				break;
			case GO_BRING_LEFT:
				this.takeItemHand(true, settlerList);
				break;
			case GO_BRING_RIGHT:
				this.takeItemHand(false, settlerList);
				break;
			case GO_WORK:
				this.work(delta);
				break;
			case GO_FREE://tut nix
				break;
			default:
				break;
		}
	}
	/**
	 * @brief geht in die gewünschte richtung
	 * @param delta
	 * @param target wo es hingehen soll
	 */
	private void walk(float delta,Vector2D target) {
		Vector2D direction = this.position.getTwoPointVector(target);
		direction = direction.normalize();
		direction = direction.scale(this.getSpeed()*delta);
		this.position = this.position.add(direction);
	}
	/**
	 * @brief gibt Job zurück welches zum Item passt
	 * @param tem (undertail anspielung)
	 * @return
	 */
	private static String getJobFromItem(IItems tem) {
		switch(tem.getName()) {
			case "Hammer":
				return "Builder";
			case "Saw":
				return "Sawyer";
			case "Axe":
				return "Woodcutter";
			default:
				return "NormalSettler";
		}
	}
	
	protected abstract void work(float delta);
}
