package Main;

import Display.DisplayMultiplayerScreen;
import Display.DisplayScreen;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BreakBlock;
import Game.GameStates.GameOverState;
import Game.GameStates.GameState;
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

import java.awt.*;
import java.awt.image.BufferStrategy;


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
        winState = new WinState(handler);

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
        if (marioVelocityX < 0 && mario.getY() +  2*(handler.getHeight()/3) < handler.getCamera().getY()+handler.height) {
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
        if (luigiVelocityX < 0 && luigi.getY() +  2*(handler.getHeight()/3) < handler.getCamera2().getY()+handler.height) {
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
        
        
        //Second Screen
        bs2 = display2.getCanvas().getBufferStrategy();

        if(bs2 == null){
            display2.getCanvas().createBufferStrategy(3);
            return;
        }
        g3 = bs2.getDrawGraphics();
        //Clear Screen
        g3.clearRect(0, 0,  handler.width, handler.height);
        
        g3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		g3.setColor(Color.RED);
        g3.drawString("Please Wait!", handler.width/2 - 80, handler.height/2);
        
        
        //Draw Here!
        Graphics2D g2 = (Graphics2D) g.create();
        Graphics2D g4 = (Graphics2D) g3.create();
        
        
        if(State.getState() != null)
            State.getState().render(g);
        	if(State.isMultiplayer() && State.getState() instanceof GameState) {
        		
        		handler.getMap().drawMap2(g4);
        		
        		g3.setColor(Color.GREEN);
    			String luigicoins = String.valueOf(Player.luigicoins);
    			g3.drawString("LCoins = " + luigicoins,handler.getWidth() - 120, 40);

    			if (!( handler.getLuigi().moving)) {
    				g3.drawImage(Images.finishBlock,handler.getWidth()/16,handler.getHeight()/16,75,75,null);
    				g3.setFont(new Font("Segoe UI", Font.BOLD, 20));
    				g3.setColor(Color.WHITE);
    				g3.drawString("First to touch this wins!",handler.getWidth()/16, (handler.getHeight()/16) -10);

    			}

    			g3.setFont(new Font("Segoe UI", Font.BOLD, 20));
    			g3.setColor(Color.RED);
    			String mariocoins = String.valueOf(Player.mariocoins);
    			g3.drawString("MCoins = " + mariocoins, handler.getWidth() - 120, 20);

        	}
        	else if(State.isMultiplayer() && State.getState() instanceof WinState){
        		State.getState().render(g3);
//        		g3.setFont(new Font("Segoe UI", Font.BOLD, 40));
//        		if(Player.mariowins) {
//            		g3.setColor(Color.RED);
//            		g3.drawString("Mario WINS", 56 + 100 + 100, 250);
//            	}
//            	else if(Player.luigiwins) {
//            		g3.setColor(Color.GREEN);
//            		g3.drawString("Luigi WINS", 56 + 100 + 100, 250);
//            	}
        		
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

