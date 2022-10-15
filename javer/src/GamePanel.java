package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100;
    // fps counter
    private int  frames = 0;
    private long lastCheck = 0;

    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect( xDelta,  yDelta, 100, 100);

        checkFps();
        repaint();
    }
}
