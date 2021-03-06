package Game.GameStates;

import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public abstract class State {

	private static State currentState = null;
	private static boolean multiplayer = false;
	//Player 1 Character
	private static boolean mario_enabledp1 = false;
	private static boolean wario_enabledp1 = false;
	//Player 2 Character
	private static boolean luigi_enabledp2 = false;
	private static boolean yoshi_enabledp2 = false;


	/////////////////////////////////////////////////////////////
	//Character Enablers Getters And Setters
	
	//##---->//|\\<----##\\
	//##---->Mario<----## P1\\
	public static boolean isMario_enabledp1() {return mario_enabledp1;}
	public static void setMario_enabledp1(boolean mario_enabledp1) {State.mario_enabledp1 = mario_enabledp1;}
	//##---->//|\\<----##\\
	//##---->Wario<----## P1\\
	public static boolean isWario_enabledp1() {return wario_enabledp1;}
	public static void setWario_enabledp1(boolean wario_enabledp1) {State.wario_enabledp1 = wario_enabledp1;}
	//##---->//|\\<----##\\
	//##---->Luigi<----## P2\\
	public static boolean isLuigi_enabledp2() {return luigi_enabledp2;}
	public static void setLuigi_enabledp2(boolean luigi_enabledp2) {State.luigi_enabledp2 = luigi_enabledp2;}
	//##---->//|\\<----##\\
	//##---->Yoshi<----## P2\\
	public static boolean isYoshi_enabledp2() {return yoshi_enabledp2;}
	public static void setYoshi_enabledp2(boolean yoshi_enabledp2) {State.yoshi_enabledp2 = yoshi_enabledp2;}
	//##---->//|\\<----##\\
	/////////////////////////////////////////////////////////////


	public static void setState(State state){
		currentState = state;
	}

	public static State getState(){
		return currentState;
	}

	public static boolean isMultiplayer() {
		return multiplayer;
	}

	public static void setMultiplayer(boolean multiplayer) {
		State.multiplayer = multiplayer;
	}

	//CLASS

	protected Handler handler;

	public State(Handler handler){
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}

