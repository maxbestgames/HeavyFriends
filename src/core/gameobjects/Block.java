package core.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import core.Game;
import core.display.Camera;
import core.display.Window;
import core.enums.BlockType;
import core.enums.EntityID;
import core.visualgronk.Animation;
import core.visualgronk.Texture;


public abstract class Block extends GameObject {
	
	protected static int blockSize = 32;
	protected Random r = new Random();
	
	private BlockType type;

	protected BufferedImage blockTexture;
	protected Animation anim;
	
	public Block(int x, int y, EntityID id, BlockType type, String texPath) {
		super(x, y, id);
		this.type = type;
		tex = new Texture(texPath, 32, 32);
		blockTexture = tex.getSprite(0, 0);
		
	}

	public void render(Graphics g) {
		if (( Math.abs( Math.abs( Camera.getCamCenter().getX() )-Math.abs( getX() ) ) < Window.getVisibleScreenX()/2 + 400)
			&& ( Math.abs( Math.abs( Camera.getCamCenter().getY() )-Math.abs( getY() ) ) < Window.getVisibleScreenY()/2 + 400)) {
		
			Graphics2D g2d = (Graphics2D) g;
			if (!drawBoundingBoxes) {
				//g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				//g.fillRect((int) x, (int) y, blockSize, blockSize);
			} else {
				if (!Game.getMouseBounds().intersects(getBounds())) {
					g.setColor(Color.WHITE);
					g.drawRect((int) x, (int) y, blockSize, blockSize);
				} else {
					g.setColor(Color.YELLOW);
					g.fillRect((int) x, (int) y, blockSize, blockSize);
				}
			}
			
			if(drawTextures && type == BlockType.Dirt) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Cobblestone) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Grass) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Stonebrick) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Claybrick) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Wood) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			if(drawTextures && type == BlockType.Chain) g2d.drawImage(blockTexture, (int) x, (int) y, null);
			
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
