package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.enums.ID;
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
		if (handler.getNumPlayers()>1) //player2 hud
			drawPlayerHUD(g, 1, (Window.getVisibleScreenX()-204-15), 15);
		
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
		g.drawString(""+Game.getFPS(), Window.getVisibleScreenX()-30, 15);
		g.drawString("" + Game.getTPS(), Window.getVisibleScreenX()-30, 30);
		g.drawString(Game.getWorldHandler().getPlayers().getPlayer(ID.Player).getState().toString(), Window.getVisibleScreenX()-100, 45);
	}
}
