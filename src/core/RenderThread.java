package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.PriorityQueue;

import core.display.Camera;
import core.display.HUD;
import core.gameobjects.GameObject;
import core.input.KeyInput;
import core.input.MouseInput;

public class RenderThread extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 2897736761695538217L;
	boolean running = true;
	private Camera cam;
	private HUD hud;
	private KeyInput keyIn;
	private MouseInput mouseIn;
	
	private static PriorityQueue<GameObject> renderPrique;
	
	public RenderThread(Game game, KeyInput keyIn, MouseInput mouseIn) {

		hud = new HUD(Game.getWorldHandler());
		this.keyIn = keyIn;
		this.mouseIn = mouseIn;
		
		renderPrique = new PriorityQueue<GameObject>();
		
		cam = new Camera(0, 0);
		this.addKeyListener(this.keyIn);
		this.addMouseListener(this.mouseIn);
		this.addMouseMotionListener(this.mouseIn);
		this.addMouseWheelListener(this.mouseIn);
		
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
		
		//g2d.scale(4, 4);
		g2d.translate(Camera.getX(), Camera.getY());
		
		Game.getWorldHandler().render(g);
		
		while (!renderPrique.isEmpty()) {
			GameObject tempObject = renderPrique.poll();
			tempObject.render(g2d);
		}
		
		if(GameObject.isDrawBoundingBoxes())
			g2d.draw(Game.getMouseBounds());
		
		g2d.translate(-Camera.getX(), -Camera.getY());
		//g2d.scale(-4, -4);
		
		
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
	
	public static PriorityQueue<GameObject> getRenderQueue() {
		return renderPrique;
	}

}
