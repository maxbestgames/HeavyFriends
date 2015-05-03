package core.gameobjects.blocks;

import core.enums.BlockType;
import core.enums.EntityID;
import core.gameobjects.Block;

public class Claybrick extends Block {

	public Claybrick(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Claybrick, "assets/texture/brick-vine-cobble-dirt.png");
		
		blockTexture = tex.getSprite(3, 0);
	}


	public void tick() {}

}
