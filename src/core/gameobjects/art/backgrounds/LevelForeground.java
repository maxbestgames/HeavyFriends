package core.gameobjects.art.backgrounds;

import core.enums.EntityID;
import core.enums.RenderPriority;
import core.gameobjects.GameObject;

public abstract class LevelForeground extends GameObject {

	public LevelForeground() {
		super(0, 0, EntityID.ArtAsset);
		rendPriority = RenderPriority.Foreground;
	}

}
