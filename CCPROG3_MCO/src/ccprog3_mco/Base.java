package ccprog3_mco;
/**
 * Class: Base
 * Description: Subclass of the abstract class Tile used to initialize each Players base
 * @author Lance Ethan S. Ong S14
 * 
 */
public class Base extends Tile 
{
    private int player; //which player it belongs to

	/**
	 * Method: Base
	 * Description: constructor for Base
	 * @param board: Board
	 * @param x: int
	 * @param y: int
	 * @param player: int
	 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
	 * 
	 */
    public Base(Board board,int x, int y, int player) 
    {
        super(board,x, y);
        this.player = player;
    }

	/**
	 * Method: getPlayer
	 * Description: getter for player
	 * @return player: int
	 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
	 * 
	 */
    public int getPlayer() 
    {
        return this.player;
    }
	/**
	 * Method: display
	 * Description: default display for Base
	 * @return String
	 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
	 * 
	 */
    public String display() 
    {
        return "P"+getPlayer(); 
    }

}
