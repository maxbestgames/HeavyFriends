package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer5 extends LevelBackground {

	public LevelBackgroundLayer5() {
		super();
		rendPriority = RenderPriority.BackgroundLayer5;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) Camera.getX()/18, (int) Camera.getY()/18, null);
		}
	}

}
