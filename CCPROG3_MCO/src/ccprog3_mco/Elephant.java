package ccprog3_mco;
/**
 * Class: Elephant
 * Description: subclass of Piece used to create an elephant instance
 * @param tile: Tile
 * @param player: Player
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 * 
 */ 
public class Elephant extends Piece
{
    /**
     * Method: Elephant
     * Description: constructor for Elephant
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
	public Elephant(Tile tile, Player player) 
	{
        super(tile, player, 8); //Elephant has a power of 8
    }
    /**
     * Method: display
     * Description: default display for Elephant
     * @return String
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 

    public String display() 
    {
        return "E"+getPlayerId()+" ";//getPlayerId to tell each piece apart
    }
}
