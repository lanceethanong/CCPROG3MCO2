package ccprog3_mco;
/**
 * Class: Dog
 * Description: subclass of piece used to create a dog instance
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public class Dog extends Piece
{
    /**
     * Method: Dog
     * Description: constructor for Dog
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
	public Dog(Tile tile, Player player) 
	{
        super(tile, player, 3); //Dog has a power of 3
    }

    /**
     * Method: display
     * Description: default display for Dog
     * @return String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "D"+getPlayerId()+" "; //playerId used to tell pieces apart
    }
}
