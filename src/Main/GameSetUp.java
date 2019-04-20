package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Display.DisplayMultiplayerScreen;
import Display.DisplayScreen;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.FinishBlock;
import Game.GameStates.CharChoosingP1;
import Game.GameStates.CharChoosingP2;
import Game.GameStates.GameOverState;
import Game.GameStates.GameState;
import Game.GameStates.InstructionsState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Game.GameStates.WinState;
import Game.World.Map;
import Game.World.MapBuilder;
import Input.Camera;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;
import Resources.MusicHandler;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
	public DisplayScreen display;

	public DisplayMultiplayerScreen display2;



	public String title;

	private boolean running = false;
	private Thread thread;
	public static boolean threadB;

	private BufferStrategy bs;
	private Graphics g;
	private BufferStrategy bs2;
	private Graphics g3;
	public UIPointer pointer;

	//Input
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public MouseManager initialmouseManager;

	//Handler
	private Handler handler;

	//States
	public State gameState;
	public State menuState;
	public State pauseState;
	public State gameoverState;
	public State winState;
	public State instructionsState;
	public State CharChoosingP1;
	public State CharChoosingP2;

	//Res.music
	private MusicHandler musicHandler;

	public GameSetUp(String title,Handler handler) {
		this.handler = handler;
		this.title = title;
		threadB=false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		initialmouseManager = mouseManager;
		musicHandler = new MusicHandler(handler);
		handler.setCamera(new Camera());
		handler.setCamera2(new Camera());
	}

	private void init(){
		display = new DisplayScreen(title, handler.width, handler.height);
		display.getFrame().setLocation(display.getFrame().getX()- display.getFrame().getWidth()/2, display.getFrame().getY());
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		display2 = new DisplayMultiplayerScreen("Luigi", handler.width, handler.height);
		display2.getFrame().setLocation(display.getFrame().getX()+  display.getFrame().getWidth(), display.getFrame().getY());

		///
		Images img = new Images();

		musicHandler.restartBackground();

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		pauseState = new PauseState(handler);
		gameoverState= new GameOverState(handler);
		instructionsState = new InstructionsState(handler);
		winState = new WinState(handler);
		CharChoosingP1 = new CharChoosingP1(handler);
		CharChoosingP2 = new CharChoosingP2(handler);
		
		State.setState(menuState);
	}

	public void reStart(){
		gameState = new GameState(handler);
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		//this runs the run method in this  class
		thread = new Thread(this);
		thread.start();
	}

	public void run(){

		//initiallizes everything in order to run without breaking
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			//makes sure the games runs smoothly at 60 FPS
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				//re-renders and ticks the game around 60 times per second
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	private void tick(){
		//checks for key types and manages them
		keyManager.tick();

		if(musicHandler.ended()){
			musicHandler.restartBackground();
		}

		//game states are the menus
		if(State.getState() != null)
			State.getState().tick();
		if (handler.isMarioInMap()) {
			updateCamera();
		} if (handler.isLuigiInMap()) {
			updateCamera2();
		}

	}

	private void updateCamera() {
		Player mario = handler.getMario();
		double marioVelocityX = mario.getVelX();
		double marioVelocityY = mario.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (marioVelocityX > 0 && mario.getX() - 2*(handler.getWidth()/3) > handler.getCamera().getX()) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityX < 0 && mario.getX() +  2*(handler.getWidth()/3) < handler.getCamera().getX()+handler.width) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityY > 0 && mario.getY() - 2*(handler.getHeight()/3) > handler.getCamera().getY()) {
			shiftAmountY = marioVelocityY;
		}
		if (marioVelocityY > 0 && mario.getY() +  2*(handler.getHeight()/3) < handler.getCamera().getY()+handler.height) {
			shiftAmountY = -marioVelocityY;
		}
		handler.getCamera().moveCam(shiftAmount,shiftAmountY);
	}
	private void updateCamera2() {
		Player luigi = handler.getLuigi();
		double luigiVelocityX = luigi.getVelX();
		double luigiVelocityY = luigi.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (luigiVelocityX > 0 && luigi.getX() - 2*(handler.getWidth()/3) > handler.getCamera2().getX()) {
			shiftAmount = luigiVelocityX;
		}
		if (luigiVelocityX < 0 && luigi.getX() +  2*(handler.getWidth()/3) < handler.getCamera2().getX()+handler.width) {
			shiftAmount = luigiVelocityX;
		}
		if (luigiVelocityY > 0 && luigi.getY() - 2*(handler.getHeight()/3) > handler.getCamera2().getY()) {
			shiftAmountY = luigiVelocityY;
		}
		if (luigiVelocityY > 0 && luigi.getY() +  2*(handler.getHeight()/3) < handler.getCamera2().getY()+handler.height) {
			shiftAmountY = -luigiVelocityY;
		}
		handler.getCamera2().moveCam(shiftAmount,shiftAmountY);
	}

	private void render(){
		bs = display.getCanvas().getBufferStrategy();

		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0,  handler.width, handler.height);

        /////////////////////////////////////////////////
		//Second Screen
		bs2 = display2.getCanvas().getBufferStrategy();

		if(bs2 == null){
			display2.getCanvas().createBufferStrategy(3);
			return;
		}
		g3 = bs2.getDrawGraphics();
		//Clear Screen
		g3.clearRect(0, 0,  handler.width, handler.height);
		

		//Draw Here!
		Graphics2D g2 = (Graphics2D) g.create();
		Graphics2D g4 = (Graphics2D) g3.create();


		if(State.getState() != null)
			State.getState().render(g2);
		//////////////////////////////////////////////////////////////////////////
		if (State.getState() instanceof MenuState) {
			g3.setFont(new Font("Segoe UI", Font.BOLD, 20));
			g3.setColor(Color.RED);
			g3.drawString("Please Wait!", handler.width/2 - 80, handler.height/2);
		}
		////////////////////////////////////////////////////////////////////////////
		if(State.isMultiplayer() && State.getState() instanceof InstructionsState) {
			g4.setColor(Color.WHITE);
			g4.setFont(new Font("SansSerif", Font.PLAIN, 40));
			g4.drawString("Controls:", handler.getWidth()/3+5, handler.getHeight()/10);
			g4.setFont(new Font("SansSerif", Font.PLAIN, 25));
			g4.setColor(Color.GREEN);
			g4.drawString("Luigi: Press \" period \" key while jumping to float.", handler.getWidth()/16, handler.getHeight()/10+50);
			g4.drawString("           UP, DOWN, LEFT, RIGHT Keys for moving.", handler.getWidth()/16, handler.getHeight()/10+100);
			g4.drawString("           \" / \" (forward slash) for running.", handler.getWidth()/16, handler.getHeight()/10+150);
			g4.drawString("           \" control \" key for jumping.", handler.getWidth()/16, handler.getHeight()/10+200);
			for(BaseStaticEntity block : handler.getMap().getBlocksOnMap() ) {
				if(block instanceof FinishBlock) {
					g4.setColor(Color.WHITE);
					g4.setFont(new Font("SansSerif", Font.PLAIN, 40));
					g4.drawString("RACE MODE:", handler.getWidth()/3+5, handler.getHeight()/10+300);
					g4.setFont(new Font("SansSerif", Font.PLAIN, 25));
					g4.drawImage(Images.finishBlock,handler.getWidth()/16, handler.getHeight()/10+350,75,75,null);
					g4.setFont(new Font("Segoe UI", Font.BOLD, 20));
					g4.drawString("First to touch this block wins!",handler.getWidth()/16+80,  handler.getHeight()/10+400);
				}
			}
		}
		////////////////////////////////////////////////////////////////////////
		if(!State.isMultiplayer() && State.getState() instanceof InstructionsState) {
			for(BaseStaticEntity block : handler.getMap().getBlocksOnMap() ) {
				if(block instanceof FinishBlock) {
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
					g2.drawString("RACE MODE:", handler.getWidth()/3+5, handler.getHeight()/10+300);
					g2.setFont(new Font("SansSerif", Font.PLAIN, 25));
					g2.drawImage(Images.finishBlock,handler.getWidth()/16, handler.getHeight()/10+350,75,75,null);
					g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
					g2.drawString("Find this block to win!",handler.getWidth()/16+80,  handler.getHeight()/10+400);
				}
			}
		}
		///////////////////////////////////////////////////////////////////////////
		if(State.isMultiplayer() && State.getState() instanceof GameState) {

			handler.getMap().drawMap2(g4);

			g4.setColor(Color.GREEN);
			String luigicoins = String.valueOf(Player.luigicoins);
			g4.drawString("LCoins = " + luigicoins,handler.getWidth() - 120, 40);
			g4.setFont(new Font("Segoe UI", Font.BOLD, 20));
			g4.setColor(Color.RED);
			String mariocoins = String.valueOf(Player.mariocoins);
			g4.drawString("MCoins = " + mariocoins, handler.getWidth() - 120, 20);
		}
		////////////////////////////////////////////////////////////////////////////
		else if(State.getState() instanceof WinState){
			State.getState().render(g4);
		}


		//End Drawing!
		bs.show();
		g.dispose();
		bs2.show();
		g3.dispose();
	}
	public Map getMap() {
		Map map = new Map(this.handler);
		Images.makeMap(0, MapBuilder.pixelMultiplier, 31, 200, map, this.handler);
		for(int i = 195; i < 200; i++) {
			map.addBlock(new BreakBlock(0, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
			map.addBlock(new BreakBlock(30*MapBuilder.pixelMultiplier, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
		}
		Mario mario = new Mario(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);
		map.addEnemy(mario);
		if (State.isMultiplayer()) {
			Luigi luigi = new Luigi(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);
			map.addEnemy(luigi);
		}
		map.addEnemy(pointer);
		threadB=true;
		return map;
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public MusicHandler getMusicHandler() {
		return musicHandler;
	}


	public MouseManager getMouseManager(){
		return mouseManager;
	}

}

