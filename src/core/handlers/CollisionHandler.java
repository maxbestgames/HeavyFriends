package core.handlers;

import core.Game;
import core.enums.ObjectAction;
import core.gameobjects.Block;
import core.gameobjects.BouncingProjectile;
import core.gameobjects.GameObject;
import core.gameobjects.TickingBlock;
import core.gameobjects.TickingGameObject;

public class CollisionHandler {

	TickingGameObject tempObject;
	long count = 0;

	public void doCollision() {
		count = 0;

		for(int i = 0; i< Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++){

			if (Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i) instanceof TickingGameObject 
					&& !(Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i) instanceof TickingBlock)) {

				tempObject = (TickingGameObject) Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i);
				tempObject.setBotStop(false);
				tempObject.setLeftStop(false);
				tempObject.setRightStop(false);
				tempObject.setTopStop(false);

				for (int j = 0; j < Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); j++) {

					if (i != j) {

						GameObject tempObject2 = Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(j);

						if (Math.abs(tempObject2.getX() - tempObject.getX()) <  300 && Math.abs(tempObject2.getY() - tempObject.getY()) < 300 ) { // are the blocks close to the obj?

							count++;

							if (tempObject2.isCollisionEnabled()) { // solid objects here

								if(tempObject.getObjBoundBox().getBoundsBottom().intersects(tempObject2.getBounds())) {//falling down

									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelY(tempObject.getVelY() * -1);
									} else {
										tempObject.setVelY(0);
									}

									tempObject.setY(tempObject2.getY() - tempObject.getHeight());
									tempObject.setAction(ObjectAction.Stationary);
									tempObject.setBotStop(true);
									//System.out.println("B: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());

								}
								if(tempObject.getObjBoundBox().getBoundsTop().intersects(tempObject2.getBounds())) { // hitting head

									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelY(tempObject.getVelY() * -1);
									} else {
										tempObject.setVelY(0);
									}

									tempObject.setY(tempObject2.getY() + Block.getBlockSize() + 2);
									//System.out.println("T: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());

								}
								if (tempObject.getObjBoundBox().getBoundsTopLeft().intersects(tempObject2.getBounds())) {
									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelX(tempObject.getVelX() * -1);
									}
									tempObject.setX(tempObject2.getX() + Block.getBlockSize());
									//System.out.println("L: "+(int) tempObject2.getX() + " " + (int) tempObject2.getY() + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
								}
								else if (tempObject.getObjBoundBox().getBoundsBotLeft().intersects(tempObject2.getBounds())) {
									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelX(tempObject.getVelX() * -1);
									}
									tempObject.setX(tempObject2.getX() + Block.getBlockSize());
									//System.out.println("L: "+(int) tempObject2.getX() + " " + (int) tempObject2.getY() + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
								}
								if (tempObject.getObjBoundBox().getBoundsTopRight().intersects(tempObject2.getBounds())) {
									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelX(tempObject.getVelX() * -1);
									}
									tempObject.setX(tempObject2.getX() - tempObject.getWidth());
									//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
								}
								else if (tempObject.getObjBoundBox().getBoundsBotRight().intersects(tempObject2.getBounds())) {
									if(tempObject instanceof BouncingProjectile) {
										tempObject.setVelX(tempObject.getVelX() * -1);
									}
									tempObject.setX(tempObject2.getX() - tempObject.getWidth());
									//System.out.println("R: "+(int) x + " " + (int) y + ", " + " " + (int) tempObject2.getX() + " " + (int) tempObject2.getY());
								}

								if (tempObject.getObjBoundBox().getBoundsLeftStop().intersects(tempObject2.getBounds())) {
									tempObject.setLeftStop(true);
								} 

								if (tempObject.getObjBoundBox().getBoundsRightStop().intersects(tempObject2.getBounds())) {
									tempObject.setRightStop(true);
								}

								if (tempObject.getObjBoundBox().getBoundsTopStop().intersects(tempObject2.getBounds())) {
									tempObject.setTopStop(true);
								}
								
								// TODO ledge edge detection left and right

							} else { // non solid blocks here

							}
						}
					}
				}
			}
		}
		//System.out.println(count);
		count = 0;
	}
}
