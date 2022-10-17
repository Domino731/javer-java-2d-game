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
    private boolean isMoving = false;
    private boolean left, top, right, down;
    private float speed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
        updatePosition();
      updateAnimationTick();
      setAnimations();
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

        isMoving = false;

        // horizontal moving
       if(left && !right){
           x -= speed;
           isMoving = true;
       } else if(right && !left){
            x += speed;
           isMoving = true;
        }

       // vertical
        if(top && !down){
            y -= speed;
            isMoving = true;
        } else if(!top && down){
            y += speed;
            isMoving = true;
        }
    }

    // getters
    public boolean getLeft(){
        return left;
    }
    public boolean getTop(){
        return top;
    }
    public boolean getRight(){
        return right;
    }
    public boolean getDown(){
        return down;
    }

    // setters
    public void setLeft(boolean v){
        left = v;
    }
    public void setUp(boolean v){
         top = v;
    }
    public void setRight(boolean v){
        right = v;
    }
    public void setDown(boolean v){
        down = v;
    }

    public void resetDirectionsBooleans() {
        top = false;
        right = false;
        down = false;
        left = false;
    }
}
