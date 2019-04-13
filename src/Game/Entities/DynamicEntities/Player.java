package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.FinishBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.Entities.StaticEntities.RotatingMisteryBlock;
import Game.Entities.StaticEntities.SurfaceBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.GameStates.GameOverState;
import Game.GameStates.State;;

public class Player extends BaseDynamicEntity {

	protected double velX,velY;
	private boolean hit = false;
	public boolean grabbed =false;

	public static int mariocoins = 0;
	public static int luigicoins = 0;

	public static boolean mariowins = false;
	public static boolean luigiwins = false;

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
			if (entity != null && this.getBounds().intersects(entity.getBounds()) && entity instanceof Mushroom && !isBig) {
				isBig = true;
				this.y -= 8;
				this.height += 8;
				setDimension(new Dimension(width, this.height));
				((Item) entity).used = true;
				entity.y = -100000;
			}
			else if (entity != null && this.getBounds().intersects(entity.getBounds()) && entity instanceof Item) {
				if (entity instanceof Coin) {
					handler.getGame().getMusicHandler().playCoin();
					if (this instanceof Mario) {
						mariocoins++;
						if(mariocoins<=1) {
							System.out.println(mariocoins + " coin for Mario");
						}else {
							System.out.println(mariocoins + " coins for Mario");
						}
					}
					else if(this instanceof Luigi) {
						luigicoins++;
						if(luigicoins<=1) {
							System.out.println(luigicoins + " coin for Luigi");
						}else {
							System.out.println(luigicoins + " coins for Luigi");
						}
					}
				}        	 
				setDimension(new Dimension(width, this.height));
				((Item) entity).used = true;
				entity.y = -100000;
			}
		}
	}


	public void checkBottomCollisions() {
		Player player = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

		Rectangle playerBottomBounds =player.getBottomBounds();

		if (!player.jumping) {
			falling = true;
		}

		for (BaseStaticEntity brick : bricks) {
			Rectangle brickTopBounds = brick.getTopBounds();
			if(playerBottomBounds.intersects(brickTopBounds)
					&& (brick instanceof FinishBlock)){
				if(State.isMultiplayer() == false) {
					mariowins = true;
					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}else{

					if (this instanceof Mario) {mariowins = true;}
					else {luigiwins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
			if (playerBottomBounds.intersects(brickTopBounds)
					&& !(brick instanceof BoundBlock) ) {
				player.setY(brick.getY() - player.getDimension().height + 1);
				falling = false;
				velY=0;
			}

			 if (playerBottomBounds.intersects(brickTopBounds)
					&& (brick instanceof BoundBlock)) {
				if(!State.isMultiplayer()) {
					State.setState(handler.getGame().gameoverState);
					handler.getGame().getMusicHandler().pauseBackground();
					handler.getGame().getMusicHandler().playmarioDies();
					handler.getMap().reset();
				}
				else {
					if (this instanceof Mario) {luigiwins = true;}
					else {mariowins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}

			}
			
		}

		for (BaseDynamicEntity enemy : enemies) {
			Rectangle enemyTopBounds = enemy.getTopBounds();
			if (playerBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof Item)) {
				if (playerBottomBounds.intersects(enemyTopBounds) && isBig 
						&& enemy instanceof DeathBlock
						&& enemy instanceof Goomba) {
					isBig = false;
					this.y += 8;
					this.height -= 8;
					setDimension(new Dimension(width, this.height));
					break;
				}
				if (playerBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof DeathBlock)) {


					if(!enemy.ded) {
						handler.getGame().getMusicHandler().playStomp();
					}
					enemy.kill();
					falling=false;
					velY=0;
				}
				else if (playerBottomBounds.intersects(enemyTopBounds) && (enemy instanceof DeathBlock)) {
					if(!State.isMultiplayer()) {
						State.setState(handler.getGame().gameoverState);
						handler.getGame().getMusicHandler().pauseBackground();
						handler.getGame().getMusicHandler().playmarioDies();
						handler.getMap().reset();
					}
					else {
						if (this instanceof Mario) {luigiwins = true;}
						else {mariowins = true;}

						State.setState(handler.getGame().winState);
						handler.getMap().reset();
					}
				}
			}
		}
	}

	public void checkTopCollisions() {
		Player player = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

		Rectangle playerTopBounds = player.getTopBounds();
		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBottomBounds = brick.getBottomBounds();
			if (playerTopBounds.intersects(brickBottomBounds)
					&& (brick instanceof FinishBlock)) {
				if(!State.isMultiplayer()) {
					mariowins = true;
					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}else{

					if (this instanceof Mario) {mariowins = true;}
					else {luigiwins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
			if (playerTopBounds.intersects(brickBottomBounds)
					&& !(brick instanceof BoundBlock)) {
				velY=0;
				player.setY(brick.getY() + brick.height);
				if(brick instanceof MisteryBlock) {
					((MisteryBlock)brick).sprite = Images.surfaceBlock;


					if (this instanceof Mario && !((MisteryBlock)brick).hit) {
						mariocoins++;
						handler.getGame().getMusicHandler().playCoin();
						System.out.println(mariocoins + " coin for Mario");
					}
					else if(this instanceof Luigi && !((MisteryBlock)brick).hit) {
						luigicoins++;
						handler.getGame().getMusicHandler().playCoin();
						System.out.println(luigicoins + " coin for Luigi");
					}
					((MisteryBlock)brick).hit = true;
				}
			}


			 if (playerTopBounds.intersects(brickBottomBounds)
					&& (brick instanceof BoundBlock)) {
				if(!State.isMultiplayer()) {
					State.setState(handler.getGame().gameoverState);
					handler.getGame().getMusicHandler().pauseBackground();
					handler.getGame().getMusicHandler().playmarioDies();
					handler.getMap().reset();
				}
				else {
					if (this instanceof Mario) {luigiwins = true;}
					else {mariowins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
	
		}

		for (BaseDynamicEntity enemy : enemies) {
			Rectangle enemyBottomBounds = enemy.getBottomBounds();
			if (playerTopBounds.intersects(enemyBottomBounds) && !(enemy instanceof Item) ) {
				if (playerTopBounds.intersects(enemyBottomBounds) && isBig) {
					isBig = false;
					this.y += 8;
					this.height -= 8;
					setDimension(new Dimension(width, this.height));
					break;
				}
				if(!State.isMultiplayer()) {
					State.setState(handler.getGame().gameoverState);
					handler.getGame().getMusicHandler().pauseBackground();
					handler.getGame().getMusicHandler().playmarioDies();
					handler.getMap().reset();
				}
				else {
					if (this instanceof Mario) {luigiwins = true;}
					else {mariowins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
		}
	}

	public void checkMarioHorizontalCollision(){
		Player player = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies = handler.getMap().getEnemiesOnMap();

		boolean playerDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle playerBounds = toRight ? player.getRightBounds() : player.getLeftBounds();

		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
			if(playerBounds.intersects(brickBounds)
					&& brick instanceof FinishBlock){
				if(State.isMultiplayer() == false) {
					mariowins = true;
					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}else{

					if (this instanceof Mario) {mariowins = true;}
					else {luigiwins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
			if (playerBounds.intersects(brickBounds)
					&& (brick instanceof RotatingMisteryBlock)) {
				handler.getGame().getMusicHandler().playCoin();
				brick.y = -10000000;

			}
			if (playerBounds.intersects(brickBounds)
					&& !(brick instanceof BoundBlock)) {
				velX=0;
				if(toRight)
					player.setX(brick.getX() - player.getDimension().width);
				else
					player.setX(brick.getX() + brick.getDimension().width);

			}

			 if (playerBounds.intersects(brickBounds) && 
					brick instanceof BoundBlock) {
				if(!State.isMultiplayer()) {
					State.setState(handler.getGame().gameoverState);
					handler.getGame().getMusicHandler().playmarioDies();
					handler.getMap().reset();
				}
				else {
					if (this instanceof Mario) {luigiwins = true;}
					else {mariowins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}

			
		}

		for(BaseDynamicEntity enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (playerBounds.intersects(enemyBounds) && !(enemy instanceof Item)
					) {
				if (playerBounds.intersects(enemyBounds) && isBig) {
					isBig = false;
					this.y += 8;
					this.height -= 8;
					setDimension(new Dimension(width, this.height));
					break;
				}
				playerDies = true;
				break;
			}
			if(playerBounds.intersects(enemyBounds) 
					&& ((enemy instanceof Goomba) || (enemy instanceof DeathBlock))) {
				if(!State.isMultiplayer()) {
					State.setState(handler.getGame().gameoverState);
					handler.getMap().reset();
				}
				else {
					if (this instanceof Mario) {luigiwins = true;}
					else {mariowins = true;}

					State.setState(handler.getGame().winState);
					handler.getMap().reset();
				}
			}
		}

		if(playerDies) {
			if(!State.isMultiplayer()) {
				State.setState(handler.getGame().gameoverState);
				handler.getMap().reset();
			}
			else {
				if (this instanceof Mario) {luigiwins = true;}
				else {mariowins = true;}

				State.setState(handler.getGame().winState);
				handler.getMap().reset();
			}
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

	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}


}
