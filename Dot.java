package Snake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Dot {
	private int x;
    private int y;
    protected Color color = Color.BLACK;
    public final static int DOT_SIZE = 20;
    
    enum Type{SNAKE_BODY,BOARD,BOMBOM};

    /**
     * Initialize a new box with its center located at (startX, startY), filled * with startColor.
     */
    public Dot(int posX, int posY) {
        x = posX;
        y = posY;
    }    

    /** Draws the box at its current position on to surface. */
    public void draw(Graphics surface) {
        // Draw the object
        surface.setColor(color);
        surface.fillRect(x - DOT_SIZE / 2, y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE);
        surface.setColor(Color.BLACK);
        ((Graphics2D) surface).setStroke(new BasicStroke(2.0f));
        surface.drawRect(x - DOT_SIZE / 2, y - DOT_SIZE / 2, DOT_SIZE, DOT_SIZE);
    }
    
    public int getPosX(){
    	return x;
    }
    public int getPosY(){
    	return y;
    }
    
    public void setPos(int x1, int y1){
    	x=x1; y=y1;
    }
    
    public boolean equalPos(Dot other){
    	if( x == other.x && y == other.y)
    		return true;
    	return false;
    }
}
