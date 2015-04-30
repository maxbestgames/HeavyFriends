package core.levels;

import core.handlers.WorldHandler;
import core.display.HUD;

public class Spawner {
	
	protected WorldHandler handler;
	protected HUD hud;
	
	public Spawner(WorldHandler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		
	}
}
