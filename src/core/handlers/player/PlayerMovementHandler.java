package core.handlers.player;

import core.enums.PlayerAction;
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
	
	
	
	
	public void goProning() {
		// Dont forget bounding boxes need to change.
		player.setY(player.getY() + player.getWidth());
		player.setX(player.getX() - player.getWidth()/2);
		int newHeight = player.getWidth();
		player.setWidth(player.getHeight());
		player.setHeight(newHeight);
		player.setState(PlayerState.Proning);
		
	}
	
	public void goStanding() {
		if(player.getMovement().isProning() ) player.setX(player.getX() + player.getHeight()/2 );
		player.setY(player.getY() - player.getHeight() );
		player.setWidth(player.getHeight() );
		player.setHeight(player.getHeight() * 2);
		player.setState(PlayerState.Standing);
	}
	
	public void goCrouching() {// TOO DO
		if(player.getMovement().isJumping() ) {
			player.setY(player.getY() - player.getHeight()/10 ); //crouch jumping balance check denominator not sure about 10 be careful!
		} else {
		player.setY(player.getY() + player.getWidth() );
		}
		
		player.setHeight(player.getWidth() );
		player.setState(PlayerState.Crouching);
	}
	
	public boolean isJumping() {
		return (player.getPlayerAction() == PlayerAction.Jumping);
	}
	
	public boolean isFalling() {
		return (player.getPlayerAction() == PlayerAction.Falling);
	}
	
	public boolean isStationary() {
		return (player.getPlayerAction() == PlayerAction.Stationary);
	}
	
	public boolean isStanding() {
		return (player.getPlayerState() == PlayerState.Standing);
	}
	
	public boolean isProning() {
		return (player.getPlayerState() == PlayerState.Proning);
	}
	
	public boolean isCrouching() {
		return (player.getPlayerState() == PlayerState.Crouching);
	}
	
	public boolean isProneShelling() {
		return (player.getPlayerState() == PlayerState.ProneShelling);
	}
	
	public boolean isCrouchShelling() {
		return (player.getPlayerState() == PlayerState.CrouchShelling);
	}
	
	public boolean isStandShelling() {
		 return (player.getPlayerState() == PlayerState.StandShelling);
	}

	public boolean isJumpingAllowed() {

		if (player.getPlayerAction() == PlayerAction.Stationary) return true;
		return false;
	}

	public boolean isStandingAllowed() {
		
		if (player.getPlayerState() == PlayerState.Crouching) return true;
		if (player.getPlayerState() == PlayerState.Proning) return true;
		if (player.getPlayerState() == PlayerState.StandShelling) return true;
		return false;
		
	}
	
	public boolean isFallingAllowed() {
		
		if (player.getPlayerAction() == PlayerAction.Jumping) return true;
		if (player.getPlayerAction() == PlayerAction.Stationary) return true;
		return false;
		
	}
	
	public boolean isCrouchingAllowed() {
		
		if (player.getPlayerState() == PlayerState.Standing) return true;
		if (player.getPlayerState() == PlayerState.CrouchShelling) return true;
		return false;

	}
	
	public boolean isProningAllowed() {
		
		if (player.getPlayerState() == PlayerState.Standing) return true;
		if (player.getPlayerState() == PlayerState.ProneShelling) return true;
		return false;

	}
	
	public boolean isGravityEnabled() {

		if (player.getPlayerAction() == PlayerAction.Jumping) return true;
		if (player.getPlayerAction() == PlayerAction.Falling) return true;
		return false;
	}
	
	
}
