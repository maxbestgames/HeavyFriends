package core.gameobjects;

import core.enums.BlockType;
import core.enums.EntityID;

public class Claybrick extends Block {

	public Claybrick(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Claybrick, "assets/texture/brick-vine-cobble-dirt.png");
		
		blockTexture = tex.getSprite(3, 0);
	}


	public void tick() {}

}
