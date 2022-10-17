package javer.src;

import javer.src.inputs.KeyboardInputs;
import javer.src.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Engine engine;

    public GamePanel(Engine engine){
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        this.engine = engine;
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        System.out.println("GamePanel created");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        engine.render(g);
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize( size);
    }

    public Engine getEngine(){
        return engine;
    }
}
