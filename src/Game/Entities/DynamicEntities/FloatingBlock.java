package Game.Entities.DynamicEntities;



import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;


public class FloatingBlock extends BaseDynamicEntity{

	public int moved = 0;
	public Animation anim;
	

    public FloatingBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.floatingBlock[1]);
        anim = new Animation(160, Images.floatingBlock);
    }

	@Override
	public void tick() {
            super.tick();
            anim.tick();
            checkHorizontal();
            move();
	}
	
	protected void move() {
		if(direction.equals("Right")){
			if(moved == 5){
				moved = 0;
				direction = "Left";
			}
            x+=velX;
            moved+=velX;
        }else{
        	if(moved == 5){
				moved = 0;
				direction = "Right";
			}
            x-=velX;
            moved+= velX;
        }
	}
}
