package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class Coin extends Item {

	public Animation anim;

	public Coin(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.coin[0]);
		anim = new Animation(160 ,Images.coin);
	}

	@Override
	public void tick(){                       
		super.tick();
		anim.tick(); 
		
		checkHorizontal();


	}
}
