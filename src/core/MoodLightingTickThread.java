package core;

import java.io.IOException;

public class MoodLightingTickThread implements Runnable {
	
	boolean running = true;
	
	public MoodLightingTickThread() {
		
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
			
			if (delta >= 2) { // half a tick behind
				System.out.println("FFT ticks skipped");
				delta = 1.1;
			}
			while(delta>=1){
				
				//System.out.println("bin 0: " +Game.getCaptureThread().getOut().size());
				//System.out.println("bin 1: "+Game.getCaptureThread().getOut2().size());
				
				SoundCaptureThread.flipBin(); //starts at 0
				
				Game.getFFTT().startGeneration();
				
				//  ->Clear bin after analysis
				
				ticks++;
				delta--;
			}
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				//Game.setTPS(ticks);
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
	
	public void setRunningFalse() {
		running = false;
	}
}
