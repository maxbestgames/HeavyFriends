package core.sonicmadness;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class LineIn {

	public LineIn() {

		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info: mixerInfos){
			Mixer m = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = m.getSourceLineInfo();
			for (Line.Info lineInfo:lineInfos){
				System.out.println (info.getName()+"---"+lineInfo);
				Line line = null;
				try {
					line = m.getLine(lineInfo);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				System.out.println("\t-----"+line);
			}
			lineInfos = m.getTargetLineInfo();
			for (Line.Info lineInfo:lineInfos){
				System.out.println (m+"---"+lineInfo);
				Line line = null;
				try {
					line = m.getLine(lineInfo);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				System.out.println("\t-----"+line);

			}
			
			AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		    try {
				TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    

		}
	}
}
/*
byte tempBuffer[] = new byte[10000];
ByteArrayOutputStream out = new ByteArrayOutputStream();
counter = 20;

// Microphone
while (counter != 0) {
 int count = line.read(tempBuffer, 0, tempBuffer.length);
 if (count > 0) {
  out.write(tempBuffer, 0, count);
 }
 counter--;
}
out.close();



 */