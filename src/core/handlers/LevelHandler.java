package core.handlers;

import java.util.LinkedList;

import core.enums.LevelID;
import core.levels.LevelBuilder;

public class LevelHandler {
	
	LinkedList<LevelBuilder> levelList = new LinkedList<LevelBuilder>();
	
	public void addObject(LevelBuilder l){
		levelList.add(l);
	}
	
	public void removeObject(LevelBuilder l){
		levelList.remove(l);
	}
	
	public int getNumLevels() {
		return levelList.size();
	}
	
	public LevelBuilder getLevel(int index) {
		return levelList.get(index);
	}
	
	public LevelBuilder getLevel(LevelID lId) {
		LevelBuilder tempLevel;
		for(int i = 0; i<levelList.size(); i++){
			tempLevel = (LevelBuilder) levelList.get(i);
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
