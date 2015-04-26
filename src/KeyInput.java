import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyPressed;
	private boolean[] keyDown;
	
	
	public KeyInput(Handler handler){
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
		
		for(int i = 0; i < handler.getSize();i++){
			GameObject tempObject = handler.getObject(i);
			if (tempObject.getId()==ID.Player) {
				// player keys
				if (keyPressed[KeyEvent.VK_W] && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY( -15 );
					keyDown[0] = true;
				}
				if (keyPressed[KeyEvent.VK_A]) {
					tempObject.setVelX( -5 );
					keyDown[1] = true;
				}
				if (keyPressed[KeyEvent.VK_D]) {
					tempObject.setVelX( 5 );
					keyDown[2] = true;
				}
				//if (key==KeyEvent.VK_S) tempObject.setVelY( 5 );


			}
			if(tempObject.getId()==ID.Player2){
				// player2 keys
				if (keyPressed[KeyEvent.VK_UP] && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY( -15 );
					keyDown[3] = true;
				}

				if(keyPressed[KeyEvent.VK_LEFT]) {
					tempObject.setVelX( -5 );
					keyDown[4] = true;
				}
				if(keyPressed[KeyEvent.VK_RIGHT]) {
					tempObject.setVelX( 5 );
					keyDown[5] = true;
				}
				//if(key==KeyEvent.VK_DOWN) tempObject.setVelY( 5 );

			}
		}


		// release code
		for(int i = 0; i < handler.getSize();i++){
			GameObject tempObject = handler.getObject(i);
			if (tempObject.getId()==ID.Player) {
				// player keys
				if (!keyPressed[KeyEvent.VK_W] && !tempObject.isJumping()) {
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

				//vertical movement
				if(!keyDown[0]) tempObject.setVelY(0);
				if(!keyDown[1] && !keyDown[2]) tempObject.setVelX(0);

			}
			if(tempObject.getId()==ID.Player2){
				// player2 keys
				if(!keyPressed[KeyEvent.VK_UP] && !tempObject.isJumping()) {
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
				//if(key==KeyEvent.VK_DOWN) tempObject.setVelY( 0 );

				//vertical movement
				if(!keyDown[3]) tempObject.setVelY(0);
				if(!keyDown[4] && !keyDown[5]) tempObject.setVelX(0);

			}
		}
	}
}
