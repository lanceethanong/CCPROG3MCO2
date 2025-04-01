package ccprog3_mco;
/**
 * Class: Leopard
 * Description: subclass of Piece used to initiate a new Leopard instance
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public class Leopard extends Piece
{
    /**
     * Method: Leopard
     * Description: constructor for Leopard
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
	public Leopard(Tile tile, Player player) 
	{
        super(tile, player, 5);//leopard has a power of 5
    }

    /**
     * Method: display
     * Description: default display for Leopard
     * @return String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "l "+getPlayerId()+" "; //getPlayerId and small l(Lion is big L) to tell each piece apart
    }

}
