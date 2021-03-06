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
		return new Rectangle((int) obj.getX() + obj.getWidth() - 1, (int) obj.getY()+obj.getHeight()/2-12, 1, 1);
	}
	
	public Rectangle getBoundsBotLeft() {
		return new Rectangle((int) obj.getX(), (int) obj.getY() + obj.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsBotRight() {
		return new Rectangle((int) obj.getX() + obj.getWidth() - 1, (int) obj.getY()+obj.getHeight()/2+12, 1, 1);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) obj.getX() + obj.getWidth()/2-1, (int) obj.getY(), 2, 1);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) obj.getX() + obj.getWidth()/2-2, (int) obj.getY() + obj.getHeight() - 1, 4, 1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) obj.getX(), (int) obj.getY(), obj.getWidth(), obj.getHeight());
	}
	
	public Rectangle getBoundsBotStop() {
		return new Rectangle((int) obj.getX() + 3, (int) obj.getY() + obj.getHeight() + 1, obj.getWidth() - 6, 1);
	}
	
	public Rectangle getBoundsRightStop() {
		return new Rectangle((int) obj.getX() + obj.getWidth() + 1, (int) obj.getY() + 7, 1, obj.getHeight() - 14);
	}
	
	public Rectangle getBoundsLeftStop() {
		return new Rectangle((int) obj.getX() - 2, (int) obj.getY() + 7, 1, obj.getHeight() - 14);
	}
	
	public Rectangle getBoundsTopStop() {
		return new Rectangle((int) obj.getX() + 3, (int) obj.getY() -  2, obj.getWidth() - 6, 1);
	}
	
	public Rectangle getBoundsLedgeLeft() {
		return new Rectangle((int) (obj.getX() - obj.getWidth()/2), (int) obj.getY() + obj.getHeight() + 10, 4, 4);
	}
	
	public Rectangle getBoundsLedgeRight() {
		return new Rectangle((int) obj.getX() + obj.getWidth() + obj.getWidth()/2 -4, (int) obj.getY() + obj.getHeight() + 10, 4, 4);
	}
	
	//TODO ledge edge left and right detection boxes
}
