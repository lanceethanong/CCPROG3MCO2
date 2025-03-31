package ccprog3_mco;

/**
 * Class: Rat
 * Description: subclass of Piece used to initialize a Rat instance who is a Swimmer
 * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
 * 
 */ 
public class Rat extends Piece implements Swimmer
{
    /**
     * Method: Rat
     * Description: constructor for Rat
     * @param tile: Tile
     * @param player: Player
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
	public Rat(Tile tile, Player player) 
	{
        super(tile, player, 1);//rat has a power of 1
    }
    /**
     * Method: display
     * Description: default display for Rat
     * @return String
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "R"+getPlayerId()+" "; 
    }
    /**
     * Method: swim
     * Description: Implements the swim method found on the interface Swimmer
     * @param targetTile: Tile
     * @return currentTile: Tile
     * @author Lance Ethan S. Ong  & Nick Jenson Crescini S14
     * 
     */ 
    public Tile swim(Tile targetTile) 
    {
        if (targetTile == null || !targetTile.getType().equals("Lake")) //If the targetTile is either not a lake or out of bounds
        {
            return null; //does not implement or do anything
        }
        
        //Moves rat to the lake(similar to move)
        currentTile.setPiece(null);
        targetTile.setPiece(this);
        currentTile = targetTile;
        return currentTile; //return the currentTile
    }
}
