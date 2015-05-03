package core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Random;

import core.display.Window;
import core.enums.EntityID;
import core.enums.LevelID;
import core.handlers.WorldHandler;
import core.input.KeyInput;
import core.levels.TestRealm;


public class Game implements Runnable {
	
	public static int WIDTH, HEIGHT;
	private static int NUM_PLAYERS;
	private Thread render, tick;
	private boolean running = false;
	private static WorldHandler handler;
	private Random r;
	//private Spawner spawner;
	private static KeyInput keyInput;
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
		
		keyInput = new KeyInput(handler);
		
		//TODO make spawners work
		
		currentLevel = LevelID.TestRealm;
		String levelFilePath = "assets/maps/testMap.png";
		handler.addLevel(new TestRealm(LevelID.TestRealm, levelFilePath));
		NUM_PLAYERS++;
		
		rt = new RenderThread(this, keyInput);
		tt = new TickThread(this, keyInput);
		
		getRenderThread().getCamera().setCamCenter(handler.getPlayers().getPlayer(EntityID.Player));
		
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
		running = true;
	}
	
	public synchronized void startRender(){
		tick =  new Thread(tt);
		tick.start();
		running = true;
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
	
	public RenderThread getRenderThread() {
		return rt;
	}
	
	public TickThread getTickThread() {
		return tt;
	}

	public void run() {}
}
