package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
import core.enums.EntityID;
import core.enums.PlayerState;
import core.handlers.PlayerBoundsHandler;
import core.handlers.PlayerCollisionHandler;
import core.handlers.PlayerMovementHandler;
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	
	Texture tex;
	
	private PlayerState currentPlayerState;
	
	private PlayerBoundsHandler boundBox;
	private PlayerMovementHandler movement;
	private PlayerCollisionHandler col;
	
	private boolean drawHitBoxes = true;
	private boolean drawTextures = false;
	
	
	
	public Player(int x, int y, EntityID id) {
		super(x, y, id);
		currentPlayerState = PlayerState.Falling;
		tex = new Texture("assets/spritemaps/coolguy.png", 32, 32);
		
		boundBox = new PlayerBoundsHandler(this);
		movement = new PlayerMovementHandler(this);
		
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
		
	}
	
	
	

	public void render(Graphics g) {
		
		if(id==EntityID.Player){
			//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, width, height);
		}
		else if(id==EntityID.Player2){
			g.setColor(Color.YELLOW);
			g.fillRect((int) x, (int) y, width, height);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		
		if(drawTextures) {
			g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
			
		}
		
		if (drawHitBoxes) {
			g2d.draw(boundBox.getBoundsTop());
			g2d.draw(boundBox.getBoundsBottom());
			g2d.draw(boundBox.getBoundsLeft());
			g2d.draw(boundBox.getBoundsRight());
			g2d.draw(boundBox.getBounds());
		}
	}

	
	
	public PlayerState getState() {
		return currentPlayerState;
	}
	
	public void setState(PlayerState state) {
		currentPlayerState = state;
	}
	/*
	 * returns players state
	 */

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
	
	

	//public boolean isProneAllowed() {
		
	//}
	

}
