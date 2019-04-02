package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Luigi extends Player{

	
	

	public Luigi(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.luigiSmallWalkRight[0]
				,new Animation(175,Images.luigiSmallWalkLeft)
				, new Animation(175,Images.luigiSmallWalkRight)
				, new Animation(150,Images.luigiBigWalkLeft)
				, new Animation(150,Images.luigiBigWalkRight)
				, new Animation(115,Images.luigiBigRunLeft)
				, new Animation(115,Images.luigiBigRunRight));
		if(isBig){
			this.y-=8;
			this.height+=8;
			setDimension(new Dimension(width, this.height));
		}
	}

	@Override
	public void tick(){
	    if(!grabbed) {
            super.tick();
            if (!this.getHit()) {
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_CONTROL) && !handler.getKeyManager().up2 && !handler.getKeyManager().down2) {
                    this.jump();
                }

                if (handler.getKeyManager().right2 && !handler.getKeyManager().up2 && !handler.getKeyManager().down2) {
                    if (handler.getKeyManager().runbutt2) {
                        velX = 6;
                        running = true;
                    } else {
                        velX = 3;
                        running = false;
                    }
                    if (facing.equals("Left")) {
                        changeDirrection = true;
                    }
                    facing = "Right";
                    moving = true;
                } else if (handler.getKeyManager().left2 && !handler.getKeyManager().up2 && !handler.getKeyManager().down2) {
                    if (handler.getKeyManager().runbutt2) {
                        velX = -6;
                        running = true;
                    } else {
                        velX = -3;
                        running = false;
                    }
                    if (facing.equals("Right")) {
                        changeDirrection = true;
                    }
                    facing = "Left";
                    moving = true;
                } else {
                    velX = 0;
                    moving = false;
                }
                if (jumping && velY <= 0) {
                    jumping = false;
                    falling = true;
                } else if (jumping) {
                    velY = velY - gravityAcc;
                    y = (int) (y - velY);
                }

                if (falling) {
                    y = (int) (y + velY);
                    velY = velY + gravityAcc;
                }
                x += velX;
            } else {
                this.setX(this.getX() - 30);
                this.setY(this.getY() - 30);
            }
        }
	}

	public void drawLuigi(Graphics2D g2) {
		if(!grabbed) {
			if (!isBig) {
				if (handler.getKeyManager().up) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.luigiSmallJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.luigiSmallJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.luigiSmallJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.luigiSmallJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.luigiSmallWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.luigiSmallWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiSmallJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiSmallJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiSmallJumpRight[1], x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiBigJumpLeft[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiBigJumpRight[4], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiBigJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiBigJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(playerBigLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(playerBigLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.luigiBigWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(playerBigRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(playerBigRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.luigiBigWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiBigJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiBigJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiBigJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiBigJumpRight[1], x, y, width, height, null);
							}
						}
					}
				} else {
					if (!running) {
						changeDirrection = false;
						changeDirectionCounter = 0;
						drawLuigi(g2);
					}
					if (facing.equals("Right")) {
						g2.drawImage(Images.luigiBigJumpRight[4], x, y, width, height, null);
					} else {
						g2.drawImage(Images.luigiBigJumpLeft[4], x, y, width, height, null);
					}
				}
			}
		}
	}
	
}
