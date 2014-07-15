import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class GridComponent extends JComponent
{
	private int width;
	private int height;
	public GridComponent(int width, int height)
	{
	this.width = width;
	this.height = height;
	setPreferredSize(new Dimension(1000, 1000));//moves grid to side of the screen

	}
	public void paintComponent(Graphics g)
	{
	Graphics2D g2 = (Graphics2D) g;

			int countwidth = width;
			int countheight=height;
			int size = 20;
	
			for( int i = 0; i < countwidth; i ++)
			{
				for( int j = 0; j < countheight; j++)
				{
				Rectangle grid = new Rectangle( 300 + i * size, 20 + j * size, size, size);	
				g2.draw(grid);
			
				}
			}
	 }
	
	
}