package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.RenderPriority;

public abstract class LevelBackgroundLayer2 extends LevelBackground{

	public LevelBackgroundLayer2() {
		super();
		rendPriority = RenderPriority.BackgroundLayer2;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) Camera.getX()/75, (int) Camera.getY()/75, null);
		}
	}

}
