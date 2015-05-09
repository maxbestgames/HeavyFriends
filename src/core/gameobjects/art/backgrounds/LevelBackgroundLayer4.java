package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer4 extends LevelBackground {

	public LevelBackgroundLayer4() {
		super();
		rendPriority = RenderPriority.BackgroundLayer4;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) Camera.getX()/35, (int) Camera.getY()/35, null);
		}
	}

}
