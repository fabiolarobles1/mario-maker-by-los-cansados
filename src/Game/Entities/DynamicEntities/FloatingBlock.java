package Game.Entities.DynamicEntities;



import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;


public class FloatingBlock extends BaseDynamicEntity{

	public int movedx = 0;
	public int movedy = 0;
	public String prevXmov = "";
	public String Ymov = "";
	Random rand = new Random();
	public boolean movingcircle = rand.nextBoolean();
	public boolean movingup = rand.nextBoolean();

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
		if(!movingcircle) {
			//Move in X
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
			//Move in Y
			if(movedy == 50){
				movedy = 0;	
				movingup = !movingup;
			}
			if(movingup) {
				y-=1;
				movedy+=1;	     
			}
			else {
				y+=1;
				movedy+=1;	   
			}
		}
		//Circle
		else {
			//////////////////////////////
			if(direction.equals("Right")){
				if(movedx == 150){
					movedx = 0;
					prevXmov = "Right";
					direction = "Put";
				}
				x+=velX;
				movedx+=velX;
			}
			/////////////////////////////////
			else if(direction.equals("Left")){
				if(movedx == 150){
					movedx = 0;
					prevXmov = "Left";
					direction = "Put";
				}
				x-=velX;
				movedx+=velX;
			}
			////////////////////////////////////
			else if(direction.equals("Put")) {
				if(movedy == 50){
					if(prevXmov.equals("Right")) {
						direction = "Left";
					}
					else {
						direction = "Right";
					}
				}
			}
			///////////////////////////////////
			//Move in Y Circle
			if(movedy == 50){
				movedy = 0;	
				movingup = !movingup;
			}
			if(direction.equals("Put")) {
				if(movingup) {
					y-=1;
					movedy+=1;	     
				}
				else {
					y+=1;
					movedy+=1;	   
				}
			}
			
		}
		
	}
}
