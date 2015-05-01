package core.gameobjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import core.Game;
import core.display.Window;
import core.enums.BlockType;
import core.enums.EntityID;
import core.visualgronk.Texture;


public class Block extends GameObject{
	
	protected static int blockSize = 32;
	Random r = new Random();
	boolean drawBounds = true;
	boolean drawTexture = true;
	
	private BlockType type;

	
	public Block(int x, int y, EntityID id, BlockType type) {
		super(x, y, id);
		this.type = type;
		String blockFileTexture = "assets/texture/testBlock.png";
		tex = new Texture(blockFileTexture, 32, 32);
	}

	public void render(Graphics g) {
		if ((Math.abs(Math.abs(Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getX())-Math.abs(getX())) < Window.getVisibleScreenX()/2 + 400)
			&& (Math.abs(Math.abs(Game.getWorldHandler().getPlayers().getPlayer(EntityID.Player).getY())-Math.abs(getY())) < Window.getVisibleScreenY()/2 + 400)) {
		
			Graphics2D g2d = (Graphics2D) g;
			if (!drawBounds) {
				g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
				g.fillRect((int) x, (int) y, blockSize, blockSize);
			} else {
				g.setColor(Color.WHITE);
				g.drawRect((int) x, (int) y, blockSize, blockSize);
			}
			if(drawTexture && type == BlockType.Dirt) {
				g2d.drawImage(tex.getSprite(0, 0), (int) x, (int) y, null);
			}
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
