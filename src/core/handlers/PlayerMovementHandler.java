package core.handlers;

import core.enums.PlayerState;
import core.gameobjects.Player;

public class PlayerMovementHandler {
	
	Player player;
	
	float proneVelX;
	float proneVelY;
	float crouchVelX;
	float crouchVelY;
	
	public PlayerMovementHandler(Player player) {
		this.player = player;
	}
	
	
	
	
	public void goProne() {
		// Dont forget bounding boxes need to change.
		int newHeight = player.getWidth();
		player.setWidth(player.getHeight());
		player.setHeight(newHeight);
		player.setState(PlayerState.Proning);
		
	}
	
	public void goStanding() {
		int newHeight = player.getWidth();
		player.setWidth(player.getHeight());
		player.setHeight(newHeight);
		player.setY(player.getY()-player.getHeight()-100);
		player.setState(PlayerState.Standing);
	}
	
	public boolean isJumping() {
		return (player.getState() == PlayerState.Jumping);
	}
	
	public boolean isFalling() {
		return (player.getState() == PlayerState.Falling);
	}
	
	public boolean isStanding() {
		return (player.getState() == PlayerState.Standing);
	}
	
	public boolean isProning() {
		return (player.getState() == PlayerState.Proning);
	}
	
	public boolean isCrouching() {
		return (player.getState() == PlayerState.Crouching);
	}
	
	public boolean isProneFalling() {
		return (player.getState() == PlayerState.ProneFalling);
	}
	
	public boolean isCrouchFalling() {
		return (player.getState() == PlayerState.CrouchFalling);
	}
	
	public boolean isProneJumping() { 
		return (player.getState() == PlayerState.ProneJumping);
	}
	
	public boolean isCrouchJumping() {
		return (player.getState() == PlayerState.CrouchJumping);
	}
	
	public boolean isProneShelling() {
		return (player.getState() == PlayerState.ProneShelling);
	}
	
	public boolean isCrouchShelling() {
		return (player.getState() == PlayerState.CrouchShelling);
	}
	
	public boolean isStandShelling() {
		 return (player.getState() == PlayerState.StandShelling);
	}

	public boolean isJumpingAllowed() {

		if (player.getState() == PlayerState.Standing) return true;
		if (player.getState() == PlayerState.CrouchJumping) return true;
		if (player.getState() == PlayerState.ProneJumping) return true;
		return false;
	}

	public boolean isStandingAllowed() {
		
		if (player.getState() == PlayerState.Falling) return true;
		if (player.getState() == PlayerState.Crouching) return true;
		if (player.getState() == PlayerState.Proning) return true;
		if (player.getState() == PlayerState.StandShelling) return true;
		if (player.getState() == PlayerState.Jumping) return true;
		return false;
		
	}
	
	public boolean isFallingAllowed() {
		
		if (player.getState() == PlayerState.Jumping) return true;
		if (player.getState() == PlayerState.Standing) return true;
		if (player.getState() == PlayerState.ProneFalling) return true;
		if (player.getState() == PlayerState.CrouchFalling) return true;
		return false;
		
	}
	
	public boolean isCrouchingAllowed() {
		
		if (player.getState() == PlayerState.Standing) return true;
		if (player.getState() == PlayerState.CrouchFalling) return true;
		if (player.getState() == PlayerState.Proning) return true;
		if (player.getState() == PlayerState.CrouchShelling) return true;
		if (player.getState() == PlayerState.CrouchJumping) return true;
		return false;

	}
	
	public boolean isProningAllowed() {
		
		if (player.getState() == PlayerState.Standing) return true;
		if (player.getState() == PlayerState.ProneFalling) return true;
		if (player.getState() == PlayerState.ProneShelling) return true;
		if (player.getState() == PlayerState.Crouching) return true;
		return false;

	}
	
	public boolean isProneFallingAllowed() {
		
		if (player.getState() == PlayerState.Falling) return true;
		if (player.getState() == PlayerState.CrouchFalling) return true;
		return false;
				
	}
	
	public boolean isCrouchFallingAllowed() {
		
		if (player.getState() == PlayerState.Falling) return true;
		if (player.getState() == PlayerState.ProneFalling) return true;
		return false;
	}
	
	public boolean isProneJumpingAllowed() {
		
		if (player.getState() == PlayerState.Jumping) return true;
		if (player.getState() == PlayerState.CrouchJumping) return true;
		return false;
		
	}
	
	public boolean isCrouchJumpingAllowed() {
		
		if (player.getState() == PlayerState.Jumping) return true;
		if (player.getState() == PlayerState.ProneJumping) return true;
		return false;
		
	}
	
	public boolean isGravityEnabled() {

		if (player.getState() == PlayerState.ProneFalling) return true;
		if (player.getState() == PlayerState.ProneJumping) return true;
		if (player.getState() == PlayerState.CrouchFalling) return true;
		if (player.getState() == PlayerState.CrouchJumping) return true;
		if (player.getState() == PlayerState.Jumping) return true;
		if (player.getState() == PlayerState.Falling) return true;
		return false;
	}
	
	
}
