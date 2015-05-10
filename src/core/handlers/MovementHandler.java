package core.handlers;

import core.enums.ObjectAction;
import core.enums.ObjectState;
import core.gameobjects.TickingGameObject;


public class MovementHandler {
	
	TickingGameObject obj;
	protected float gravity;
	protected float patrolSpeed;
	protected float maxSpeed;
	protected float jumpVel;
	
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

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getPatrolSpeed() {
		return patrolSpeed;
	}

	public void setPatrolSpeed(float patrolSpeed) {
		this.patrolSpeed = patrolSpeed;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getJumpVel() {
		return jumpVel;
	}

	public void setJumpVel(float jumpVel) {
		this.jumpVel = jumpVel;
	}
	
}
