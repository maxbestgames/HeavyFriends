package core.handlers;

import core.gameobjects.Player;
import core.levels.LevelSpawner;

public class WorldHandler {
	
	Handler players = new Handler();
	Handler levels = new Handler();
	
	public void addPlayer(Player p) {
		players.addObject(p);
	}
	
	public void removePlayer(Player p) {
		players.removeObject(p);
	}
	
	public void addLevel(LevelSpawner level) {
		levels.addObject(level);
	}
	
	public void removeLevel() {
		
	}
	
	public void tickLevel() {
		
	}
	
	
	
	// players and our level handlers will be in this list.
	// level handlers will tick their objects themselves if that level is the current level

}
