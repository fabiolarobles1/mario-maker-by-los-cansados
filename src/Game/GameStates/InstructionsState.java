package Game.GameStates;


import Display.UI.UIStringButton;

import Input.KeyManager;
import Input.MouseManager;
import Main.Handler;
import Resources.Images;
import Display.UI.UIAnimationButton;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import java.awt.*;


public class InstructionsState extends State {
	public UIManager uiManager;

	public int DiplayHeight,DisplayWidth;


	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;


	public InstructionsState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);


		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();

		keyManager = handler.getGame().keyManager;
		mouseManager = new MouseManager();


	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);

if(!State.isMultiplayer()) {
		uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /2), 128, 64, "OK", () -> {
		
			State.setState(handler.getGame().gameState);

		}, handler,Color.WHITE));
}if(State.isMultiplayer()) {
	uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /10), 128, 64, "OK", () -> {
		
		State.setState(handler.getGame().gameState);

	}, handler,Color.WHITE));
}
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		//g.drawImage(Images.backgrounds[background], 0, 0, handler.getWidth(), handler.getHeight(), null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 40));
		g.drawString("Controls:", handler.getWidth()/3+5, handler.getHeight()/10);
		g.setFont(new Font("SansSerif", Font.PLAIN, 25));
		g.drawString("Mario: Press \"V\" while jumping to double jump.", 10, handler.getHeight()/10+50);
		g.drawString("           W, A ,S ,D keys for moving.", 10, handler.getHeight()/10+100);
		g.drawString("           Shift for running.", 10, handler.getHeight()/10+150);
		g.drawString("           Space for jumping.", 10, handler.getHeight()/10+200);
		if(State.isMultiplayer()) {
			g.drawString("Luigi: Press \" period \" key while jumping to float.", 10, handler.getHeight()/10+250);
			g.drawString("           UP, DOWN, LEFT, RIGHT Keys for moving.", 10, handler.getHeight()/10+300);
			g.drawString("           \" / \" (forward slash) for running.", 10, handler.getHeight()/10+350);
			g.drawString("           \" control \" key for jumping.", 10, handler.getHeight()/10+400);
		}
		uiManager.Render(g);
	}

}
