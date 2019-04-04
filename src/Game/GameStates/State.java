package Game.GameStates;

import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public abstract class State {

    private static State currentState = null;
    private static boolean multiplayer = false;

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

