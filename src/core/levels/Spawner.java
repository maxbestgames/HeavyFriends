package core.levels;

import core.handlers.Handler;
import core.HUD;

public class Spawner {
	
	protected Handler handler;
	protected HUD hud;
	
	public Spawner(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		
	}
}
