package javer.src.entities;

import javer.src.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javer.src.utils.Constants.Player.GetPlayerSpriteAmount;

public class Player extends Entity{
    // animations
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 25;
    private int playerAction = Constants.Player.IDLE;
    private int playerDirection = -1;
    private boolean isMoving = false;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
      updateAnimationTick();
      setAnimations();
      updatePosition();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex], (int) x,(int) y, 128, 80, null);
    }

    // load animations from sprite (player_sprites.png) and them to animations array
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            BufferedImage  img = ImageIO.read(is);
            // 5 is first level in sprite
            animations = new BufferedImage[7][8];
            for (int j = 0; j < animations.length; j++ ){
                for (int i = 0; i < animations[j].length; i++ ){
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40 );
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDirection(int direction ){
        isMoving = true;
        this.playerDirection = direction;
    }

    public void changeIsMoving(boolean isMoving){
        this.isMoving = isMoving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= GetPlayerSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }
    }


    private void setAnimations() {
        if(isMoving){
            playerAction = Constants.Player.RUNNING;
        }
        else {
            playerAction = Constants.Player.IDLE;
        }
    }

    private void updatePosition() {
        if(isMoving){
            switch (playerDirection){
                case Constants.Directions.LEFT:
                    x -= 5;
                    break;
                case Constants.Directions.UP:
                    y -= 5;
                    break;
                case Constants.Directions.RIGHT:
                    x += 5;
                    break;
                case Constants.Directions.DOWN:
                    y +=5;
                    break;
            }
        }

    }
}
