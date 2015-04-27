package core;

import java.awt.Canvas;

import javax.swing.JFrame;


public class Window extends Canvas {
	
	public static int visibleScreenX,visibleScreenY;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setBounds(50, 50, width-100, height-100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		visibleScreenX = (int) frame.getContentPane().getWidth();
		visibleScreenY = (int) frame.getContentPane().getHeight();
		
		//frame.setAlwaysOnTop(true);
		
		game.start();
		
	}

	public static int getVisibleScreenX() {
		return visibleScreenX;
	}

	public static void setVisibleScreenX(int visibleScreenX) {
		Window.visibleScreenX = visibleScreenX;
	}

	public static int getVisibleScreenY() {
		return visibleScreenY;
	}

	public static void setVisibleScreenY(int visibleScreenY) {
		Window.visibleScreenY = visibleScreenY;
	}
}
