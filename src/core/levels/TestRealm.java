package core.levels;

import core.HUD;
import core.Window;
import core.enums.ID;
import core.gameobjects.Block;
import core.handlers.Handler;
import core.handlers.LevelHandler;

public class TestRealm extends LevelSpawner {

	public TestRealm(LevelHandler handler, HUD hud) {
		super(handler, hud);

	}
	
	public void createLevel() {
		for(int i = 0; i < Window.getVisibleScreenX()+Block.getBlockSize(); i += Block.getBlockSize()) {
			handler.add(new Block(i, (int)(Window.visibleScreenY-0.2*Window.visibleScreenY), ID.Block, handler));
			handler.
		}
		
		for(int i = 0; i < Window.getVisibleScreenX()+Block.getBlockSize(); i += Block.getBlockSize()) {
			handler.add(new Block(i, (int)(Window.visibleScreenY-0.6*Window.visibleScreenY), ID.Block, handler));
		}
			
	}

	public Handler getObjects() {
		return handler;
	}

}
