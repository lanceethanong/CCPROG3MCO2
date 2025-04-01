package ccprog3_mco;

/**
 * Class: ClassicBoard
 * Description: Implementation of the classic Jungle game board (9x7 layout)
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 */
public class ClassicBoard extends Board {
    /**
     * Method: ClassicBoard
     * Description: Constructs a ClassicBoard with two players
     * @param player1 : First player
     * @param player2 : Second player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public ClassicBoard(Player player1, Player player2) {
        super("Classic Board (9x7)", player1, player2);
    }

    /**
     * Method: getX
     * Description: Gets the number of rows (x-dimension)
     * @return int : Number of rows (9)
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int getX() {
        return 9;
    }

    /**
     * Method: getY
     * Description: Gets the number of columns (y-dimension)
     * @return int : Number of columns (7)
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public int getY() {
        return 7;
    }

    /**
     * Method: initializeTiles
     * Description: Initializes all tiles on the board with their proper types
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void initializeTiles() {
        // Initialize lakes
        board[3][1] = new Lake(this,3,1);
        board[3][2] = new Lake(this,3,2);
        board[4][1] = new Lake(this,4,1);
        board[4][2] = new Lake(this,4,2);
        board[5][1] = new Lake(this,5,1);
        board[5][2] = new Lake(this,5,2);
        
        board[3][4] = new Lake(this,3,4);
        board[3][5] = new Lake(this,3,5);
        board[4][4] = new Lake(this,4,4);
        board[4][5] = new Lake(this,4,5);
        board[5][4] = new Lake(this,5,4);
        board[5][5] = new Lake(this,5,5);
        
        // Initialize bases
        board[0][3] = new Base(this,0,3,1);
        board[8][3] = new Base(this,8,3,2);
        
        // Initialize traps
        board[0][2] = new Trap(this,0,2,1);
        board[0][4] = new Trap(this,0,4,1);
        board[1][3] = new Trap(this,1,3,1);
        board[8][2] = new Trap(this,8,2,2);
        board[8][4] = new Trap(this,8,4,2);
        board[7][3] = new Trap(this,7,3,2);
        
        // Fill remaining tiles with empty spaces
        for(int i = 0; i < getX();i++) {
            for(int j = 0; j < getY();j++) {
                if(!(board[i][j] instanceof Trap) && !(board[i][j] instanceof Base) && !(board[i][j] instanceof Lake)) {
                    board[i][j] = new EmptyTile(this,i,j);
                }
            }
        }
    }
    
    /**
     * Method: initializePieces
     * Description: Places all pieces in their starting positions
     * @param player1 : First player's pieces
     * @param player2 : Second player's pieces
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    public void initializePieces(Player player1, Player player2) {
        // Player 1 pieces
        new Lion(board[0][0], player1);
        new Tiger(board[0][6], player1);
        new Dog(board[1][1], player1);
        new Cat(board[1][5], player1);
        new Rat(board[2][0], player1);
        new Leopard(board[2][2], player1);
        new Wolf(board[2][4], player1);
        new Elephant(board[2][6], player1);

        // Player 2 pieces
        new Lion(board[8][6], player2);
        new Tiger(board[8][0], player2);
        new Dog(board[7][5], player2);
        new Cat(board[7][1], player2);
        new Rat(board[6][6], player2);
        new Leopard(board[6][4], player2);
        new Wolf(board[6][2], player2);
        new Elephant(board[6][0], player2);
    }
    
    /**
     * Method: getTile
     * Description: Gets the tile at specified coordinates
     * @param x : The x-coordinate (row)
     * @param y : The y-coordinate (column)
     * @return Tile : The Tile at (x,y) or null if coordinates are invalid
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     */
    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= getX() || y < 0 || y >= getY()) {
            return null;
        }
        return board[x][y];
    }
}








