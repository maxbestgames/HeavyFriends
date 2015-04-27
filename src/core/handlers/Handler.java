package core.handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import core.gameobjects.GameObject;
import core.gameobjects.TickingGameObject;
import core.gameobjects.Player;
import core.enums.ID;


public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			
			if (object.get(i) instanceof TickingGameObject) {
				TickingGameObject tempObject = (TickingGameObject) object.get(i);
				tempObject.tick();
			}
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i<object.size(); i++){
			GameObject tempObject =object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject gObject){
		object.add(gObject);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public GameObject getObject(int i) {
		return object.get(i);
	}
	
	public int getSize() {
		return object.size();
	}
	
	public Player getPlayer() {
		GameObject tempObject;
		for (int i = 0; i<getSize(); i++) {
			tempObject = getObject(i);
			if (tempObject.getId() == ID.Player) {
				return (Player) tempObject;
			}
		}
		return null;
	}
	
}
