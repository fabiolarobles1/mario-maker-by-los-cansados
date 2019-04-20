package Game.GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Display.UI.UIManager;
import Display.UI.UIStringButton;
import Input.MouseManager;
import Main.Handler;
import Resources.Images;

public class CharChoosingP1 extends State {
	public UIManager uiManager;
	private MouseManager mouseManager;
	

	public int DiplayHeight,DisplayWidth;
	


	public CharChoosingP1(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		
		handler.getMouseManager().setUimanager(uiManager);
		

		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();
	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		if(!State.isMultiplayer()) {
			//Mario
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Mario", () -> {
				
				State.setMario_enabledp1(true);
				State.setLuigi_enabledp1(false);
				State.setWario_enabledp1(false);
				State.setState(handler.getGame().instructionsState);

			}, handler,Color.RED));
			//Luigi
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Luigi", () -> {
				
				State.setMario_enabledp1(false);
				State.setLuigi_enabledp1(true);
				State.setWario_enabledp1(false);
				State.setState(handler.getGame().instructionsState);

			}, handler,Color.GREEN));
			//Wario
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 +100, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Wario", () -> {

				State.setMario_enabledp1(false);
				State.setLuigi_enabledp1(false);
				State.setWario_enabledp1(true);
				State.setState(handler.getGame().instructionsState);

			}, handler,Color.YELLOW));
		}
		if(State.isMultiplayer()) {
			//Mario
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Mario", () -> {	
				State.setMario_enabledp1(true);
				State.setLuigi_enabledp1(false);
				State.setWario_enabledp1(false);
				State.setState(handler.getGame().CharChoosingP2);

			}, handler,Color.RED));
			//Luigi
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Luigi", () -> {
				
				State.setMario_enabledp1(false);
				State.setLuigi_enabledp1(true);
				State.setWario_enabledp1(false);
				State.setState(handler.getGame().CharChoosingP2);

			}, handler,Color.GREEN));
			//Wario
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 + 100, handler.getHeight() - (handler.getHeight() /4), 128, 64, "Wario", () -> {

				State.setMario_enabledp1(false);
				State.setLuigi_enabledp1(false);
				State.setWario_enabledp1(true);
				State.setState(handler.getGame().CharChoosingP2);

			}, handler,Color.YELLOW));
		}

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 10));
		g.drawString("PLAYER 1 CHARACTER", handler.getWidth() / 2 - 50, 100);
		g.drawImage(Images.marioSmallWalkLeft[0],handler.getWidth() / 2 - 32 - 150, handler.getHeight() - (handler.getHeight() /4) -70,75,75,null);
		g.drawImage(Images.luigiSmallWalkLeft[0],handler.getWidth() / 2 - 40, handler.getHeight() - (handler.getHeight() /4) -70,75,75,null);
		g.drawImage(Images.warioSmallWalkLeft[0],handler.getWidth() / 2 + 100, handler.getHeight() - (handler.getHeight() /4) -70,75,75,null);
		uiManager.Render(g);

	}

}
