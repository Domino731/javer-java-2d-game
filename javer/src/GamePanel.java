package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;

    private BufferedImage img, subImg;

    public GamePanel(){
        setPanelSize();
        importImage();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        System.out.println("GamePanel created");
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
        subImg = img.getSubimage(0, 0, 64, 40);
        g.drawImage(subImg, (int) xDelta,(int) yDelta, 128, 80, null);
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize( size);
    }
}
