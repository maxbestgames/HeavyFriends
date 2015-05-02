package core.visualgronk;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

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
	
	public void getSprites(int width, int height) {
		sprite = new BufferedImage[(int) (bs.getWidth()/width)][(int) (bs.getHeight()/height)];
		
		for (int i = 0; i < bs.getHeight()/height; i++) { //Run down image
			for (int j = 0; j < bs.getWidth()/width; j++ ) { //run across image
				sprite[j][i] = bs.grabImage(j, i, width, height); //TODO test to make sure that I, J are correct way around.
			}
		}
	}
	
	public void fixAlpha() {
		
	}
	
	public BufferedImage getSprite(int col, int row) {
		return sprite[col][row];
	}
	
	public BufferedImage flipHoriz(BufferedImage image) {
	    BufferedImage tempImage = image;
		
		for (int i=0;i<image.getWidth();i++)
	        for (int j=0;j<image.getHeight()/2;j++)
	        {
	            int tmp = image.getRGB(i, j);
	            tempImage.setRGB(i, j, image.getRGB(i, image.getHeight()-j-1));
	            tempImage.setRGB(i, image.getHeight()-j-1, tmp);
	        }
		return tempImage;
	}
	
	public BufferedImage flipVert(BufferedImage image) {
		BufferedImage tempImage = image;
		
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
	
	

}
