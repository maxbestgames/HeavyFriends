package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import core.Game;
import core.enums.EntityID;
import core.enums.PlayerAction;
import core.enums.PlayerState;
import core.handlers.player.PlayerBoundsHandler;
import core.handlers.player.PlayerCollisionHandler;
import core.handlers.player.PlayerMovementHandler;
import core.visualgronk.Animation;
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	
	private Animation playerWalk;
	private Animation playerCrawl;
	
	private PlayerState currentPlayerState;
	private PlayerAction currentPlayerAction;
	
	private PlayerBoundsHandler boundBox;
	private PlayerMovementHandler movement;
	private PlayerCollisionHandler col;
	
	private boolean drawHitBoxes = false;
	private boolean drawTextures = true;
	
	
	
	public Player(int x, int y, EntityID id) {
		super(x, y, id);
		currentPlayerState = PlayerState.Standing;
		currentPlayerAction = PlayerAction.Falling;
		
		tex = new Texture("assets/spritemaps/stickmang.png", 32, 64);
		
		playerWalk = new Animation(5, tex.getSprite(0, 0), tex.getSprite(1, 0), tex.getSprite(2, 0));
		playerCrawl = new Animation(5, tex.getSprite(7,0), tex.getSprite(8,0));
		
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
		
	}
	
	public void render(Graphics g) {
		
		if(id==EntityID.Player){
			//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(Color.WHITE);
			g.fillRect((int) x, (int) y, width, height);
		}
		else if(id==EntityID.Player2){
			g.setColor(Color.YELLOW);
			g.fillRect((int) x, (int) y, width, height);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		
		if(drawTextures) {
			if (velX > 0 && currentPlayerState == PlayerState.Standing) playerWalk.drawAnimation(g, (int) x, (int) y);
			else if (velX <0 && currentPlayerState == PlayerState.Standing) playerWalk.drawAnimation(g, (int) x, (int) y);
			if (velX > 0 && currentPlayerState == PlayerState.Crouching)  playerCrawl.drawAnimation(g, (int) x, (int) y-32);
			else if (velX < 0 && currentPlayerState == PlayerState.Crouching)  playerCrawl.drawAnimation(g, (int) x, (int) y-32);
			
			//if (velX > 0 && currentPlayerState == PlayerState.Proning)
			
			
			
			else g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
			
		}
		
		if (drawHitBoxes) {
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

	
	
	public PlayerState getState() {
		return currentPlayerState;
	}
	
	public void setState(PlayerState state) {
		currentPlayerState = state;
	}
	
	public PlayerAction getAction() {
		return currentPlayerAction;
	}
	
	public void setAction(PlayerAction action) {
		currentPlayerAction = action;
	}
	
	public PlayerBoundsHandler getBoundBox() {
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

	
	
}
