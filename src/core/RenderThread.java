package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import core.display.Camera;
import core.display.HUD;
import core.handlers.WorldHandler;
import core.input.KeyInput;

public class RenderThread extends Canvas implements Runnable {
	
	boolean running = true;
	private Camera cam;
	private HUD hud;
	private KeyInput keyIn;
	
	public RenderThread(Game game, KeyInput keyIn) {

		hud = new HUD(Game.getWorldHandler());
		this.keyIn = keyIn;
		
		cam = new Camera(0, 0);
		this.addKeyListener(keyIn);
		
	}
	
	public void run() {
		this.requestFocus();
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				Game.setFPS(frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void stop(){
		try{
			//tick.join();
			Thread.currentThread().join(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d =(Graphics2D) g;
		
		g.setColor(Color.BLACK); // background colour
		//g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		
		//g2d.scale(2, 2);
		g2d.translate(cam.getX(), cam.getY());
		Game.getWorldHandler().render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		//g2d.scale(-2, -2);
		
		
		hud.render(g);

		g.dispose();
		g2d.dispose();
		if (running)
			bs.show();
	
	}
	
	public void setRunningFalse() {
		running = false;
	}
	
	public HUD getHud() {
		return hud;
	}
	
	public Camera getCamera() {
		return cam;
	}
}
