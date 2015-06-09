package core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SoundCaptureThread implements Runnable {

	TargetDataLine line;

	//Thread thread;

	String errStr;

	AudioInputStream audioInputStream;

	ByteArrayOutputStream out;
	ByteArrayOutputStream out2;
	
	byte[] data;
	
	int numBytesRead;
	int frameSizeInBytes;
	int bufferLengthInFrames;
	int bufferLengthInBytes;

	boolean running = true;

	static int bin = 0;

	public SoundCaptureThread() {
		//duration = 0;
		audioInputStream = null;

		// define the required attributes for our line,
		// and make sure a compatible line is supported.

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = 44100.0f;
		int channels = 2;
		//int frameSize = 4;
		int sampleSize = 16;
		boolean bigEndian = true;

		AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);

		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			shutDown("Line matching " + info + " not supported.");
			return;
		}

		// get and open the target data line for capture.

		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
		} catch (LineUnavailableException ex) {
			shutDown("Unable to open the line: " + ex);
			return;
		} catch (SecurityException ex) {
			shutDown(ex.toString());
			//JavaSound.showInfoDialog();
			return;
		} catch (Exception ex) {
			shutDown(ex.toString());
			return;
		}

		// capture data to array/s
		out = new ByteArrayOutputStream();
		out2 = new ByteArrayOutputStream();
		frameSizeInBytes = format.getFrameSize();
		bufferLengthInFrames = line.getBufferSize() / 16;
		bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		data = new byte[bufferLengthInBytes];
		
		line.start();
	}

	private void shutDown(String message) {
		//System.out.println("Broken out");

		// we reached the end of the stream.
		// stop and close the line.
		try {
		line.stop();
		line.close();
		} catch(NullPointerException e) {
			return;
		}
		line = null;

		// stop and close the output stream
		try {
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if ((errStr = message) != null) {
			//thread = null;
			//playB.setEnabled(true);
			//captB.setText("Record");
			running = false;
			System.err.println(errStr);
		}
	}

	public void run() {

		while (running && line != null) {

			if (bin == 0) {
				if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1)
					break;

				out.write(data, 0, numBytesRead);
			}
			else {
				if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1)
					break;

				out2.write(data, 0, numBytesRead);
			}
			
		}
	}

	public ByteArrayOutputStream getOut() {
		return out;
	}

	public void setOut(ByteArrayOutputStream out) {
		this.out = out;
	}

	public ByteArrayOutputStream getOut2() {
		return out2;
	}

	public void setOut2(ByteArrayOutputStream out2) {
		this.out2 = out2;
	}

	public void resetOut() {
		out.reset();
	}

	public void resetOut2() {
		out2.reset();
	}

	public byte[] getOutAsByteArray() {
		return out.toByteArray();
	}

	public byte[] getOut2AsByteArray() {
		return out2.toByteArray();
	}

	public static int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		SoundCaptureThread.bin = bin;
	}

	public static void flipBin() {
		if (bin == 0) bin = 1;
		else if (bin == 1) bin = 0;
		else throw new RuntimeException("SoundCaptureThread in illegal state");
	}

	public void setRunningFalse() {
		running = false;
	}

}