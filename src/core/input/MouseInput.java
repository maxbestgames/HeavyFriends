package core.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import core.Game;
import core.display.Camera;
import core.enums.EntityID;
import core.gameobjects.GameObject;

public class MouseInput extends MouseAdapter {
	
	KeyInput keyIn;
	
	public MouseInput(KeyInput keyIn) {
		this.keyIn = keyIn;
	}
	
	public void mousePressed(MouseEvent e) {
		if( keyIn.isPressed(KeyEvent.VK_F3) && keyIn.isPressed(KeyEvent.VK_C) && e.getButton() == e.BUTTON1) {
			GameObject tempObject;
			for(int i = 0; i <= Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(); i++ ) {
				if (i < Game.getWorldHandler().getCurrentLevelObjectHandler().getSize()) {
					tempObject = Game.getWorldHandler().getCurrentLevelObjectHandler().getObject(i);
				} else {
					tempObject = Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player);
				}
				
				if (Game.getMouseBounds().intersects(tempObject.getBounds())) {
					Camera.setCamCenter(tempObject);
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
}
