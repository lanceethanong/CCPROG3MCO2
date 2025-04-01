package ccprog3_mco;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* Class: MouseMovement
* Description: Allows for user to move the pieces via mouse click and drag
* References: https://www.youtube.com/watch?v=jzCxywhTAUI
* @author Lance Ethan S. Ong  Nick Jenson Crescini S14
*/
public class MouseMovement extends MouseAdapter 
{
    public int x, y; //x and y
    public boolean mousePressed; //If mouse is clicked(wants to move the piece)
    
    /**
    * Method: mousePressed
    * Description: When user clicks on a piece
    * @author Lance Ethan S. Ong  Nick Jenson Crescini S14
    */
    @Override
    public void mousePressed(MouseEvent e) 
    {
        mousePressed = true; //sets mouse press as true
        updatePosition(e); //moves the piece to the tile mouse cursor is on
    }

    /**
    * Method: mouseDragged
    * Description: When user drags the piece to a tile
    * @author Lance Ethan S. Ong  Nick Jenson Crescini S14
    */
    @Override
    public void mouseDragged(MouseEvent e) {
        updatePosition(e); //moves the piece to the tile mouse drags the cursor to
    }

    /**
    * Method: mouseReleased
    * Description: When user releases the button signaling thats where they want to place the piece
    * @author Lance Ethan S. Ong  Nick Jenson Crescini S14
    */
    
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false; //when mouse is release it signals a move
        updatePosition(e);
    }
    
    /**
    * Method: mouseMoved
    * Description: When the move has been successfully moved to a tile
    * @author Lance Ethan S. Ong  Nick Jenson Crescini S14
    */
    @Override
    public void mouseMoved(MouseEvent e) {
        updatePosition(e);
    }
    
    /**
    * Method: updatePosition
    * Description: Updates the entities on the board relative to the mouse cursor position
    * @author Lance Ethan S. Ong  Nick Jenson Crescini S14
    */
    private void updatePosition(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}