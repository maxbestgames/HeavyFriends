package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;
import core.gameobjects.TickingBlock;
import core.visualgronk.Animation;

public class Lava extends TickingBlock {

	public Lava(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Lava, "assets/texture/lava.png");

		anim = new Animation(15, tex.rotate(tex.getSprite(r.nextInt(4), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f), 
				tex.rotate(tex.getSprite(r.nextInt(4), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f),
				tex.rotate(tex.getSprite(r.nextInt(4), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f),
				tex.rotate(tex.getSprite(r.nextInt(4), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f));
		
		collisionEnabled = false;
		
	}

	public void tick() {
		anim.runAnimation();

	}

}
