package core.gameobjects;

import core.enums.EntityID;
import core.handlers.BoundsHandler;

public abstract class TickingGameObject extends GameObject{

	protected BoundsHandler objBoundBox;
	
	public TickingGameObject(int x, int y, EntityID id) {
		super(x, y, id);
	}

	
	public abstract void tick();
	
	public BoundsHandler getObjBoundBox() {
		return objBoundBox;
	}
}
