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
import core.gameobjects.Player;
import core.handlers.CollisionHandler;
import core.handlers.WorldHandler;
import core.input.KeyInput;
import core.input.MouseInput;
import core.levels.testRealm.TestRealm;


public class Game {
	
	public static int WIDTH, HEIGHT;
	private Thread render, tick, lightTick;
	private static Thread fft;
	private static Thread soundCap;
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
	private static SoundCaptureThread sct;
	private static MoodLightingTickThread lt;
	private static FastFourierTransformThread fftt;
	
	
	public static void main(String[] args) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight();
		
		
		//new TestMic();
	
		
		new Game();
	}
	
	public Game() {
		
		sct = new SoundCaptureThread();
		lt = new MoodLightingTickThread();
		fftt = new FastFourierTransformThread();
		
		r = new Random();
		
		handler = new WorldHandler();
		
		col = new CollisionHandler();
		
		keyInput = new KeyInput(handler);
		mouseInput = new MouseInput(keyInput);
		
		handler.addPlayer(new Player(0, 0, EntityID.Player));
		
		//TODO make spawners work
		
		
		
		
		currentLevel = LevelID.TestRealm;
		String levelFilePath = "assets/maps/testMap3.png";
		TestRealm tr = new TestRealm(LevelID.TestRealm, levelFilePath);
		handler.addLevel(tr);
		handler.getLevel(LevelID.TestRealm).getObjHandler().addObject(tr.getBL1());
		//handler.getLevel(LevelID.TestRealm).getObjHandler().addObject(tr.getBL2());
		
		
		
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
	
	public void startThreads() {
		startRender();
		startTick();
		startSC();
		startLT();
		startFFTT();
	}
	
	public synchronized void startTick(){
		render =  new Thread(rt);
		render.setName("Render");
		render.start();
		//running = true;
	}
	
	public synchronized void startRender(){
		tick =  new Thread(tt);
		tick.setName("Tick");
		tick.start();
		//running = true;
	}
	
	public synchronized void startLT(){
		lightTick = new Thread(lt);
		lightTick.setName("MoodLightingTicker");
		lightTick.start();
	}
	
	public synchronized void startFFTT(){
		fft = new Thread(fftt);
		fft.setName("FFFFFFTTTTTTTTTTTTTT");
		fft.start();
	}
	
	public synchronized void startSC(){
		soundCap = new Thread(sct);
		soundCap.setName("I'm Listening...");
		soundCap.start();
	}
	
	public static void stop(){
		rt.setRunningFalse();
		tt.setRunningFalse();
		sct.setRunningFalse();
		fftt.setRunningFalse();
		lt.setRunningFalse();
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
	
	public static SoundCaptureThread getCaptureThread() {
		return sct;
	}
	
	public static FastFourierTransformThread getFFTT() {
		return fftt;
	}
	
	public static Thread getFFT() {
		return fft;
	}

	public static CollisionHandler getCol() {
		return col;
	}

	public static KeyInput getKeyInput() {
		return keyInput;
	}

}
