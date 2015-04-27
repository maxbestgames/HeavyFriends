package core.handlers;

import core.gameobjects.GameObject;

public class ObjectHandler extends Handler {
	
	public void addObject(GameObject gObject){
		object.add(gObject);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
}
