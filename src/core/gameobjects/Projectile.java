package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.Game;
import core.enums.EntityID;
import core.gameobjects.TickingGameObject;
import core.handlers.MovementHandler;
import core.visualgronk.Animation;

public abstract class Projectile extends TickingGameObject {

	protected float gravity;
	protected Animation anim;
	
	public Projectile(int x, int y, float gravity, float velX, float velY) {
		super(x, y, EntityID.Projectile);
		this.gravity = gravity;
		this.velX = velX;
		this.velY = velY;
		mov = new MovementHandler(this);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(mov.isGravityEnabled()) {
			velY += gravity;
		}
		
		velX = Game.clamp(velX, -20, 20);
		velY = Game.clamp(velY, -20, 20);
		
		if (anim != null)
			anim.runAnimation();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (drawBoundingBoxes) {
			g.setColor(Color.GREEN);
			g2d.draw(getBounds());
			g2d.fill(getBounds());
			g.setColor(Color.BLUE);
			g2d.draw(objBoundBox.getBoundsTop());
			g2d.draw(objBoundBox.getBoundsBottom());
			g2d.draw(objBoundBox.getBoundsTopLeft());
			g2d.draw(objBoundBox.getBoundsTopRight());
			g.setColor(Color.BLUE);
			g2d.draw(objBoundBox.getBoundsBotLeft());
			g2d.draw(objBoundBox.getBoundsBotRight());
			g.setColor(Color.RED);
			g2d.draw(objBoundBox.getBounds());
			g.setColor(Color.WHITE);
			g2d.draw(objBoundBox.getBoundsBotStop());
			g2d.draw(objBoundBox.getBoundsRightStop());
			g2d.draw(objBoundBox.getBoundsLeftStop());
			g2d.draw(objBoundBox.getBoundsTopStop());

		}
		if (drawTextures){
			if (anim != null) anim.drawAnimation(g, (int) x, (int) y);
			else g.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
		}
	}
	
	

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	

}
