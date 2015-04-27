package core.levels;

import java.awt.image.BufferedImage;

import core.enums.LevelID;
import core.handlers.ObjectHandler;
import core.visualgronk.LevelLoader;

public abstract class LevelSpawner {

	LevelID lId;
	ObjectHandler handler;
	LevelLoader loader;
	BufferedImage map;
	String filename;
	
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
}
