package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
import core.enums.BlockType;
import core.enums.ID;
import core.enums.PlayerState;
import core.visualgronk.LevelLoader;
import core.visualgronk.Texture;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private float gravity = 0.9f;
	
	Texture tex;
	
	private PlayerState currentPlayerState;
	
	private boolean drawHitBoxes = true;
	private boolean drawTextures = false;
	
	
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		currentPlayerState = PlayerState.Falling;
		tex = new Texture("assets/spritemaps/coolguy.png");
		tex.getTextures(0, 0, 32, 32);
		
		width = 32;
		height = 64;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//System.out.println(currentPlayerState.toString());
		
		//if(currentPlayerState == PlayerState.Falling || currentPlayerState == PlayerState.Jumping) {
			velY += gravity;
		//}
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
	}
	
	public void doPlayerCollision() {
		boolean intersectBottom = false;
		
		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);
			
			
			if (Math.abs(tempObject.getX() - getX()) < (width * getVelX() + 200) || 
					Math.abs(tempObject.getY() - getY()) < (height * getVelY() + 200) ) { // are the blocks close to the player?
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					velX = 0.4f;
					x = tempObject.getX() + Block.getBlockSize();
					//System.out.println("L: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					velX = -0.4f;
					x = tempObject.getX() - width;
					//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
					velY = 0;
					y = tempObject.getY() + Block.getBlockSize() + 2;
					//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
					velY = 0;
					y = tempObject.getY() - height;
					setState(PlayerState.Standing);
					intersectBottom = true;
					//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				} 
				

				//next level block collision here here

				if (this.getId() != tempObject.getId() && tempObject instanceof Player) { // this is me, player 2 is tempObject

					Player player2 = (Player) tempObject;

					if ( getBoundsLeft().intersects( player2.getBoundsRight() ) ) { // player 2 is on the left of player 1
						this.setX( (float) (player2.getX()+player2.getBounds().getWidth()+2) );
					}
					else if ( getBoundsRight().intersects( player2.getBoundsLeft() ) ) { // player 2 is on the right of player 1
						this.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2 ));
					}
					if ( getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
						this.setY( (float) (player2.getY()-player2.getBounds().getHeight()-2) );
						setState(PlayerState.Standing);
					}
					if ( getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
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
	}
	

	public void render(Graphics g) {
		
		if(id==ID.Player){
			//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, width, height);
		}
		else if(id==ID.Player2){
			g.setColor(Color.YELLOW);
			g.fillRect((int) x, (int) y, width, height);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		
		if(drawTextures) {
			g.drawImage(tex.block[0], (int) x, (int) y, null);
			
		}
		
		if (drawHitBoxes) {
			g2d.draw(getBoundsTop());
			g2d.draw(getBoundsBottom());
			g2d.draw(getBoundsLeft());
			g2d.draw(getBoundsRight());
			g2d.draw(getBounds());
		}
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + height/2-3, 2, 6);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width-2, (int) y+height/2-3, 2, 6);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + width/2-3, (int) y, 6, 2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + width/2-3, (int) y + height-2, 6, 2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
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
	
	public boolean isJumping() {
		return (currentPlayerState == PlayerState.Jumping);
	}
	
	public boolean isFalling() {
		return (currentPlayerState == PlayerState.Falling);
	}
	
	public boolean isStanding() {
		return (currentPlayerState == PlayerState.Standing);
	}
	
	public boolean isProning() {
		return (currentPlayerState == PlayerState.Proning);
	}
	
	public boolean isCrouching() {
		return (currentPlayerState == PlayerState.Crouching);
	}
	
	public boolean isProneFalling() {
		return (currentPlayerState == PlayerState.ProneFalling);
	}
	
	public boolean isCrouchFalling() {
		return (currentPlayerState == PlayerState.CrouchFalling);
	}
	
	public boolean isProneJumping() { 
		return (currentPlayerState == PlayerState.ProneJumping);
	}
	
	public boolean isCrouchJumping() {
		return (currentPlayerState == PlayerState.CrouchJumping);
	}
	
	public boolean isProneShelling() {
		return (currentPlayerState == PlayerState.ProneShelling);
	}
	
	public boolean isCrouchShelling() {
		return (currentPlayerState == PlayerState.CrouchShelling);
	}
	
	public boolean isStandShelling() {
		 return (currentPlayerState == PlayerState.StandShelling);
	}

	public boolean isJumpingAllowed() {

		if (currentPlayerState == PlayerState.Standing) return true;
		if (currentPlayerState == PlayerState.CrouchJumping) return true;
		if (currentPlayerState == PlayerState.ProneJumping) return true;
		return false;
	}
	/*
	 * is state allowed
	 */
	public boolean isStandingAllowed() {
		
		if (currentPlayerState == PlayerState.Falling) return true;
		if (currentPlayerState == PlayerState.Crouching) return true;
		if (currentPlayerState == PlayerState.Proning) return true;
		if (currentPlayerState == PlayerState.StandShelling) return true;
		if (currentPlayerState == PlayerState.Jumping) return true;
		return false;
		
	}
	
	public boolean isFallingAllowed() {
		
		if (currentPlayerState == PlayerState.Jumping) return true;
		if (currentPlayerState == PlayerState.Standing) return true;
		if (currentPlayerState == PlayerState.ProneFalling) return true;
		if (currentPlayerState == PlayerState.CrouchFalling) return true;
		return false;
		
	}
	
	public boolean isCrouchingAllowed() {
		
		if (currentPlayerState == PlayerState.Standing) return true;
		if (currentPlayerState == PlayerState.CrouchFalling) return true;
		if (currentPlayerState == PlayerState.Proning) return true;
		if (currentPlayerState == PlayerState.CrouchShelling) return true;
		if (currentPlayerState == PlayerState.CrouchJumping) return true;
		return false;

	}
	
	public boolean isProningAllowed() {
		
		if (currentPlayerState == PlayerState.Standing) return true;
		if (currentPlayerState == PlayerState.ProneFalling) return true;
		if (currentPlayerState == PlayerState.ProneShelling) return true;
		if (currentPlayerState == PlayerState.Crouching) return true;
		return false;

	}
	
	public boolean isProneFallingAllowed() {
		
		if (currentPlayerState == PlayerState.Falling) return true;
		if (currentPlayerState == PlayerState.CrouchFalling) return true;
		return false;
				
	}
	
	public boolean isCrouchFallingAllowed() {
		
		if (currentPlayerState == PlayerState.Falling) return true;
		if (currentPlayerState == PlayerState.ProneFalling) return true;
		return false;
	}
	
	public boolean isProneJumpingAllowed() {
		
		if (currentPlayerState == PlayerState.Jumping) return true;
		if (currentPlayerState == PlayerState.CrouchJumping) return true;
		return false;
		
	}
	
	public boolean isCrouchJumpingAllowed() {
		
		if (currentPlayerState == PlayerState.Jumping) return true;
		if (currentPlayerState == PlayerState.ProneJumping) return true;
		return false;
		
	}

	//public boolean isProneAllowed() {
		
	//}
	

}
