package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
<<<<<<< HEAD
import core.enums.ID;
import core.visualgronk.LevelLoader;
=======
import core.enums.EntityID;
import core.enums.PlayerState;
import core.handlers.player.PlayerBoundsHandler;
import core.handlers.player.PlayerCollisionHandler;
import core.handlers.player.PlayerMovementHandler;
>>>>>>> enemy-and-ai-start
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	
<<<<<<< HEAD
=======
	//Texture tex;
	
	private PlayerState currentPlayerState;
	
	private PlayerBoundsHandler boundBox;
	private PlayerMovementHandler movement;
	private PlayerCollisionHandler col;
	
>>>>>>> enemy-and-ai-start
	private boolean drawHitBoxes = true;
	
	Texture tex = LevelLoader.getInstance();
	
	public Player(int x, int y, EntityID id) {
		super(x, y, id);
<<<<<<< HEAD
		falling = true;
=======
		currentPlayerState = PlayerState.Falling;
		tex = new Texture("assets/spritemaps/coolguy.png", 32, 32);
		
		boundBox = new PlayerBoundsHandler(this);
		movement = new PlayerMovementHandler(this);
		col = new PlayerCollisionHandler(this);
		
		width = 32;
		height = 64;
>>>>>>> enemy-and-ai-start
	}

	public void tick() {
		x += velX;
		y += velY;
		
<<<<<<< HEAD
		if(falling || jumping) {
			velY += gravity;
		}
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
		collision();
	}
	
	private void collision(){
		for(int i=0; i< Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getSize(); i++){
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);
			
			
			if (Math.abs(tempObject.getX() - getX()) < (playerWidth * getVelX() + 200) || 
					Math.abs(tempObject.getY() - getY()) < (playerHeight * getVelY() + 200) ) { // are the blocks close to the player?
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					velX = 0.4f;
					x = tempObject.getX() + Block.getBlockSize();
					//System.out.println("L: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					velX = -0.4f;
					x = tempObject.getX() - playerWidth;
					//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
					velY = 0;
					y = tempObject.getY() + Block.getBlockSize() + 2;
					//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
					velY = 0;
					y = tempObject.getY() - playerHeight+1;
					jumping = false;
					falling = false;
					//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				} else {
					falling = true;
				}

				//next level block collision here here

				if (tempObject instanceof Player && this.getId() != tempObject.getId()) { // this is me, player 2 is tempObject

					Player player2 = (Player) tempObject;

					if ( getBoundsLeft().intersects( player2.getBoundsRight() ) ) { // player 2 is on the left of player 1
						this.setX( (float) (player2.getX()+player2.getBounds().getWidth()+2) );
					}
					else if ( getBoundsRight().intersects( player2.getBoundsLeft() ) ) { // player 2 is on the right of player 1
						this.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2 ));
					}
					if ( getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
						this.setY( (float) (player2.getY()-player2.getBounds().getHeight()-2) );
						this.setJumping(false);
					}
					if ( getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
						this.setJumping(true);
					}
				}
			}
		}
=======
		//System.out.println(currentPlayerState.toString());
		
		if(movement.isGravityEnabled()) {
			velY += gravity;
		}
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
		getColHandler().doPlayerCollision();
		
>>>>>>> enemy-and-ai-start
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
		
<<<<<<< HEAD
=======
		if(drawTextures) {
			g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
			
		}
		
>>>>>>> enemy-and-ai-start
		if (drawHitBoxes) {
			g2d.draw(boundBox.getBoundsTop());
			g2d.draw(boundBox.getBoundsBottom());
			g2d.draw(boundBox.getBoundsLeft());
			g2d.draw(boundBox.getBoundsRight());
			g2d.draw(boundBox.getBounds());
		}
	}

	
<<<<<<< HEAD
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + playerWidth-2, (int) y+playerHeight/2-3, 2, 6);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + playerWidth/2-3, (int) y, 6, 2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + playerWidth/2-3, (int) y + playerHeight-2, 6, 2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, playerWidth, playerHeight);
	}
=======
	
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
	
>>>>>>> enemy-and-ai-start

}
