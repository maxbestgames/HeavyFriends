package core.handlers.player;

import java.awt.Rectangle;

import core.gameobjects.Player;

public class PlayerBoundsHandler {
	
	Player player;
	
	public PlayerBoundsHandler(Player player) {
		this.player = player;
	}

	
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) player.getX(), (int) player.getY() + player.getHeight()/2-3, 2, 6);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) player.getX() + player.getWidth()-2, (int) player.getY()+player.getHeight()/2-3, 2, 6);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) player.getX() + player.getWidth()/2-3, (int) player.getY(), 6, 2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) player.getX() + player.getWidth()/2-3, (int) player.getY() + player.getHeight()-2, 6, 2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
	}
	
	public Rectangle getFallingBox() {
		return new Rectangle((int) player.getX() + player.getWidth()/2-3, (int) player.getY() + player.getHeight(), 6, 2);
	}
}
