package Game.GameStates;

import Display.UI.UIStringButton;
import Game.Entities.DynamicEntities.Player;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Images;
import Display.UI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class WinState extends State {

	private UIManager uiManager;

	public WinState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);


		uiManager.addObjects(new UIStringButton(56 + 100 + 100 + 32, (223+(64+16) +(64+16) + (64+16)), 128, 64, "Back to Title", () -> {
			handler.getMouseManager().setUimanager(null);
			handler.setMarioInMap(false);
			Player.mariocoins = 0;
			Player.luigicoins = 0;
			Player.mariowins =false;
			Player.luigiwins =false;
			State.setMario_enabledp1(false);
			State.setLuigi_enabledp2(false);
			State.setWario_enabledp1(false);
			State.setYoshi_enabledp2(false);
			State.setState(handler.getGame().menuState);
		},handler,Color.BLUE));

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.Over[0],0,0,handler.getWidth(),handler.getHeight(),null);
		g.setFont(new Font("Segoe UI", Font.BOLD, 40));
		if (State.isMultiplayer()) {
			if((Player.mariowins && Player.luigiwins) || (!Player.mariowins && !Player.luigiwins)) {
				if (Player.mariocoins > Player.luigicoins) {
					Player.mariowins = true;
					Player.luigiwins = false;
				}
				else if (Player.mariocoins < Player.luigicoins) {
					Player.mariowins = false;
					Player.luigiwins = true;
				}
				else if (Player.mariocoins == Player.luigicoins) {
					Player.mariowins = false;
					Player.luigiwins = false;
					g.setColor(Color.ORANGE);
					g.drawString("TIE", 56 + 100 + 100, 250);
				}
			}
			if(Player.mariowins) {

				if(State.isMario_enabledp1() ==true) {
					g.setColor(Color.RED);
				}
				if(State.isWario_enabledp1() ==true) {
					g.setColor(Color.YELLOW);
				}	
				g.drawString("Player 1 WINS", handler.getWidth()/3-10, 250);
			}
			else if(Player.luigiwins) {

				if(State.isLuigi_enabledp2() ==true) {
					g.setColor(Color.GREEN);
				}
				if(State.isYoshi_enabledp2() ==true) {
					g.setColor(Color.LIGHT_GRAY);
				}	
				g.drawString("Player 2 WINS", handler.getWidth()/3-10, 250);
			}
		}
		else {
			if(Player.mariowins) {
				if(State.isMario_enabledp1() ==true) {
					g.setColor(Color.RED);
				}
				if(State.isWario_enabledp1() ==true) {
					g.setColor(Color.YELLOW);
				}
				g.drawString("Player 1 WINS", handler.getWidth()/3-10, 250);
			}    	
		}
		uiManager.Render(g);
	}
}
