package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;

public class Dirt extends Block {

	public Dirt(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Dirt, "assets/texture/blocks.png");
		
		blockTexture = tex.rotate(tex.getSprite(r.nextInt(2), 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f);
		
	}

	public void tick() {}

}
