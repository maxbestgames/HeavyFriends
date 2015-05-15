package core.visualgronk;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Texture {
	
	SpriteSheet bs;
	private BufferedImage block_sheet = null;
	
	public BufferedImage[][] sprite;
	
	public Texture(String path, int spriteWidth, int spriteHeight) {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		block_sheet = loader.loadImage(path);
	
		bs = new SpriteSheet(block_sheet);
		
		fixAlpha();
		
		getSprites(spriteWidth, spriteHeight);
	}
	
	public Texture(BufferedImage bf) {
		block_sheet = bf;
	}
	
	public void getSprites(int width, int height) {
		sprite = new BufferedImage[(int) (bs.getWidth()/width)][(int) (bs.getHeight()/height)];
		
		for (int i = 0; i < bs.getHeight()/height; i++) { //Run down image
			for (int j = 0; j < bs.getWidth()/width; j++ ) { //run across image
				sprite[j][i] = bs.grabImage(j, i, width, height); //TODO test to make sure that I, J are correct way around.
			}
		}
	}
	
	public void fixAlpha() {
		//TODO
	}
	
	public BufferedImage getSprite(int col, int row) {
		return sprite[col][row];
	}
	
	public static BufferedImage flipHoriz(BufferedImage image) {
	    BufferedImage tempImage = deepCopy(image);
		
		for (int i=0;i<image.getWidth();i++)
	        for (int j=0;j<image.getHeight()/2;j++)
	        {
	            int tmp = image.getRGB(i, j);
	            tempImage.setRGB(i, j, image.getRGB(i, image.getHeight()-j-1));
	            tempImage.setRGB(i, image.getHeight()-j-1, tmp);
	        }
		return tempImage;
	}
	
	public static BufferedImage flipVert(BufferedImage image) {
		BufferedImage tempImage = deepCopy(image);
		
		for (int i=0;i<image.getWidth()/2;i++)
	        for (int j=0;j<image.getHeight();j++)
	        {
	            int tmp = image.getRGB(i, j);
	            tempImage.setRGB(i, j, image.getRGB(image.getWidth()-i-1, j));
	            tempImage.setRGB(image.getWidth()-i-1, j, tmp);
	        }
		return tempImage;
	}
	
	public BufferedImage rotate(BufferedImage img, float angle, float rotX, float rotY) {
		AffineTransform transform = new AffineTransform();
	    transform.rotate(angle, rotX, rotY);
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    return op.filter(img, null);
	}
	
	protected static BufferedImage deepCopy(BufferedImage bi) {
		BufferedImage b = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		Graphics g = b.getGraphics();
		g.drawImage(bi, 0, 0, null);
		g.dispose();
		return b;
	}
	
	

}
