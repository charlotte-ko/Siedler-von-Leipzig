package settlers;

import logic.Vector2D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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
	 */
	protected void changeSettlerType(ArrayList<ISettlers>settlersList,String settlerType) {
		for(int i = 0; i < settlersList.size();i++) {
			if(settlersList.get(i) != this) {
				continue;
			}
			settlersList.set(i, createSettler(settlerType,this.position));
			//mgl items übertragen
			ISettlers s = settlersList.get(i);
			s.setItemHand(this.getItemHand(true), true,settlersList);
			s.setItemHand(this.getItemHand(false), false,settlersList);
			while(!this.task.isEmpty()) {
				s.giveSettlerTask(this.taskWay.pop(), this.task.pop());
			}
			break;
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
		String newType = "NormalSettler";
		/*
		 * Hier muss noch hinzugefügt werden welches Item welchen Siedler erzeugt
		 */
		switch(item.getName()) {
			default:break;
		}
		this.changeSettlerType(settlersList, newType);
	}
	public IItems takeItemHand(boolean leftHand,ArrayList<ISettlers> settlersList) {
		if(leftHand) {
			IItems ret = this.leftHand;
			this.leftHand = null;
			return ret;
		}
		this.changeSettlerType(settlersList, "NormalSettler");
		IItems ret = this.rightHand;
		this.rightHand = null;
		return ret;
	}
	public void giveSettlerTask(Vector2D punkt,SettlersAktion aufgabe) {
		this.taskWay.push(punkt);
		this.task.push(aufgabe);
	}
	
	protected abstract void work(float delta);
}
