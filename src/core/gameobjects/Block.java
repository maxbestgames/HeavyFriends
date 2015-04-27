package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.handlers.Handler;
import core.enums.ID;


public class Block extends GameObject{
	
	private Handler handler;
	protected static int blockSize = 32;
	
	public Block(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, blockSize, blockSize);
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
