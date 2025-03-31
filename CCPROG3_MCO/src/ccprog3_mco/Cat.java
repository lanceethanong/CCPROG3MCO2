package ccprog3_mco;

/**
 * Class: Cat
 * Description: subclass of Piece used to initiate a Cat instance
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 * 
 */ 
public class Cat extends Piece
{
    /**
     * Method: Cat
     * Description: constructor for Cat
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
	public Cat(Tile tile, Player player) 
	{
        super(tile, player, 2); // Cat has a power of 2
    }

    /**
     * Method: display
     * Description: default display for Cat
     * @return String
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "C"+getPlayerId()+" "; //playerId used to tell pieces apart
    }
}
