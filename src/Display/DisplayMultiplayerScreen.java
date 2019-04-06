package Display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class DisplayMultiplayerScreen {
	private JFrame frame;
	private Canvas canvas;

	private URL iconURL;

	private String title;
	private int width, height;

	public DisplayMultiplayerScreen(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;


		createDisplay();

	}

	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(new Point(50,100));
		frame.setVisible(false);
		frame.setBackground(Color.black);

		try {
			frame.setIconImage(ImageIO.read(new File("res/Sheets/item/SL1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.black);

		frame.add(canvas);
		frame.pack();
	}


	public Canvas getCanvas(){
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}

}
