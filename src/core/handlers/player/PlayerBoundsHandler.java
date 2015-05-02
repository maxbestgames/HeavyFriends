package core.handlers.player;

import java.awt.Rectangle;

import core.gameobjects.Player;

public class PlayerBoundsHandler {
	
	Player player;
	
	public PlayerBoundsHandler(Player player) {
		this.player = player;
	}
	
	public Rectangle getBoundsTopLeft() {
		return new Rectangle((int) player.getX(), (int) player.getY() + player.getHeight()/2-12, 1, 1);
	}
	
	public Rectangle getBoundsTopRight() {
		return new Rectangle((int) player.getX() + player.getWidth() - 2, (int) player.getY()+player.getHeight()/2-12, 1, 1);
	}
	
	public Rectangle getBoundsBotLeft() {
		return new Rectangle((int) player.getX(), (int) player.getY() + player.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsBotRight() {
		return new Rectangle((int) player.getX() + player.getWidth() - 2, (int) player.getY()+player.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) player.getX() + player.getWidth()/2-3, (int) player.getY(), 1, 1);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) player.getX() + player.getWidth()/2-2, (int) player.getY() + player.getHeight() - 2, 4, 1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
	}
	
	public Rectangle getBoundsBotStop() {
		return new Rectangle((int) player.getX() + 3, (int) player.getY() + player.getHeight(), player.getWidth() - 6, 1);
	}
	
	public Rectangle getBoundsRightStop() {
		return new Rectangle((int) player.getX() + player.getWidth() + 1, (int) player.getY() + 7, 1, player.getHeight() - 14);
	}
	
	public Rectangle getBoundsLeftStop() {
		return new Rectangle((int) player.getX() - 1, (int) player.getY() + 7, 1, player.getHeight() - 14);
	}
	
	public Rectangle getBoundsTopStop() {
		return new Rectangle((int) player.getX() + 3, (int) player.getY() -  2, player.getWidth() - 6, 1);
	}
}
