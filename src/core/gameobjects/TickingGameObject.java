package core.gameobjects;

import core.enums.EntityID;

public abstract class TickingGameObject extends GameObject{

	public TickingGameObject(int x, int y, EntityID id) {
		super(x, y, id);
	}

	
	public abstract void tick();
}
