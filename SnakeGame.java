package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class SnakeGame implements KeyListener{
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    JFrame frame;
    final Render content;
    private Player player;
	
	SnakeGame(){
		content = new Render(new Canvas(30));
        frame = new JFrame("Snake!");
        Color bgColor = Color.white;
        frame.setBackground(bgColor);
        content.setBackground(bgColor);
        content.setSize(WIDTH, HEIGHT);
        content.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        content.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setSize(WIDTH, HEIGHT);
        frame.setContentPane(content);
        frame.setResizable(false);
        frame.pack();
        
		player = new Player(20*Dot.DOT_SIZE, 20*Dot.DOT_SIZE, Color.ORANGE,content.getCanvas());
//		content.getCanvas().addSnake(player.getSnake());
	}
	
	public void start() {
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowDeiconified(WindowEvent e) {
                content.start();
            }

            public void windowIconified(WindowEvent e) {
                content.stop();
            }
        });
		frame.addKeyListener(this);
        new Thread(content).start();
        new Thread(player).start();
        frame.setVisible(true);
        while(true){
        	synchronized (player) {				
        		if(player.isOver()){
        			gameOver();
        			break;
        		}
        	}
        	}
	}
	
	private void gameOver(){
		System.out.println("Final score of player: " + player.getScore());
		content.stop();
		player.stop();
	}
	
	/** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
//        displayInfo(e, "KEY TYPED: ");
    }
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
//        displayInfo(e, "KEY PRESSED: ");
        player.keyPress(e);
    }
     
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
//        displayInfo(e, "KEY RELEASED: ");
    }
    /////
	
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.start();
	}
	
	
}
