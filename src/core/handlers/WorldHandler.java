package core.handlers;

import java.awt.Graphics;

import core.Game;
import core.RenderThread;
import core.enums.LevelID;
import core.gameobjects.Player;
import core.levels.LevelBuilder;

public class WorldHandler {
	
	PlayerHandler players = new PlayerHandler();
	LevelHandler levels = new LevelHandler();
	
	public void addPlayer(Player p) {
		players.addPlayer(p);
	}
	
	public void removePlayer(Player p) {
		players.removePlayer(p);
	}
	
	public void addLevel(LevelBuilder level) {
		levels.addObject(level);
	}
	
	public void removeLevel(LevelBuilder level) {
		levels.removeObject(level);
	}

	public PlayerHandler getPlayers() {
		return players;
	}

	public LevelHandler getLevels() {
		return levels;
	}
	
	public LevelBuilder getLevel(LevelID lId) {
		LevelBuilder tempLevel;
		for (int i=0; i < levels.getNumLevels(); i++) {
			tempLevel = levels.getLevel(i);
			if (tempLevel.getId() == lId) {
				return tempLevel;
			}
		}
		return null;
	}
	
	public int getNumPlayers() {
		return players.getSize();
	}
	
	public int getNumLevels() {
		return levels.getNumLevels();
	}
	
	public void tick() {
		levels.getLevel( Game.getCurrentLevel() ).getObjHandler().tick();
		for (int i = 0; i < players.getSize(); i++) {
			players.getPlayer(i).tick();
		}
	}
	
	public void render(Graphics g) {
		levels.getLevel( Game.getCurrentLevel() ).getObjHandler().render(g);
		for (int i = 0; i < players.getSize(); i++) {
			//players.getPlayer(i).render(g);
			RenderThread.getRenderQueue().add(players.getPlayer(i) );
		}
	}
	
	public ObjectHandler getCurrentLevelObjectHandler() {
		return levels.getLevel( Game.getCurrentLevel() ).getObjHandler();
	}
	
	
	// players and our level handlers will be in this list.
	// level handlers will tick their objects themselves if that level is the current level

}
