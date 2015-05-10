package core.gameobjects.art.backgrounds;

import core.enums.EntityID;
import core.gameobjects.GameObject;

public abstract class LevelBackground extends GameObject{
	
	public LevelBackground() {
		super(0, 0, EntityID.ArtAsset);
		//rendPriority = RenderPriority.Background;
		collisionEnabled = false;
		
	}

}
