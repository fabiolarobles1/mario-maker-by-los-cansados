package Game.GameStates;

import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

import Display.UI.UIListener;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {

	public GameState(Handler handler){
		super(handler);
		handler.getGame().pointer = new UIPointer(28 * MapBuilder.pixelMultiplier,197 * MapBuilder.pixelMultiplier,128,128,handler);

	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
			State.setState(handler.getGame().pauseState);
		}if(!State.isMultiplayer()) {
			handler.getMario().tick();
			if(handler.getMap().getListener() != null && MapBuilder.mapDone) {
				handler.getMap().getListener().tick();
				handler.getMap().getHand().tick();
				handler.getMap().getWalls().tick();
			}
		}else if (State.isMultiplayer()) {
			handler.getMario().tick();
			handler.getLuigi().tick();
			if(handler.getMap().getListener() != null && MapBuilder.mapDone) {
				handler.getMap().getListener().tick();
				handler.getMap().getHand().tick();
				handler.getMap().getWalls().tick();
			}
		}
		for (BaseDynamicEntity entity:handler.getMap().getEnemiesOnMap()) {
			entity.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		handler.getMap().drawMap(g2);
				
		//Coin Display
		g.setFont(new Font("Segoe UI", Font.BOLD, 20));
		g.setColor(Color.RED);
		String mariocoins = String.valueOf(Player.mariocoins);
		g.drawString("MCoins = " + mariocoins, handler.getWidth() - 120, 20);
		if (State.isMultiplayer()) {
			//Luigi Coin 
			g.setColor(Color.GREEN);
			String luigicoins = String.valueOf(Player.luigicoins);
			g.drawString("LCoins = " + luigicoins,handler.getWidth() - 120, 40);
			//Finish Block Message
//			if (!(handler.getMario().moving)) {
//				g.drawImage(Images.finishBlock,handler.getWidth()/16,handler.getHeight()/16,75,75,null);
//				g.setFont(new Font("Segoe UI", Font.BOLD, 20));
//				g.setColor(Color.WHITE);
//				g.drawString("First to touch this wins!",handler.getWidth()/16, (handler.getHeight()/16) -10);
//
//			}
			
		}
	}

}
