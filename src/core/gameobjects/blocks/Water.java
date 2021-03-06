package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.TickingBlock;
import core.visualgronk.Animation;

public class Water extends TickingBlock {

	public Water(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Water, "assets/texture/water.png");

		anim = new Animation(15, tex.rotate(tex.getSprite(r.nextInt(2), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f), 
				tex.rotate(tex.getSprite(r.nextInt(2), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f),
				tex.rotate(tex.getSprite(r.nextInt(2), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f),
				tex.rotate(tex.getSprite(r.nextInt(2), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f));
		
		collisionEnabled = false;
		
	}

	public void tick() {
		anim.runAnimation();

	}

}
