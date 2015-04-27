package core.levels;

import core.HUD;
import core.enums.LevelID;
import core.handlers.LevelHandler;
import core.handlers.ObjectHandler;

public abstract class LevelSpawner {

	ObjectHandler handler;
	HUD hud;
	LevelID lId;
	
	public LevelSpawner(ObjectHandler handler, HUD hud, LevelID lId) {
		this.handler = handler;
		this.hud = hud;
		this.lId = lId;
	}
	
	public abstract void createLevel();
	public abstract ObjectHandler getObjects();
	
	public LevelID getId() {
		return lId;
	}
	
	public int getNumObjects() {
		return handler.getSize();
	}
	
}
