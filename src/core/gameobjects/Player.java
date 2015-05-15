package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
import core.enums.EntityID;
import core.enums.ObjectAction;
import core.enums.ObjectState;
import core.enums.PlayerAction;
import core.enums.PlayerState;
import core.enums.RenderPriority;
import core.handlers.BoundsHandler;
import core.handlers.player.PlayerBoundsHandler;
import core.handlers.player.PlayerCollisionHandler;
import core.handlers.player.PlayerMovementHandler;
import core.visualgronk.Animation;
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	public int playerHealth;
	
	private Animation playerWalk;
	private Animation playerCrawl;
	private Animation playerProne;
	
	private PlayerState currentPlayerState;
	private PlayerAction currentPlayerAction;
	
	private PlayerBoundsHandler boundBox;
	private PlayerMovementHandler movement;
	private PlayerCollisionHandler col;
	
	public Player(int x, int y, EntityID id) {
          		super(x, y, id);
		currentPlayerState = PlayerState.Standing;
		currentPlayerAction = PlayerAction.Falling;
		
		
		
		playerHealth = 120;
		
		rendPriority = RenderPriority.Player;
		
		tex = new Texture("assets/spritemaps/blackmandelux.png", 32, 64);
		
		playerWalk = new Animation(3, tex.getSprite(4, 0), tex.getSprite(5, 0), tex.getSprite(7, 0), tex.getSprite(8, 0), tex.getSprite(9, 0), tex.getSprite(10, 0), tex.getSprite(11, 0), tex.getSprite(12, 0), tex.getSprite(13, 0), tex.getSprite(14, 0), tex.getSprite(15, 0));
		playerCrawl = new Animation(3, tex.getSprite(17, 0), tex.getSprite(18, 0), tex.getSprite(19, 0));
		playerProne = new Animation(8,
				tex.rotate(tex.getSprite(25, 0), (float) (Math.PI/2), 32, 64), 
				tex.rotate(tex.getSprite(26, 0), (float) (Math.PI/2), 32, 64), 
				tex.rotate(tex.getSprite(27, 0), (float) (Math.PI/2), 32, 64));
		
		
		boundBox = new PlayerBoundsHandler(this);
		movement = new PlayerMovementHandler(this);
		col = new PlayerCollisionHandler(this);
		
		width = 32;
		height = 64;
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
		
		getColHandler().doPlayerCollision();
		
		playerWalk.runAnimation();
		playerCrawl.runAnimation();
		playerProne.runAnimation();
		
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		
		if(drawTextures) {
			if (velX > 0 && currentPlayerState == PlayerState.Standing) g.drawImage(playerWalk.getAnimation(), (int) x, (int) y, null);
			else if (velX <0 && currentPlayerState == PlayerState.Standing) g.drawImage(Texture.flipVert(playerWalk.getAnimation()), (int) x, (int) y, null);
			else if (velX == 0 && currentPlayerState == PlayerState.Standing)  g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
			
			if (velX > 0 && currentPlayerState == PlayerState.Crouching) g.drawImage(playerCrawl.getAnimation(), (int) x, (int) y-32, null);
			else if (velX < 0 && currentPlayerState == PlayerState.Crouching)  g.drawImage(Texture.flipVert(playerCrawl.getAnimation()), (int) x, (int) y-32, null);
			else if (velX == 0 && currentPlayerState == PlayerState.Crouching)  g.drawImage(tex.getSprite(17, 0), (int) x, (int) y-32, null);
			
			if (velX > 0 && currentPlayerState == PlayerState.Proning) g.drawImage(playerProne.getAnimation(),(int) x-32, (int) y-32, null);
			else if (velX < 0 && currentPlayerState == PlayerState.Proning)  g.drawImage(Texture.flipVert(playerProne.getAnimation()),(int) x-32, (int) y-32, null);
			else if (velX == 0 && currentPlayerState == PlayerState.Proning) g.drawImage(tex.rotate(tex.getSprite(24, 0), (float) (Math.PI/2), 32, 64), (int) x-32, (int) y-32, null);
			
			//if (velX > 0 && currentPlayerState == PlayerState.Proning)
			
			
		}
		
		if (drawBoundingBoxes) {
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.BLUE);
			g2d.draw(boundBox.getBoundsTop());
			g2d.draw(boundBox.getBoundsBottom());
			g2d.draw(boundBox.getBoundsTopLeft());
			g2d.draw(boundBox.getBoundsTopRight());
			g.setColor(Color.BLUE);
			g2d.draw(boundBox.getBoundsBotLeft());
			g2d.draw(boundBox.getBoundsBotRight());
			g.setColor(Color.RED);
			g2d.draw(boundBox.getBounds());
			g.setColor(Color.WHITE);
			g2d.draw(boundBox.getBoundsBotStop());
			g2d.draw(boundBox.getBoundsRightStop());
			g2d.draw(boundBox.getBoundsLeftStop());
			g2d.draw(boundBox.getBoundsTopStop());

		}
	}

	
	
	public PlayerState getPlayerState() {
		return currentPlayerState;
	}
	
	public void setState(PlayerState state) {
		currentPlayerState = state;
	}
	
	public PlayerAction getPlayerAction() {
		return currentPlayerAction;
	}
	
	public void setAction(PlayerAction action) {
		currentPlayerAction = action;
	}
	
	public PlayerBoundsHandler getPlayerBoundBox() {
		return boundBox;
	}

	public PlayerMovementHandler getMovement() {
		return movement;
	}
	
	public PlayerCollisionHandler getColHandler() {
		return col;
	}

	public Rectangle getBounds() {
		return boundBox.getBounds();
	}
	
	
	/** Use setAction(PlayerAction) instead.*/
	@Deprecated
	public void setAction(ObjectAction action) {}
	
	/** Use setState(PlayerState) instead.*/
	@Deprecated
	public void setState(ObjectState state) {}
	
	/** Use getPlayerState() instead.*/
	@Deprecated
	public ObjectState getState() { return null; }
	
	/** Use getPlayerAction() instead.*/
	@Deprecated
	public ObjectAction getAction() { return null; }
	
	/** Use getPlayerBoundBox() instead.*/
	@Deprecated
	public BoundsHandler getObjBoundBox() { return objBoundBox; }

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
}
