package Snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Bot extends Player{

	public Bot(int startPosX, int startPosY, Color src_color, Canvas area_src) {
		super(startPosX, startPosY, src_color, area_src);
		// TODO Auto-generated constructor stub
	}
	@Override
	 public void run() {
		while(true){
			Random generator = new Random (System.currentTimeMillis());
			int st = generator.nextInt(3);
			int e = 0;
			switch(st){
			case 0 :
				e = KeyEvent.VK_RIGHT; break;
			case 1 :
				e = KeyEvent.VK_UP; break;
			case 2 :
				e = KeyEvent.VK_LEFT; break;
			case 3 :
				e = KeyEvent.VK_DOWN; break;
			default:
				break;
			}
			this.keyPress(e);
		}
	}	
	
}
