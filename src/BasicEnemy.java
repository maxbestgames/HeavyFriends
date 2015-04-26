import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class BasicEnemy extends GameObject {
	Random r = new Random();
	int basicEnemySize = 16;
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		velX=0;
		velY=0;
	}


	public void tick() {
		x+=velX;
		y+=velY;
		if(y<=0||y>=Game.HEIGHT-35) {
			velY *= -1;
		}
		if(x<=0||x>=Game.WIDTH-15) {
			velX *= -1;
		}
		//handler.addObject( new EffectTrail(x, y, ID.EffectTrail, Color.RED, 16, 16, handler, 0.01f) );
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, basicEnemySize, basicEnemySize);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, basicEnemySize, basicEnemySize);
	}

}
