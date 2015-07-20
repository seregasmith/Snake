/**
 * @author Sergey Kuznetsov
 * Innopolis University
 * Summer School 2015
 */

package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	private ArrayList<SnakeDot> dots = new ArrayList<SnakeDot>();
	private MoveVector nextStep = MoveVector.UP;
	private Color color = Color.DARK_GRAY;
	private Player player_parent;
	private int length = 3;
	public boolean isMoved = true;
		
	public Snake(int posX, int posY, int length_src,MoveVector move_vector, Color clr, Player parent){
		color = clr;
		length = length_src;
		int xInc = 0; int yInc = 0;
		nextStep = move_vector;
		switch(nextStep){
		case UP :
			yInc-- ; break;
		case DOWN :
			yInc++ ; break;
		case RIGHT :
			xInc++ ; break;
		case LEFT :
			xInc-- ; break;
		default:
			break;
		}
		int curX = posX; int curY = posY;
		for(int i = 0; i < length; i++){
			dots.add(new SnakeDot(curX, curY, color, this));
			curX -= xInc*Dot.DOT_SIZE; curY -= yInc*Dot.DOT_SIZE;
		}
		player_parent = parent;
	}
	
	public List<SnakeDot> getDots() {
		return dots.subList(1, dots.size());
//		SnakeDot[] res = new SnakeDot[dots.size()];
//		int i = 0;
//		
//		for(SnakeDot dot : dots){
//			res[i++] = dot;
//		}
//		return res;
	}
	
	public SnakeDot getHead(){
		return dots.get(0);
	}
	
	public void move(){
		int xInc = 0; int yInc = 0;
		switch(nextStep){
		case UP :
			yInc-- ; break;
		case DOWN :
			yInc++ ; break;
		case RIGHT :
			xInc++ ; break;
		case LEFT :
			xInc-- ; break;
		default:
			break;
		}
		if(length < dots.size()-1){
			dots.add(0, new SnakeDot(dots.get(0).getPosX()+xInc*Dot.DOT_SIZE,
					dots.get(0).getPosY()+yInc*Dot.DOT_SIZE, color, this));
			dots.remove(dots.size()-1);
		}
		else{
			dots.add(0, new SnakeDot(dots.get(0).getPosX()+xInc*Dot.DOT_SIZE,
				dots.get(0).getPosY()+yInc*Dot.DOT_SIZE, color, this));
//			dots.remove(dots.size()-1);
		}
		isMoved = true;
	}
	
	public void incLength(){
		length++;
	}
	
	public void setNextStep(MoveVector vector){
		if(isMoved){
			nextStep = vector;
			isMoved = false;
		}
	}
	
	public MoveVector getNextStep(){
		return nextStep;
	}
	
	public void repaint(Graphics surface,int canvas_size){
		for( Dot dot : dots){
			if( dot.getPosX() > canvas_size - Dot.DOT_SIZE ){
				dot.setPos(0, dot.getPosY());
			};
			if( dot.getPosX() < 0 ){
				dot.setPos(canvas_size - Dot.DOT_SIZE, dot.getPosY());
			};
			if( dot.getPosY() > canvas_size - Dot.DOT_SIZE ){
				dot.setPos(dot.getPosX(), 0);
			};
			if( dot.getPosY() < 0 ){
				dot.setPos(dot.getPosX(), canvas_size - Dot.DOT_SIZE);
			};
    		dot.draw(surface);
		}
	}
}
