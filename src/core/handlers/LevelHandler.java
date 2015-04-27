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
	
	public int getNumLevels() {
		return levelList.size();
	}
	
	public LevelSpawner getLevel(int index) {
		return levelList.get(index);
	}
	
	public LevelSpawner getLevel(LevelID lId) {
		LevelSpawner tempLevel;
		for(int i = 0; i<levelList.size(); i++){
			tempLevel = (LevelSpawner) levelList.get(i);
			if (tempLevel.getId() == lId) {
				return tempLevel;
			}
		}
		return null;
	}
	
	public void addLevel(LevelID lId) {
		//levelList.add(new LevelSpawner())
	}

}
