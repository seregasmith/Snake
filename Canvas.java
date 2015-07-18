package Snake;

import java.awt.Graphics;
import java.util.ArrayList;
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
		for(int i = 0; i < size-2; i++){
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
			bombom.setPos(x*Dot.DOT_SIZE,y*Dot.DOT_SIZE); // may be just move
			if( checkIsFreeDot(bombom) )
				isFree = true;
			System.out.println(bombom.getPosX() + ", " + bombom.getPosY());
		}
	}
	
	public Canvas(int dots_mount) {
		size = dots_mount;
		addBoards();
		generateRandBomBom();
	}
	
	public void draw(Graphics surface) {
		bombom.draw(surface);
    	for( Barrier dot : barrier_dots)
    		dot.draw(surface);
    	for( Snake snake : snakes){
    		snake.move();
    		snake.repaint(surface);
    	}
    }
	
	public void addSnake(Snake src) {
		snakes.add(src);
	}
	
	public ArrayList<Snake> getSnakes() { // I think it's stupid =) But the rule all attributes should be private.
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
		return true;
	}
}
