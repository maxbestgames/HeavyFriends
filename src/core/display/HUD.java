package core.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.enums.EntityID;
import core.handlers.WorldHandler;


public class HUD {
	
	private final int MAX_PLAYERS = 2;
	Random r = new Random();
	WorldHandler handler;
	
	public HUD(WorldHandler handler) {
		this.handler = handler;
				
		for (int i = 0; i<MAX_PLAYERS; i++) {
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		drawPlayerHUD(g, 0, 15, 15); //player hud
		
		drawFreq(g);
		
	}
	
	public void drawPlayerHUD(Graphics g, int playerNum, int x, int y) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 204, 13);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 204, 13);
		if (Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerHealth() >50) g.setColor(Color.GREEN);
		else if (Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerHealth() >30) g.setColor(Color.YELLOW);
		else if (Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerHealth() > 15) g.setColor(Color.ORANGE);
		else g.setColor(Color.RED);
		g.fillRect(x+2, y+2, Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getPlayerHealth() *2 , 10);
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
			//g.drawLine(10+(i-2)/30, Window.getVisibleScreenY()  - prevPointY, 10+(i/3), 
					//Window.getVisibleScreenY() - prevPointY);
			g.drawLine(10+(i-1)/5, Window.getVisibleScreenY()  - prevPointY, 10+(i/5), 
					Window.getVisibleScreenY() - currentY);
		}
		
	}
}
