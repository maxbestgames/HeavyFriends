package core.gameobjects;

import core.enums.EnemyAIState;
import core.enums.EnemyType;
import core.enums.EntityID;
import core.enums.RenderPriority;
import core.handlers.MovementHandler;

public abstract class Enemy extends TickingGameObject {
	
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
}
