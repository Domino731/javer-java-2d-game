package javer.src;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(int width, int height){
          jFrame = new JFrame();
          jFrame.setSize(width, height);
          jFrame.setVisible(true);

          System.out.println("Game window created " + width + " x " + height);
    }
}
