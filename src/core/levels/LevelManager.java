package core.levels;

import core.handlers.WorldHandler;
import core.display.HUD;

public class LevelManager {
	
	protected WorldHandler handler;
	protected HUD hud;
	
	public LevelManager(WorldHandler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		
	}
}
