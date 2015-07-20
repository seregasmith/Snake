/**
 * @author Sergey Kuznetsov
 * Innopolis University
 * Summer School 2015
 */

package Snake;

import java.awt.Color;

public class SnakeDot extends Dot{
	Snake parent_snake;

	public SnakeDot(int posX, int posY, Color color, Snake parent) {
		super(posX, posY);
		this.color = color;
		parent_snake = parent;
	}

}
