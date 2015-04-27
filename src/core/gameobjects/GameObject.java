package core.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import core.enums.ID;


public abstract class GameObject {
	protected float x,y; //pos on screen
	protected ID id;
	protected float velX,velY;
	protected boolean falling;
	protected boolean jumping;
	
	public GameObject(float x,float y,ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float newX) {
		x = newX;
	}
	public float getX() {
		return x;
	}
	public void setY(float newY) {
		y = newY;
	}
	public float getY() {
		return y;
	}
	public void setId(ID newId) {
		id = newId;
	}
	public ID getId() {
		return id;
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
	
}
