package Game.GameStates;


import Display.UI.UIStringButton;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.FinishBlock;
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
	private MouseManager mouseManager;


	public int DiplayHeight,DisplayWidth;



	public InstructionsState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);


		handler.getMouseManager().setUimanager(uiManager);


		DisplayWidth=(handler.getWidth())+(handler.getWidth()/2);
		DiplayHeight = handler.getHeight();

	}

	@Override
	public void tick() {

		handler.getMouseManager().setUimanager(uiManager);

		
			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /4), 128, 64, "OK", () -> {

				State.setState(handler.getGame().gameState);

			}, handler,Color.RED));
		
//		if(State.isMultiplayer()) {
//			uiManager.addObjects(new UIStringButton(handler.getWidth() / 2 - 32, handler.getHeight() - (handler.getHeight() /4), 128, 64, "OK", () -> {
//
//
//				State.setState(handler.getGame().gameState);
//
//			}, handler,Color.RED));
//   	}
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);

		if(State.isMario_enabledp1()==true || State.isWario_enabledp1() == true){
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			g.drawString("Controls:", handler.getWidth()/3+5, handler.getHeight()/10);
			g.setFont(new Font("SansSerif", Font.PLAIN, 25));
			g.setColor(Color.RED);
			g.drawString("Mario or Wario: Press \"V\" while jumping to double jump.", handler.getWidth()/16, handler.getHeight()/10+50);
			g.drawString("           W, A ,S ,D keys for moving.", handler.getWidth()/16, handler.getHeight()/10+100);
			g.drawString("           Shift key for running.", handler.getWidth()/16, handler.getHeight()/10+150);
			g.drawString("           Space bar for jumping.", handler.getWidth()/16, handler.getHeight()/10+200);
		}
//		if(State.isLuigi_enabledp1()==true) {
//			g.setColor(Color.WHITE);
//			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
//			g.drawString("Controls:", handler.getWidth()/3+5, handler.getHeight()/10);
//			g.setFont(new Font("SansSerif", Font.PLAIN, 25));
//			g.setColor(Color.RED);
//			g.drawString("Luigi: Press \" V \" key while jumping to float.", handler.getWidth()/16, handler.getHeight()/10+50);
//			g.drawString("           W, A, S, D Keys for moving.", handler.getWidth()/16, handler.getHeight()/10+100);
//			g.drawString("           Shift  for running.", handler.getWidth()/16, handler.getHeight()/10+150);
//			g.drawString("           Space bar for jumping.", handler.getWidth()/16, handler.getHeight()/10+200);
//		}
		if(State.isMultiplayer()) {
			for(BaseStaticEntity block : handler.getMap().getBlocksOnMap() ) {
				if(block instanceof FinishBlock) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("SansSerif", Font.PLAIN, 40));
					g.drawString("RACE MODE:", handler.getWidth()/3+5, handler.getHeight()/10+300);
					g.setFont(new Font("SansSerif", Font.PLAIN, 25));
					g.drawImage(Images.finishBlock,handler.getWidth()/16, handler.getHeight()/10+350,75,75,null);
					g.setFont(new Font("Segoe UI", Font.BOLD, 20));
					g.drawString("First to touch this block wins!",handler.getWidth()/16+80,  handler.getHeight()/10+400);
				}
			}
		}else if(!State.isMultiplayer() ) {
			for(BaseStaticEntity block : handler.getMap().getBlocksOnMap() ) {
				if(block instanceof FinishBlock) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("SansSerif", Font.PLAIN, 40));
					g.drawString("RACE MODE:", handler.getWidth()/3+5, handler.getHeight()/10+300);
					g.setFont(new Font("SansSerif", Font.PLAIN, 25));
					g.drawImage(Images.finishBlock,handler.getWidth()/16, handler.getHeight()/10+350,75,75,null);
					g.setFont(new Font("Segoe UI", Font.BOLD, 20));
					g.drawString("Find this block to win!",handler.getWidth()/16+80,  handler.getHeight()/10+400);
				}
			}
		}
		uiManager.Render(g);
	}

}
