package core.handlers;

import core.Game;
import core.enums.PlayerAction;
import core.gameobjects.Block;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.gameobjects.TickingGameObject;

public class CollisionHandler {

	private boolean leftStop = false;
	private boolean rightStop = false;
	private boolean topStop = false;
	//private boolean canMove = true;


	public CollisionHandler() {

	}

	public void doCollision() {

		boolean intersectBottom = false;
		rightStop = false;
		topStop = false;
		leftStop = false;


		for(int i=0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){
			for (int j =0; j < Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); j++) {

				if (i != j && Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i) instanceof TickingGameObject) {
					
					TickingGameObject tempObject = (TickingGameObject) Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i);
					GameObject tempObject2 = Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(j);

					if (Math.abs(tempObject2.getX() - tempObject.getX()) <  300 && Math.abs(tempObject2.getY() - tempObject.getY()) < 300 ) { // are the blocks close to the obj?

						if (tempObject2.isCollisionEnabled()) { // solid objects here

							if(tempObject.getObjBoundBox().getBoundsBottom().intersects(tempObject2.getBounds())) { //falling down
								tempObject.setVelY(0);
								tempObject.setY(tempObject2.getY() - tempObject.getHeight());
								//tempObject.setAction(PlayerAction.Stationary);
								intersectBottom = true;
								//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}
							if(tempObject.getObjBoundBox().getBoundsTop().intersects(tempObject2.getBounds())) { // hitting head
								tempObject.setVelY(0);
								tempObject.setY(tempObject2.getY() + Block.getBlockSize() + 2);
								//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}
							if (tempObject.getObjBoundBox().getBoundsTopLeft().intersects(tempObject2.getBounds())) {
								//tempObject.setVelX(0.4f);
								tempObject.setX(tempObject2.getX() + Block.getBlockSize());
								//System.out.println("L: "+(int) tempObject2.getX() + " " + (int) tempObject2.getY() + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}
							if (tempObject.getObjBoundBox().getBoundsBotLeft().intersects(tempObject2.getBounds())) {
								//tempObject.setVelX(0.4f);
								tempObject.setX(tempObject2.getX() + Block.getBlockSize());
								//System.out.println("L: "+(int) tempObject2.getX() + " " + (int) tempObject2.getY() + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}
							if (tempObject.getObjBoundBox().getBoundsTopRight().intersects(tempObject2.getBounds())) {
								//tempObject.setVelX(-0.4f);
								tempObject.setX(tempObject2.getX() - tempObject.getWidth());
								//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}
							if (tempObject.getObjBoundBox().getBoundsBotRight().intersects(tempObject2.getBounds())) {
								//tempObject.setVelX(-0.4f);
								tempObject.setX(tempObject2.getX() - tempObject.getWidth());
								//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
							}

							if (tempObject.getObjBoundBox().getBoundsLeftStop().intersects(tempObject2.getBounds())) {
								leftStop = true;
							} 

							if (tempObject.getObjBoundBox().getBoundsRightStop().intersects(tempObject2.getBounds())) {
								rightStop = true;
							}

							if (tempObject.getObjBoundBox().getBoundsTopStop().intersects(tempObject2.getBounds())) {
								topStop = true;
							}
							
						} else { // non solid blocks here

						}
					}
				}
			}
		}
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
