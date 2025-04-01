package ccprog3_mco;

import javax.swing.*;

/**
 * Class: GameController
 * Description: Main controller class that manages game flow and user interactions
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */
public class GameController {
    /* parameters of GameController
     * model : The GameModel being controlled
     * view : The GameView being updated
     * mouse : Handles mouse movement and clicks
     */
    private GameModel model;
    private GameView view;
    private MouseMovement mouse;

    /**
     * Method: GameController
     * Description: Constructs a GameController with specified model and view
     * @param model : The GameModel to control
     * @param view : The GameView to update
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.mouse = new MouseMovement();
        setupMouseListeners();
    }
    
    /**
     * Method: setupMouseListeners
     * Description: Sets up mouse listeners for the view
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    private void setupMouseListeners() {
        view.addMouseListener(mouse);
        view.addMouseMotionListener(mouse);
    }
    
    /**
     * Method: update
     * Description: Main game update loop called repeatedly during gameplay
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void update() {
        if (model.getBoard() == null) return;
        
        if (mouse.mousePressed && model.getActivePiece() == null) { //If the current mouse click has a piece
            // Handle piece selection
            int row = mouse.y / Board.SQUARE_SIZE;
            int col = mouse.x / Board.SQUARE_SIZE;
            Tile clickedTile = model.getBoard().getTile(row, col);
            
            if (clickedTile != null && clickedTile.getPiece() != null  &&
                clickedTile.getPiece().getPlayerId() == model.getCurrentPlayer()) 
            {
                model.setActivePiece(clickedTile.getPiece()); //If the current player tries to move their own piece
            }
        } 
        else if (!mouse.mousePressed && model.getActivePiece() != null) {
            // Handle piece movement
            int row = mouse.y / Board.SQUARE_SIZE;
            int col = mouse.x / Board.SQUARE_SIZE;
            
            if (model.tryMovePiece(row, col)) //Switches turns after movement
            {
                view.updateTurnIndicator();
            }
            model.setActivePiece(null); //Resets for next movement
        }
    }
    
    /**
     * Method: handleNextButton
     * Description: Handles the Next button click on the name screen
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void handleNextButton() {
        model.setPlayerNames(view.getPlayer1NameInput(), view.getPlayer2NameInput());
        view.setupAnimalScreen();
    }
    
    /**
     * Method: handleAnimalSelection
     * Description: Handles animal selection during setup phase
     * @param index : The index of the selected animal
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void handleAnimalSelection(int index) {
        if (model.getPlayer1Animal() == null) //When p1 has yet to select
        {
            model.selectAnimal(1, index);
            JOptionPane.showMessageDialog(null, model.getPlayer1Animal(),model.getPlayer1Name()+" selected", JOptionPane.INFORMATION_MESSAGE); //shows a message
            view.updateAnimalSelection(1, model.getPlayer1Animal()); //updates selection
            view.disableAnimalButton(index); //So that the other person cant pick the same card
        } else if (model.getPlayer2Animal() == null) //When p2 has yet to select
        {
            model.selectAnimal(2, index);
            JOptionPane.showMessageDialog(null, model.getPlayer2Animal(),model.getPlayer2Name()+" selected", JOptionPane.INFORMATION_MESSAGE); //shows an error message
            view.updateAnimalSelection(2, model.getPlayer2Animal());//updates selection
            view.disableAnimalButton(index);

            if (model.bothAnimalsSelected()) //Once selection is finished
            {
                view.revealAllAnimals(); //Shows the placement of each animals
                String result = model.determineWinner();
                JOptionPane.showMessageDialog(view, result, "Battle Result", JOptionPane.INFORMATION_MESSAGE); //Shows who won
                int firstPlayer = model.determineIntWinner(); //first player is whoever wins
                model.setCurrentPlayer(firstPlayer); //sets player to move first
                model.initializeGame();
                view.startGame(); //Starts the game
            }
        }
    }
    
    /**
     * Method: getMouseX
     * Description: Gets the current mouse x-position
     * @return int : The x-coordinate
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int getMouseX(){ 
        return mouse.x; 
    }
    
    /**
     * Method: getMouseY
     * Description: Gets the current mouse y-position
     * @return int : The y-coordinate
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int getMouseY(){ 
        return mouse.y; 
    }
}