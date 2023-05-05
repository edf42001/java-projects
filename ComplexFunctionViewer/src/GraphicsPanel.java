import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel{
	
	public GraphicsPanel() throws Exception{
		setPreferredSize(new Dimension(800,800));  // Set these dimensions to the width of your background picture
					
        this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g){	
		Graphics2D g2 = (Graphics2D) g;
		
		HashMap<Integer, Integer> terms = new HashMap<Integer, Integer>();
		terms.put(3, 1);
		terms.put(0, 1);
		Equation equation = new Equation(terms);
		
		
		for(int x = 0; x<800; x++){
			for(int y = 0; y<800; y++){
				Complex c = new Complex((x-400)/100.0, -(y-400)/100.0);
				Complex c2 = equation.calculate(c);
				g2.setColor(c2.getColor());
				g2.drawLine(x,y,x,y);
			}
		}
	}
}
