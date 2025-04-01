package ccprog3_mco;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Class: GameView
 * Description: Main view component for the Jungle King game that handles all GUI screens
 * including name input, animal selection, and the main game board.
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */
public class GameView extends JPanel 
{
    /* parameters of GameView
    * model : Reference to the game model
    * controller : Reference to the game controller
    * boardView : Handles board visualization
    * boardController : Handles board interactions
    * board : Reference to the game board
    * nameScreen : Panel for player name input
    * animalScreen : Panel for animal selection
    * turnPanel : Panel showing turn indicators
    * player1Field, player2Field : Text fields for player names
    * nextButton : Button to proceed from name screen
    * animalButtons : Array of animal selection buttons
    * Various labels : UI elements for instructions and status
    */
    private GameModel model;
    private GameController controller;
    private BoardView boardView;
    private BoardController boardController;
    private Board board;
    // UI components
    private JPanel nameScreen, animalScreen, turnPanel;
    private JTextField player1Field, player2Field;
    private JButton nextButton;
    private JButton[] animalButtons;
    private JLabel p1AnimalLabel, p2AnimalLabel, p1Label, p2Label, jklabel, turnIndicatorTop, turnIndicatorBottom;
    private JTextArea jklabel2;
    
    /**
    * Method: GameView
    * Description: Constructs the game view with reference to the game model
    * @param model The game model to associate with this view
    * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
    */
    public GameView(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(GameModel.W, GameModel.H));
        setBackground(Color.black);
        setLayout(new BorderLayout());
        
