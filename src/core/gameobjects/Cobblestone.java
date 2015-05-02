package core.gameobjects;

import core.enums.BlockType;
import core.enums.EntityID;

public class Cobblestone extends Block{

	public Cobblestone(int x, int y) {
		super(x, y, EntityID.Block, BlockType.Cobblestone, "assets/texture/cobble.png");
		
		blockTexture = tex.rotate(tex.getSprite(0, 0),(float) (r.nextInt(4)*Math.PI/2), 16.0f, 16.0f);
		
	}

	public void tick() {}

}
