package Game.World;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Game.Entities.DynamicEntities.AnnoyingPest;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.Coin;
import Game.Entities.DynamicEntities.DeathBlock;
import Game.Entities.DynamicEntities.FloatingBlock;
import Game.Entities.DynamicEntities.FlyingGoomba;
import Game.Entities.DynamicEntities.Goomba;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Mushroom;
import Game.Entities.StaticEntities.AlternateSurfaceBlock;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.Cloud;
import Game.Entities.StaticEntities.FinishBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.Entities.StaticEntities.RotatingMisteryBlock;
import Game.Entities.StaticEntities.SurfaceBlock;
import Main.Handler;
import Resources.Images;

public class MapBuilder {

	public static int pixelMultiplier = 48;
	public static int boundBlock = new Color(0,0,0).getRGB();
	public static int mario = new Color(255,0,0).getRGB();
	public static int surfaceBlock = new Color(255,106,0).getRGB();
	public static int alternateSurfaceBlock = new Color(209,218,82).getRGB();
	public static int breakBlock = new Color(0,38,255).getRGB();
	public static int coin = new Color(255, 154, 244).getRGB();
	public static int misteryBlock = new Color(255,216,0).getRGB();
	public static int rotatingmisteryBlock = new Color(183,252,0).getRGB();
	public static int mushroom = new Color(178,0,255).getRGB();
	public static int goomba = new Color(167,15,1).getRGB();
	public static int luigi = new Color(0,255,0).getRGB();
	public static int cloud = new Color(205, 255, 247).getRGB();
	public static int deathBlock = new Color(116, 141, 172).getRGB();
	public static int finishBlock = new Color(58, 141, 172).getRGB();
	public static int floatingBlock = new Color(215, 237, 218).getRGB();
	public static int flyinggoomba = new Color(186, 173, 80).getRGB();
	public static boolean mapDone = false; 
//205, 255, 247
	public static Map createMap(BufferedImage mapImage, Handler handler){
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == boundBlock){
					BaseStaticEntity BoundBlock = new BoundBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BoundBlock);
				}else if(currentPixel == mario){
					BaseDynamicEntity Mario = new Mario(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Mario);
				}else if(currentPixel == surfaceBlock){
					BaseStaticEntity SurfaceBlock = new SurfaceBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(SurfaceBlock);
				}else if(currentPixel == alternateSurfaceBlock){
					BaseStaticEntity AlternateSurfaceBlock = new AlternateSurfaceBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(AlternateSurfaceBlock);
				}else if(currentPixel == breakBlock){
					BaseStaticEntity BreakBlock = new BreakBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BreakBlock);
				}else if(currentPixel == coin){
					BaseDynamicEntity Coin = new Coin(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Coin);
				}else if(currentPixel == deathBlock){
					BaseDynamicEntity DeathBlock = new DeathBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(DeathBlock);
				}else if(currentPixel == misteryBlock){
					BaseStaticEntity MisteryBlock = new MisteryBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(MisteryBlock);
				}else if(currentPixel == finishBlock){
					BaseStaticEntity FinishBlock = new FinishBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(FinishBlock);
				}else if(currentPixel == floatingBlock){
					BaseDynamicEntity FloatingBlock = new FloatingBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(FloatingBlock);
				}else if(currentPixel == flyinggoomba){
					BaseDynamicEntity FlyingGoomba = new FlyingGoomba(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(FlyingGoomba);
				}else if(currentPixel == rotatingmisteryBlock){
					BaseStaticEntity RotatingMisteryBlock = new RotatingMisteryBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(RotatingMisteryBlock);
				}else if(currentPixel == cloud){
					BaseStaticEntity Cloud = new Cloud(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(Cloud);
				}else if(currentPixel == mushroom){
					BaseDynamicEntity Mushroom = new Mushroom(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Mushroom);
				}else if(currentPixel == goomba){
					BaseDynamicEntity Goomba = new Goomba(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Goomba);
				}else if(currentPixel == luigi){
					BaseDynamicEntity Luigi = new Luigi(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Luigi);
				}
			}

		}
		if(mapDone) {
			Images.makeMap(50, pixelMultiplier, mapImage.getWidth(), 100, mapInCreation, handler);
			for(int i = 96; i < 101; i++) {
				mapInCreation.addBlock(new BreakBlock(49*pixelMultiplier, i*pixelMultiplier,48,48,handler));
				mapInCreation.addBlock(new BreakBlock(54*pixelMultiplier, i*pixelMultiplier,48,48,handler));
			}
		}
		return mapInCreation;
	}

}
