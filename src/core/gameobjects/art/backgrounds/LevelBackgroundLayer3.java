package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer3 extends LevelBackground {

	public LevelBackgroundLayer3() {
		super();
		rendPriority = RenderPriority.BackgroundLayer3;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) Camera.getX()/50, (int) Camera.getY()/50, null);
		}
	}

}
