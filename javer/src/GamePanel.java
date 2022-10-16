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
    private float xDir = 0.1f, yDir = 0.1f;
    private Color color = new Color(255,50,90);
    // fps counter
    private int  frames = 0;
    private long lastCheck = 0;
    private Random random;

    private BufferedImage img, subImg;

    public GamePanel(){
        setPanelSize();
        importImage();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
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

    private void checkFps(){
        frames++;
        if(System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }

    private void updateRectangle(){
      xDelta += xDir;
      if(xDelta > 400 || xDelta < 0){
          xDir *= -1;
          color = getRandomColor();
      }

      yDelta += yDir;
      if(yDelta > 400 || yDelta < 0){
          yDir *= -1;
          color = getRandomColor();
      }
    }
    private Color getRandomColor(){
        int bound = 255;
        int r = random.nextInt(bound);
        int g = random.nextInt(bound);
        int b = random.nextInt(bound);
        return new Color(r,g,b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        subImg = img.getSubimage(0, 0, 64, 40);
        g.drawImage(subImg, (int) xDelta,(int) yDelta, 128, 80, null);
        checkFps();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize( size);
    }
}
