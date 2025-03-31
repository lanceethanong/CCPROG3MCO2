// Board.java
package ccprog3_mco;

/**
 * Class: Board
 * Description: Abstract base class representing a game board
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 */
public abstract class Board {
    /* parameters of Board
     * boardName : Name of the board
     * board : 2D array of tiles
     * player1 : Player 1 object
     * player2 : Player 2 object
     * SQUARE_SIZE : Size of each board square
     * HALF_SQUARE_SIZE : Half of SQUARE_SIZE
     */
    private String boardName;
    protected Tile[][] board;
    private Player player1;
    private Player player2;
    public static final int SQUARE_SIZE = 80;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;
    
    /**
     * Method: Board
     * Description: Constructs a Board with specified name and players
     * @param name : The name of the board
     * @param player1 : First player
     * @param player2 : Second player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Board(String name, Player player1, Player player2) {
        this.boardName = name;
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Tile[getX()][getY()];
        initializeTiles();
        initializePieces(player1, player2);
    }

    /**
     * Method: getBoardName
     * Description: Gets the board name
     * @return String : The board name
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public String getBoardName() {
        return this.boardName;
    }

    /**
     * Method: setBoardName
     * Description: Sets the board name
     * @param name : The new board name
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void setBoardName(String name) {
        this.boardName = name;
    }
    
    /**
     * Method: getTile
     * Description: Abstract method to get tile at coordinates
     * @param x : The x-coordinate
     * @param y : The y-coordinate
     * @return Tile : The tile at (x,y)
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public abstract Tile getTile(int x, int y);
    
    /**
     * Method: initializeTiles
     * Description: Abstract method to initialize tiles
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public abstract void initializeTiles();
    
    /**
     * Method: initializePieces
     * Description: Abstract method to initialize pieces
     * @param player1 : First player
     * @param player2 : Second player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public abstract void initializePieces(Player player1, Player player2);
    
    /**
     * Method: getX
     * Description: Abstract method to get number of rows
     * @return int : Number of rows
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public abstract int getX();
    
    /**
     * Method: getY
     * Description: Abstract method to get number of columns
     * @return int : Number of columns
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public abstract int getY();
    
    /**
     * Method: isGameOver
     * Description: Checks if the game is over (a base has been captured)
     * @return boolean : true if game is over, false otherwise
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public boolean isGameOver() {
        for (int row = 0; row < getX(); row++) {
            for (int col = 0; col < getY(); col++) {
                Tile tile = board[row][col];
                if (tile instanceof Base && tile.getPiece() != null) {
                    Piece piece = tile.getPiece();
                    Base base = (Base) tile;
                    if (piece.getPlayerId() != base.getPlayer()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Method: getBoard
     * Description: Gets the board array
     * @return Tile[][] : 2D array of Tiles
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Tile[][] getBoard() {
        return board;
    }
    
    /**
     * Method: getPlayer1
     * Description: Gets player 1
     * @return Player : Player 1
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Player getPlayer1() {
        return player1;
    }
    
    /**
     * Method: getPlayer2
     * Description: Gets player 2
     * @return Player : Player 2
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Player getPlayer2() {
        return player2;
    }
}