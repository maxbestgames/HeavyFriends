package core.display;

import core.gameobjects.GameObject;


public class Camera {
	
	private static float x;
	private static float y;
	private float velX, velY, vel;
	private float difX, difY;
	
	static GameObject camCenter;

	private float desiredX, desiredY;
	
	public Camera(float x, float y) {
		Camera.x = x;
		Camera.y = y;
		vel = 0.015f;
		
	}
	
	public void tick() {
		
		desiredX = -camCenter.getX() +  Window.getVisibleScreenX()/2;
		desiredY = -camCenter.getY() +  Window.getVisibleScreenY()/2;
		
		difX = Math.abs(x - desiredX);
		difY = Math.abs(y - desiredY);
		
		if (Math.abs(x-desiredX) > Window.getVisibleScreenX()*0.2) {
		
			if (x > desiredX) velX = -vel * difX;// player is to the right
			if (x < desiredX) velX = vel * difX; //player is to the left
		
		} else {
			if (Math.abs(velX) > vel/100 ) {
				velX = velX/2;
			} else 
				velX = 0;
		}
		
		if (Math.abs(y- desiredY) > Window.getVisibleScreenY() * 0.2) {
			if (y > desiredY) velY = -vel * difY * 5;// player is  below
			if (y < desiredY) velY = vel * difY * 3; //player is above
		} else {
			if (Math.abs(velY) > vel/100) {
				velY = velY/2;
			} else
				velY = 0;
		}
		
		//Game.clamp(velX, -maxVel, maxVel);
		//Game.clamp(velY, -maxVel, maxVel);
		
		x += velX;
		y += velY;
		
		//System.out.println("pos [" + x + ", " + y +"] --> [" + desiredX + ", " + desiredY + "] "
		//		+ "dif ["+ difX + ", " + difY + "]");
	}
	
	public void setX(float x) {
		Camera.x = x;
	}
	
	public void setY(float y) {
		Camera.y = y;
	}
	
	public static float getX() {
		return x;
	}
	
	public static float getY() {
		return y;
	}
	
	public static void setCamCenter(GameObject object) {
		camCenter = object;
	}
	
	public static GameObject getCamCenter() {
		return camCenter;
	}
	
}
