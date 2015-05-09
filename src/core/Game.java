package core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.Random;

import core.display.Camera;
import core.display.Window;
import core.enums.EntityID;
import core.enums.LevelID;
import core.handlers.CollisionHandler;
import core.handlers.WorldHandler;
import core.input.KeyInput;
import core.input.MouseInput;
import core.levels.testRealm.TestRealm;


public class Game implements Runnable {
	
	public static int WIDTH, HEIGHT;
	private static int NUM_PLAYERS;
	private Thread render, tick;
	//private boolean running = false;
	private static WorldHandler handler;
	private static CollisionHandler col;
	//private static MovementHandler mov;
	
	@SuppressWarnings("unused")
	private Random r;
	//private Spawner spawner;
	private static KeyInput keyInput;
	private static MouseInput mouseInput;
	private static LevelID currentLevel;
	private static int fps;
	private static int tps;
	
	private static RenderThread rt;
	private static TickThread tt;
	
	public static void main(String[] args) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight();
		
		NUM_PLAYERS = 0;
		new Game();
	}
	
	public Game() {
		
		r = new Random();
		
		handler = new WorldHandler();
		
		col = new CollisionHandler();
		
		keyInput = new KeyInput(handler);
		mouseInput = new MouseInput(keyInput);
		
		
		//TODO make spawners work
		
		
		
		currentLevel = LevelID.TestRealm;
		String levelFilePath = "assets/maps/testMap3.png";
		TestRealm tr = new TestRealm(LevelID.TestRealm, levelFilePath);
		handler.addLevel(tr);
		NUM_PLAYERS++;
		handler.getCurrentLevelObjectHandler().addObject(tr.getBL1());
		
		
		
		rt = new RenderThread(this, keyInput, mouseInput);
		tt = new TickThread(keyInput);
		
		//getRenderThread().getCamera();
		Camera.setCamCenter(handler.getPlayers().getPlayer(EntityID.Player));
		
		new Window(WIDTH, HEIGHT,"Heavy Friends", this);
		
	}
	
	public static int clamp(int var, int min, int max){
		if (var<=min) return var = min;
		else if (var>=max) return var = max;
		else return var;
	}
	
	public static float clamp(float var, float min, float max){
		if (var<=min) return var = min;
		else if (var>=max) return var = max;
		else return var;
	}
	
	public static Rectangle getMouseBounds() {
		return new Rectangle((int) (MouseInfo.getPointerInfo().getLocation().getX() - Window.getFrameBounds().getX() - Camera.getX() - 1), 
				(int) (MouseInfo.getPointerInfo().getLocation().getY() - Window.getFrameBounds().getY() - Camera.getY() - 2 ), 4, 4 );
	}
	
	public static int getNumPlayers() {
		return NUM_PLAYERS;
	}
	
	public void startThreads() {
		startRender();
		startTick();
	}
	
	public synchronized void startTick(){
		render =  new Thread(rt);
		render.start();
		//running = true;
	}
	
	public synchronized void startRender(){
		tick =  new Thread(tt);
		tick.start();
		//running = true;
	}
	
	public static void stop(){
		rt.setRunningFalse();
		tt.setRunningFalse();
	}

	public static LevelID getCurrentLevel() {
		return currentLevel;
	}
	
	public static WorldHandler getWorldHandler() {
		return handler;
	}
	
	public static int getFPS() {
		return fps;
	}
	
	public static void setFPS(int renderFps) {
		fps = renderFps;
	}
	
	public static int getTPS() {
		return tps;
	}
	
	public static void setTPS(int tickTPS) {
		tps = tickTPS;
	}
	
	public static RenderThread getRenderThread() {
		return rt;
	}
	
	public TickThread getTickThread() {
		return tt;
	}

	public void run() {}

	public static CollisionHandler getCol() {
		return col;
	}

	public static KeyInput getKeyInput() {
		return keyInput;
	}

}
