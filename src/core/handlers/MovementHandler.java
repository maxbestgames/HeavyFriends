package core.handlers;

import core.enums.ObjectAction;
import core.enums.ObjectState;
import core.gameobjects.TickingGameObject;

/*
 * the algorithm is:
Find the destination tile (where the Object is).
Put the starting tile (where the monster is) on the OPEN list. It's starting cost is zero.
While the OPEN list is not empty, and a path isn't found:
Get the tile from the OPEN list with the lowest movement cost. Let's call it the CURRENT tile.
If this is the destination tile, the path has been found. Exit the loop now.
Find the tiles to which you can immediately walk to from this tile. These would the tiles around this tile, which don't contain obstacles. Call these tiles "successors".
For each successor:
Set the successor's parent to the CURRENT tile.
Set the successor's movement cost to the parent's movement cost, plus 1 (for diagonal movements, add more if it takes longer to go diagonally in your game).
If the successor doesn't exist on either the OPEN list or the CLOSED list, add it to the OPEN list. Otherwise, if the successor's movement cost is lower than the movement cost of the same tile on one of the lists, delete the occurrences of the successor from the lists add the successor to the OPEN list Otherwise, if the successor's movement cost is higher than that of the same tile on one of the lists, get rid of the successor
Delete the CURRENT tile from the OPEN list, and put it on the CLOSED list.
If the while loop has been ended because the OPEN list is empty, there is no path.
If this is not the case, the last tile pulled from the OPEN list, and its parents, describe the shortest path (in reverse order - i.e. from the Object to the monster - you should read the list of tiles back to front).
 */



public class MovementHandler {
	
	TickingGameObject obj;
	
	public MovementHandler(TickingGameObject obj) {
		this.obj = obj;
	}
	
	public boolean isJumping() {
		return (obj.getAction() == ObjectAction.Jumping);
	}
	
	public boolean isFalling() {
		return (obj.getAction() == ObjectAction.Falling);
	}
	
	public boolean isStationary() {
		return (obj.getAction() == ObjectAction.Stationary);
	}
	
	public boolean isStanding() {
		return (obj.getState() == ObjectState.Standing);
	}

	public boolean isGravityEnabled() {
		if (obj.getAction() == ObjectAction.Jumping) return true;
		if (obj.getAction() == ObjectAction.Falling) return true;
		return false;
	}
	
	public void calculateDoMovment() {
	}
	
	public void setJumpingOrFalling() {
		if (obj.getVelY() > 0) {
			obj.setAction(ObjectAction.Falling);
		} else if (obj.getVelY() < 0) {
			obj.setAction(ObjectAction.Jumping);
		}
		
		if (isGravityEnabled() && !isJumping()) {
			obj.setAction(ObjectAction.Falling);
		}
	}
	
}
