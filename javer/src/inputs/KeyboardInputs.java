package javer.src.inputs;

import javer.src.GamePanel;
import javer.src.utils.Constants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    // constructor
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.getEngine().getPlayer().setDirection(Constants.Directions.UP);
                break;
            case KeyEvent.VK_S:
                gamePanel.getEngine().getPlayer().setDirection(Constants.Directions.DOWN);
                break;
            case KeyEvent.VK_A:
                gamePanel.getEngine().getPlayer().setDirection(Constants.Directions.LEFT);
                break;
            case KeyEvent.VK_D:
                gamePanel.getEngine().getPlayer().setDirection(Constants.Directions.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                gamePanel.getEngine().getPlayer().changeIsMoving(false);
        }
    }
}
