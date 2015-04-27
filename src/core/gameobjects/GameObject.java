package core.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import core.enums.ID;
import core.handlers.ObjectHandler;


public abstract class GameObject {
	protected float x,y; //pos on screen
	protected ID id;
	protected float velX,velY;
	protected boolean falling;
	protected boolean jumping;
	protected ObjectHandler handler;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		handler = new ObjectHandler();
	}
	
	public GameObject(float x, float y, ID id, ObjectHandler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public ObjectHandler getHandler() {
		return handler;
	}

	public void setHandler(ObjectHandler handler) {
		this.handler = handler;
	}
	
	
	
}
