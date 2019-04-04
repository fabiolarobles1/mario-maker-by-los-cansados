package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.GameStates.GameOverState;
import Game.GameStates.State;;

public class Player extends BaseDynamicEntity {

    protected double velX,velY;

    public String facing = "Left";
    public boolean moving = false;
    public Animation playerSmallLeftAnimation,playerSmallRightAnimation,playerBigLeftWalkAnimation,playerBigRightWalkAnimation,playerBigLeftRunAnimation,playerBigRightRunAnimation;
    public boolean falling = true, jumping = false,isBig=false,running = false,changeDirrection=false;
    public double gravityAcc = 0.38;
    int changeDirectionCounter=0;

    public Player(int x, int y, int width, int height, Handler handler, BufferedImage sprite,Animation PSLA,Animation PSRA,Animation PBLWA,Animation PBRWA,Animation PBLRA,Animation PBRRA) {
        super(x, y, width, height, handler, sprite);
        playerBigLeftRunAnimation=PBLRA;
        playerBigLeftWalkAnimation=PBLWA;
        playerBigRightRunAnimation=PBRRA;
        playerBigRightWalkAnimation=PBRWA;
        playerSmallLeftAnimation=PSLA;
        playerSmallRightAnimation=PSRA;
    }

    @Override
    public void tick(){

        if (changeDirrection) {
            changeDirectionCounter++;
        }
        if(changeDirectionCounter>=10){
            changeDirrection=false;
            changeDirectionCounter=0;
        }

        checkBottomCollisions();
        checkMarioHorizontalCollision();
        checkTopCollisions();
        checkItemCollision();
        if(!isBig) {
            if (facing.equals("Left") && moving) {
                playerSmallLeftAnimation.tick();
            } else if (facing.equals("Right") && moving) {
                playerSmallRightAnimation.tick();
            }
        }else{
            if (facing.equals("Left") && moving && !running) {
                playerBigLeftWalkAnimation.tick();
            } else if (facing.equals("Left") && moving && running) {
                playerBigLeftRunAnimation.tick();
            } else if (facing.equals("Right") && moving && !running) {
                playerBigRightWalkAnimation.tick();
            } else if (facing.equals("Right") && moving && running) {
                playerBigRightRunAnimation.tick();
            }
        }
    }

    private void checkItemCollision() {

        for (BaseDynamicEntity entity : handler.getMap().getEnemiesOnMap()) {
            if (entity != null && getBounds().intersects(entity.getBounds()) && entity instanceof Mushroom && !isBig) {
                isBig = true;
                this.y -= 8;
                this.height += 8;
                setDimension(new Dimension(width, this.height));
                ((Item) entity).used = true;
                entity.y = -100000;
            }
            else if (entity != null && getBounds().intersects(entity.getBounds()) && entity instanceof Item) {
            	setDimension(new Dimension(width, this.height));
                ((Item) entity).used = true;
                entity.y = -100000;
            }
        }
    }


    public void checkBottomCollisions() {
        Player mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

        Rectangle marioBottomBounds =getBottomBounds();

        if (!mario.jumping) {
            falling = true;
        }

        for (BaseStaticEntity brick : bricks) {
            Rectangle brickTopBounds = brick.getTopBounds();
            if (marioBottomBounds.intersects(brickTopBounds)
            		&& !(brick instanceof BoundBlock)) {
                mario.setY(brick.getY() - mario.getDimension().height + 1);
                falling = false;
                velY=0;
            }
            else if (marioBottomBounds.intersects(brickTopBounds)
            		&& (brick instanceof BoundBlock)) {
            	State.setState(handler.getGame().gameoverState);
            	handler.getMap().reset();
            }
        }

        for (BaseDynamicEntity enemy : enemies) {
            Rectangle enemyTopBounds = enemy.getTopBounds();
            if (marioBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof Item)) {
            	if (marioBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof DeathBlock)) {
            		
            	
                if(!enemy.ded) {
                    handler.getGame().getMusicHandler().playStomp();
                }
                enemy.kill();
                falling=false;
                velY=0;
            	}
            	else if (marioBottomBounds.intersects(enemyTopBounds) && (enemy instanceof DeathBlock)) {
            		State.setState(handler.getGame().gameoverState);
                    handler.getMap().reset();
            	}
            }
        }
    }

    public void checkTopCollisions() {
        Player mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

        Rectangle marioTopBounds = mario.getTopBounds();
        for (BaseStaticEntity brick : bricks) {
            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)
            		&& !(brick instanceof BoundBlock)) {
                velY=0;
                mario.setY(brick.getY() + brick.height);
            }
            else if (marioTopBounds.intersects(brickBottomBounds)
            		&& !(brick instanceof BoundBlock)) {
            	State.setState(handler.getGame().gameoverState);
            	handler.getMap().reset();
            }
        }
        
        for (BaseDynamicEntity enemy : enemies) {
        	Rectangle enemyBottomBounds = enemy.getBottomBounds();
        	if (marioTopBounds.intersects(enemyBottomBounds) && !(enemy instanceof Item) ) {
        		State.setState(handler.getGame().gameoverState);
            	handler.getMap().reset();
        	}
        }
    }

    public void checkMarioHorizontalCollision(){
        Player mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamicEntity> enemies = handler.getMap().getEnemiesOnMap();

        boolean marioDies = false;
        boolean toRight = moving && facing.equals("Right");

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (BaseStaticEntity brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)
            		&& !(brick instanceof BoundBlock)) {
                velX=0;
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
                
            }
            else if (marioBounds.intersects(brickBounds) && 
            		brick instanceof BoundBlock) {
            	State.setState(handler.getGame().gameoverState);
            	handler.getMap().reset();
            }
        }

        for(BaseDynamicEntity enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (marioBounds.intersects(enemyBounds) && !(enemy instanceof Item)
            		) {
                marioDies = true;
                break;
            }
            else if(marioBounds.intersects(enemyBounds) 
            		&& ((enemy instanceof Goomba) || (enemy instanceof DeathBlock))) {
            	State.setState(handler.getGame().gameoverState);
                handler.getMap().reset();
            }
        }

        if(marioDies) {
        	State.setState(handler.getGame().gameoverState);
            handler.getMap().reset();
        }
    }

    public void jump() {
        if(!jumping && !falling){
            jumping=true;
            velY=10;
            handler.getGame().getMusicHandler().playJump();
        }
    }

    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }


}
