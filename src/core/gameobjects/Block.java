package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import core.handlers.ObjectHandler;
import core.enums.ID;


public class Block extends GameObject{
	
	protected static int blockSize = 32;
	Random r = new Random();
	
	public Block(int x, int y, ID id, ObjectHandler handler) {
		super(x, y, id, handler);
	}

	public void render(Graphics g) {
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fillRect((int) x, (int) y, blockSize, blockSize);
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
