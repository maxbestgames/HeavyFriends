import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends TickingGameObject{
	Random r = new Random();
	private Handler handler;
	private int playerWidth = 32;
	private int playerHeight = 64;
	private float gravity = 0.9f;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		falling = true;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
		}
		x = Game.clamp(x, 0, Window.visibleScreenX-playerWidth);
		y = Game.clamp(y, 0, Window.visibleScreenY-playerHeight);
		
		collision();
	}
	
	private void collision(){
		for(int i=0; i<handler.getSize(); i++){
			GameObject tempObject = handler.getObject(i);
			if(tempObject.getId()==ID.Block){ // environment Blocks
				if(getBoundsBottom().intersects(tempObject.getBounds())){ //falling down
					velY = 0;
					y = tempObject.getY() - playerHeight;
					jumping = false;
					falling = false;
				}else {
					falling = true;
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())){ // hitting head
					velY = 0;
					y = tempObject.getY() + Block.getBlockSize();
				}
				
				
				
				//next block collision here here
			}
			
			if ( (tempObject.getId() == ID.Player && this.getId() != ID.Player) 
					|| tempObject.getId() == ID.Player2 && this.getId() != ID.Player2) { // other players
				
				if ( getBounds().intersects( tempObject.getBounds() ) ) { // player vs player 2
					tempObject.setVelX(tempObject.getVelX()*-1);
					//tempObject.setX(getX());
					//setX(tempObject.getX());
					setVelX(getVelX()*-1);
					tempObject.setVelY(tempObject.getVelY()*-1);
					//tempObject.setY(getY());
					//setY(tempObject.getY());
					setVelY(getVelY()*-1);
				}
			}
		}
	}
	

	public void render(Graphics g) {
		if(id==ID.Player){
			//Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, playerWidth, playerHeight);
		}
		else if(id==ID.Player2){
			g.setColor(Color.BLUE);
			g.fillRect((int) x, (int) y, playerWidth, playerHeight);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBounds());
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y+2, 2, playerHeight-4);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + playerWidth-2, (int) y+2, 2, playerHeight-4);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+2, (int) y, playerWidth-4, playerHeight/2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x+2, (int) y + playerHeight/2, playerWidth-4, playerHeight/2);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, playerWidth, playerHeight);
	}

}
