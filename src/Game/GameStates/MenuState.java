package Game.GameStates;


import Display.DisplayMultiplayerScreen;
import Display.DisplayScreen;
import Display.UI.UIStringButton;
import Game.World.MapBuilder;
import Input.KeyManager;
import Input.MouseManager;
import Main.Handler;
import Resources.Images;
import Display.UI.UIAnimationButton;
import Display.UI.UIImageButton;
import Display.UI.UIManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {
	private boolean partOne = false;
	private boolean partTwo = false;
	private boolean partThree = false;
	public UIManager uiManager;
	private int background;
	private String mode= "Menu";


	private DisplayScreen display;
	private DisplayMultiplayerScreen display2;
	
	
	private int[] str={83,117,98,32,116,111,32,80,101,119,100,115};
	private String str2="";


	private BufferStrategy bs;
	private Graphics g;
	private UIAnimationButton but;
	private boolean creatingMap=false;
	public int GridWidthPixelCount,GridHeightPixelCount,DiplayHeight,DisplayWidth;
	public int GridPixelsize;
	int colorSelected = MapBuilder.boundBlock;
	Color[][] blocks;
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private boolean clicked = true;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);
		background = new Random().nextInt(9);

		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();
		GridPixelsize = 20;
		GridHeightPixelCount = DiplayHeight/GridPixelsize;
		GridWidthPixelCount = DisplayWidth/GridPixelsize;
		blocks = new Color[GridWidthPixelCount][GridHeightPixelCount];
		keyManager = handler.getGame().keyManager;
		mouseManager = new MouseManager();
		for (int i:str) { str2+=(char)i;}
		this.but = new UIAnimationButton(handler.getWidth() - (handler.getWidth()/ 8),(handler.getHeight()/0b1100),32, 32 , Images.item, () -> {
			if(but.getdraw() && !handler.isMarioInMap()) {handler.setMap(handler.getGame().getMap());
			handler.getGame().getMusicHandler().pauseBackground();
			handler.getGame().getMusicHandler().play("Megalovania");
			State.setState(handler.getGame().gameState);}}, this.handler);
		uiManager.addObjects(new UIImageButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8), 128, 64, Images.butstart, () -> {
			if(!handler.isMarioInMap()) {
				mode = "Select";
			}
		}));
	}

	@Override
	public void tick() {
		if(!creatingMap) {
			handler.getMouseManager().setUimanager(uiManager);
			uiManager.tick();
			if (mode.equals("Select")) {
				mode = "Selecting";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);



				//1Player
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, handler.getHeight() / 2 + (handler.getHeight() / 10), 128, 64, "1 Player", () -> {
					if(!handler.isMarioInMap()) {
						mode = "SelectingMode";

					}
				}, handler,Color.BLACK));

				//2Players
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (64), 128, 64, "2 Players", () -> {
					if(!handler.isMarioInMap()) {
						mode = "SelectingMode";
						State.setMultiplayer(true);
						handler.getGame().display2.getFrame().setVisible(true);


					}
				}, handler,Color.BLACK));


				uiManager.addObjects(this.but);
			}
			if (mode.equals("SelectingMode")) {
				mode = "Selecting";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);

				//New Map
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) - (64), 128, 64, "New Map", () -> {
					if(!handler.isMarioInMap()) {
						mode = "Menu";
						initNew("New Map Creator", handler);
					}
				}, handler,Color.BLACK));


				uiManager.addObjects(new UIStringButton((handler.getWidth() / 2 - 64)+128, handler.getHeight() / 2 + (handler.getHeight() / 10), 128, 64, "Hello World", () -> {
					if(!handler.isMarioInMap()) {
						mode = "Menu";
						if (State.isMultiplayer()) {
							handler.setMap(MapBuilder.createMap(Images.helloworld_mult_Map, handler));
						}
						else {
							handler.setMap(MapBuilder.createMap(Images.helloworldMap, handler));	
						}
						State.setState(handler.getGame().CharChoosingP1);
					}
				}, handler,Color.BLACK));
				
				
				
				uiManager.addObjects(new UIStringButton((handler.getWidth() / 2 - 64)+128, handler.getHeight() / 2 + (handler.getHeight() / 10)+64, 128, 64, "Basic Race", () -> {
					if(!handler.isMarioInMap()) {
						mode = "Menu";
						if (State.isMultiplayer()) {
							handler.setMap(MapBuilder.createMap(Images.marioAndLuigiRace, handler));
						}
						else {
							handler.setMap(MapBuilder.createMap(Images.marioRaceSolo, handler));	
						}
						State.setState(handler.getGame().CharChoosingP1);
					}
				}, handler,Color.BLACK));



				//testMap1
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, handler.getHeight() / 2 + (handler.getHeight() / 10), 128, 64, "Map 1", () -> {
					if(!handler.isMarioInMap()) {
						mode = "Menu";
						if(!State.isMultiplayer()) {
							handler.setMap(MapBuilder.createMap(Images.testMap, handler));
						}else {
							handler.setMap(MapBuilder.createMap(Images.testMapMultiplayer, handler));
						}
						State.setState(handler.getGame().CharChoosingP1);
					}
				}, handler,Color.BLACK));

				//testmap2
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (64), 128, 64, "Map 2", () -> {
					if(!handler.isMarioInMap()) {
						mode = "Menu";
						if(!State.isMultiplayer()) {
							handler.setMap(MapBuilder.createMap(Images.testMaptwo, handler));
						}else {
							handler.setMap(MapBuilder.createMap(Images.testMaptwoMultiplayer, handler));
						}
						State.setState(handler.getGame().CharChoosingP1);
					}
				}, handler,Color.BLACK));

				//other
				uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 64, (handler.getHeight() / 2) + (handler.getHeight() / 10) + (128), 128, 64, "Other", () -> {
					if(!handler.isMarioInMap()){
						mode = "Menu";
						JFileChooser chooser = new JFileChooser("/maps");
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"JPG, & PNG Images", "jpg", "png");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(null);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
							try {
								handler.setMap(MapBuilder.createMap(ImageIO.read(chooser.getSelectedFile()), handler));
								State.setState(handler.getGame().CharChoosingP1);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}, handler,Color.BLACK));
				uiManager.addObjects(this.but);
			}
			if (mode.equals("Selecting") && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && (!handler.isMarioInMap())) {
				mode = "Menu";
				uiManager = new UIManager(handler);
				handler.getMouseManager().setUimanager(uiManager);
				uiManager.addObjects(new UIImageButton(handler.getWidth() / 2 - 64, handler.getHeight() / 2 + (handler.getHeight() / 8), 128, 64, Images.butstart, () -> {
					mode = "Select";
				}));
			}
		}else{
			handler.getGame().mouseManager=null;
			tickNewScreen();
			if(clicked){
				clicked = mouseManager.isLeftPressed();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(!creatingMap) {
			g.setColor(Color.GREEN);
			g.drawImage(Images.backgrounds[background], 0, 0, handler.getWidth(), handler.getHeight(), null);
			g.drawImage(Images.title, 0, 0, handler.getWidth(), handler.getHeight(), null);
			uiManager.Render(g);
		}else{
			renderNewScreen();
		}
	}

	private void initNew(String title,Handler handler){
		display = new DisplayScreen(title + "              (H for Mapping)", DisplayWidth, DiplayHeight);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		creatingMap = true;
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,0), new Point(0, 0), "cursor1");
		display.getCanvas().setCursor(c);
	}

	private void tickNewScreen(){
		//for the tin take each value and divide by 255.
		//Ex for a red tint you wan the RGB : 255,0,0 so the tint is 1,0,0
		//Erase
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = Color.WHITE.getRGB();
		}
		//Mario
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.mario;
		}
		//BreakBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.breakBlock;
		}
		//MisteryBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,1,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.misteryBlock;
		}
		//SurfaceBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0.5f,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.surfaceBlock;
		}
		//BoundBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0,0,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.boundBlock;
		}
		//Mushroom
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,069f,0,1), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.mushroom;
		}
		//Goomba
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,06549f,0.05882f,0.003921f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.goomba;
		}
		//Luigi
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L) && State.isMultiplayer()){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0, 1, 0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.luigi;
		}
		//RotatingMysteryBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.717647f,09.88235f,0), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.rotatingmisteryBlock;
		}
		//Coin
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,1,0.60392156f,0.9568627f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.coin;
		}
		//Cloud
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.80392156f,1,0.968626f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.cloud;
		}
		//DeathBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.454901f,0.5529f,0.674509f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.deathBlock;
		}
		//FinishBlock
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.22745f,0.5529f,0.674509f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.finishBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1) ){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.84313f,0.929411f,0.85490f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.floatingBlock;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A) ){
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(Images.tint(Images.Cursor,0.729411f,0.6784313f,0.313725f), new Point(0, 0), "cursor1");
			display.getCanvas().setCursor(c);
			colorSelected = MapBuilder.flyinggoomba;
		}
		if(mouseManager.isLeftPressed() && !clicked){
			int posX =mouseManager.getMouseX()/GridPixelsize;
			int posY =mouseManager.getMouseY()/GridPixelsize;
			System.out.println(posX + " , " +posY);
			blocks[posX][posY]=new Color(colorSelected);
			clicked=true;
		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && State.isMultiplayer()==false){
			for (int i = 0; i < GridWidthPixelCount; i++) {
				for (int j = 0; j < GridHeightPixelCount; j++) {
					if(blocks[i][j]!=null && blocks[i][j].equals(new Color(MapBuilder.mario)) && blocks[i][j+1]!=null&& !blocks[i][j+1].equals(new Color(MapBuilder.mario))){
						handler.setMap(MapBuilder.createMap(createImage(GridWidthPixelCount,GridHeightPixelCount,blocks,JOptionPane.showInputDialog("Enter file name: ","Mario Heaven")), handler));
						State.setState(handler.getGame().CharChoosingP1);
						creatingMap=false;
						display.getFrame().setVisible(false);
						display.getFrame().dispose();
						handler.getGame().mouseManager=handler.getGame().initialmouseManager;
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(display.getFrame(), "You cant have a map without at least a Mario and a floor right under him. (M for Mario)");

		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && State.isMultiplayer()){
			for (int i = 0; i < GridWidthPixelCount; i++) {
				for (int j = 0; j < GridHeightPixelCount; j++) {
					if(blocks[i][j]!=null && blocks[i][j].equals(new Color(MapBuilder.luigi)) && blocks[i][j+1]!=null && !blocks[i][j+1].equals(new Color(MapBuilder.mario)) && !blocks[i][j+1].equals(new Color(MapBuilder.luigi))){

						partOne = true;
						
					}else if(blocks[i][j]!=null && blocks[i][j].equals(new Color(MapBuilder.mario)) && blocks[i][j+1]!=null&& !blocks[i][j+1].equals(new Color(MapBuilder.mario))&& !blocks[i][j+1].equals(new Color(MapBuilder.luigi))){

						partTwo =true;
					}
				}
			}if(partOne==false || partTwo==false) {
				JOptionPane.showMessageDialog(display.getFrame(), "You cant have a map without at least a Luigi and a Mario, plus a floor right under them.");
				partOne = false;
				partTwo = false;
			}else if(partOne==true && partTwo==true) {
				handler.setMap(MapBuilder.createMap(createImage(GridWidthPixelCount,GridHeightPixelCount,blocks,JOptionPane.showInputDialog("Enter file name: ","Mario Heaven")), handler));
				State.setState(handler.getGame().CharChoosingP1);
				creatingMap=false;
				display.getFrame().setVisible(false);
				display.getFrame().dispose();
				handler.getGame().mouseManager=handler.getGame().initialmouseManager;
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H) && State.isMultiplayer()==false){
			JOptionPane.showMessageDialog(display.getFrame(), "Number key <-> Color Mapping: \n" +
					"0 -> Erase \n" +
					"1 -> Floating Block (Green Gray)\n" +
					"2 -> Break Block (Blue)\n" +
					"3 -> Mystery Block (Yellow)\n" +
					"4 -> Surface Block (Orange)\n" +
					"5 -> Bounds Block (Black)\n" +
					"6 -> Mushroom (Purple)\n" +
					"7 -> Goomba (Brown)\n"+ 
					"8 -> Rotating Mystery Block (Yellow-Green)\n"+ 
					"9 -> Coin (Fusha)\n"+ 
					"C -> Cloud (Sky Blue)\n"+ 
					"D -> Death Block (Gray)\n"+ 
					"F -> Finish Race Block (Blue Gray)\n"+ 
					"M -> Mario (Red)\n" +
					"A -> Flying Goomba (Light Brown)");
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H) && State.isMultiplayer()==true){
			JOptionPane.showMessageDialog(display.getFrame(), "Number key <-> Color Mapping: \n" +
					"0 -> Erase \n" +
					"1 -> Floating Block (Green Gray)\n" +
					"2 -> Break Block (Blue)\n" +
					"3 -> Mystery Block (Yellow)\n" +
					"4 -> Surface Block (Orange)\n" +
					"5 -> Bounds Block (Black)\n" +
					"6 -> Mushroom (Purple)\n" +
					"7 -> Goomba (Brown)\n"+ 
					"8 -> Rotating Mystery Block (Yellow-Green)\n"+ 
					"9 -> Coin (Fusha)\n"+ 
					"C -> Cloud (Sky Blue)\n"+ 
					"D -> Death Block (Gray)\n"+
					"F -> Finish Race Block (Blue Gray)\n"+ 
					"L -> Luigi(Green)\n"+
					"M -> Mario (Red)\n"+
					"A -> Flying Goomba (Light Brown)");
			
			
		}
	}
	public UIAnimationButton getBut() {
		return this.but;
	}

	private void renderNewScreen(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0,  handler.getWidth()+handler.getWidth()/2, handler.getHeight());

		//Draw Here!
		Graphics2D g2 = (Graphics2D) g.create();

		g.setColor(Color.white);
		g.fillRect(0,0,handler.getWidth()+(handler.getWidth()/2),handler.getHeight());

		for (int i = 0; i <= DisplayWidth; i = i + GridPixelsize) {
			g.setColor(Color.BLACK);
			g.drawLine(i,0,i,DiplayHeight);
		}
		for (int i = 0; i <= DiplayHeight; i = i + GridPixelsize) {
			g.setColor(Color.BLACK);
			g.drawLine(0, i, DisplayWidth , i);
		}
		for (int i = 0; i < GridWidthPixelCount; i++) {
			for (int j = 0; j < GridHeightPixelCount; j++) {
				if(blocks[i][j]!=null && !blocks[i][j].equals(Color.WHITE)){
					g.setColor((blocks[i][j]));
					g.fillRect((i*GridPixelsize),(j*GridPixelsize),GridPixelsize,GridPixelsize);
				}
			}
		}

		//End Drawing!
		bs.show();
		g.dispose();
	}

	public BufferedImage createImage(int width,int height,Color[][] blocks,String name){

		// Create buffered image object
		BufferedImage img = null;
		MapBuilder.mapDone=false;
		if(name.equals(str2)) MapBuilder.mapDone = true;

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// file object
		File f = null;

		// create random values pixel by pixel
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if(blocks[x][y]!=null) {
					img.setRGB(x, y, blocks[x][y].getRGB());
				}else{
					img.setRGB(x, y, Color.WHITE.getRGB());

				}
			}
		}

		// write image
		try
		{
			String path = getClass().getClassLoader().getResource(".").getPath();
			String path2 = path.substring(0,path.indexOf("/out/"))+"/res/maps/"+name+".png";
			f = new File(path2);
			System.out.println("File saved in: "+path2);
			ImageIO.write(img, "png", f);
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
		}
		return img;
	}


	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getBackground() {
		return background;
	}

}
