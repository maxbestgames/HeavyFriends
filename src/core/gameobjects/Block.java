package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import core.handlers.ObjectHandler;
import core.visualgronk.LevelLoader;
import core.visualgronk.Texture;
import core.enums.BlockType;
import core.enums.ID;


public class Block extends GameObject{
	
	protected static int blockSize = 32;
	Random r = new Random();
	boolean drawBounds = true;
	boolean drawTexture = true;
	
	private BlockType type;

	
	public Block(int x, int y, ID id, ObjectHandler handler, BlockType type, String texPath) {
		super(x, y, id, handler, texPath);
		this.type = type;
		tex.getTextures(0, 0, 32, 32);
	}

	public void render(Graphics g) {
		if (!drawBounds) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.fillRect((int) x, (int) y, blockSize, blockSize);
		} else {
			g.setColor(Color.BLUE);
			g.drawRect((int) x, (int) y, blockSize, blockSize);
		}
		if(drawTexture && type == BlockType.Dirt) {
			g.drawImage(tex.block[0], (int) x, (int) y, null);
			
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, blockSize, blockSize);
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static void setBlockSize(int blockSize) {
		Block.blockSize = blockSize;
	}

}
