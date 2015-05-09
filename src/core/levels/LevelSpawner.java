package core.levels;

import java.awt.image.BufferedImage;

import core.enums.LevelID;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer1;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer2;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer3;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer4;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer5;
import core.gameobjects.art.backgrounds.LevelBackgroundLayer6;
import core.handlers.ObjectHandler;
import core.visualgronk.LevelLoader;

public abstract class LevelSpawner {

	protected LevelID lId;
	protected ObjectHandler handler;
	protected LevelLoader loader;
	protected BufferedImage map;
	protected String filename;
	
	protected LevelBackgroundLayer1 bL1;
	protected LevelBackgroundLayer2 bL2;
	protected LevelBackgroundLayer3 bL3;
	protected LevelBackgroundLayer4 bL4;
	protected LevelBackgroundLayer5 bL5;
	protected LevelBackgroundLayer6 bL6;
	
	
	public LevelSpawner(LevelID lId, String filename) {
		this.lId = lId;
		this.filename = filename;
		handler = new ObjectHandler();
		loader = new LevelLoader(handler, filename); //TODO
	}
	
	public LevelID getId() {
		return lId;
	}
	
	public ObjectHandler getObjHandler() {
		return handler;
	}
	
	public LevelBackgroundLayer1 getBL1() {
		return bL1;
	}
	
	public LevelBackgroundLayer2 getBL2() {
		return bL2;
	}
	
	public LevelBackgroundLayer3 getBL3() {
		return bL3;
	}
	
	public LevelBackgroundLayer4 getBL4() {
		return bL4;
	}
	
	public LevelBackgroundLayer5 getBL5() {
		return bL5;
	}
	
	public LevelBackgroundLayer6 getBL6() {
		return bL6;
	}
}
