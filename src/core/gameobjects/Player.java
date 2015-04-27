package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import core.Game;
import core.Window;
import core.handlers.ObjectHandler;
import core.enums.ID;


public class Player extends TickingGameObject{
	Random r = new Random();
	
	private int playerWidth = 32;
	private int playerHeight = 64;
	private float gravity = 0.9f;
	
	private boolean drawHitBoxes = true;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		falling = true;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
		}
		//x = Game.clamp(x, 0, Window.visibleScreenX-playerWidth);
		//y = Game.clamp(y, 0, Window.vaisibleScreenY-playerHeight);
		
		collision();
	}
	
	private void collision(){
		for(int i=0; i< Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getSize(); i++){
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);
			
			if(tempObject.getId()==ID.Block){ // environment Blocks
				
				/*if (getBoundsLeft().intersects(tempObject.getBounds())) {
					velX = 0;
					x = tempObject.getX() + Block.getBlockSize() + 2;
				} else if (getBoundsRight().intersects(tempObject.getBounds())) {
					velX = 0;
					x = tempObject.getX() - playerWidth - 2;
				}*/
				if(getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
					velY = 0;
					y = tempObject.getY() + Block.getBlockSize() + 2;
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
					velY = 0;
					y = tempObject.getY() - playerHeight;
					jumping = false;
					falling = false;
				} else {
					falling = true;
				}
				
				
				
				
				//next level block collision here here
			}
			
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
	

	public void render(Graphics g) {
		
		if(id==ID.Player){
			//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, playerWidth, playerHeight);
		}
		else if(id==ID.Player2){
			g.setColor(Color.BLUE);
			g.fillRect((int) x, (int) y, playerWidth, playerHeight);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		
		if (drawHitBoxes) {
			g2d.draw(getBoundsTop());
			g2d.draw(getBoundsBottom());
			g2d.draw(getBoundsLeft());
			g2d.draw(getBoundsRight());
			g2d.draw(getBounds());
		}
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y+10, 2, playerHeight-20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + playerWidth-2, (int) y+10, 2, playerHeight-20);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+5, (int) y, playerWidth-10, playerHeight/2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x+5, (int) y + playerHeight/2, playerWidth-10, playerHeight/2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, playerWidth, playerHeight);
	}

}
