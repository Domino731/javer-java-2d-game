package javer.src;

public class Engine {
    private GameWindow gameWindow;
    public Engine(){
        gameWindow = new GameWindow(400, 400);
        System.out.println("Engine started");
    }
}
