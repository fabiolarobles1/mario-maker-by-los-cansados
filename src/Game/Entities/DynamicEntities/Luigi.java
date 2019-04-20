package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

import Game.GameStates.State;

public class Luigi extends Player{

	private	boolean doubleJump = false;
	private boolean floating = false;
	long time;

	public  Animation mswl = new Animation(160, Images.marioSmallWalkLeft);
	public  Animation mswr = new Animation(160, Images.marioSmallWalkRight);
	public  Animation msjl = new Animation(160, Images.marioSmallJumpLeft);
	public  Animation msjr = new Animation(160, Images.marioSmallJumpRight);
	public  Animation mbwl = new Animation(160, Images.marioBigWalkLeft);
	public  Animation mbwr = new Animation(160, Images.marioBigWalkRight);
	public  Animation mbrl = new Animation(160, Images.marioBigRunLeft);
	public  Animation mbrr = new Animation(160, Images.marioBigRunRight);
	public  Animation mbjl = new Animation(160, Images.marioBigJumpLeft);
	public  Animation mbjr = new Animation(160, Images.marioBigJumpRight);

	public  Animation lswl = new Animation(160, Images.luigiSmallWalkLeft);
	public  Animation lswr = new Animation(160, Images.luigiSmallWalkRight);
	public  Animation lsjl = new Animation(160, Images.luigiSmallJumpLeft);
	public  Animation lsjr = new Animation(160, Images.luigiSmallJumpRight);
	public  Animation lbwl = new Animation(160, Images.luigiBigWalkLeft);
	public  Animation lbwr = new Animation(160, Images.luigiBigWalkRight);
	public  Animation lbrl = new Animation(160, Images.luigiBigRunLeft);
	public  Animation lbrr = new Animation(160, Images.luigiBigRunRight);
	public  Animation lbjl = new Animation(160, Images.luigiBigJumpLeft);
	public  Animation lbjr = new Animation(160, Images.luigiBigJumpRight);

	public  Animation wswl = new Animation(160, Images.warioSmallWalkLeft);
	public  Animation wswr = new Animation(160, Images.warioSmallWalkRight);
	public  Animation wsjl = new Animation(160, Images.warioSmallJumpLeft);
	public  Animation wsjr = new Animation(160, Images.warioSmallJumpRight);
	public  Animation wbwl = new Animation(160, Images.warioBigWalkLeft);
	public  Animation wbwr = new Animation(160, Images.warioBigWalkRight);
	public  Animation wbrl = new Animation(160, Images.warioBigRunLeft);
	public  Animation wbrr = new Animation(160, Images.warioBigRunRight);
	public  Animation wbjl = new Animation(160, Images.warioBigJumpLeft);
	public  Animation wbjr = new Animation(160, Images.warioBigJumpRight);


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
		if(State.isLuigi_enabledp2() == true) {
			if(!grabbed) {
				super.tick();
				if (!this.getHit()) {
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
		if(State.isMario_enabledp2() == true
				|| State.isWario_enabledp2() == true) {
			if(!grabbed) {
				super.tick();

				if (!this.getHit()) {
					if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_CONTROL) && !handler.getKeyManager().up2 && !handler.getKeyManager().down2) {
						this.jump();

					}if(jumping && handler.getKeyManager().keyJustPressed(KeyEvent.VK_PERIOD) && !handler.getKeyManager().up2 && !handler.getKeyManager().down2 && doubleJump==false) {
						this.doubleJump();
						doubleJump =true;
					}
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
					doubleJump=false;
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
		if(State.isLuigi_enabledp2() == true) {
			if(!grabbed) {
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
								g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Right") && moving) {
								g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
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
		}
		if(State.isMario_enabledp2() == true) {
			if (!isBig) {
				if (handler.getKeyManager().up2) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.marioSmallJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioSmallJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down2) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.marioSmallJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioSmallJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(mswl.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(mswr.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.marioSmallWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.marioSmallWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioSmallJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioSmallJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioSmallJumpRight[1], x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up2) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioBigJumpLeft[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioBigJumpRight[4], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down2) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioBigJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioBigJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(mbrl.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(mbwl.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.marioBigWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(mbrr.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(mbwr.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.marioBigWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.marioBigJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.marioBigJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.marioBigJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.marioBigJumpRight[1], x, y, width, height, null);
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
						g2.drawImage(Images.marioBigJumpRight[4], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioBigJumpLeft[4], x, y, width, height, null);
					}
				}
			}
		}
		if(State.isWario_enabledp2() == true) {
			if (!isBig) {
				if (handler.getKeyManager().up2) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.warioSmallJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.warioSmallJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down2) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.warioSmallJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.warioSmallJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(wswl.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(wswr.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.warioSmallWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.warioSmallWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.warioSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.warioSmallJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.warioSmallJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.warioSmallJumpRight[1], x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up2) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.warioBigJumpLeft[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.warioBigJumpRight[4], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down2) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.warioBigJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.warioBigJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(wbrl.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(wbwl.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.warioBigWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(wbrr.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(wbwr.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.warioBigWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.warioBigJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.warioBigJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.warioBigJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.warioBigJumpRight[1], x, y, width, height, null);
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
						g2.drawImage(Images.warioBigJumpRight[4], x, y, width, height, null);
					} else {
						g2.drawImage(Images.warioBigJumpLeft[4], x, y, width, height, null);
					}
				}
			}
		}
	
	}
	public void doubleJump() {
		if(jumping && !falling ){
			velY=10;
			handler.getGame().getMusicHandler().playJump();	
		}
	}
	public void floating( ) {

		if(jumping && !falling ){
			handler.getGame().getMusicHandler().playStomp();
			floating = true;
			falling = false;
			jumping = false;
			time = System.currentTimeMillis();


		}}
}
