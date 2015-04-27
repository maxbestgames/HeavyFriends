package core.handlers;

import core.enums.ID;
import core.gameobjects.Player;

public class PlayerHandler {
	
	ObjectHandler playerList = new ObjectHandler();
	
	public void addPlayer(Player p) {
		playerList.addObject(p);
	}
	
	public void removePlayer(Player p) {
		playerList.removeObject(p);
	}
	
	public Player getPlayer(ID id) {
		Player tempPlayer;
		for (int i=0; i<playerList.getSize(); i++) {
			tempPlayer = (Player) playerList.getObject(i);
			if (tempPlayer.getId() == id) {
				return (Player) tempPlayer;
			}
		}
		return null;
	}
	
	public int getSize() {
		return playerList.getSize();
	}
	
	public Player getPlayer(int i) {
		return (Player) playerList.getObject(i);
	}
	
	public ObjectHandler getObjectHandler() {
		return playerList;
	}
}
