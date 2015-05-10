package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer1 extends LevelBackground {

	public LevelBackgroundLayer1() {
		super();
		rendPriority = RenderPriority.BackgroundLayer1;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) -Camera.getX(), (int) -Camera.getY(), null);
		}
	}

}
