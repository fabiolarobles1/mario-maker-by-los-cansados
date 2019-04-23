package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Display.UI.UIManager;
import Display.UI.UIStringButton;
import Game.Entities.DynamicEntities.Player;
import Input.MouseManager;
import Main.Handler;
import Resources.Images;

public class CharChoosingP2 extends State {
	public UIManager uiManager;
	private MouseManager mouseManager;
	

	public int DiplayHeight,DisplayWidth;
	


	public CharChoosingP2(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		
		handler.getMouseManager().setUimanager(uiManager);
		

		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();
	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		if(State.isMultiplayer()) {
			
			//Luigi
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Luigi", () -> {
				
				
					State.setLuigi_enabledp2(true);
					State.setYoshi_enabledp2(false);
					State.setState(handler.getGame().instructionsState);
				

			}, handler,Color.GREEN));
			
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 +100, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Yoshi", () -> {

				State.setLuigi_enabledp2(false);
				State.setYoshi_enabledp2(true);
				State.setState(handler.getGame().instructionsState);

			}, handler,Color.YELLOW));
			
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.drawString("PLAYER 2 CHARACTER SELECT", handler.getWidth() / 2 - 230, 100);
		
		g.drawImage(Images.luigiSmallWalkLeft[0],handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4) -70,75,75,null);
		g.drawImage(Images.yoshiSmallWalkLeft[0],handler.getWidth() / 2 + 100, handler.getHeight() - (handler.getHeight() /4) -70,75,75,null);
//		if(State.isMario_enabledp1()==true) {
//			g.setFont(new Font("SansSerif", Font.PLAIN, 15));
//			g.drawString("Taken!!", handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4) -70 - 25);
//		}
//		if(State.isLuigi_enabledp1()==true) {
//			g.setFont(new Font("SansSerif", Font.PLAIN, 15));
//			g.drawString("Taken!!", handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /4) -70 - 25);
//		}
//		if(State.isWario_enabledp1()==true) {
//			g.setFont(new Font("SansSerif", Font.PLAIN, 15));
//			g.drawString("Taken!!", handler.getWidth() / 2 - 32 +135, handler.getHeight() - (handler.getHeight() /4) -70 - 25);
//		}
		uiManager.Render(g);

	}

}
