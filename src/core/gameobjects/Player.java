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
import core.handlers.PlayerMovementHandler;
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	
	Texture tex;
	
	private PlayerState currentPlayerState;
	
	private PlayerBoundsHandler boundBox;
	private PlayerMovementHandler movement;
	
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
		
	}
	
	public void doPlayerCollision() {
		boolean intersectBottom = false;
		boolean fallingOn = true;
		
		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);
			
			
			if (Math.abs(tempObject.getX() - getX()) < (width * getVelX() + 200) || 
					Math.abs(tempObject.getY() - getY()) < (height * getVelY() + 200) ) { // are the blocks close to the player?
				
				if (boundBox.getBoundsLeft().intersects(tempObject.getBounds())) {
					velX = 0.4f;
					x = tempObject.getX() + Block.getBlockSize();
					//System.out.println("L: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (boundBox.getBoundsRight().intersects(tempObject.getBounds())) {
					velX = -0.4f;
					x = tempObject.getX() - width;
					//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(boundBox.getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
					velY = 0;
					y = tempObject.getY() + Block.getBlockSize() + 2;
					//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(boundBox.getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
					velY = 0;
					y = tempObject.getY() - height;
					setState(PlayerState.Standing);
					intersectBottom = true;
					//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (boundBox.getFallingBox().intersects(tempObject.getBounds())) {
					fallingOn = false;
				}
				

				//next level block collision here here

				if (this.getId() != tempObject.getId() && tempObject instanceof Player) { // this is me, player 2 is tempObject

					Player player2 = (Player) tempObject;

					if ( boundBox.getBoundsLeft().intersects( player2.boundBox.getBoundsRight() ) ) { // player 2 is on the left of player 1
						this.setX( (float) (player2.getX()+player2.getBounds().getWidth()+2) );
					}
					else if ( boundBox.getBoundsRight().intersects( player2.boundBox.getBoundsLeft() ) ) { // player 2 is on the right of player 1
						this.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2 ));
					}
					if ( boundBox.getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
						this.setY( (float) (player2.getY()-player2.getBounds().getHeight()-2) );
						setState(PlayerState.Standing);
					}
					if ( boundBox.getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
						setState(PlayerState.Jumping);
					}
				}
			}
		}
		
		if ( !intersectBottom )  {
			if (velY > 0) {
				setState(PlayerState.Falling);
			} else if (velY < 0) {
				setState(PlayerState.Jumping);
			}
		}
		if (fallingOn) {
			setState(PlayerState.Falling);
		}
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

	public Rectangle getBounds() {
		return boundBox.getBounds();
	}
	
	

	//public boolean isProneAllowed() {
		
	//}
	

}
