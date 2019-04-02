package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Images;

public class DeathBlock extends BaseDynamicEntity {

    public DeathBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.deathBlock);
    }

//    @Override
//    public void tick(){
//            if (falling) {
//                y = (int) (y + velY);
//                velY = velY + gravityAcc;
//                checkFalling();
//            }
//            checkHorizontal();
//            move();
//        
//    }

}
