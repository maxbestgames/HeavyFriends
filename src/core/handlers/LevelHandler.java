package core.handlers;

import core.gameobjects.GameObject;
import core.gameobjects.TickingGameObject;
import core.levels.LevelSpawner;

public class LevelHandler extends Handler {
	
	public void addObject(LevelSpawner l){
		object.add(l);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	//TODO
	public void doSomething(){
		for(int i = 0; i<object.size(); i++){
			LevelSpawner tempLevel = (LevelSpawner) object.get(i);
			//The thing!
		}
	}

}
