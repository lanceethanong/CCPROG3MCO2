package ccprog3_mco;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane; //To alert of any error messages

/**
 * Class: Piece 
 * Description: Main class used for initializing the pieces on the board along with handling their own movement conditions when encountering events like(obstacles,capture and win conditions)
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */
public class Piece {
	/* parameters of Piece
	 * power : level of each block
	 * originalPower : copies the power of the piece(used to restore power once the piece leaves the trap)
	 * player : which player the piece belongs to
	 * currentTile : which tile the piece is currently at
	 */
    protected int power,originalPower; // protected in order for subclasses to be able to access the power
    private Player player; //Player type used to access methods in player
    protected Tile currentTile; //Tile type used to access methods in tile
    /**
     * Method: Piece
     * Description: Constructor used to initialize new piece Objects
     * @param tile: used to initialize which tile the piece starts
     * @param player: integer to initialize which player it belongs to
     * @param power: used to initialize the pieces power         
     * @author Lance Ethan S. Ong S14
     * 
     */
    public Piece(Tile tile, Player player, int power) {
        this.currentTile = tile;
        this.player = player;
        this.power = power;
        this.originalPower = power; //to restore after user steps out of trap
        tile.setPiece(this); // adds the piece to the tile 
        player.addPiece(this); // adds it to the list of pieces each player has
    }
    
