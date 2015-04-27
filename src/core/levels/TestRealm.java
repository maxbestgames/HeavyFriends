package core.levels;

import core.HUD;
import core.Window;
import core.enums.ID;
import core.gameobjects.Block;
import core.handlers.Handler;

public class TestRealm extends LevelSpawner {

	public TestRealm(Handler handler, HUD hud) {
		super(handler, hud);

	}
	
	public void createLevel() {
		for(int i = 0; i < Window.getVisibleScreenX()+Block.getBlockSize(); i += Block.getBlockSize()) {
			handler.addObject(new Block(i, (int)(Window.visibleScreenY-0.2*Window.visibleScreenY), ID.Block, handler));
		}
		
		for(int i = 0; i < Window.getVisibleScreenX()+Block.getBlockSize(); i += Block.getBlockSize()) {
			handler.addObject(new Block(i, (int)(Window.visibleScreenY-0.6*Window.visibleScreenY), ID.Block, handler));
		}
			
	}

}
