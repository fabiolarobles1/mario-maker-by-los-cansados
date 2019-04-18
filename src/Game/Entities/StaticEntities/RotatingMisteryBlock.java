package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;
import java.awt.*;

public class RotatingMisteryBlock extends BaseStaticEntity {
	public boolean hit = false;
	public Animation anim;
    public RotatingMisteryBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height,handler, Images.rotatingmisteryBlock[0]);
        anim = new Animation(160, Images.rotatingmisteryBlock);
    }

    @Override
    public void tick(){
    	super.tick();
        anim.tick();
    }
}