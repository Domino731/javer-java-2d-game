package javer.src;

import javax.swing.*;

// window which is responsible for game content
public class GameWindow {
    private JFrame jFrame;
    public GameWindow(int width, int height, GamePanel gamePanel){
          jFrame = new JFrame();
          jFrame.setSize(width, height);
          jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          jFrame.add(gamePanel);
          // center the window
          jFrame.setLocationRelativeTo(null);
          jFrame.setVisible(true);

          System.out.println("Game window created " + width + " x " + height);
    }
}
