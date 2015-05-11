package core.display;

import java.awt.Rectangle;

import javax.swing.JFrame;

import core.Game;


public class Window {
	
	private static int visibleScreenX,visibleScreenY;
	private static JFrame frame;
	
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		frame.setBounds(50, 50, width-100, height-100);
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(Game.getRenderThread());
		frame.setVisible(true);
		visibleScreenX = (int) frame.getContentPane().getWidth();
		visibleScreenY = (int) frame.getContentPane().getHeight();
		
		frame.setAlwaysOnTop(true);
		
		game.startThreads();
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
	
	public static Rectangle getFrameBounds() {
		return new Rectangle((int) frame.getContentPane().getLocationOnScreen().getX(), 
				(int) frame.getContentPane().getLocationOnScreen().getY(), visibleScreenX, visibleScreenY);
	}
}
