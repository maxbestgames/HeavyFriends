package core.levels;

import core.HUD;
import core.handlers.Handler;

public abstract class LevelSpawner {

	Handler handler;
	HUD hud;
	
	public LevelSpawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public abstract void createLevel();
	
}
