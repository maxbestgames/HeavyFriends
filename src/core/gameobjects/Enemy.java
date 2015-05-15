package core.gameobjects;

import core.enums.EnemyAIState;
import core.enums.EnemyType;
import core.enums.EntityID;
import core.enums.RenderPriority;
import core.handlers.MovementHandler;

public abstract class Enemy extends TickingGameObject {
	
	// trying to make a new type of enemy but it wont appear in the game?
	// make sure you set the initial health or it will be removed on its first tick!
	
	protected int health;
	protected EnemyType type;
	protected EnemyAIState currentAiState;
	
	protected MovementHandler movement;
	
	public Enemy(int x, int y, EntityID id, EnemyType type) {
		super(x, y, id);
		this.type = type;
		
		rendPriority = RenderPriority.Enemies;
	}
	
	public MovementHandler getMovementHandler() {
		return movement;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
