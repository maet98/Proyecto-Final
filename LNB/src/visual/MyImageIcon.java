package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MyImageIcon extends ImageIcon {
	public MyImageIcon(String filename) {
		super(filename);
	};
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		if(getImageObserver()==null) {
			g.drawImage(getImage(), c.getWidth()/2-getIconWidth()/2, c.getHeight()/2 - getIconHeight()/2, c);
		}
		else {
			g.drawImage(getImage(), 
 		    c.getWidth()/2 - getIconWidth()/2, 
 		    c.getHeight()/2 - getIconHeight()/2, 
 		    getImageObserver()); 
		}
	}
}
