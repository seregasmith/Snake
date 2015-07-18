package Snake;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Thread{
	

	private int snake_length = 4;
	private Snake snake;
	private Color color;
	private int score = 0;
	private Canvas area;
	private boolean isOver = false;
	
	public Player(int startPosX, int startPosY, Color src_color, Canvas area_src){
		color = src_color;
		snake = new Snake(startPosX, startPosY, snake_length, MoveVector.UP,color,this);
		area = area_src;
		area.addSnake(snake);
	}
	
	public Snake getSnake(){
		return snake;
	}
	
	public void eatBomBom(){
		score+=10;
		snake.incLength();
		System.out.println("Player eat bombom. Total score is " + score);
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean isOver() {
		return isOver;
	}
	
	public void run(){
		while (!isOver) {
			if (checkIsOver())
				isOver = true;
//			System.out.println(snake.getHead().getPosX() + ", "
//					+ snake.getHead().getPosY() + ", "
//					+ area.getBombom().getPosX() + ", "
//					+ area.getBombom().getPosY());
			if (snake.getHead().equalPos(area.getBombom())) {
				area.generateRandBomBom();
				eatBomBom();
			}
//			try {
//				sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		stop();
	}

	public void keyPress(KeyEvent e) {

	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	    	if(
	    			snake.getNextStep() != MoveVector.RIGHT
	    			&&
	    			snake.getNextStep() != MoveVector.LEFT
	    			)
	    		snake.setNextStep(MoveVector.LEFT);
//	        System.out.println("LEFT");
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	if(
	    			snake.getNextStep() != MoveVector.RIGHT
	    			&&
	    			snake.getNextStep() != MoveVector.LEFT
	    			)
	    		snake.setNextStep(MoveVector.RIGHT);
//	    	System.out.println("RIGHT");
	    }

	    if (key == KeyEvent.VK_UP) {
	    	if(
	    			snake.getNextStep() != MoveVector.UP
	    			&&
	    			snake.getNextStep() != MoveVector.DOWN
	    			)
	    		snake.setNextStep(MoveVector.UP);
//	    	System.out.println("UP");
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	if(
	    			snake.getNextStep() != MoveVector.UP
	    			&&
	    			snake.getNextStep() != MoveVector.DOWN
	    			)
	    		snake.setNextStep(MoveVector.DOWN);
//	    	System.out.println("DOWN");
	    }
	}
	
	///GAME LOGIC
	private boolean checkIsOver(){
		for(Barrier dot : area.getBarries()){
			if ( snake.getHead().equalPos(dot) ){
//				System.out.println("The game is over");
				return true;
			}
		}
		return false;
	}
}
