package ccprog3_mco;
import javax.swing.JFrame;

/**
 * Class: GameDriver
 * Description: Main class that launches the Jungle King game application
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 */
public class GameDriver {
    /**
     * Method: main
     * Description: Application entry point
     * @param args : Command line arguments (unused)
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public static void main(String[] args) {
        // Create and configure main window
        JFrame window = new JFrame("Jungle King");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
        // Create and add game panel
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        
        // Center window and make visible
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}