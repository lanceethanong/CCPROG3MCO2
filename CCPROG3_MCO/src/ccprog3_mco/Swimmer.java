package ccprog3_mco;
/**
 * Class: Swimmer
 * Description: interface for Swimmer(those that can go to the Lake)
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public interface Swimmer 
{
    /**
     * Method: swim
     * Description: swims to lake tile
     * @param targetTile: Tile
     * @return Tile
     */
    public Tile swim(Tile targetTile);
}