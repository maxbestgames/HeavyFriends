package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;

public class Chain extends Block{

	public Chain(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Chain, "assets/texture/chain.png");
		
		blockTexture = tex.getSprite(0, 0);
		
	}

	public void tick() {}

}
