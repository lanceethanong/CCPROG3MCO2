package ccprog3_mco;
/**
 * Class: Tile
 * Description: Abstract Class used in initializing each tile on a board and handling special tiles and initiating and places the pieces on them
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */
public abstract class Tile 
{
	/* Parameters
	 * x: x coordinates(protected to be accessible by subclasses)
	 * y: y coordinates(protected to be accessible by subclasses)
	 * piece: Piece type vairable used to access its respective methods
	 * board: Board type variable used to associate the tile to a board(protected to be accessible by subclasses)
	 */
	protected int x;
	protected int y;
	private Piece piece;
	protected Board board;
	/**
	 * Method: Tile
	 * Description: Constructor for Tile
	 * @param board: Board
	 * @param x: int
	 * @param y: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public Tile(Board board,int x,int y)
	{
		this.board = board;
		this.x = x;
		this.y = y;
	}
	/**
	 * Method: getBoard
	 * Description: gets the board object
	 * @return board: Board
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Method: setPiece
	 * Description: places a Piece on the Tile
	 * @param piece: Piece
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	
	/**
	 * Method: getPiece
	 * Description: retrieves the Piece that is on the tile
	 * @return piece: Piece
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public Piece getPiece()
	{
		return piece;
	}
	/**
	 * Method: getX
	 * Description: retrieves the X coordinates of the tile
	 * @return x: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * Method: getY
	 * Description: retrieves the Y coordinates of the tile
	 * @return y: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * Method: getType
	 * Description: retrieves the "name" of the type of tile the current instance is
	 * @return getClass().getSimpleName(): String
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public String getType()
	{
		return getClass().getSimpleName(); //getsimpleName to just get the name of the class(no packages or paths)
	}
	/**
	 * Method: display
	 * Description: shows how the tile will be displayed on the Game
	 * @return String
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
	public String display() 
	{
	    if (piece != null) // If there is a piece on the tile
	    {
	        return piece.display() + " "; //Display the piece
	    }
//	    return getDefaultSymbol(); // Display the symbol for the tile
		return null;
	}

}
