package javer.src;

import javer.src.inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel(){
        addKeyListener(new KeyboardInputs());
        System.out.println("GamePanel created");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawRect(100, 100, 100, 100);
    }
}
