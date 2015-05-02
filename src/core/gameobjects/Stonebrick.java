package core.gameobjects;

import core.enums.BlockType;
import core.enums.EntityID;

public class Stonebrick extends Block {

	public Stonebrick(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Stonebrick, "assets/texture/brick-vine-cobble-dirt.png");
		
		blockTexture = tex.getSprite(4, 0);
	}


	public void tick() {}

}
