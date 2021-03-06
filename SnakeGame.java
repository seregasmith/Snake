/**
 * @author Sergey Kuznetsov
 * Innopolis University
 * Summer School 2015
 */

package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JFrame;

public class SnakeGame implements KeyListener {
	public static final int WIDTH = 350;
	public static final int HEIGHT = 350;
	public static final boolean HAS_BOARDS = true;
	public static final int BARRIERS_MOUNT = 15;
	public static final String FILE_PATH = "/tmp/snake_leaders";
	public static final String PLAYER_NAME = "JOHN";
	JFrame frame;
	final Render content;
	private Player player;
	private Bot bot;
	

	SnakeGame() {
		content = new Render(new Canvas(15), 15*Dot.DOT_SIZE);
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

		player = new Player(10 * Dot.DOT_SIZE, 10 * Dot.DOT_SIZE, Color.RED,
				content.getCanvas());
		bot = new Bot(8 * Dot.DOT_SIZE, 10 * Dot.DOT_SIZE, Color.RED,
				content.getCanvas());
		// content.getCanvas().addSnake(player.getSnake());
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
		new Thread(bot).start();
		frame.setVisible(true);
		while (true) {
			synchronized (player) {
				synchronized (content) {
					if (player.isOver()) {
						gameOver();
						break;
					}
				}
			}
		}
	}

	private void gameOver() {
		System.out.println("Final score of player: " + player.getScore());
		try {
			Writer writer = new Writer(FILE_PATH);
			writer.write(PLAYER_NAME + " " + player.getScore());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drawLeaderBoard();
		content.stop();
		player.stop();
	}

	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) {
		// displayInfo(e, "KEY TYPED: ");
	}

	/** Handle the key pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
		// displayInfo(e, "KEY PRESSED: ");
		player.keyPress(e);
	}

	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
		// displayInfo(e, "KEY RELEASED: ");
	}
	
	public void drawLeaderBoard() {
		LeadersReader reader = new LeadersReader(FILE_PATH);
		reader.read();
		Map<String,Integer> strings = reader.getStrings();
		content.drawLeaderBoard(strings);
	}

	// ///

	public static void main(String[] args) {
		SnakeGame game = new SnakeGame();
		game.start();
	}

}
