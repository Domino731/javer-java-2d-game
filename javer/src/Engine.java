package javer.src;

import javer.src.entities.Player;

import java.awt.*;

// Engine class with game panel & window
public class Engine implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILE_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
    // Entities
    private Player player;

    // on start, create the game panel and game window classes
    public Engine(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(GAME_WIDTH, GAME_HEIGHT, gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
        System.out.println("Engine started");
    }

    private void initClasses(){
        player = new Player(100, 100);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
       player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost() {
        player.resetDirectionsBooleans();
    }
}
