package Game.Entities.DynamicEntities;



import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;


public class AnnoyingPest extends BaseDynamicEntity{

	public int movedx = 0;
	public int movedy = 0;
	public String prevXmov = "";
	public String Ymov = "";
	
	Random rand = new Random();
	public boolean movingcircle = rand.nextBoolean();
	public boolean movingup = rand.nextBoolean();
	public boolean movingright= rand.nextBoolean();
	public boolean firstdirection = false;

	public Animation leftflight;
	public Animation rightflight;

	public AnnoyingPest(int x, int y, int width, int height, Handler handler, Animation LF, Animation RF) {
		super(x, y, width, height, handler, Images.Rannoyingpest[0]);
		//108,52
		
		leftflight = LF;
		rightflight = RF;
	}

	@Override
	public void tick() {
		//anim.tick();
		if(getDirection().equals("Right")){
			rightflight.tick();
		}
		else if(getDirection().equals("Left")){
			leftflight.tick();
		}
		checkHorizontal();
		move();
	}

	protected void move() {
		if(movingright == true && firstdirection == false) {
			setDirection("Right");
			firstdirection = true;
		}
		else if(movingright == false && firstdirection == false){
			setDirection("Left");
			firstdirection = true;
		}
		if(!movingcircle) {
			//Move in X
			if(getDirection().equals("Right")){
				if(movedx == 150){
					movedx = 0;
					setDirection("Left");
				}
				x+=velX;
				movedx+=velX;
			}else{
				if(movedx == 150){
					movedx = 0;
					setDirection("Right");
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
			if(getDirection().equals("Right")){
				if(movedx == 150){
					movedx = 0;
					prevXmov = "Right";
					setDirection("Put");
				}
				x+=velX;
				movedx+=velX;
			}
			/////////////////////////////////
			else if(getDirection().equals("Left")){
				if(movedx == 150){
					movedx = 0;
					prevXmov = "Left";
					setDirection("Put");
				}
				x-=velX;
				movedx+=velX;
			}
			////////////////////////////////////
			else if(getDirection().equals("Put")) {
				if(movedy == 50){
					if(prevXmov.equals("Right")) {
						setDirection("Left");
					}
					else {
						setDirection("Right");
					}
				}
			}
			///////////////////////////////////
			//Move in Y Circle
			if(movedy == 50){
				movedy = 0;	
				movingup = !movingup;
			}
			if(getDirection().equals("Put")) {
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
