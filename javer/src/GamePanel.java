package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
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

    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
        System.out.println("GamePanel created");
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
        updateRectangle();
        super.paintComponent(g);
        g.setColor(color);
        g.drawRect( (int)xDelta,  (int)yDelta, 100, 100);

        checkFps();
    }

}
