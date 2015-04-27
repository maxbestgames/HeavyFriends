package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import core.enums.ID;
import core.enums.LevelID;
import core.gameobjects.Player;
import core.handlers.WorldHandler;
import core.levels.Spawner;


public class Game extends Canvas implements Runnable {
	
	static int WIDTH, HEIGHT;
	private static int NUM_PLAYERS;
	private Thread thread;
	private boolean running = false;
	private WorldHandler handler;
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
		
		keyInput = new KeyInput(handler);
		this.addKeyListener(keyInput);
		
		new Window(WIDTH, HEIGHT,"Test game", this);
		hud = new HUD(handler);
		spawner = new Spawner(handler, hud);
		
		r = new Random();
		
		//TODO make spawners work
		//handler.createLevel();
		
		handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
		NUM_PLAYERS++;
		handler.addObject(new Player(WIDTH/2+50, HEIGHT/2+46, ID.Player2, handler));
		NUM_PLAYERS++;
		
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
		
		g.setColor(Color.BLACK);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		handler.render(g);
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
	

}
