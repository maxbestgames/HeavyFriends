package core.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import core.enums.EntityID;
import core.enums.PlayerState;
import core.gameobjects.GameObject;
import core.gameobjects.Player;
import core.handlers.WorldHandler;

public class KeyInput extends KeyAdapter{
	
	private WorldHandler handler;
	private boolean[] keyPressed;
	private boolean[] keyDown;
	
	
	public KeyInput(WorldHandler handler){
		this.handler = handler;
		
		keyPressed = new boolean[200];
		keyDown = new boolean[6];
		for (int i=0; i < keyDown.length; i++) {
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		keyPressed[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e){
		keyPressed[e.getKeyCode()] = false;	
	}
	
	public void update() {
		Player tempPlayer;
		GameObject tempObject;

		for (int i = 0; i < handler.getNumPlayers(); i++) {
			tempPlayer = handler.getPlayers().getPlayer(i); 
			
			// player controls
			if (tempPlayer.getId()==EntityID.Player) {
				// player keys
				
				//basic movement
				if (keyPressed[KeyEvent.VK_W] && tempPlayer.getState() == PlayerState.Standing ) {

					tempPlayer.setVelY( -15 );
					keyDown[0] = true;
				}
				if (keyPressed[KeyEvent.VK_A]) {
					tempPlayer.setVelX( -5 );
					keyDown[1] = true;
				}
				if (keyPressed[KeyEvent.VK_D]) {
					tempPlayer.setVelX( 5 );
					keyDown[2] = true;
				}
				
				//prone
				if (keyPressed[KeyEvent.VK_S] && tempPlayer.getMovement().isProningAllowed() ) {
					tempPlayer.getMovement().goProne();

			}
			if(tempPlayer.getId()==EntityID.Player2){
				// player2 keys
				if (keyPressed[KeyEvent.VK_UP] && tempPlayer.getMovement().isJumpingAllowed()) {
					tempPlayer.setState(PlayerState.Jumping);
					tempPlayer.setVelY( -15 );
					keyDown[3] = true;
				}

				if(keyPressed[KeyEvent.VK_LEFT]) {
					tempPlayer.setVelX( -5 );
					keyDown[4] = true;
				}
				if(keyPressed[KeyEvent.VK_RIGHT]) {
					tempPlayer.setVelX( 5 );
					keyDown[5] = true;
				}
				//if(key==KeyEvent.VK_DOWN) tempObject.setVelY( 5 );
			}

			// key release code
			if (tempPlayer.getId()==EntityID.Player) {
				// player keys
				if (!keyPressed[KeyEvent.VK_W] && !tempPlayer.getMovement().isJumping()) {
					//tempObject.setVelY( 0 );
					keyDown[0] = false;
				}
				if (!keyPressed[KeyEvent.VK_A]) {
					//tempObject.setVelX( 0 );
					keyDown[1] = false;
				}
				//if (key==KeyEvent.VK_S) tempObject.setVelY( 0 );
				if (!keyPressed[KeyEvent.VK_D]) {
					//tempObject.setVelX( 0 );
					keyDown[2] = false;
				}
				
				//prone
				if ( !keyPressed[KeyEvent.VK_S] && tempPlayer.getMovement().isStandingAllowed()
						&& !tempPlayer.getMovement().isGravityEnabled() ) {
					System.out.println("going to standing");
					tempPlayer.getMovement().goStanding();
				}

				if(!keyDown[1] && !keyDown[2]) tempPlayer.setVelX(0);

			}
			if (tempPlayer.getId()==EntityID.Player2){
				// player2 keys
				if(!keyPressed[KeyEvent.VK_UP] && !tempPlayer.getMovement().isJumping()) {
					//tempObject.setVelY( 0 );
					keyDown[3] = false;
				}

				if(!keyPressed[KeyEvent.VK_LEFT]) {
					//tempObject.setVelX( 0 );
					keyDown[4] = false;
				}
				if(!keyPressed[KeyEvent.VK_RIGHT]) {
					//tempObject.setVelX( 0 );
					keyDown[5] = false;
				}
				
				if(!keyDown[4] && !keyDown[5]) tempPlayer.setVelX(0);

			}
			
			//object controls - not in use yet
			/*for (int j = 0; j < handler.getLevel( Game.getCurrentLevel() ).getNumObjects(); j++) {
				tempObject = handler.getLevel( Game.getCurrentLevel() ).getObjects().getObject(j);

				
			}*/
		}
	}
}
}
