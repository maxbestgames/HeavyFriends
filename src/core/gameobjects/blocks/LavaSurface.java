package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;
import core.gameobjects.TickingBlock;
import core.visualgronk.Animation;

public class LavaSurface extends TickingBlock {
	
	public LavaSurface(int x, int y) {
		super(x, y, EntityID.Block, BlockType.LavaSurface, "assets/texture/lava.png");
		
		anim = new Animation(15, tex.getSprite(r.nextInt(4)+4, 0), tex.getSprite(r.nextInt(4)+4, 0),
				tex.getSprite(r.nextInt(4)+4, 0), tex.getSprite(r.nextInt(4)+4, 0),
				tex.getSprite(r.nextInt(4)+4, 0), tex.getSprite(r.nextInt(4)+4, 0),
				tex.getSprite(r.nextInt(4)+4, 0), tex.getSprite(r.nextInt(4)+4, 0));
		
		collisionEnabled = false;
		
	}

	public void tick() {
		anim.runAnimation();
	}
	
	
}
