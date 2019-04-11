package Game.Entities.DynamicEntities;



import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;


public class FloatingBlock extends BaseDynamicEntity{

	public int movedx = 0;
	public int movedy = 0;
	public boolean movingup = true;
	public boolean movingdown = false;
	public Animation anim;
	

    public FloatingBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, 108, 52, handler, Images.floatingBlock[0]);
        anim = new Animation(100, Images.floatingBlock);
    }

	@Override
	public void tick() {
            anim.tick();
            checkHorizontal();
            move();
	}
	
	protected void move() {
		if(direction.equals("Right")){
			if(movedx == 150){
				movedx = 0;
				direction = "Left";
			}
            x+=velX;
            movedx+=velX;
        }else{
        	if(movedx == 150){
				movedx = 0;
				direction = "Right";
			}
            x-=velX;
            movedx+= velX;
        }
		if(movedy == 50){
			movedy = 0;	
			movingup = !movingup;
		}
		if(movingup) {
			 y-=1;
		     movedy+=1;
		     movingdown = false;
		}
		else {
			 y+=1;
		     movedy+=1;
		     movingdown = false;
		}
		
	}
}
