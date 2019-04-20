package Game.Entities.DynamicEntities;



import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;

import Game.Entities.StaticEntities.BaseStaticEntity;


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
	public boolean moving_alot_x = rand.nextBoolean();
	//public boolean moving_alot_x = true;
	public boolean moving_alot_y = rand.nextBoolean();
	//public boolean moving_alot_y = true;
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
				if(moving_alot_x == true) {
					if(movedx == 300){
						movedx = 0;
						setDirection("Left");
					}
				}
				else {
					if(movedx == 150){
						movedx = 0;
						setDirection("Left");
					}
				}
				x+=velX;
				movedx+=velX;
			}else{
				if(moving_alot_x == true) {
					if(movedx == 300){
						movedx = 0;
						setDirection("Right");
					}
				}
				else {
					if(movedx == 150){
						movedx = 0;
						setDirection("Right");
					}
				}
				x-=velX;
				movedx+= velX;
			}
			//Move in Y
			if(moving_alot_y == true) {
				if(movedy == 100){
					movedy = 0;	
					movingup = !movingup;
				}
			}
			else {
				if(movedy == 50){
					movedy = 0;	
					movingup = !movingup;
				}
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
				if(moving_alot_x == true) {
					if(movedx == 300){
						movedx = 0;
						prevXmov = "Right";
						setDirection("Put");
					}
				}
				else {
					if(movedx == 150){
						movedx = 0;
						prevXmov = "Right";
						setDirection("Put");
					}
				}
				x+=velX;
				movedx+=velX;
			}
			/////////////////////////////////
			else if(getDirection().equals("Left")){
				if(moving_alot_x == true) {
					if(movedx == 300){
						movedx = 0;
						prevXmov = "Left";
						setDirection("Put");
					}
				}
				else {
					if(movedx == 150){
						movedx = 0;
						prevXmov = "Left";
						setDirection("Put");
					}
				}
				x-=velX;
				movedx+=velX;
			}
			////////////////////////////////////
			else if(getDirection().equals("Put")) {
				if(moving_alot_y == true) {
					if(movedy == 100){
						if(prevXmov.equals("Right")) {
							setDirection("Left");
						}
						else {
							setDirection("Right");
						}
					}	
				}
				else {
					if(movedy == 50){
						if(prevXmov.equals("Right")) {
							setDirection("Left");
						}
						else {
							setDirection("Right");
						}
					}
				}
			}
			///////////////////////////////////
			//Move in Y Circle
			if(moving_alot_y == true) {
				if(movedy == 100){
					movedy = 0;	
					movingup = !movingup;
				}
			}
			else {
				if(movedy == 50){
					movedy = 0;	
					movingup = !movingup;
				}
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
	
	protected void checkHorizontal() {
        boolean toRight = getDirection().equals("Right");

        Rectangle mushroomBounds = toRight ? getRightBounds() : getLeftBounds();


        for(BaseDynamicEntity enemy : handler.getMap().getEnemiesOnMap()){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (mushroomBounds.intersects(enemyBounds) && enemy instanceof Player) {
                if(toRight) {
                    setDirection("Left");
                    setX(enemy.getX() - getDimension().width);
                }
                else{
                    setDirection("Right");
                    setX(enemy.getX() + enemy.getDimension().width);
                }
            }
        }

    }
}
