package core.handlers.player;

import core.Game;
import core.enums.PlayerAction;
import core.gameobjects.Block;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.gameobjects.TickingGameObject;
import core.gameobjects.art.backgrounds.LevelBackground;
import core.gameobjects.art.backgrounds.LevelForeground;

public class PlayerCollisionHandler {

	private Player player;
	private boolean fallingOn = true;
	private boolean leftStop = false;
	private boolean rightStop = false;
	private boolean topStop = false;
	//private boolean canMove = true;

	private int objCount;
	
	
	public PlayerCollisionHandler(Player player) {
		this.player = player;
	}
	
	public void doPlayerCollision() {
		@SuppressWarnings("unused")
		long time = System.currentTimeMillis();
		
		boolean intersectBottom = false;
		rightStop = false;
		topStop = false;
		fallingOn = true;
		leftStop = false;
		
		int count = 0;

		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			
			GameObject tempObject = Game.getWorldHandler().getLevel( Game.getCurrentLevel() ).getObjHandler().getObject(i);

			if (Math.abs(tempObject.getX() - player.getX()) < (Math.abs(player.getWidth()) * Math.abs(player.getVelX()) + 300) && 
					Math.abs(tempObject.getY() - player.getY()) < (Math.abs(player.getHeight()) * Math.abs(player.getVelY()) + 300) ) { // are the blocks close to the player?
				
				if(tempObject.isCollisionEnabled() && player.getPlayerBoundBox().getBounds().intersects(tempObject.getBounds()) && tempObject instanceof TickingGameObject ){
					player.setPlayerHealth(player.getPlayerHealth() - 1 );
				}

				if (tempObject.isCollisionEnabled() && !(tempObject instanceof TickingGameObject) 
						&& !(tempObject instanceof LevelBackground) && !(tempObject instanceof LevelForeground) ) { // solid objects here
					if(player.getPlayerBoundBox().getBoundsBottom().intersects(tempObject.getBounds()) ) { //falling down
						player.setVelY(0);
						player.setY(tempObject.getY() - player.getHeight());
						player.setAction(PlayerAction.Stationary);
						intersectBottom = true;
						//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}
					if(player.getPlayerBoundBox().getBoundsTop().intersects(tempObject.getBounds()) ) { // hitting head
						player.setVelY(0);
						player.setY(tempObject.getY() + Block.getBlockSize() + 2);
						//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}
					if (player.getPlayerBoundBox().getBoundsTopLeft().intersects(tempObject.getBounds()) ) {
						//player.setVelX(0.4f);
						player.setX(tempObject.getX() + Block.getBlockSize());
						//System.out.println("L: "+(int) tempObject.getX() + " " + (int) tempObject.getY() + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}
					if (player.getPlayerBoundBox().getBoundsBotLeft().intersects(tempObject.getBounds()) ) {
						//player.setVelX(0.4f);
						player.setX(tempObject.getX() + Block.getBlockSize());
						//System.out.println("L: "+(int) tempObject.getX() + " " + (int) tempObject.getY() + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}
					if (player.getPlayerBoundBox().getBoundsTopRight().intersects(tempObject.getBounds()) ) {
						//player.setVelX(-0.4f);
						player.setX(tempObject.getX() - player.getWidth());
						//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}

					if (player.getPlayerBoundBox().getBoundsBotRight().intersects(tempObject.getBounds()) ) {
						//player.setVelX(-0.4f);
						player.setX(tempObject.getX() - player.getWidth());
						//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
					}
					if (player.getPlayerBoundBox().getBoundsBotStop().intersects(tempObject.getBounds()) ) {
						fallingOn = false;
					}

					if (player.getPlayerBoundBox().getBoundsLeftStop().intersects(tempObject.getBounds()) ) {
						leftStop = true;
					} 

					if (player.getPlayerBoundBox().getBoundsRightStop().intersects(tempObject.getBounds()) ) {
						rightStop = true;
					}

					if (player.getPlayerBoundBox().getBoundsTopStop().intersects(tempObject.getBounds()) ) {
						topStop = true;
					}

					//next level block collision here here

					if (player.getId() != tempObject.getId() && tempObject instanceof Player) { // this is me, player 2 is tempObject

						Player player2 = (Player) tempObject;

						if ( player.getPlayerBoundBox().getBoundsTopLeft().intersects( player2.getPlayerBoundBox().getBoundsTopRight() ) ) { // player 2 is on the left of player 1
							player.setX( (float) (player2.getX() + player2.getBounds().getWidth() + 2) );
						}
						else if ( player.getPlayerBoundBox().getBoundsTopRight().intersects( player2.getPlayerBoundBox().getBoundsTopLeft() ) ) { // player 2 is on the right of player 1
							player.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2) );
						}
						if ( player.getPlayerBoundBox().getBoundsBotLeft().intersects( player2.getPlayerBoundBox().getBoundsBotRight() ) ) { // player 2 is on the left of player 1
							player.setX( (float) (player2.getX() + player2.getBounds().getWidth() + 2) );
						}
						else if ( player.getPlayerBoundBox().getBoundsBotRight().intersects( player2.getPlayerBoundBox().getBoundsBotLeft() ) ) { // player 2 is on the right of player 1
							player.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2) );
						}
						if ( player.getPlayerBoundBox().getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
							player.setY( (float) (player2.getY() - player2.getBounds().getHeight() - 2) );
							player.setAction(PlayerAction.Stationary);
						}
						if ( player.getPlayerBoundBox().getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
							player.setAction(PlayerAction.Jumping);
						}
					}
				} else { // non solid blocks here
					
				}
				count++;
			}
		}
		
		if ( !intersectBottom )  {
			if (player.getVelY() > 0) {
				player.setAction(PlayerAction.Falling);
			} else if (player.getVelY() < 0) {
				player.setAction(PlayerAction.Jumping);
			}
		}
		if (fallingOn && !player.getMovement().isJumping()) {
			player.setAction(PlayerAction.Falling);
		}
		
		if (count != 0) 
			objCount = count;
		
		//System.out.println("Player Tick: " + (System.currentTimeMillis() - time) + "ms");
		
		if (count == 0) {
			System.exit(0); 
		}
	}
	
	public boolean getFallingOn() {
		return fallingOn;
	}
	
	public int getNumCloseObjects() {
		return objCount;
	}
	
	public boolean getLeftStop() {
		return leftStop;
	}
	
	public boolean getRightStop() {
		return rightStop;
	}
	
	public boolean getTopStop() {
		return topStop;
	}
}
