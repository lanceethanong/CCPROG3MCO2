package ccprog3_mco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class: GameModel
 * Description: Model class containing all game state and logic
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 */
public class GameModel {
    /* constants of GameModel
     * W : Window width
     * H : Window height
     */
    public static final int W = 1100;
    public static final int H = 800;
    
    /* parameters of GameModel
     * board : The game board
     * player1 : Player 1 object
     * player2 : Player 2 object
     * currentPlayer : ID of current player (1 or 2)
     * activePiece : Currently selected piece
     * player1Name : Name of player 1
     * player2Name : Name of player 2
     * player1Animal : Selected animal for player 1
     * player2Animal : Selected animal for player 2
     * animalRanks : Array of animal names in rank order
     * animals : List of available animals for selection
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
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public GameModel() {
        animals = new ArrayList<>(List.of(animalRanks));
        Collections.shuffle(animals);
    }
    
    /**
     * Method: initializeGame
     * Description: Initializes the game with current players and board
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
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
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void setPlayerNames(String p1Name, String p2Name) {
        this.player1Name = p1Name;
        this.player2Name = p2Name;
    }
    
    /**
     * Method: setCurrentPlayer
     * Description: Sets the current player
     * @param firstPlayer : The player ID (1 or 2) to set as current
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void setCurrentPlayer(int firstPlayer) {
        this.currentPlayer = firstPlayer;
    }
    
    /**
     * Method: selectAnimal
     * Description: Selects an animal for a player during setup
     * @param player : The player ID (1 or 2)
     * @param animalIndex : The index of the selected animal
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void selectAnimal(int player, int animalIndex) {
        String animal = animals.get(animalIndex);
        if (player == 1) {
            player1Animal = animal;
        } else {
            player2Animal = animal;
        }
        animals.set(animalIndex, animal);
    }
    
    /**
     * Method: bothAnimalsSelected
     * Description: Checks if both players have selected animals
     * @return boolean : true if both have selected, false otherwise
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public boolean bothAnimalsSelected() {
        return player1Animal != null && player2Animal != null;
    }
    
    /**
     * Method: determineWinner
     * Description: Determines the winner of the animal selection phase
     * @return String : A string describing the result
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public String determineWinner() {
        int p1Rank = getAnimalRank(player1Animal);
        int p2Rank = getAnimalRank(player2Animal);

        if (p1Rank < p2Rank) {
            return player1Name + "'s " + player1Animal + " (Rank " + (p1Rank+1) + 
                   ") beats " + player2Name + "'s " + player2Animal + " (Rank " + (p2Rank+1) + 
                   ")!\n" + player1Name + " will move first!";
        } else if (p1Rank > p2Rank) {
            return player2Name + "'s " + player2Animal + " (Rank " + (p2Rank+1) + 
                   ") beats " + player1Name + "'s " + player1Animal + " (Rank " + (p1Rank+1) + 
                   ")!\n" + player2Name + " will move first!";
        } else {
            return "Both animals have the same rank!\nA coin toss will decide who goes first!";
        }
    }
    
    /**
     * Method: determineIntWinner
     * Description: Determines the winner as an integer (for game logic)
     * @return int : 1 if player 1 wins, 2 if player 2 wins, or random if tie
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public int determineIntWinner() {
        int p1Rank = getAnimalRank(player1Animal);
        int p2Rank = getAnimalRank(player2Animal);

        if (p1Rank < p2Rank) {
            return 1;
        } else if (p1Rank > p2Rank) {
            return 2;
        } else {
            return Math.random() < 0.5 ? 1 : 2;
        }
    }
    
    /**
     * Method: getAnimalRank
     * Description: Gets the rank of an animal
     * @param animal : The animal name
     * @return int : The rank index (0 is highest)
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14@author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    private int getAnimalRank(String animal) {
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
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void setActivePiece(Piece piece) {
        this.activePiece = piece;
    }
    
    /**
     * Method: tryMovePiece
     * Description: Attempts to move the active piece to specified coordinates
     * @param row : The target row
     * @param col : The target column
     * @return boolean : true if move was successful, false otherwise
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public boolean tryMovePiece(int row, int col) {
        if (activePiece == null) return false;
        
        Tile targetTile = board.getTile(row, col);
        if (targetTile != null && activePiece.isValidMove(targetTile)) {
            boolean moved = activePiece.moveTo(targetTile);
            if (moved) {
                switchTurn();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method: switchTurn
     * Description: Switches turn to the other player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void switchTurn() {
        if (currentPlayer == 1) {
            currentPlayer = player2.hasPiecesLeft() ? 2 : 1;
        } else {
            currentPlayer = player1.hasPiecesLeft() ? 1 : 2;
        }
    }
    
    /**
     * Method: resetGame
     * Description: Resets the game state for a new game
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void resetGame() {
        player1Animal = null;
        player2Animal = null;
        animals = new ArrayList<>(List.of(animalRanks));
        Collections.shuffle(animals);
        currentPlayer = 1;
        activePiece = null;
    }
    
    // Getters
    public Board getBoard() { return board; }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public int getCurrentPlayer() { return currentPlayer; }
    public Piece getActivePiece() { return activePiece; }
    public String[] getAnimalRanks() { return animalRanks; }
    public List<String> getAnimals() { return animals; }
    public String getPlayer1Name() { return player1Name; }
    public String getPlayer2Name() { return player2Name; }
    public String getPlayer1Animal() { return player1Animal; }
    public String getPlayer2Animal() { return player2Animal; }
}