import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable {
	
	static int WIDTH, HEIGHT;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawner spawner;
	KeyInput keyInput;
	
	public static void main(String[] args) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		WIDTH = gd.getDisplayMode().getWidth();
		HEIGHT = gd.getDisplayMode().getHeight();
		
		
		new Game();
	}
	
	public Game() {
		
		handler = new Handler();
		
		keyInput = new KeyInput(handler);
		this.addKeyListener(keyInput);
		
		new Window(WIDTH, HEIGHT,"Test game", this);
		hud = new HUD();
		spawner = new Spawner(handler, hud);
		
		
		r = new Random();
		
		for(int i = 0; i<10; i++){
			//handler.addObject(new BasicEnemy(r.nextInt(250), r.nextInt(250), ID.BasicEnemy, handler));

		}
		handler.createLevel();
		handler.addObject(new Player(WIDTH/2+50, HEIGHT/2+46, ID.Player2, handler));
		handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
		
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
	

}
