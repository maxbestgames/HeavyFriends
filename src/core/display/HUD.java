package core.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.enums.EntityID;
import core.handlers.WorldHandler;


public class HUD {
	
	public static int[] HEALTH;
	private final int MAX_PLAYERS = 2;
	Random r = new Random();
	WorldHandler handler;
	
	public HUD(WorldHandler handler) {
		this.handler = handler;
		
		HEALTH = new int[MAX_PLAYERS];
		
		for (int i = 0; i<MAX_PLAYERS; i++) {
			HEALTH[i] = 100;
		}
	}
	
	public void tick() {
		
		for (int i=0; i < handler.getNumPlayers() && i < MAX_PLAYERS; i++) {

			Game.clamp(HEALTH[i], 0, 100);
			
		}
	}
	
	public void render(Graphics g) {
		
		for (int i=0; i < handler.getNumPlayers() && i< MAX_PLAYERS; i++) {
			if (HEALTH[i] == 0) {
				g.setColor(Color.BLACK);
				g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
			}
		}
		
		drawPlayerHUD(g, 0, 15, 15); //player hud
		
		drawFreq(g);
		
	}
	
	public void drawPlayerHUD(Graphics g, int playerNum, int x, int y) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 204, 13);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 204, 13);
		if (HEALTH[playerNum] >50) g.setColor(Color.GREEN);
		else if (HEALTH[playerNum] >30) g.setColor(Color.YELLOW);
		else if (HEALTH[playerNum] > 15) g.setColor(Color.ORANGE);
		else g.setColor(Color.RED);
		g.fillRect(x+2, y+2, HEALTH[playerNum] *2 , 10);
		g.setColor(Color.WHITE);
		g.drawString("FPS "+Game.getFPS(), Window.getVisibleScreenX()-100, 15);
		g.drawString("TPS " + Game.getTPS(), Window.getVisibleScreenX()-100, 30);
		g.drawString(Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerState().toString(), Window.getVisibleScreenX()-100, 45);
		g.drawString(Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerAction().toString(), Window.getVisibleScreenX()-100, 60);
		g.drawString("Vel Y "+ Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getVelY(), Window.getVisibleScreenX()-100, 75);
		g.drawString("Vel X " + Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getVelX(), Window.getVisibleScreenX()-100, 90);
		g.drawString("X " + Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getX(), Window.getVisibleScreenX()-100, 105);
		g.drawString("Y " + Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getY(), Window.getVisibleScreenX()-100, 120);
		g.drawString("E: " + Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getColHandler().getNumCloseObjects()+" of "
				+ "" +Game.getWorldHandler().getCurrentLevelObjectHandler().getSize(), Window.getVisibleScreenX()-100, 135);		
		g.drawString("" + Game.getMouseBounds().getMinX() + ", " + Game.getMouseBounds().getMinY(), Window.getVisibleScreenX()-100, 150);
	}
	
	public void drawFreq(Graphics g) {
		int prevPointY, currentY = 50;
		double[] pointData = Game.getFFTT().getDataBin();
		//System.out.println(pointData[300]*2);
		g.setColor(new Color( Game.clamp((int) (2*pointData[50]),0,255), 0, 0 ));
		for (int i=1; i < pointData.length; i+=2) {
			prevPointY = currentY;
			currentY = (int) pointData[i] + 50;
			if (currentY < 0) {
				//System.out.println(currentY);
				currentY = 50;
			}
			g.drawLine(10+(i-2)/3, Window.getVisibleScreenY()  - prevPointY, 10+(i/3), 
					Window.getVisibleScreenY() - prevPointY);
			g.drawLine(10+(i-1)/3, Window.getVisibleScreenY()  - prevPointY, 10+(i/3), 
					Window.getVisibleScreenY() - currentY);
		}
		
	}
}
