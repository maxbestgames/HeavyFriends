package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import core.enums.ID;
import core.enums.LevelID;
import core.gameobjects.Player;
import core.handlers.WorldHandler;
import core.levels.Spawner;
import core.levels.TestRealm;


public class Game extends Canvas implements Runnable {
	
	public static int WIDTH, HEIGHT;
	private static int NUM_PLAYERS;
	private Thread thread;
	private boolean running = false;
	private static WorldHandler handler;
	private Camera cam;
	private Random r;
	private HUD hud;
	//private Spawner spawner;
	private KeyInput keyInput;
	private static LevelID current;
	
	public static void main(String[] args) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight();
		
		NUM_PLAYERS = 0;
		
		new Game();
	}
	
	public Game() {
		
		handler = new WorldHandler();
		cam = new Camera(0, 0);
		
		keyInput = new KeyInput(handler);
		this.addKeyListener(keyInput);
		
		//TODO make spawners work
		handler.addPlayer( new Player(WIDTH/2, 100, ID.Player ) );
		//handler.addPlayer(new Player(WIDTH/2, HEIGHT/2, ID.Player, ));
		NUM_PLAYERS++;
		//handler.addPlayer( new Player(WIDTH/2+50, HEIGHT/2+46, ID.Player2 ) );
		//NUM_PLAYERS++;
		
		TestRealm level1 = new TestRealm(LevelID.TestRealm, "assets\\maps\\testMap.png");
		current = LevelID.TestRealm;
		handler.addLevel(level1);
		
		
		new Window(WIDTH, HEIGHT,"Test game", this);
		hud = new HUD(handler);
		
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 40.0;
		double timePerTick = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += ((now-lastTime)/timePerTick);
			lastTime = now;
			while(delta>=1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println("fps-" + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		
		handler.tick();
		cam.tick(handler.getPlayers().getPlayer(ID.Player));
		hud.tick();
		
		keyInput.update();
		
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d =(Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		hud.render(g);

		g.dispose();
		bs.show();
	
	}
	
	public static int clamp(int var, int min, int max){
		if(var<=min){
			return var = min;
		}else if(var>=max)
			return var = max;
		else 
			return var;
	}
	
	public static float clamp(float var, float min, float max){
		if(var<=min){
			return var = min;
		}else if(var>=max)
			return var = max;
		else 
			return var;
	}
	
	public static int getNumPlayers() {
		return NUM_PLAYERS;
	}
	
	public synchronized void start(){
		thread =  new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LevelID getCurrentLevel() {
		return current;
	}
	
	public static WorldHandler getWorldHandler() {
		return handler;
	}
	

}
