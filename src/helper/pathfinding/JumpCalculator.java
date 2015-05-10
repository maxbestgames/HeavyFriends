package helper.pathfinding;

import core.gameobjects.Enemy;

public class JumpCalculator {

	Enemy obj;
	float velX, velY;
	
	public JumpCalculator(Enemy obj) {
		this.obj = obj;
	}
	
	public boolean isJumpPossible(float targetX, float targetY) {
		
		//get max jump velocity from the obj
		float jumpVel = obj.getMovementHandler().getJumpVel();
		//get max lateral velocity from the obj
		float maxVel = obj.getMovementHandler().getMaxSpeed();
		//get position of the object
		float x = obj.getX();
		float y = obj.getY();
		//get gravity value from the obj
		float gravity = obj.getMovementHandler().getGravity();
		
		
		//calculate max height
		do {
			jumpVel += gravity;
			y += jumpVel;
		
		} while (jumpVel > 0 );
		
		float maxHeight = obj.getY() - y;
		//calculate max range
		do {
			jumpVel += gravity;
			y += jumpVel;
			x += maxVel;
		} while (y > obj.getY());
		
		float maxRange = x - obj.getX(); 
		
		if (Math.abs(targetX - obj.getX()) < maxRange/2 && Math.abs(targetY - obj.getY()) < maxHeight) // TODO fix over estimation. use trig
			return true;
		
		return false;
	}
	
	public void calculateVelocities(float targetX, float targetY) {
		
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
}
