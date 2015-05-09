package core.gameobjects.art.backgrounds;

import java.awt.Graphics;

import core.display.Camera;
import core.enums.EntityID;
import core.enums.RenderPriority;
import core.gameobjects.GameObject;

public abstract class LevelForeground extends GameObject {

	public LevelForeground() {
		super(0, 0, EntityID.ArtAsset);
		rendPriority = RenderPriority.Foreground;
	}
	
	public void render(Graphics g) {
		if (tex != null) {
			g.drawImage(tex.getSprite(0, 0), (int) Camera.getX(), (int) Camera.getY(), null);
		}
	}

}
