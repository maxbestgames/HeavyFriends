package core.gameobjects;

import core.enums.EntityID;

public abstract class Enemy extends TickingGameObject {

	public Enemy(int x, int y, EntityID id) {
		super(x, y, id);
		
	}

	
}
