/**
 * @author Sergey Kuznetsov
 * Innopolis University
 * Summer School 2015
 */

package Snake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Canvas {
	private ArrayList<Barrier> barrier_dots = new ArrayList<Barrier>();
	private BomBom bombom = new BomBom(0,0);
	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private int size;
	
	// METHODS
	private void addBoards(){
		int x = 0; int y = 0;
		for(int i = 0; i < size; i++){
			barrier_dots.add(new Barrier(x,y));
			x += Dot.DOT_SIZE;
		}
		for(int i = 0; i < size-1; i++){
			y += Dot.DOT_SIZE;
			barrier_dots.add(new Barrier(0,y));
			barrier_dots.add(new Barrier((size-1)*Dot.DOT_SIZE,y));
		}
		x=0;
		for(int i = 0; i < size; i++){
			barrier_dots.add(new Barrier(x,y));
			x += Dot.DOT_SIZE;
		}
	}
	
	public void generateRandBomBom(){
		boolean isFree = false;
		int x = 0; int y = 0;
		while(!isFree){
			Random generator = new Random (System.currentTimeMillis());
			x = 1+generator.nextInt(size-2); y = 1 + generator.nextInt(size-2);
//			System.out.println("BOMBOM: "+ x + ", " + y);
//			if(!snakes.isEmpty()){
////				System.out.println(snakes.get(0).getHead().getPosX()/Dot.DOT_SIZE + ", " + snakes.get(0).getHead().getPosY()/Dot.DOT_SIZE);
////				for(SnakeDot dot : snakes.get(0).getDots())
////					System.out.println(dot.getPosX()/Dot.DOT_SIZE + ", " + dot.getPosY()/Dot.DOT_SIZE);
//			}
			bombom.setPos(x*Dot.DOT_SIZE,y*Dot.DOT_SIZE);
			if( checkIsFreeDot(bombom) )
				isFree = true;
		}
	}
	
	public void generateRandBarrier(){
		boolean isFree = false;
		int x = 0; int y = 0;
		Barrier barrier = new Barrier(x, y);
		while(!isFree){
			Random generator = new Random (System.currentTimeMillis());
			x = 0+generator.nextInt(size-1); y = 0 + generator.nextInt(size-1);
//			System.out.println("BOMBOM: "+ x + ", " + y);
//			if(!snakes.isEmpty()){
////				System.out.println(snakes.get(0).getHead().getPosX()/Dot.DOT_SIZE + ", " + snakes.get(0).getHead().getPosY()/Dot.DOT_SIZE);
////				for(SnakeDot dot : snakes.get(0).getDots())
////					System.out.println(dot.getPosX()/Dot.DOT_SIZE + ", " + dot.getPosY()/Dot.DOT_SIZE);
//			}
			barrier.setPos(x*Dot.DOT_SIZE,y*Dot.DOT_SIZE);
			if( checkIsFreeDot(barrier) )
				isFree = true;
		}
		barrier_dots.add(barrier);
	}
	
	public Canvas(int dots_mount) {
		size = dots_mount;
		if(SnakeGame.HAS_BOARDS)
			addBoards();
		else{
			for(int i=0; i< SnakeGame.BARRIERS_MOUNT; i++)
				generateRandBarrier();
		}
		generateRandBomBom();
	}
	
	public void drawBoardLine(Graphics surface){
		surface.setColor(Color.BLUE);
        ((Graphics2D) surface).setStroke(new BasicStroke(2.0f));
        surface.drawRect(0, 0, size*Dot.DOT_SIZE, size*Dot.DOT_SIZE);
	}
	
	public void draw(Graphics surface) {
		drawBoardLine(surface);
		bombom.draw(surface);
    	for( Barrier dot : barrier_dots)
    		dot.draw(surface);
    	for( Snake snake : snakes){
    		snake.move();
    		snake.repaint(surface, size*Dot.DOT_SIZE);
    	}
    	
    }
	
	public void addSnake(Snake src) {
		snakes.add(src);
	}
	
	public ArrayList<Snake> getSnakes() {
		return snakes;
	}
	
	public ArrayList<Barrier> getBarries() {
		return barrier_dots;
	}
	
	public BomBom getBombom() {
		return bombom;
	}
	
	private boolean checkIsFreeDot(Dot src){
		for(Snake snake : snakes){
			for(Dot dot : snake.getDots())
				if ( dot.equalPos(src) )
					return false;
		}
		for(Barrier dot : barrier_dots){
			if ( dot.equalPos(src) )
				return false;
		}
		return true;
	}
}
