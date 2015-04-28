package core;

import core.Game;
import core.gameobjects.GameObject;


public class Camera {
	
	private float x, y;
	private float velX, velY, maxVel, vel;
	private int delay, ticks;
	private float difX, difY;
	
	private float desiredX, desiredY;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
		maxVel = 25;
		delay = 2;
		vel = 0.01f;
		
	}
	
	public void tick(GameObject player) {
		
		/*if ( ticks < delay) {
			ticks++;
		} else {
			ticks = 0;
			velX = Math.abs(x -player.getX() +  Window.getVisibleScreenX()/2);
			velY = Math.abs(y -player.getY() + Window.getVisibleScreenY()/2);
		}*/
		
		desiredX = -player.getX() +  Window.getVisibleScreenX()/2;
		desiredY = -player.getY() +  Window.getVisibleScreenY()/2;
		
		difX = Math.abs(x - desiredX);
		difY = Math.abs(y - desiredY);
		
		if (Math.abs(x-desiredX) > Window.getVisibleScreenX()*0.2) {
		
			if (x > desiredX) velX = -vel * difX;// player is to the right
			if (x < desiredX) velX = vel * difX; //player is to the left
		
		} else {
			velX = 0;
		}
		
		if (Math.abs(y- desiredY) > Window.getVisibleScreenY() * 0.2) {
			if (y > desiredY) velY = -vel * difY * 3;// player is to the below
			if (y < desiredY) velY = vel * difY * 3; //player is to the left
		
		} else {
			velY = 0;
		}
		
		Game.clamp(velX, -maxVel, maxVel);
		Game.clamp(velY, -maxVel, maxVel);
		
		x += velX;
		y += velY;
		
		//System.out.println("pos [" + x + ", " + y +"] --> [" + desiredX + ", " + desiredY + "] "
		//		+ "dif ["+ difX + ", " + difY + "]");
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
