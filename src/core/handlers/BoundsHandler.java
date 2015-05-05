package core.handlers;

import java.awt.Rectangle;

import core.gameobjects.TickingGameObject;

public class BoundsHandler {
	
	TickingGameObject obj;
	
	public BoundsHandler(TickingGameObject obj) {
		this.obj = obj;
	}
	
	public Rectangle getBoundsTopLeft() {
		return new Rectangle((int) obj.getX(), (int) obj.getY() + obj.getHeight()/2-12, 1, 1);
	}
	
	public Rectangle getBoundsTopRight() {
		return new Rectangle((int) obj.getX() + obj.getWidth() - 2, (int) obj.getY()+obj.getHeight()/2-12, 1, 1);
	}
	
	public Rectangle getBoundsBotLeft() {
		return new Rectangle((int) obj.getX(), (int) obj.getY() + obj.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsBotRight() {
		return new Rectangle((int) obj.getX() + obj.getWidth() - 2, (int) obj.getY()+obj.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) obj.getX() + obj.getWidth()/2-3, (int) obj.getY(), 1, 1);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) obj.getX() + obj.getWidth()/2-2, (int) obj.getY() + obj.getHeight() - 2, 4, 1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) obj.getX(), (int) obj.getY(), obj.getWidth(), obj.getHeight());
	}
	
	public Rectangle getBoundsBotStop() {
		return new Rectangle((int) obj.getX() + 3, (int) obj.getY() + obj.getHeight(), obj.getWidth() - 6, 1);
	}
	
	public Rectangle getBoundsRightStop() {
		return new Rectangle((int) obj.getX() + obj.getWidth() + 1, (int) obj.getY() + 7, 1, obj.getHeight() - 14);
	}
	
	public Rectangle getBoundsLeftStop() {
		return new Rectangle((int) obj.getX() - 1, (int) obj.getY() + 7, 1, obj.getHeight() - 14);
	}
	
	public Rectangle getBoundsTopStop() {
		return new Rectangle((int) obj.getX() + 3, (int) obj.getY() -  2, obj.getWidth() - 6, 1);
	}
	
	//TODO ledge edge left and right detection boxes
}
