package Game.Entities.DynamicEntities;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Game.GameStates.State;
import Main.Handler;
import Resources.Images;

public class Luigi extends Player{
	private boolean floating = false;
	private boolean extra = false;
	long time;
	public Luigi(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.luigiSmallWalkRight[0]
				, lswl
				, lswr
				, lbwl
				, lbwr
				, lbrl
				, lbrr);

		if(isBig){
			this.y-=8;
			this.height+=8;
			setDimension(new Dimension(width, this.height));
		}
	}


	@Override
	public void tick(){

		if(State.isLuigi_enabledp2() == true || State.isYoshi_enabledp2()==true) {
			if(!grabbed) {
				super.tick();
				if (!this.getHit()) {
					if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
						extra = true;
					}
					if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_CONTROL) && !handler.getKeyManager().up2 && !handler.getKeyManager().down2) {
						this.jump();
					} if (jumping && handler.getKeyManager().keyJustPressed(KeyEvent.VK_PERIOD) && !handler.getKeyManager().up2 && !handler.getKeyManager().down2 && floating==false) {
						this.floating();
					}
					if(System.currentTimeMillis()>= time+1500 && floating) {
						falling = true;
						jumping = false;
						floating = false;
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
					if (floating) {
						jumping = false;
						falling= false;
					}
					if (jumping  && velY <= 0) {
						jumping = false;
						falling = true;



					} else if (jumping) {
						velY = velY - gravityAcc;
						y = (int) (y - velY);
					}

					if (falling) {
						y = (int) (y + velY);
						velY = velY + gravityAcc;
						floating=false;
					}
					x += velX;
				} else {
					this.setX(this.getX() - 30);
					this.setY(this.getY() - 30);
				}
			}

		}


	}

	public void drawLuigi(Graphics2D g2) {


		if(!grabbed) {
			if(State.isLuigi_enabledp2() == true) {
				if(floating) {
					if (!isBig) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiSmallJumpRight[0], x, y, width, height, null);
						}
					}else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiBigJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiBigJumpRight[0], x, y, width, height, null);
						}

					}
				}else {

					if (!isBig) {
						if (handler.getKeyManager().up2) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiSmallJumpLeft[2], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiSmallJumpRight[2], x, y, width, height, null);
							}
						} else if (handler.getKeyManager().down2) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiSmallJumpLeft[3], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiSmallJumpRight[3], x, y, width, height, null);
							}
						} else if (!jumping && !falling ) {
							if (facing.equals("Left") && moving ) {
								g2.drawImage(lswl.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Right") && moving) {
								g2.drawImage(lswr.getCurrentFrame(), x, y, width, height, null);
							}
							if (facing.equals("Left") && !moving ) {
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
							if (handler.getKeyManager().up2) {
								if (facing.equals("Left")) {
									g2.drawImage(Images.luigiBigJumpLeft[4], x, y, width, height, null);
								} else {
									g2.drawImage(Images.luigiBigJumpRight[4], x, y, width, height, null);
								}
							} else if (handler.getKeyManager().down2) {
								if (facing.equals("Left")) {
									g2.drawImage(Images.luigiBigJumpLeft[3], x, y, width, height, null);
								} else {
									g2.drawImage(Images.luigiBigJumpRight[3], x, y, width, height, null);
								}
							} else if (!jumping && !falling) {
								if (facing.equals("Left") && moving && running ) {
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




			else if(State.isYoshi_enabledp2() == true) {
				if(floating) {
					if (!isBig) {
						if (facing.equals("Left")) {							
							g2.drawImage(Images.yoshiSmallJumpLeft[0], x, y, width, height, null);														
						} else {
							g2.drawImage(Images.yoshiSmallJumpRight[0], x, y, width, height, null);
						}
					}else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.yoshiBigJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.yoshiBigJumpRight[0], x, y, width, height, null);
						}

					}
				}else{
					if (!isBig) {

						if (handler.getKeyManager().up2) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.yoshiSmallJumpLeft[2], x, y, width, height, null);
							} else {
								g2.drawImage(Images.yoshiSmallJumpRight[2], x, y, width, height, null);
							}
						} else if (handler.getKeyManager().down2) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.yoshiSmallJumpLeft[3], x, y, width, height, null);
							} else {
								g2.drawImage(Images.yoshiSmallJumpRight[3], x, y, width, height, null);
							}
						} else if (!jumping && !falling) {
							if (facing.equals("Left") && moving) {
								yswl.tick();
								g2.drawImage(yswl.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Right") && moving) {
								yswr.tick();
								g2.drawImage(yswr.getCurrentFrame(), x, y, width, height, null);
							}
							if (facing.equals("Left") && !moving) {
								g2.drawImage(Images.yoshiSmallWalkLeft[0], x, y, width, height, null);
							} else if (facing.equals("Right") && !moving) {
								g2.drawImage(Images.yoshiSmallWalkRight[0], x, y, width, height, null);
							}
						} else {
							if (jumping) {
								if (facing.equals("Left")) {
									if(extra == true && handler.getKeyManager().runbutt2) {
										ysjle.tick();
										g2.drawImage(ysjle.getCurrentFrame(), x, y, width, height, null);
									}
									else {
										g2.drawImage(Images.yoshiSmallJumpLeft[0], x, y, width, height, null);
									}

								} else {
									if(extra == true && handler.getKeyManager().runbutt2) {
										ysjre.tick();
										g2.drawImage(ysjre.getCurrentFrame(), x, y, width, height, null);
									}
									else {
										g2.drawImage(Images.yoshiSmallJumpRight[0], x, y, width, height, null);
									}
								}

							} else {
								if (facing.equals("Left")) {
									g2.drawImage(Images.yoshiSmallJumpLeft[1], x, y, width, height, null);
								} else {
									g2.drawImage(Images.yoshiSmallJumpRight[1], x, y, width, height, null);
								}
							}
						}
					} else {
						if (!changeDirrection) {
							if (handler.getKeyManager().up2) {
								if (facing.equals("Left")) {
									g2.drawImage(Images.yoshiBigJumpLeft[4], x, y, width, height, null);
								} else {
									g2.drawImage(Images.yoshiBigJumpRight[4], x, y, width, height, null);
								}
							} else if (handler.getKeyManager().down2) {
								if (facing.equals("Left")) {
									g2.drawImage(Images.yoshiBigJumpLeft[3], x, y, width, height, null);
								} else {
									g2.drawImage(Images.yoshiBigJumpRight[3], x, y, width, height, null);
								}
							} else if (!jumping && !falling) {
								if (facing.equals("Left") && moving && running) {
									ybrl.tick();
									g2.drawImage(ybrl.getCurrentFrame(), x, y, width, height, null);
								} else if (facing.equals("Left") && moving && !running) {
									ybwl.tick();
									g2.drawImage(ybwl.getCurrentFrame(), x, y, width, height, null);
								} else if (facing.equals("Left") && !moving) {
									g2.drawImage(Images.yoshiBigWalkLeft[0], x, y, width, height, null);
								} else if (facing.equals("Right") && moving && running) {
									ybrr.tick();
									g2.drawImage(ybrr.getCurrentFrame(), x, y, width, height, null);
								} else if (facing.equals("Right") && moving && !running) {
									ybwr.tick();
									g2.drawImage(ybwr.getCurrentFrame(), x, y, width, height, null);
								} else if (facing.equals("Right") && !moving) {
									g2.drawImage(Images.yoshiBigWalkRight[0], x, y, width, height, null);
								}
							} else {
								if (jumping) {
									if (facing.equals("Left")) {
										g2.drawImage(Images.yoshiBigJumpLeft[0], x, y, width, height, null);
									} else {
										g2.drawImage(Images.yoshiBigJumpRight[0], x, y, width, height, null);
									}

								} else {
									if (facing.equals("Left")) {
										g2.drawImage(Images.yoshiBigJumpLeft[1], x, y, width, height, null);
									} else {
										g2.drawImage(Images.yoshiBigJumpRight[1], x, y, width, height, null);
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
								g2.drawImage(Images.yoshiBigJumpRight[4], x, y, width, height, null);
							} else {
								g2.drawImage(Images.yoshiBigJumpLeft[4], x, y, width, height, null);
							}
						}
					}
				}
			}
		}

	}






	public void floating( ) {

		if(jumping && !falling ){
			handler.getGame().getMusicHandler().playStomp();
			floating = true;
			falling = false;
			jumping = false;
			time = System.currentTimeMillis();

		}
	}
}
