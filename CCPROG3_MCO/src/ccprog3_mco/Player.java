package ccprog3_mco;
import java.util.ArrayList; 

/**
 * Class: Player
 * Description: used to initiate a player instance and check their pieces and if they still have pieces left
 * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
 * 
 */ 
public class Player {
    private String name; //Name of the player(default Player1 and Player2)
    private ArrayList<Piece> pieces; // List of pieces per player
    private int number;//player number
    
    /**
     * Method: Player
     * Description: constructor for Player
     * @param number: int
     * @param name: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public Player(int number,String name) 
    {
    	this.number = number;
        this.name = name;
        this.pieces = new ArrayList<>(); //creates a new ArrayList to store the pieces
    }

    /**
     * Method: getName
     * Description: getter for name
     * @return name: String
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public String getName() 
    {
        return name;
    }
    /**
     * Method: getPlayerId
     * Description: getter for number
     * @return number: int
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public int getPlayerId() 
    {
        return number;
    }
    /**
     * Method: getPieces
     * Description: getter for ArrayList Piece
     * @return pieces: ArrayList Piece
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public ArrayList<Piece> getPieces() 
    {
        return pieces; //gets the pieces on the players list(updates after adding and removing)
    }

    /**
     * Method: addPiece
     * Description: adds the piece to the users so called inventory
     * @param piece: Piece
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public void addPiece(Piece piece) 
    {
        pieces.add(piece);
    }

    /**
     * Method: capturedPiece
     * Description: removes the piece from the players list once it is captured by another piece on the board
     * @param piece: Piece
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public void capturedPiece(Piece piece) 
    {
        pieces.remove(piece);
    }

    /**
     * Method: hasPiecesLeft
     * Description: checks if the user still has pieces left on the board(game does not end if they dont) and checks whether it should switch players or not
     * @return true or false: boolean
     * @author Lance Ethan S. Ong   Nick Jenson Crescini S14
     * 
     */ 
    public boolean hasPiecesLeft() 
    {
    	if(pieces.isEmpty()) //If the Users List has no more pieces 
    	{
    		return false; //no pieces left
    	}
    	else
    	{
    		return true; //has pieces left
    	}
    }
    
}