        setupNameScreen();
    }
    
    /**
     * Method: setupNameScreen
     * Description: Initializes the name input screen with game title, instructions,
     * and input fields for player names.
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */    
    private void setupNameScreen() {
        nameScreen = new JPanel();
        nameScreen.setLayout(new GridLayout(4, 2, 10, 10)); //grid layout
        nameScreen.setBackground(new Color(34, 139, 34));
        
        jklabel = new JLabel("Jungle King"); //Title label
        jklabel.setForeground(Color.YELLOW);
        jklabel.setFont(new Font("Calibri", Font.PLAIN, 50));
        
        jklabel2 = new JTextArea( // Instructions
            "Instructions\n" +
            "Player 1 controls the top\n" +
            "Player 2 controls the bottom.\n" +
            "First to capture the enemy base Wins!\n" +
            "Only rats can cross lake blocks while tigers and lions can jump across\n" +
            "Be careful of traps as it drains your powers and stuns you for one turn\n" +
            "Animals can only capture other animals if they have the same or\n" +
            "higher power(With the exception of Rat and Elephant)\n" +
            "Good luck and have fun!"
        );
        jklabel2.setForeground(Color.WHITE);
        jklabel2.setFont(new Font("Calibri", Font.PLAIN, 17));
        jklabel2.setBackground(new Color(34, 139, 34));
        jklabel2.setEditable(false);
        
        p1Label = new JLabel("Player 1 Name:"); //Instructions to enter name
        p1Label.setForeground(Color.WHITE);
        p1Label.setFont(new Font("Calibri", Font.PLAIN, 30));
        
        player1Field = new JTextField(); //Player1 name input
        player1Field.setPreferredSize(new Dimension(50, 50));
        player1Field.setFont(new Font("Calibri", Font.PLAIN, 40));
        player1Field.setForeground(Color.red);
        player1Field.setText("Player 1"); //Automatically set to Player 1(can be changed)
        
        p2Label = new JLabel("Player 2 Name:");
        p2Label.setForeground(Color.WHITE);
        p2Label.setFont(new Font("Calibri", Font.PLAIN, 30));
        
        player2Field = new JTextField(); //Player1 name input
        player2Field.setPreferredSize(new Dimension(100, 100));
        player2Field.setFont(new Font("Calibri", Font.PLAIN, 40));
        player2Field.setForeground(Color.BLUE);
        player2Field.setText("Player 2");//Automatically set to Player 2(can be changed)
        
        nextButton = new JButton(); //adds a button to go to the next screen
        nextButton.setBounds(0, 100, 100, 50);
        nextButton.setText("Next");
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 30));
        nextButton.addActionListener(e -> controller.handleNextButton()); //handles the screen switching
        
        //adds the labels to the screen
        nameScreen.add(jklabel);
        nameScreen.add(jklabel2);
        nameScreen.add(p1Label);
        nameScreen.add(player1Field);
        nameScreen.add(p2Label);
        nameScreen.add(player2Field);
        nameScreen.add(nextButton);
        
        add(nameScreen, BorderLayout.CENTER);
    }
    
    /**
     * Method: setupAnimalScreen
     * Description: Initializes the animal selection screen with selection buttons,
     * player selection status, and power ranking information.
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */   
    public void setupAnimalScreen() {
        remove(nameScreen); //setup new screen
        
        animalScreen = new JPanel();
        animalScreen.setLayout(new BorderLayout());
        
        JPanel animalSelectionPanel = new JPanel();
        animalSelectionPanel.setLayout(new GridLayout(3, 4, 10, 10));
        
        animalButtons = new JButton[8]; //Generates 8 buttons for each animal
        for (int i = 0; i < 8; i++) {
            int index = i;
            animalButtons[i] = new JButton(String.valueOf(i + 1));
            animalButtons[i].setFont(new Font("Calibri", Font.PLAIN, 30));
            animalButtons[i].addActionListener(e -> controller.handleAnimalSelection(index)); //handles animal selection
            animalSelectionPanel.add(animalButtons[i]); //adss to screen
        }
        
        p1AnimalLabel = new JLabel(model.getPlayer1Name() + " : Not selected"); //Player1 animal selection
        p2AnimalLabel = new JLabel(model.getPlayer2Name() + " : Not selected");//Player2 animal selection
        animalSelectionPanel.add(p1AnimalLabel);
        animalSelectionPanel.add(p2AnimalLabel);
        
        JPanel rankingPanel = new JPanel(); //Shows the power of each animal
        rankingPanel.setLayout(new GridLayout(9, 1, 5, 5));
        rankingPanel.setPreferredSize(new Dimension(200, 300));
        rankingPanel.setBackground(Color.LIGHT_GRAY);
        
        JLabel titleLabel = new JLabel("Power Ranking");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        rankingPanel.add(titleLabel);
        //Displays the Animal Rankings on the Side
        for (int i = 0; i < model.getAnimalRanks().length; i++) 
        {
            JLabel rankLabel = new JLabel((i + 1) + ". " + model.getAnimalRanks()[i]);
            rankLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
            rankLabel.setHorizontalAlignment(JLabel.CENTER);
            rankingPanel.add(rankLabel);
        }
        
        animalScreen.add(animalSelectionPanel, BorderLayout.CENTER);
        animalScreen.add(rankingPanel, BorderLayout.EAST);
        
        add(animalScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /**
     * Method: startGame
     * Description: Transitions to the main game screen with board visualization
     * and turn indicators.
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void startGame() {
        remove(animalScreen);

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add turn indicators
        turnIndicatorTop = new JLabel(model.getPlayer1().getName() + "'s Turn", SwingConstants.CENTER);
        turnIndicatorBottom = new JLabel(model.getPlayer2().getName() + "'s Turn", SwingConstants.CENTER);

        turnIndicatorTop.setFont(new Font("Calibri", Font.BOLD, 32));
        turnIndicatorBottom.setFont(new Font("Calibri", Font.BOLD, 32));

        turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(2, 1, 10, 10));
        turnPanel.setPreferredSize(new Dimension(250, 100));
        turnPanel.setBackground(Color.BLACK);

        turnPanel.add(turnIndicatorTop);
        turnPanel.add(turnIndicatorBottom);

        updateTurnIndicator();

        // Create board panel
        JPanel boardPanel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                if (model.getBoard() != null) 
                {
                	board = model.getBoard();
                    boardView = new BoardView(board);
                    boardController = new BoardController(board, boardView);
                    boardView.displayBoard((Graphics2D) g);
                    // Draw active piece if being dragged
                    if (boardController.getActivePiece() != null) 
                    {
                        drawPieceAtCursor((Graphics2D) g, boardController.getActivePiece(), 
                                        controller.getMouseX(), controller.getMouseY());
                    }
                }
            }
        };
        boardPanel.setPreferredSize(new Dimension(
            model.getBoard().getY() * Board.SQUARE_SIZE,
            model.getBoard().getX() * Board.SQUARE_SIZE
        ));

        // Add components to main panel
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(turnPanel, BorderLayout.EAST);
        mainPanel.setOpaque(false);
        boardPanel.setOpaque(false);
        turnPanel.setOpaque(false);
        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /**
     * Method: updateTurnIndicator
     * Description: Updates the turn display to highlight the current player's turn
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */ 
    public void updateTurnIndicator() {
        if (model.getCurrentPlayer() == 1) {
            turnIndicatorTop.setForeground(Color.RED);
            turnIndicatorBottom.setForeground(Color.BLACK);
        } else {
            turnIndicatorTop.setForeground(Color.BLACK);
            turnIndicatorBottom.setForeground(Color.BLUE);
        }
    }

    /**
     * Method: updateAnimalSelection
     * Description: Updates the animal selection display for the specified player
     * @param player The player number (1 or 2)
     * @param animal The selected animal name
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void updateAnimalSelection(int player, String animal) 
    {
        if (player == 1) {
            p1AnimalLabel.setText(model.getPlayer1Name() + " : " + animal);
        } else {
            p2AnimalLabel.setText(model.getPlayer2Name() + " : " + animal);
        }
    }

    /**
     * Method: disableAnimalButton
     * Description: Disables an animal selection button after it's been chosen
     * @param index The index of the button to disable
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void disableAnimalButton(int index) {
        animalButtons[index].setEnabled(false);
    }

    /** Method: revealAllAnimals
    * Description: Reveals all animal types by updating button text and disabling all buttons
    * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
    */
    public void revealAllAnimals() {
        for (int i = 0; i < animalButtons.length; i++) {
            String animal = model.getAnimals().get(i);
            if (animal != null) {
                animalButtons[i].setText(animal);
            }
            animalButtons[i].setEnabled(false);
        }
    }
    /**
     * Method: getPlayer1NameInput
     * Description: Gets the name input for player 1
     * @return The name entered for player 1
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer1NameInput() {
        return player1Field.getText();
    }
    /**
     * Method: getPlayer2NameInput
     * Description: Gets the name input for player 2
     * @return The name entered for player 2
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer2NameInput() {
        return player2Field.getText();
    }
    
    /**
     * Method: setController
     * Description: Sets the controller for this view
     * @param controller The game controller to associate with this view
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }
    
    /**
     * Method: paintComponent
     * Description: Custom painting of game components
     * @param g The Graphics object to protect
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model.getBoard() != null) {
            Graphics2D g2 = (Graphics2D) g;
            model.getBoard().getBoard();
            
            if (model.getActivePiece() != null) 
            {
                drawPieceAtCursor(g2, model.getActivePiece(), controller.getMouseX(), controller.getMouseY());
            }
        }
    }
    /**
 * Method: drawPieceAtCursor
 * Description: Draws a game piece at the specified cursor position
 * @param g2 The Graphics2D object for drawing
 * @param piece The piece to draw
 * @param x The x-coordinate of the cursor
 * @param y The y-coordinate of the cursor
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */
    private void drawPieceAtCursor(Graphics2D g2, Piece piece, int x, int y) 
    {
        String pieceType = piece.getType().toLowerCase();
        int playerID = piece.getPlayerId();
        
        String imagePath = "src/Pictures/" + pieceType + playerID + ".png";
        try //If there is an image to be generated
        {
            Image pieceImage = ImageIO.read(new File(imagePath));
            int pieceSize = Board.SQUARE_SIZE - 10;
            g2.drawImage(pieceImage, x - pieceSize / 2, y - pieceSize / 2, pieceSize, pieceSize, null);
        } catch (IOException e) { //If no image is available defaults to each pieces and tiles default label
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString(pieceType.charAt(0) + String.valueOf(playerID), x - 10, y);
        }
    }
}