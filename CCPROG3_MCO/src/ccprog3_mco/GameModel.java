package ccprog3_mco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class: GameModel
 * Description: Model class containing all game state and logic
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */

public class GameModel {
    /* constants of GameModel
     * W : Window width
     * H : Window height
     */
    public static final int W = 1100;
    public static final int H = 800;
    
    /* parameters of GameModel
     * board : Of type Board used to access the board and its methods
     * player1: type Player used to handle player1 and their methods
     * player2: type Player used to handle player2 and their methods
     * currentPlayer: shows which player is currently playing
     * activePiece: Which piece is the user trying to currently move
     * player1Name  player2Name : Names of the players
     * player1Animal  player2Animal: the animal selections
     * animalRanks: Lists down in order the rankings of the animals
     * animals: contains a list of the animals
     */
    private Board board;
    private Player player1;
    private Player player2;
    private int currentPlayer;
    private Piece activePiece;
    
    private String player1Name, player2Name;
    private String player1Animal, player2Animal;
    private final String[] animalRanks = {"Elephant", "Lion", "Tiger", "Leopard", "Wolf", "Dog", "Cat", "Rat"};
    private List<String> animals;
    
    /**
     * Method: GameModel
     * Description: Constructs a GameModel and initializes animal selection list
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public GameModel() {
        animals = new ArrayList<>(List.of(animalRanks));
        Collections.shuffle(animals);
    }
    
    /**
     * Method: initializeGame
     * Description: Initializes the game with current players and board
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void initializeGame() {
        player1 = new Player(1, player1Name);
        player2 = new Player(2, player2Name);
        board = new ClassicBoard(player1, player2);
    }
    
    /**
     * Method: setPlayerNames
     * Description: Sets player names
     * @param p1Name : Player 1's name
     * @param p2Name : Player 2's name
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void setPlayerNames(String p1Name, String p2Name) {
        this.player1Name = p1Name;
        this.player2Name = p2Name;
    }
    /**
     * Method: setCurrentPlayer
     * Description: Sets the current player
     * @param firstPlayer : The player ID (1 or 2) to set as current
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void setCurrentPlayer(int firstPlayer){
        this.currentPlayer=firstPlayer;
    }
    /**
     * Method: selectAnimal
     * Description: Selects an animal for a player during setup
     * @param player : The player ID (1 or 2)
     * @param animalIndex : The index of the selected animal
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void selectAnimal(int player, int animalIndex) {
        String animal = animals.get(animalIndex);
        if (player == 1) {
            player1Animal = animal;
        } else {
            player2Animal = animal;
        }
        // Mark the animal as selected
        animals.set(animalIndex, animal); // Keep the animal name for display
    }
    /**
     * Method: bothAnimalsSelected
     * Description: Checks if both players have selected animals
     * @return boolean : true if both have selected, false otherwise
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public boolean bothAnimalsSelected() {
        return player1Animal != null & player2Animal != null;
    }
    /**
     * Method: determineWinner
     * Description: Determines the winner of the animal selection phase
     * @return String : A string describing the result
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String determineWinner() 
    {
        int p1Rank = getAnimalRank(player1Animal);
        int p2Rank = getAnimalRank(player2Animal);

        if (p1Rank < p2Rank)
        {
            return player1Name + "'s " + player1Animal + " (Rank " + (p1Rank+1) + 
                   ") beats " + player2Name + "'s " + player2Animal + " (Rank " + (p2Rank+1) + 
                   ")!\n" + player1Name + " will move first!";
        } 
        else
        {
            return player2Name + "'s " + player2Animal + " (Rank " + (p2Rank+1) + 
                   ") beats " + player1Name + "'s " + player1Animal + " (Rank " + (p1Rank+1) + 
                   ")!\n" + player2Name + " will move first!";
        } 
    }
    /**
     * Method: determineIntWinner
     * Description: Determines the winner as an integer (for game logic)
     * @return int : 1 if player 1 wins, 2 if player 2 wins, or random if tie
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int determineIntWinner() {
        int p1Rank = getAnimalRank(player1Animal);
        int p2Rank = getAnimalRank(player2Animal);

        if (p1Rank < p2Rank) {
            return 1;
        } 
        else
        {
            return 2;
        } 
    }
    /**
     * Method: getAnimalRank
     * Description: Gets the rank of an animal
     * @param animal : The animal name
     * @return int : The rank index (0 is highest)
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14@author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    private int getAnimalRank(String animal) 
    {
        for (int i = 0; i < animalRanks.length; i++) {
            if (animalRanks[i].equals(animal)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Method: setActivePiece
     * Description: Sets the currently active piece
     * @param piece : The piece to make active
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void setActivePiece(Piece piece) {
        this.activePiece = piece;
    }
    
    /** Method: tryMovePiece
    * Description: Attempts to move the active piece to specified coordinates
    * @param row : The target row
    * @param col : The target column
    * @return boolean : true if move was successful, false otherwise
    * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
    */
    public boolean tryMovePiece(int row, int col) 
    {
        if (activePiece == null) return false; //If user is not trying to move any piece
        
        Tile targetTile = board.getTile(row, col); //gets the tile the piece is on
        if (targetTile != null && activePiece.isValidMove(targetTile)) { //If its a valid move
            boolean moved = activePiece.moveTo(targetTile); //moves the piece 
            if (moved) { //If move is successful switch turn
                switchTurn();
                return true;
            }
        }
        return false; //if user tries to make an invalid move
    }
    
    /**
     * Method: switchTurn
     * Description: Switches turn to the other player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void switchTurn() {
        if (currentPlayer == 1) {
            currentPlayer = player2.hasPiecesLeft() ? 2 : 1; //game continues even if opponent has no pieces left
        } else {
            currentPlayer = player1.hasPiecesLeft() ? 1 : 2;
        }
    }


    /**
     * Method: getBoard
     * Description: gets the current board object
     * @return board: Board
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public Board getBoard() { return board; }
    /**
     * Method: getPlayer1
     * Description: gets the player1 object
     * @return player1: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public Player getPlayer1() { return player1; }
    /**
     * Method: getPlayer2
     * Description: gets the player2 object
     * @return player2: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public Player getPlayer2() { return player2; }
    /**
     * Method: getCurrentPlayer
     * Description: gets the currentplayer
     * @return currentPlayer: int
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int getCurrentPlayer() { return currentPlayer; }
    /**
     * Method: getActivePiece
     * Description: gets the current piece the player is trying to move
     * @return activePiece: Piece
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public Piece getActivePiece() { return activePiece; }
    /**
     * Method: getAnimalRanks
     * Description: gets the list of animalRanks
     * @return animalRanks: String[]
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String[] getAnimalRanks() { return animalRanks; }
    /**
     * Method: getAnimals
     * Description: gets the list of animals
     * @return animals: List of Animas
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public List<String> getAnimals() { return animals; }
    /**
     * Method: getPlayer1Name
     * Description: gets the player1 name
     * @return player1Name: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer1Name() { return player1Name; }
    /**
     * Method: getPlayer2Name
     * Description: gets the player2 name
     * @return player2Name: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer2Name() { return player2Name; }
    /**
     * Method: getPlayer1Animal
     * Description: gets player1's animal selection
     * @return player1Animal: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer1Animal() { return player1Animal; }
    /**
     * Method: getPlayer2Animal
     * Description: gets player2's animal selection
     * @return player2Animal: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public String getPlayer2Animal() { return player2Animal; }
}