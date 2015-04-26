import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class HUD {
	public static int HEALTH = 100;
	Random r = new Random();
	
	public void tick() {
		if (HEALTH <=0) {
			HEALTH = 0;
		} else if (HEALTH > 100) {
			HEALTH = 100;
		}
	}
	public void render(Graphics g) {
		if(HEALTH==0){
			g.setColor(Color.BLACK);
			g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		}else{
			g.setColor(Color.GRAY);
			g.fillRect(15, 15, 204, 13);
			g.setColor(Color.WHITE);
			g.drawRect(15, 15, 204, 13);
			if (HEALTH >50) g.setColor(Color.GREEN);
			else if (HEALTH >30) g.setColor(Color.YELLOW);
			else if (HEALTH > 15) g.setColor(Color.ORANGE);
			else g.setColor(Color.RED);
			g.fillRect(17, 17, HEALTH *2 , 10);
		}
	}

}
