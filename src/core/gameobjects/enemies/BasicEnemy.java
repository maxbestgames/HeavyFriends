package core.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.Game;
import core.enums.EnemyType;
import core.enums.EntityID;
import core.gameobjects.Enemy;
import core.handlers.enemy.EnemyMovementHandler;

public class BasicEnemy extends Enemy {

	private boolean drawHitBoxes = true;
	
	float gravity;
	
	public BasicEnemy(int x, int y, EntityID id, EnemyType type) {
		super(x, y, id, type);
		
		movement = new EnemyMovementHandler();	
		gravity = 0.9f;
		height = 32;
		width = 32;

	}



	public void render(Graphics g) {

		//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);

		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GREEN);

		if (drawHitBoxes) {
			g2d.draw(getBounds());
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//System.out.println(currentPlayerState.toString());
		
		if(movement.isGravityEnabled()) {
			velY += gravity;
		}
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	
	private EnemyAIState decideAction() {
		return EnemyAIState.Patrolling;
	}
	
	private void performAction() {
		EnemyAIState state = decideAction();
		
		
		if (state == EnemyAIState.Patrolling) {
			if (currentDirection == 1) { // patrolling right
				if (getCollisonHandler.canMoveRight) {
					move right
				} else {
					current direction = -1;
					move left
				}
			} else { // patrolling left
				if (getCollisonHandler.canMoveLeft) {
					move left
				} else {
					current direction = 1;
					move right
				}
			}
		}
		
		if (state == EnemyAIState.Searching) {
			
			pathfind to alert space()
			move in direction()
				
		}
		
		if (state == EnemyAIState.Persuing) {
			
			
			pathfind to player()
			move in direction
			
		}
		
		if (state == EnemyAIState.Suspicious) {
			move carefully, looking intently. higher chance of player detection
		}
		
		if (state == EnemyAIState.Waiting) {
			hold position
		}
		
		if (state == EnemyAIState.Sleeping) {
			prone, hold position
		}
		
		if (state == EnemyAIState.MeleeAttacking) {
			perform attack at player
		}
		
		if (state == EnemyAIState.RangedAttacking) {
			perform attack at player
		}
		
		if (state == EnemyAIState.Fleeing) {
			pathfind away from player
			move in direction
		}
		
		if (state == EnemyAIState.Hiding) {
			hold position
			lower chance of player detection
			maybe regen health
		}
		
		if (state == EnemyAIState.Dead) {
			hold position, show dead texture
			
		}
		
		
		
		
		
		
		
	}
}
