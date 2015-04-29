package core.gameobjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
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

	
	public Block(int x, int y, ID id, BlockType type) {
		super(x, y, id);
		this.type = type;
		String blockFileTexture = "assets"+File.separator+"texture"+File.separator+"blakcl.png";
		tex = new Texture(blockFileTexture);
		tex.getTextures(0, 0, 32, 32);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (!drawBounds) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.fillRect((int) x, (int) y, blockSize, blockSize);
		} else {
			g.setColor(Color.WHITE);
			g.drawRect((int) x, (int) y, blockSize, blockSize);
		}
		if(drawTexture && type == BlockType.Dirt) {
			
			g2d.setColor(new Color(0, 0, 0, 0));
			g2d.setComposite(AlphaComposite.Src);
			g2d.drawImage(tex.block[0], (int) x, (int) y, null);
			//g2d.setComposite(AlphaComposite.Src);
			g2d.setColor(Color.WHITE);
			
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
