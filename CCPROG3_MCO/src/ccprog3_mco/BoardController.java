package ccprog3_mco;

/**
 * Class: BoardController
 * Description: Controller class that manages interactions between the Board model and BoardView
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 */
public class BoardController {
    /* parameters of BoardController
     * model : The Board model being controlled
     * view : The BoardView being updated
     * activePiece : The currently selected piece
     */
    private Board model;
    private BoardView view;
    private Piece activePiece;
    
    /**
     * Method: BoardController
     * Description: Constructs a BoardController with specified model and view
     * @param model : The Board model to control
     * @param view : The BoardView to update
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public BoardController(Board model, BoardView view) {
        this.model = model;
        this.view = view;
    }
    
    /**
     * Method: getTile
     * Description: Gets the tile at specified coordinates
     * @param x : The x-coordinate
     * @param y : The y-coordinate
     * @return Tile : The Tile at (x,y) or null if invalid coordinates
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Tile getTile(int x, int y) {
        return model.getTile(x, y);
    }
    
    /**
     * Method: setActivePiece
     * Description: Sets the currently active piece
     * @param piece : The piece to set as active
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public void setActivePiece(Piece piece) {
        this.activePiece = piece;
    }
    
    /**
     * Method: getActivePiece
     * Description: Gets the currently active piece
     * @return Piece : The active Piece or null if none
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public Piece getActivePiece() {
        return activePiece;
    }
    
    /**
     * Method: tryMovePiece
     * Description: Attempts to move the active piece to specified coordinates
     * @param row : The target row
     * @param col : The target column
     * @param currentPlayer : The ID of the current player
     * @return boolean : true if move was successful, false otherwise
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     */
    public boolean tryMovePiece(int row, int col, int currentPlayer) {
        if (activePiece == null) return false;        
        Tile targetTile = model.getTile(row, col);
        if (targetTile == null) return false;
        
        if (activePiece.isValidMove(targetTile)) {
            boolean moved = activePiece.moveTo(targetTile);
            if (moved) {
                activePiece = null;
                return true;
            }
        }
        
        activePiece = null;
        return false;
    }
}