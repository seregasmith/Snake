package Snake;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LeaderBoard {
	static class ValueComparator implements Comparator<String> {
   	 
        Map<String, Integer> map;
     
        public ValueComparator(Map<String, Integer> base) {
            this.map = base;
        }
     
        public int compare(String a, String b) {
            if (map.get(a) >= map.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys 
        }
    }
	
	public static TreeMap<String, Integer> SortByValue 
	(Map<String, Integer> strings2) {
		ValueComparator vc =  new ValueComparator(strings2);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(strings2);
		return sortedMap;
	}
	
	private int x;
    private int y;
    protected Color color = new Color(200,100,50,100);
    private static int size = 20;
    private Map<String,Integer> strings = new HashMap<String, Integer>();

    /**
     * Initialize a new box with its center located at (startX, startY), filled * with startColor.
     */
    public LeaderBoard(int posX, int posY, int boardSize) {
        x = posX;
        y = posY;
        size = boardSize;
    }    

    /** Draws the box at its current position on to surface. */
    public void draw(Graphics surface) {
        // Draw the object
        surface.setColor(color);
        surface.fillRect(x, y, size, size);
        surface.setColor(Color.BLACK);
        ((Graphics2D) surface).setStroke(new BasicStroke(5.0f));
        surface.drawRect(x, y, size, size);
        int labelPosX = x+30;
        int labelPosY = y + 50;
    	surface.setFont(Font.decode(Font.SERIF).deriveFont((float)14));
    	surface.drawString("LEADERS:", labelPosX, labelPosY);
    	labelPosY += 20;
    	Map<String,Integer> sorted_strings = SortByValue(strings);
        for(String key : sorted_strings.keySet()){
        	String string = key + "....." + strings.get(key);
	          
	        surface.drawString(string, labelPosX, labelPosY);
	        if(labelPosY < size)
	        	labelPosY += 20;
	        else
	        	break;
        }
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
    
    public void setStrings(Map<String,Integer> strings_src){
    	strings = strings_src;
    }
}
