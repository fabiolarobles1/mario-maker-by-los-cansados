package Game.GameStates;

import Display.UI.UIStringButton;
import Main.Handler;
import Resources.Images;
import Display.UI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class WinState extends State {

    private UIManager uiManager;

    public WinState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);


        uiManager.addObjects(new UIStringButton(56 + 100 + 100 + 32, (223+(64+16) +(64+16) + (64+16)), 128, 64, "Back to Title", () -> {
            handler.getMouseManager().setUimanager(null);
            handler.setIsInMap(false);
            State.setState(handler.getGame().menuState);
        },handler,Color.GREEN));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.Over[0],0,0,handler.getWidth(),handler.getHeight(),null);
        g.setFont(new Font("AR ESSENCE", Font.PLAIN, 20));
        uiManager.Render(g);
    }
}
