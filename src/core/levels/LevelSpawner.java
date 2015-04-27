package core.levels;

import core.HUD;
import core.handlers.Handler;
import core.handlers.LevelHandler;
import core.handlers.ObjectHandler;

public abstract class LevelSpawner {

	ObjectHandler handler;
	HUD hud;
	
	public LevelSpawner(ObjectHandler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public abstract void createLevel();
	public abstract ObjectHandler getObjects();
	
}
