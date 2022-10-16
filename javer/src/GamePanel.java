package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;
import javer.src.utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javer.src.utils.Constants.Player.GetPlayerSpriteAmount;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    private BufferedImage img, subImg;

    // animations
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = Constants.Player.IDLE;

    public GamePanel(){
        setPanelSize();
        importImage();
        loadAnimations();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        System.out.println("GamePanel created");
    }

    private void loadAnimations() {
        // 5 is first level in sprite
        animations = new BufferedImage[7][8];
        for (int j = 0; j < animations.length; j++ ){
            for (int i = 0; i < animations[j].length; i++ ){
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40 );
            }
        }

    }

    private void importImage(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
           img = ImageIO.read(is);
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

    public void changeXDelta(int value) {
        xDelta += value;
    }

    public void changeYDelta(int value){
        yDelta += value;
    }

    public void setDelta(int x, int y){
        xDelta = x;
        yDelta = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        g.drawImage(animations[playerAction][animationIndex], (int) xDelta,(int) yDelta, 128, 80, null);
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

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize( size);
    }
}
