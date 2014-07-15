import javax.swing.JFrame;
import javax.swing.JPanel;
 


import java.awt.Graphics;
 
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
import java.awt.Point;
 
public class Grid extends JPanel
{
	int rows;
	int columns;
 
	int xCo = -1;
	int yCo = -1;
 
	Point[][] points;
 
    public Grid(int r,int c) 
    {
    	rows = r;
    	columns = c;
    	points = new Point[rows][columns];
    
    	paintComponent(z);
    	this.addMouseListener(new UserClick());
    }
 
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	for(int x=0;x<rows;x++)
    	{
    		for(int y=0;y<columns;y++)
    		{
    			points[x][y] = new Point(x*50,y*50);
    			if(xCo==x && yCo==y)
    				g.fillRect(x*50,y*50,50,50);
    			else
    				g.drawRect(x*50,y*50,50,50);
    		}
    	}
    	xCo = -1;
    	yCo = -1;
    }
 
    public class UserClick extends MouseAdapter
    {
    	public UserClick()
    	{
    		super();
    	}
    	public void mouseClicked(MouseEvent e)
    	{
    		Point p = e.getPoint();
    		System.out.println("The Cell at location "+(int)(p.getX()/50)+","+(int)(p.getY()/50)+" was pressed.");
    		xCo = (int)(p.getX()/50);
    		yCo = (int)(p.getY()/50);
    	}
    }
    
    public static void main(String[] args) {
    	Grid r = new Grid(10,4);
	}
 
}