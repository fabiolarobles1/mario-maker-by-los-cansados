package Game.Entities.DynamicEntities;

import java.awt.Rectangle;

import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class FlyingGoomba extends AnnoyingPest{

	public FlyingGoomba(int x, int y, int width, int height, Handler handler) {
		super(x, y, 80, 50,handler,new Animation(160, Images.Lannoyingpest),new Animation(160, Images.Rannoyingpest));
	}
	protected void checkHorizontal() {
        boolean toRight = getDirection().equals("Right");

        Rectangle mushroomBounds = toRight ? getRightBounds() : getLeftBounds();

        for (BaseStaticEntity brick : handler.getMap().getBlocksOnMap()) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (mushroomBounds.intersects(brickBounds)) {
                if(toRight) {
                    setDirection("Left");
                    setX(brick.getX() - getDimension().width);
                }
                else{
                    setDirection("Right");
                    setX(brick.getX() + brick.getDimension().width);
                }
            }
        }

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
