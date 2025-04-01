package ccprog3_mco;
/**
 * Class: EmptyTile
 * Description: subclass of the abstract class Tile used to initiate an EmptyTile
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */
public class EmptyTile extends Tile 
{
	/**
	 * Method: EmptyTile
	 * Description: constructor for EmptyTile
	 * @param board: Board
	 * @param x: int
	 * @param y: int
	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
	 * 
	 */
     public EmptyTile(Board board,int x,int y) 
	 {    	 
	        super(board,x, y); 
	 }
 	/**
 	 * Method: display
 	 * Description: default display of EmptyTile
 	 * @return String
 	 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 	 * 
 	 */   
	 public String display() 
	 {
	        return ". "; 
	 }
}
