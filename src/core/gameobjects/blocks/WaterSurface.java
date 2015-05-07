package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.TickingBlock;
import core.visualgronk.Animation;

public class WaterSurface extends TickingBlock {
	
	public WaterSurface(int x, int y) {
		super(x, y, EntityID.Block, BlockType.WaterSurface, "assets/texture/water.png");
		
		anim = new Animation(15, tex.getSprite(r.nextInt(2)+2, 0), tex.getSprite(r.nextInt(2)+2, 0),
				tex.getSprite(r.nextInt(2)+2, 0), tex.getSprite(r.nextInt(2)+2, 0));
		
		collisionEnabled = false;
		
	}

	public void tick() {
		anim.runAnimation();
	}
	
	
}
