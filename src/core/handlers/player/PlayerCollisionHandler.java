package core.handlers.player;

import core.Game;
import core.enums.PlayerState;
import core.gameobjects.Block;
import core.gameobjects.GameObject;
import core.gameobjects.Player;

public class PlayerCollisionHandler {

	private Player player;
	private boolean fallingOn = true;
	
	public PlayerCollisionHandler(Player player) {
		this.player = player;
	}
	
	public void doPlayerCollision() {
		boolean intersectBottom = false;
		fallingOn = true;
		
		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);
			
			
			if (Math.abs(tempObject.getX() - player.getX()) < (player.getWidth() * player.getVelX() + 200) || 
					Math.abs(tempObject.getY() - player.getY()) < (player.getHeight() * player.getVelY() + 200) ) { // are the blocks close to the player?
				
				if (player.getBoundBox().getBoundsLeft().intersects(tempObject.getBounds())) {
					player.setVelX(0.4f);
					player.setX(tempObject.getX() + Block.getBlockSize());
					//System.out.println("L: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (player.getBoundBox().getBoundsRight().intersects(tempObject.getBounds())) {
					player.setVelX(-0.4f);
					player.setX(tempObject.getX() - player.getWidth());
					//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(player.getBoundBox().getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
					player.setVelY(0);
					player.setY(tempObject.getY() + Block.getBlockSize() + 2);
					//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if(player.getBoundBox().getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
					player.setVelY(0);
					player.setY(tempObject.getY() - player.getHeight());
					player.setState(PlayerState.Standing);
					intersectBottom = true;
					//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
				}
				if (player.getBoundBox().getFallingBox().intersects(tempObject.getBounds())) {
					fallingOn = false;
				}
				

				//next level block collision here here

				if (player.getId() != tempObject.getId() && tempObject instanceof Player) { // this is me, player 2 is tempObject

					Player player2 = (Player) tempObject;

					if ( player.getBoundBox().getBoundsLeft().intersects( player2.getBoundBox().getBoundsRight() ) ) { // player 2 is on the left of player 1
						player.setX( (float) (player2.getX()+player2.getBounds().getWidth()+2) );
					}
					else if ( player.getBoundBox().getBoundsRight().intersects( player2.getBoundBox().getBoundsLeft() ) ) { // player 2 is on the right of player 1
						player.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2 ));
					}
					if ( player.getBoundBox().getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
						player.setY( (float) (player2.getY()-player2.getBounds().getHeight()-2) );
						player.setState(PlayerState.Standing);
					}
					if ( player.getBoundBox().getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
						player.setState(PlayerState.Jumping);
					}
				}
			}
		}
		
		if ( !intersectBottom )  {
			if (player.getVelY() > 0) {
				player.setState(PlayerState.Falling);
			} else if (player.getVelY() < 0) {
				player.setState(PlayerState.Jumping);
			}
		}
		if (fallingOn && !player.getMovement().isJumping()) {
			player.setState(PlayerState.Falling);
		}
	}
	
	public boolean getFallingOn() {
		return fallingOn;
	}
}
