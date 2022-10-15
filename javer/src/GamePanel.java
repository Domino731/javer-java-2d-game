package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;

    public GamePanel(){
        mouseInputs = new MouseInputs();

        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        System.out.println("GamePanel created");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(100, 100, 100, 100);
    }
}
