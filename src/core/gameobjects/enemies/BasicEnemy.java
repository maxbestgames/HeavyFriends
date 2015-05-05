package core.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
import core.enums.EnemyAIState;
import core.enums.EnemyType;
import core.enums.EntityID;
import core.enums.ObjectAction;
import core.enums.ObjectState;
import core.enums.PlayerState;
import core.gameobjects.Enemy;
import core.handlers.MovementHandler;
import core.visualgronk.Animation;
import core.visualgronk.Texture;

public class BasicEnemy extends Enemy {

	protected Random r;

	private PlayerState currentObjectState;
	
	private Animation walk;
	boolean drawTextures = true;
	protected boolean drawHitBoxes = true;
	
	protected int direction;
	
	protected float gravity;
	protected float patrolSpeed = 4.5f;
	
	public BasicEnemy(int x, int y, EntityID id, EnemyType type) {
		super(x, y, id, type);
		
		r = new Random();
		
		movement = new MovementHandler(this);
		
		tex = new Texture("assets/spritemaps/Theman.png", 32, 64);
		
		walk = new Animation(3, tex.getSprite(1, 0), tex.getSprite(2, 0), tex.getSprite(3, 0) );
		
		currentAction = ObjectAction.Falling;
		currentState = ObjectState.Standing;
		
		
		gravity = 0.9f;
		height = 64;
		width = 32;
		
		direction = r.nextInt(2);
		if (direction == 0) direction = -1;

	}

	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		//g.fillRect((int) x, (int) y, width, height);

		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GREEN);
		
		if(drawTextures) {
			if (velX > 0 && currentState == ObjectState.Standing) walk.drawAnimation(g, (int) x, (int) y);
			else if (velX <0 && currentState == ObjectState.Standing) walk.drawAnimation(g, (int) x, (int) y);
			else if (velX == 0 && currentState == ObjectState.Standing)  g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
		}

		if (drawHitBoxes) {
			g2d.draw(getBounds());
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		
		walk.runAnimation();

		
		if(movement.isGravityEnabled()) {
			velY += gravity;
		}
		
		performAction();
		movement.setJumpingOrFalling();
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	
	protected EnemyAIState decideAction() {
		return EnemyAIState.Patrolling;
	}
	
	protected void performAction() {
		EnemyAIState state = decideAction();
		
		if (state == EnemyAIState.Patrolling) {
			if (direction == 1) { // patrolling right
				if (!rightStop) {
					setVelX(patrolSpeed);
				} else {
					direction = -1;
					setVelX(-patrolSpeed);
				}
			} else { // patrolling left
				if (!leftStop) {
					setVelX(-patrolSpeed);
				} else {
					direction = 1;
					setVelX(patrolSpeed);
				}
			}
		}
		/*
		
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
		*/
		
		
	}
}
