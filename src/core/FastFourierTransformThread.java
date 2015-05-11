package core;

import java.nio.ByteBuffer;

public class FastFourierTransformThread implements Runnable {

	boolean running = true;
	boolean generateFFT = false;

	double[] fftOutput;

	public FastFourierTransformThread() {

	}

	public void run() {
		while (running) {
			if (generateFFT) {
				generateFFT = false;
				generateFFT();
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}
			}
		}
		
		
	}


	public void generateFFT() {
		double[] pcmAsFloats = null;
		

		if (Game.getCaptureThread().getOut() != null && Game.getCaptureThread().getOut2() != null) {

			if (SoundCaptureThread.getBin() == 0) { // do bin 1
				pcmAsFloats = floatMe(shortMe(Game.getCaptureThread().getOut2AsByteArray()));
				
				int arraySize = 0;
				if (pcmAsFloats.length != 0) {
					while (arraySize < pcmAsFloats.length) {
						int counter = 0;
						arraySize = (int) Math.pow(2, counter);
						counter++;
					}
				}
				
				double[] floatTo2 = new double[arraySize];
				double[] imaginaryZeros = new double[floatTo2.length];
				
				for (int i = 0; i < arraySize; i++) {
					if(i < pcmAsFloats.length) {
						floatTo2[i] = pcmAsFloats[i];
					} else {
						floatTo2[i] = pcmAsFloats[pcmAsFloats.length-i-1];
					}
					imaginaryZeros[i] = 0;
				}
				fftOutput = FFTbase.fft(floatTo2, imaginaryZeros, true);

				System.out.println("bin 1 length " + fftOutput.length);
				
				
				//TODO
				//Game.getCaptureThread().out2.reset();

			} else { // do bin 0
				pcmAsFloats = floatMe(shortMe(Game.getCaptureThread().getOutAsByteArray()));

				double[] floatTo2 = new double[32 - Integer.numberOfLeadingZeros(pcmAsFloats.length - 1)];
				double[] imaginaryZeros = new double[floatTo2.length];

				int arraySize = 0;
				if (pcmAsFloats.length != 0) {
					while (arraySize < pcmAsFloats.length) {
						int counter = 0;
						arraySize = (int) Math.pow(2, counter);
						counter++;
					}
				}

				for (int i = 0; i < arraySize; i++) {
					if(i < pcmAsFloats.length) {
						floatTo2[i] = pcmAsFloats[i];
					} else {
						floatTo2[i] = pcmAsFloats[pcmAsFloats.length-i-1];
					}
					imaginaryZeros[i] = 0;
				}
				fftOutput = FFTbase.fft(floatTo2, imaginaryZeros, true);

				System.out.println("bin 0 length " + fftOutput.length);

				//TODO
				//Game.getCaptureThread().out.reset();
			}
		}
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

	public static double[] floatMe(short[] pcms) {
		double[] floaters = new double[pcms.length];
		//System.out.println("Creating double array of lenght " + floaters.length);
		for (int i = 0; i < pcms.length; i++) {
			floaters[i] = pcms[i];
		}
		return floaters;
	}

	public static short[] shortMe(byte[] bytes) {
		short[] out = new short[bytes.length / 2]; // will drop last byte if odd number
		//System.out.println("Creating short array of lenght " + out.length);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		for (int i = 0; i < out.length; i++) {
			out[i] = bb.getShort();
		}
		return out;
	}

	public void startGeneration() {
		generateFFT = true;
	}
}
