package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer6 extends LevelBackground {

	public LevelBackgroundLayer6() {
		super();
		rendPriority = RenderPriority.BackgroundLayer6;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) (-Camera.getX() - Camera.getX()/2), (int) -Camera.getY(), null);
		}
	}

}
