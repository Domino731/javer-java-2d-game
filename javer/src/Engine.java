package javer.src;

// Engine class with game panel & window
public class Engine {
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    // on start, create the game panel and game window classes
    public Engine(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(400, 400, gamePanel);
        gamePanel.requestFocus();

        System.out.println("Engine started");
    }
}
