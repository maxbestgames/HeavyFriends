package core.levels.testRealm;

import java.awt.Rectangle;

import core.gameobjects.art.backgrounds.LevelBackgroundLayer1;
import core.visualgronk.Texture;

public class TestRealmBL1 extends LevelBackgroundLayer1 {
	
	public TestRealmBL1() {
		//tex = new Texture();
		
		tex = new Texture("assets/backgrounds/testBackground.png", 2000, 500);
	}

	public Rectangle getBounds() {
		return null;
	}

}
