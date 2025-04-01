package ccprog3_mco;

import javax.swing.*;
import java.awt.*;
/**
 * Class: GamePanel
 * Description: Main game panel that contains the game view and runs the game loop
 *  References: https://www.youtube.com/watch?v=jzCxywhTAUI
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */
public class GamePanel extends JPanel implements Runnable 
{
    /* parameters of GamePanel
     * FPS : Target frames per second
     * gameThread : Game loop thread
     * model : Game model
     * view : Game view
     * controller : Game controller
     */
    private final int FPS = 999999999;
    private Thread gameThread;
    private GameModel model;
    private GameView view;
    private GameController controller;
    
    /**
     * Method: GamePanel
     * Description: Constructs a GamePanel and initializes MVC components
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public GamePanel() {
        model = new GameModel();
        view = new GameView(model);
        controller = new GameController(model, view);
        view.setController(controller);
        
        setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        
        launchGame();
    }
    
    /**
     * Method: launchGame
     * Description: Launches the game thread
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * Method: run
     * Description: Main game loop
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            if (delta >= 1) {
                controller.update(); // Update game state
                view.repaint(); // Repaint the view
                delta--;
            }
        }
    }
    
}