    /**
     * Method: moveTo
     * Description: main method used to handle the movement conditions of the pieces(other conditions are stacked onto each other ensuring each move is valid)
     * @param targetTile: stored data of the targetTile
     * @return boolean: true or false       
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean moveTo(Tile targetTile) {
    // If the targetTile is a base
    if(targetTile instanceof Base) 
    {
        if(!ownTile(targetTile)) //If its not their own base
        {
            if(canMove(targetTile)) //If the direction of the move is valid
            {            	
                move(targetTile); //Moves the target to the tile
                JOptionPane.showMessageDialog(null, this.getPlayer().getName() + " has captured the enemy base and is declared the winner!","Winner",JOptionPane.INFORMATION_MESSAGE); //Alerts that the game is over
                
                ImageIcon trophyIcon = new ImageIcon(getClass().getResource("/Pictures/congratulations.jpg")); // Icon
                
                JOptionPane.showMessageDialog //Shows end screen
                (
                    null, 
                    "Congratulations "+this.getPlayer().getName() +" on winning the game and Thank You for Playing!\n",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE,
                    trophyIcon 
                );                
                System.exit(0); //closes the game
                return true;
            }
            return false; //Invalid direction
        }
        return false; //is own tile
    }
    // If the targetTile is a trap
    else if(targetTile instanceof Trap) {
    	
    	
        if (!ownTile(targetTile)) //Enemy trap
        {
            if (canMove(targetTile)) //If valid direction
            {
                move(targetTile); //moves to target tile
                this.stun(); //drains the users power
                JOptionPane.showMessageDialog(null, currentTile.getPiece().getType() + " has stepped on the enemy trap and has their power drained", "Trapped", JOptionPane.WARNING_MESSAGE); //alert message
                return true; //valid move
            }
            return false; // invalid direction
        } 
        else //If its your own trap
        {
            // Own trap - no stun effect
            if (targetTile.getPiece() != null) //If there is a piece on the enemy tile
            {
                if (isHigherPower(targetTile)) //If the piece on the trap is capturable(Assumed it is as powers are drained)
                {
                    Piece capturedPiece = targetTile.getPiece(); //calls the piece on the targetTile as Captured
                    capturedPiece.getPlayer().capturedPiece(capturedPiece); // removes the piece captured from the player list
                    move(targetTile); //moves to target
                    return true; //valid move
                }
                return false; //invalid move(assumed always valid)
            }
            // Moving to empty own trap(no effect)
            if (canMove(targetTile)) 
            {
                move(targetTile); //moves to target
                return true; //valid move
            }
            return false; //Invalid Direction
        }           
    }
    	// If the currentTile is a Trap and targetTile is not a Trap
        else if (currentTile instanceof Trap && !(targetTile instanceof Trap)) 
        {                          	
            this.recover(); // Restore the piece's power
            move(targetTile); //Moves to targettile
            return true; //valid move            
        }
    
        // If the targetTile is a lake
        else if(targetTile instanceof Lake) 
        {
            if(canMove(targetTile)) //If valid direction
            {
                if(this instanceof Swimmer) //If the piece implements the Swimmer interface
                {
                    ((Swimmer)this).swim(targetTile); //Swims to the target Tile
                    return true; //valid move
                }
                else //If it is a non swimmer(Not a Rat)
                {
                    JOptionPane.showMessageDialog(null,"Only Rat can cross the Lake","Invalid Move",JOptionPane.ERROR_MESSAGE);//error alert
                    return false; //invalid move
                }   
            }
            return false; //invalid direction
        }
    
    // If the targetTile has a piece
        else if(targetTile.getPiece() != null) 
        { 
            if(isValidMove(targetTile)) //If the move is valid(checked for isHigherPower and canMove)
            {
                Piece capturedPiece = targetTile.getPiece(); //gets the piece at the target and marks it captured
                capturedPiece.getPlayer().capturedPiece(capturedPiece); // removes the piece from the players list
                move(targetTile); //moves to targettile
                return true; //valid move
            }
            return false;//invalid move
        }
    
        // If the targetTile is empty and move is valid
        else if (targetTile.getPiece() == null && isValidMove(targetTile)) 
        {
            move(targetTile);//moves to targettile
            return true;//valid move
        }
        return false; //all other conditions(out of bounds or invalid direction) 
    }
    
    /**
     * Method: move
     * Description: method used to move the target from tile to tile
     * @param targetTile: stored data of the targetTile   
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public void move(Tile targetTile)
    {
        currentTile.setPiece(null); //deletes piece from the tile
        targetTile.setPiece(this); //puts it in the new tile
        currentTile = targetTile; //update position 
    }
    /**
     * Method: isValidMove
     * Description: method that is used to check every condition to see whether the move is valid and is normally called in the main movement method moveTo
     * @param targetTile: stored data of the targetTile   
     * @return true or false: boolean
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean isValidMove(Tile targetTile)
    {
        // Allow movement into an empty tile
        if(targetTile.getPiece() == null) 
        {
            if(canMove(targetTile)) //If valid direction
            {
                return true;//valid
            }       
            if(ownTile(targetTile))//If user steps onto their own base(can be changed)
            {
            	return false;//invalid
            }
            else//other invalid moves
            {
                return false;//invalid
            }
        }
        
        else if(targetTile.getPiece() != null)
        {   // If there's a piece in the target tile, check if it can be captured
        	
            if(isHigherPower(targetTile)) //If currentPiece can capture target Piece
            {            	
                if(canMove(targetTile)) //If is valid direction
                {
                    if(currentTile instanceof Lake && this instanceof Swimmer) //Special rule that rat cannot capture any pieces outside the lake while still being inside
                    {
                    	if(!(targetTile instanceof Lake)) //Tries to capture elephant outside the lake
                    	{
                    	JOptionPane.showMessageDialog(null, "Rat cannot capture any pieces outside the lake while inside the lake!", "Invalid Move", JOptionPane.ERROR_MESSAGE); //shows an error message
                        return false;//invalid move
                    	}
                    	else //lake to lake capture is allowed
                    	{
                    		return true;
                    	}
                    }
                    return true; // valid move(capturable piece)
                }
                else //invalid direction
                {
                    return false;//invalid move
                }
            }
        }
        return false;
    }
    
    /**
     * Method: canMove
     * Description: method used to check whether the direction and how many spaces the piece moves is allowed(includes lake crossing and piece movement rules)
     * @param targetTile: stored data of the targetTile
     * @return true or false: boolean   
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean canMove(Tile targetTile) 
    {
        int targetX = targetTile.getX(); //target x
        int targetY = targetTile.getY(); //target y
        int currentX = currentTile.getX(); //current x
        int currentY = currentTile.getY(); //current y

        // Standard movement logic: move 1 tile up,down,left,right(can also be modified)
        if ((Math.abs(targetX - currentX) + Math.abs(targetY - currentY)) == 1) 
        {
            return true; //valid direction
        }

        // Special case: Tiger and Lion hopping over lakes
        if (this instanceof Hopper) 
        {
            // Check if the movement is in a straight line (horizontal or vertical)
            if (currentX == targetX || currentY == targetY) 
            {
                // Check if the path is entirely over lake tiles
                if (isPathOverLake(currentTile, targetTile)) 
                {
                    // Check if there is a Rat blocking the path on lake tiles
                    if (alongthePath(currentX, currentY, targetX, targetY)) 
                    {
                        return true; // Allow the hop over lakes
                    } else 
                    {
                        JOptionPane.showMessageDialog(null, "A Rat is blocking the path!", "Invalid Move", JOptionPane.ERROR_MESSAGE); //Alerts that a rat is blocking
                        return false; //invalid move
                    }
                } 
                else 
                {
                    // Tigers and Lions cannot move more than 1 tile unless crossing a lake
                    JOptionPane.showMessageDialog(null, "Cannot move in that direction!(Unless hopping across the lake)", "Invalid Move", JOptionPane.ERROR_MESSAGE);//Cannot do that movement outside of hopping over lakes
                    return false;//invalid move
                }
            }
        }
        
        // Invalid move
        JOptionPane.showMessageDialog(null, "Cannot move in that direction!", "Invalid Move", JOptionPane.ERROR_MESSAGE);//All other invalid moves 
        return false;            
    }
    /**
     * Method: isHigherPower
     * Description: method used to check whether a piece can capture the targetPiece based on if they have a higher power than them
     * @param targetTile: stored data of the targetTile
     * @return true or false: boolean 
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean isHigherPower(Tile targetTile){
        // If the tile is empty, allow movement
        if(targetTile.getPiece() == null) 
        {
            return true;
        }

        Piece targetPiece = targetTile.getPiece(); //gets the piece on the targetTile

        // check if the current piece is a Rat and the target is an Elephant
        if(this.getType().equals("Rat") && targetPiece.getType().equals("Elephant"))
        {      	
            return true; // Rat can eat Elephant
        }
        if(ownPiece(targetTile)) //If user tries to capture their own piece 
        {
        	JOptionPane.showMessageDialog(null, "Cannot capture your own piece!", "Invalid Move", JOptionPane.ERROR_MESSAGE); //shows an error message
            return false; //invalid move
        }

        // If the current piece has equal or higher power, allow capturing
        if(this.getPower() >= targetPiece.getPower())
        {
            if(this.getType().equals("Elephant") && targetPiece.getType().equals("Rat")) //If theres a special case(Elephant cannot eat Rat)
            {
            	JOptionPane.showMessageDialog(null, "Special Rule: (Elephant cannot capture Rat)", "Invalid Move!", JOptionPane.ERROR_MESSAGE); //shows an error message
                return false; //Invalid move due to Special Rule
            }
            return true; //otherwise is a valid move
        }

        JOptionPane.showMessageDialog(null, "Cannot move: " + this.getType() +"(Power:"+this.getPower()+") "+" cannot eat " + targetPiece.getType()+"(Power:"+targetPiece.getPower()+") ","Invalid Move!", JOptionPane.ERROR_MESSAGE);//Alerts that opponent piece is more powerful
        return false;//cannot capture target
    }
    
    /**
     * Method: ownPiece
     * Description: method used to check whether a user is running into their own piece
     * @param targetTile: stored data of the targetTile
     * @return true or false: boolean
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean ownPiece(Tile targetTile)
    {
        Piece targetPiece = targetTile.getPiece(); // gets the piece on the targettile
        if(targetPiece==null) //If there is no piece on the tile
        {
            return false;
        }
        // prevents capturing own piece
        if(this.getPlayerId() == targetPiece.getPlayerId())
        {
            return true; // Cannot capture own piece
        }
        return false; //the target Tile piece is the opponents
    }

    /**
     * Method: ownTile
     * Description: method used to check whether user is going to their own tile(base and trap)
     * @param targetTile: stored data of the targetTile
     * @return true or false: boolean  
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean ownTile(Tile targetTile)
    {
        
        if(targetTile.getType().equals("Trap")) //If piece goes into a trap
        {
            Trap traptile = (Trap) targetTile; //Instantiates the tile as a trap object
            if(traptile.getPlayer() == this.getPlayerId()) //If it is their own trap
            {
                return true;
            }
            else //If enemy trap
            return false;
        }
        // If piece goes into a base
        else if(targetTile.getType().equals("Base"))
        {
        	Base basetile = (Base) targetTile; //Instantiates the tile as a base object
        	 if(basetile.getPlayer() == this.getPlayerId()) //If its their own base
             {
             	JOptionPane.showMessageDialog(null, "Cannot go to your own base!", "Invalid Move!", JOptionPane.ERROR_MESSAGE); //shows an error message
             	return true;
             }
            return false; // It is the enemy base(Win condition)
        }
        return false; //If its anything else
    }
  
    /**
     * Method: alongthePath
     * Description: method used to see whether there the start and end direction of the move follows the bounds and conditions(for the game checks the direction of the hop and if there is a rat blocking the way)
     * @param preX: int
     * @param preY: int
     * @param targetX: int
     * @param targetY: int
     * @return true or false: boolean
     * References: https://www.youtube.com/watch?v=jzCxywhTAUI
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean alongthePath(int preX, int preY, int targetX, int targetY) 
    {
        Board board = currentTile.getBoard(); // Get the board from the current tile

        // Check vertical movement (upward direction)
        if (preX > targetX && preY == targetY) 
        {
            for (int x = preX - 1; x > targetX; x--) 
            {
                Tile intermediateTile = board.getTile(x, preY); // Get the tile on the path
                if (intermediateTile != null && intermediateTile instanceof Lake && intermediateTile.getPiece() != null && intermediateTile.getPiece().getType().equals("Rat")) {
                    // If the tile is valid and it is indeed a lake hop but there is a rat blocking the way
                    return false; //Cant cross the lake
                }
            }
        }
        
        // Check vertical movement (downward direction)
        if (preX < targetX && preY == targetY) {
            for (int x = preX + 1; x < targetX; x++) {
                Tile intermediateTile = board.getTile(x, preY); // Get the tile on the path
                if (intermediateTile != null && intermediateTile instanceof Lake && intermediateTile.getPiece() != null && intermediateTile.getPiece().getType().equals("Rat")) {
                	// If the tile is valid and it is indeed a lake hop but there is a rat blocking the way
                    return false;//cant cross the lake
                }
            }
        }

        // Check horizontal movement (left direction)
        if (preY > targetY && preX == targetX) {
            for (int y = preY - 1; y > targetY; y--) {
                Tile intermediateTile = board.getTile(preX, y); // Get the tile on the path
                if (intermediateTile != null && intermediateTile instanceof Lake &&  intermediateTile.getPiece() != null && intermediateTile.getPiece().getType().equals("Rat")) {
                	// If the tile is valid and it is indeed a lake hop but there is a rat blocking the way
                    return false; //cant cross the lake
                }
            }
        }

        // Check horizontal movement (right direction)
        if (preY < targetY && preX == targetX) {
            for (int y = preY + 1; y < targetY; y++) {
                Tile intermediateTile = board.getTile(preX, y); // Get the tile on the path
                if (intermediateTile != null && intermediateTile instanceof Lake && intermediateTile.getPiece() != null && intermediateTile.getPiece().getType().equals("Rat")) {
                	// If the tile is valid and it is indeed a lake hop but there is a rat blocking the way
                    return false; //cannot cross the lake
                }
            }
        }

        // If no rat is along the path and on a lake block gives the clear to cross
        return true;
    }
    /**
     * Method: isPathOverLake
     * Description: method used to see whether there the start and end direction of the move follows the bounds and conditions(for the game checks whether it is a hop over the lake and if there is a rat blocking the way)
     * @param startTile: Tile
     * @param endTile: Tile
     * @return true or false: boolean
     * References: https://www.youtube.com/watch?v=jzCxywhTAUI(Rook Movement)
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean isPathOverLake(Tile startTile, Tile endTile) 
    {
        int startX = startTile.getX(); //startX
        int startY = startTile.getY(); //startY
        int endX = endTile.getX(); //endX
        int endY = endTile.getY(); //endY

        // Determine the direction of movement
        int deltaX = Integer.compare(endX, startX); //if end is more than start then down else up
        int deltaY = Integer.compare(endY, startY); //if end is more than start then right else left

        // Check each tile along the path
        int currentX = startX + deltaX;
        int currentY = startY + deltaY;
        while (currentX != endX || currentY != endY) //The distance of the hop
        {
            Tile currentTile = startTile.getBoard().getTile(currentX, currentY); //Iterates through the tile on the path
            if (currentTile == null || !currentTile.getType().equals("Lake")) //If it is actually not a hop over the lake(meaning it tries to hop from empty to empty or lake to another place)
            {
                return false; // Path is not entirely over a lake
            }
            currentX += deltaX; //newX
            currentY += deltaY; //newY
        }

        return true; // Path is entirely over a lake
    }
    /**
     * Method: getType
     * Description: getter for Type
     * @return String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String getType() 
    {
        return this.getClass().getSimpleName(); // Returns the class name (not including packages or path)
    }
    /**
     * Method: display
     * Description: paramter for display(depends on animal)
     * @return String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String display() 
    {
        return "?"; //placeholder
    }

    /**
     * Method: stun
     * Description: drains the piece's power when they step on the trap
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public void stun() 
    {
        power = 0; // Power is temporarily set to 0
    }
    
    /**
     * Method: getCurrentTile
     * Description: gets the current Tile instance
     * @return currentTile: Tile
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public Tile getCurrentTile() 
    {
        return currentTile; 
    }
    
    /**
     * Method: recover
     * Description: restores the pieces power when they step out of the trap
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public void recover() 
    {
        power = originalPower; // Restore original power
    }
    /**
     * Method: getPower
     * Description: getter for power
     * @return power: int
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public int getPower() 
    {
        return power; 
    }

    /**
     * Method: getPlayer
     * Description: getter for player instance
     * @return player: Player
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public Player getPlayer()
    {
    	return player;
    }
    /**
     * Method: getPlayerId
     * Description: getter for playerId(shortcut and can be removed if needed)
     * @return playerId: int
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public int getPlayerId()
    {
    	return player.getPlayerId();
    }

}
