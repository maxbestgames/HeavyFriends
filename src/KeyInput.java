import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		for(int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()==ID.Player) {
				// player keys
				if (key==KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY( -10 );
				}
				if (key==KeyEvent.VK_A) tempObject.setVelX( -5 );
				//if (key==KeyEvent.VK_S) tempObject.setVelY( 5 );
				if (key==KeyEvent.VK_D) tempObject.setVelX( 5 );
				
			}
			if(tempObject.getId()==ID.Player2){
				// player2 keys
				if (key==KeyEvent.VK_UP && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY( -10 );
				}
				//if(key==KeyEvent.VK_DOWN) tempObject.setVelY( 5 );
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX( -5 );
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX( 5 );

			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()==ID.Player) {
				// player keys
				if (key==KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setVelY( 0 );
				}
				if (key==KeyEvent.VK_A) tempObject.setVelX( 0 );
				//if (key==KeyEvent.VK_S) tempObject.setVelY( 0 );
				if (key==KeyEvent.VK_D) tempObject.setVelX( 0 );
					
			}
			if(tempObject.getId()==ID.Player2){
				// player2 keys
				if(key==KeyEvent.VK_UP && !tempObject.isJumping()) {
					tempObject.setVelY( 0 );
				}
				//if(key==KeyEvent.VK_DOWN) tempObject.setVelY( 0 );
				if(key==KeyEvent.VK_LEFT) tempObject.setVelX( 0 );
				if(key==KeyEvent.VK_RIGHT) tempObject.setVelX( 0 );

			}
		}
	}
}
