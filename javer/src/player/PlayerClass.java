package javer.src.player;

import javer.src.engine.EngineGraphic;
import javer.src.engine.constants.DIRECTION;
import javer.src.engine.constants.ENTITY_ACTION;
import javer.src.utils.Constants;

// TODO: rename to "Player"
public class PlayerClass extends EngineGraphic {
    DIRECTION direction;
    ENTITY_ACTION entityAction;
    private int animationTick, animationIndex, animationSpeed = 25;
    private int playerAction = Constants.Player.IDLE;
    private boolean isMoving = false, isAttacking = false;
    private boolean left, top, right, down;
    private float speed = 2.0f;

    public PlayerClass(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    private void changeDirection(){

    }

}
