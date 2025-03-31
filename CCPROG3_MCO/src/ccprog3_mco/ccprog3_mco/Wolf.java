package ccprog3_mco;
/**
 * Class: Wolf
 * Description: subclass of Piece used to initialize a Wolf instance
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 * 
 */ 
public class Wolf extends Piece
{
    /**
     * Method: Wolf
     * Description: constructor for Wolf
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
	public Wolf(Tile tile, Player player) {
        super(tile, player, 4);//Wolf has a power of 4
    }


    public String display() 
    {
        return "W"+getPlayerId()+" ";//getPlayerId to tell pieces apart
    }
}
