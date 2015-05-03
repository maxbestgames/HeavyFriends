package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;

public class Grass extends Block {

	public Grass(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Grass, "assets/texture/blocks.png");
		
		blockTexture = tex.getSprite(r.nextInt(3)+2, 0);
	}

	public void tick() {}

}
