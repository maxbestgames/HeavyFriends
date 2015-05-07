package core;

import core.input.KeyInput;

public class TickThread implements Runnable {
	
	boolean running = true;
	private KeyInput keyIn;
	//private Game game;

	public TickThread(KeyInput keyIn) {
		this.keyIn = keyIn;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;//per second
		double timePerTick = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int ticks = 0;

		while(running){
			long now = System.nanoTime();
			delta += ((now-lastTime)/timePerTick);
			lastTime = now;
			while(delta>=1){
				tick();
				ticks++;
				delta--;
			}

			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				Game.setTPS(ticks);
				ticks = 0;
			}
		}
		stop();
	}
	
	public synchronized void stop(){
		try{
			//tick.join();
			Thread.currentThread().join(1000);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tick(){
		
		Game.getWorldHandler().tick();
		Game.getCol().doCollision();
		Game.getRenderThread().getCamera().tick();
		Game.getRenderThread().getHud().tick();
		keyIn.update();
		
	}
	
	public void setRunningFalse() {
		running = false;
	}
}
