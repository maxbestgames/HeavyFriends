package core.handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import core.enums.EntityID;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.gameobjects.TickingGameObject;


public class ObjectHandler {

	LinkedList<Object> object = new LinkedList<Object>();;
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			
			if (object.get(i) instanceof TickingGameObject) {
				TickingGameObject tempObject = (TickingGameObject) object.get(i);
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<object.size(); i++){
			GameObject tempObject =(GameObject) object.get(i);
			tempObject.render(g);
		}
	}
	
	public GameObject getObject(int i) {
		return (GameObject) object.get(i);
	}
	
	public int getSize() {
		return object.size();
	}
	
	public void addObject(GameObject gObject){
		object.add(gObject);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	/**
	 * @deprecated
	 */
	public Player getPlayer() {	
		GameObject tempObject;
		for (int i = 0; i<getSize(); i++) {
			tempObject = getObject(i);
			if (tempObject.getId() == EntityID.Player) {
				return (Player) tempObject;
			}
		}
		return null;
	}
	
}
