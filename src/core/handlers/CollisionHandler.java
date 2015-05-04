package core.handlers;

import core.Game;
import core.enums.PlayerAction;
import core.gameobjects.Block;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.gameobjects.TickingGameObject;

public class CollisionHandler {

	private TickingGameObject obj;


	private boolean fallingOn = true;
	private boolean leftStop = false;
	private boolean rightStop = false;
	private boolean topStop = false;
	//private boolean canMove = true;

	private int objCount;


	public CollisionHandler() {

	}

	public void doCollision() {

		boolean intersectBottom = false;
		rightStop = false;
		topStop = false;
		fallingOn = true;
		leftStop = false;

		int count = 0;

		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			for (int j =0; j < Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); j++) {

				GameObject tempObject = Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i);
				GameObject tempObject2 = Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(j);

				if (i != j) {

					if (Math.abs(tempObject.getX() - player.getX()) < (Math.abs(player.getWidth()) * Math.abs(player.getVelX()) + 300) && 
							Math.abs(tempObject.getY() - player.getY()) < (Math.abs(player.getHeight()) * Math.abs(player.getVelY()) + 300) ) { // are the blocks close to the player?

						if (tempObject.isCollisionEnabled()) { // solid objects here

							if(player.getBoundBox().getBoundsBottom().intersects(tempObject.getBounds())) { //falling down
								player.setVelY(0);
								player.setY(tempObject.getY() - player.getHeight());
								player.setAction(PlayerAction.Stationary);
								intersectBottom = true;
								//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if(player.getBoundBox().getBoundsTop().intersects(tempObject.getBounds())) { // hitting head
								player.setVelY(0);
								player.setY(tempObject.getY() + Block.getBlockSize() + 2);
								//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if (player.getBoundBox().getBoundsTopLeft().intersects(tempObject.getBounds())) {
								//player.setVelX(0.4f);
								player.setX(tempObject.getX() + Block.getBlockSize());
								//System.out.println("L: "+(int) tempObject.getX() + " " + (int) tempObject.getY() + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if (player.getBoundBox().getBoundsBotLeft().intersects(tempObject.getBounds())) {
								//player.setVelX(0.4f);
								player.setX(tempObject.getX() + Block.getBlockSize());
								//System.out.println("L: "+(int) tempObject.getX() + " " + (int) tempObject.getY() + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if (player.getBoundBox().getBoundsTopRight().intersects(tempObject.getBounds())) {
								//player.setVelX(-0.4f);
								player.setX(tempObject.getX() - player.getWidth());
								//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if (player.getBoundBox().getBoundsBotRight().intersects(tempObject.getBounds())) {
								//player.setVelX(-0.4f);
								player.setX(tempObject.getX() - player.getWidth());
								//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject.getX() + " " + (int) tempObject.getY());
							}
							if (player.getBoundBox().getBoundsBotStop().intersects(tempObject.getBounds())) {
								fallingOn = false;
							}

							if (player.getBoundBox().getBoundsLeftStop().intersects(tempObject.getBounds())) {
								leftStop = true;
							} 

							if (player.getBoundBox().getBoundsRightStop().intersects(tempObject.getBounds())) {
								rightStop = true;
							}

							if (player.getBoundBox().getBoundsTopStop().intersects(tempObject.getBounds())) {
								topStop = true;
							}

							//next level block collision here here

							if (player.getId() != tempObject.getId() && tempObject instanceof Player) { // this is me, player 2 is tempObject

								Player player2 = (Player) tempObject;

								if ( player.getBoundBox().getBoundsTopLeft().intersects( player2.getBoundBox().getBoundsTopRight() ) ) { // player 2 is on the left of player 1
									player.setX( (float) (player2.getX() + player2.getBounds().getWidth() + 2) );
								}
								else if ( player.getBoundBox().getBoundsTopRight().intersects( player2.getBoundBox().getBoundsTopLeft() ) ) { // player 2 is on the right of player 1
									player.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2) );
								}
								if ( player.getBoundBox().getBoundsBotLeft().intersects( player2.getBoundBox().getBoundsBotRight() ) ) { // player 2 is on the left of player 1
									player.setX( (float) (player2.getX() + player2.getBounds().getWidth() + 2) );
								}
								else if ( player.getBoundBox().getBoundsBotRight().intersects( player2.getBoundBox().getBoundsBotLeft() ) ) { // player 2 is on the right of player 1
									player.setX( (float) (player2.getX() - player2.getBounds().getWidth() - 2) );
								}
								if ( player.getBoundBox().getBoundsBottom().intersects( player2.getBounds() ) ) { // player 2 is under player 1
									player.setY( (float) (player2.getY() - player2.getBounds().getHeight() - 2) );
									player.setAction(PlayerAction.Stationary);
								}
								if ( player.getBoundBox().getBoundsTop().intersects( player2.getBounds() ) ) { // player 2 is on top of player 1
									player.setAction(PlayerAction.Jumping);
								}
							}
						} else { // non solid blocks here

						}
						count++;
					}
				}
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