package core.gameobjects;

import core.enums.EnemyType;
import core.enums.EntityID;
import core.gameobjects.ai.EnemyAI;
import core.handlers.EnemyBoundsHandler;
import core.handlers.EnemyCollisionHandler;
import core.handlers.EnemyMovementHandler;

public abstract class Enemy extends TickingGameObject {

	EnemyCollisionHandler col;
	EnemyMovementHandler mov;
	EnemyBoundsHandler bound;
	EnemyAI ai;
	EnemyType type;
	
	
	public Enemy(int x, int y, EntityID id, EnemyType type) {
		super(x, y, id);
		this.type = type;
	}
	
	
}
