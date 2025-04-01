package ccprog3_mco;

/**
 * Class: Trap
 * Description: Subclass of the abstract class Tile used to initialize each Players traps
 * @author Lance Ethan S. Ong S14   Nick Jenson Crescini S14
 * 
 */
public class Trap extends Tile 
{
    private int player; //which player it belongs to
    /**
     * Class: Trap
     * Description: Constructor for Trap
     * @param board: Board
	 * @param x: int
	 * @param y: int
	 * @param player: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */
    public Trap(Board board,int x, int y, int player) 
    {
        super(board,x, y);
        this.player = player;
    }
	/**
	 * Method: getPlayer
	 * Description: getter for player
	 * @return player: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
    public int getPlayer() 
    {
        return this.player;
    }
	/**
	 * Method: display
	 * Description: default display for Trap
	 * @return String
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
    public String display() 
    {
        return "# "; 
    }

}

