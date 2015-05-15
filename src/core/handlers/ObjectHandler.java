package core.handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import core.Game;
import core.RenderThread;
import core.enums.EntityID;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.gameobjects.TickingGameObject;


public class ObjectHandler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			
			if (object.get(i).isMarkedForRemoval()) {
				removeObject(i);
			} else if (object.get(i) instanceof TickingGameObject) {
				TickingGameObject tempObject = (TickingGameObject) object.get(i);
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<object.size(); i++){
			try {
				GameObject tempObject = (GameObject) object.get(i);
				//tempObject.render(g);
				RenderThread.getRenderQueue().add(tempObject);
			} catch (NullPointerException ex) {
				// object was probably deleted exactly as render pass started
			}
			
			
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
		System.out.println("Removing " + object.getId().toString()+": " + object.getClass().getName());
		this.object.remove(object);
	}
	
	public void removeObject(int i) {
		this.object.remove(i);
	}
	
	/**
	 * Player is held in World Handler now.
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
