package core.visualgronk;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed;
	private int frames;
	
	private int index = 0; 
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	public Animation(int speed, BufferedImage... args) {
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
		
	}
	
	public void runAnimation() {
		index++;
		if(index > speed) {
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame() {
		for(int i = 0; i < frames; i++) {
			if(count == i ){
				currentImg = images[i];
			}
		}
		
		count++;
		
		if(count > frames) {
			count = 0;
		}
		
	}
	
	/**
	 * Use getAnimation() instead and draw using the caller class.
	 * 
	 * @param g Graphics object used to draw the object
	 * @param x position on the x axis
	 * @param y position on the y axis
	 */
	@Deprecated
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x, y, null);
	}
	
	public BufferedImage getAnimation() {
		return currentImg;
	}
	
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
}
