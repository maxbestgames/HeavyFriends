package core.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.enums.EntityID;
import core.enums.RenderPriority;
import core.handlers.ObjectHandler;
import core.visualgronk.Texture;


public abstract class GameObject implements Comparable<GameObject> {
	protected boolean toDestroy = false;
	
	protected boolean direction = true;
	protected float x,y; //pos on screen
	protected EntityID id;
	protected RenderPriority rendPriority;
	protected float velX,velY;
	protected ObjectHandler handler;
	protected Texture tex;
	protected BufferedImage currentTexture;
	protected int width,height;
	protected boolean collisionEnabled = true;
	protected static boolean drawTextures = true;
	protected static boolean drawBoundingBoxes = false;

	public GameObject(float x, float y, EntityID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		handler = new ObjectHandler();
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

	public EntityID getId() {
		return id;
	}

	public void setId(EntityID id) {
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
	
	public ObjectHandler getHandler() {
		return handler;
	}

	public void setHandler(ObjectHandler handler) {
		this.handler = handler;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isCollisionEnabled() {
		return collisionEnabled;
	}

	public void setCollisionEnabled(boolean collisionEnabled) {
		this.collisionEnabled = collisionEnabled;
	}
	
	public static void setDrawTextures(boolean drawTextures) {
		GameObject.drawTextures = drawTextures;
	}

	public static void setDrawBoundingBoxes(boolean drawBoundingBoxes) {
		GameObject.drawBoundingBoxes = drawBoundingBoxes;
	}

	public static boolean isDrawTextures() {
		return drawTextures;
	}

	public static boolean isDrawBoundingBoxes() {
		return drawBoundingBoxes;
	}
	
	public RenderPriority getRenderPriority() {
		return rendPriority;
	}
	
	public int compareTo(GameObject o) {
		return rendPriority.compareTo(o.getRenderPriority());
	}
	
	public void markForRemoval() {
		toDestroy = true;
	}
	
	public boolean isMarkedForRemoval() {
		return toDestroy;
	}
	
	public boolean isFacingRight() {
		return direction;
	}
	
	public boolean isFacingLeft() {
		return !direction;
	}
	
	public void setFacingRight() {
		direction = true;
	}
	
	public void setFacingLeft() {
		direction = false;
	}
}
