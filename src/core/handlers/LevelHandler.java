package core.handlers;

import java.util.LinkedList;

import core.enums.LevelID;
import core.gameobjects.GameObject;
import core.gameobjects.TickingGameObject;
import core.levels.LevelSpawner;

public class LevelHandler {
	
	LinkedList<LevelSpawner> levelList = new LinkedList<LevelSpawner>();
	
	public void addObject(LevelSpawner l){
		levelList.add(l);
	}
	
	public void removeObject(LevelSpawner l){
		levelList.remove(l);
	}
	
	//TODO
	public void doSomething(){
		for(int i = 0; i<levelList.size(); i++){
			LevelSpawner tempLevel = (LevelSpawner) levelList.get(i);
			//The thing!
		}
	}
	
	public int getNumLevels() {
		return levelList.size();
	}
	
	public LevelSpawner getLevel(int index) {
		return levelList.get(index);
	}

}
