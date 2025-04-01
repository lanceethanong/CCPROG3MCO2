package ccprog3_mco;

/**
 * Class: Lake
 * Description: Is a subclass of the Abstract class Tile used to initialize the Lake Blocks on the Board
 * @author Lance Ethan S. Ong Nick Jenson Crescini S14
 * 
 */
public class Lake extends Tile 
{
	/**
	 * Method: Lake
	 * Description: constructor for Lake
	 * @param board: Board
	 * @param x: int
	 * @param y: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
    public Lake(Board board,int x,int y) 
    {
        super(board,x, y);
    }
    
	/**
	 * Method: display
	 * Description: display for Lake
	 * @return String
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
    public String display() 
    {
        return "~ "; 
    }
}