package core.levels;

import core.HUD;
import core.enums.LevelID;
import core.handlers.LevelHandler;
import core.handlers.ObjectHandler;
import core.handlers.WorldHandler;

public abstract class LevelSpawner {

	LevelID lId;
	ObjectHandler handler;
	
	public LevelSpawner(LevelID lId) {
		this.lId = lId;
		handler = new ObjectHandler();
	}
	
	public LevelID getId() {
		return lId;
	}
	
	public ObjectHandler getObjHandler() {
		return handler;
	}
	
	public abstract void createLevel();
}
