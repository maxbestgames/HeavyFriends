package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;

public class Wood extends Block{

	public Wood(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Wood, "assets/texture/wood.png");
		
		blockTexture = tex.getSprite(0, 0);
		
	}

	public void tick() {}

}
