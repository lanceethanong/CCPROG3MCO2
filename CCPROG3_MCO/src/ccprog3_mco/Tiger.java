package ccprog3_mco;
/**
 * Class: Tiger
 * Description: subclass of Piece used to initialize a Tiger instance
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public class Tiger extends Piece implements Hopper
{
    /**
     * Method: Tiger
     * Description: constructor for Tiger
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
	public Tiger(Tile tile, Player player) 
	{
        super(tile, player, 6);//Tiger has a power of 6
    }


    public String display() 
    {
        return "T"+getPlayerId()+" ";//getPlayerId to tell the pieces apart
    }
}
