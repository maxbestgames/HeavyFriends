package core.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.Game;
import core.enums.EntityID;
import core.gameobjects.TickingGameObject;

public abstract class Projectile extends TickingGameObject{

	protected float gravity;
	
	public Projectile(int x, int y, float gravity, float velX, float velY) {
		super(x, y, EntityID.Projectile);
		this.gravity = gravity;
		this.velX = velX;
		this.velY = velY;
		
		height = 32;
		width = 32;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(true) {
			velY += gravity;
		}
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.draw(getBounds());
		g.setColor(Color.YELLOW);
		g2d.fill(getBounds());
		
	}
	
	

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	

}
