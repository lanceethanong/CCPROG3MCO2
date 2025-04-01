package ccprog3_mco;
/**
 * Class: Lion
 * Description: subclass of Piece used to initialize a Lion instance
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public class Lion extends Piece implements Hopper
{
    /**
     * Method: Lion
     * Description: constructor for Lion
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
	public Lion(Tile tile, Player player) 
	{
        super(tile, player, 7); //Lion has a power of 7
    }

    /**
     * Method: display
     * Description: default display for Lion
     * @return String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "L"+getPlayerId()+" ";  //getPlayerId and big L(Leopard is small l) to tell each piece apart
    }
       
}
