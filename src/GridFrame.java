import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;

/**
 * A frame that shows a n * n grid.
 */
public class GridFrame extends JFrame {

	private JLabel width;
	private JLabel height;
	private JTextField widthField;
	private JTextField heightField;
	private JButton button;
	private JPanel panel;
	public GridComponent component;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;

	
	public GridFrame() {
		// Use helper methods
		createTextField();
		createButton();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createTextField() {
		width = new JLabel("Width");
		height = new JLabel("Height");
		final int FIELD_WIDTH = 10;
		widthField = new JTextField(FIELD_WIDTH);
		widthField.setText(10 + "");
		heightField = new JTextField(FIELD_WIDTH);
		heightField.setText(10 + "");
	}

	private void createButton() {
		button = new JButton("Draw");
		class GridListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				component = new GridComponent(Integer.parseInt(widthField
						.getText()),Integer.parseInt(heightField
								.getText()));
				panel.add(component);
				panel.updateUI();
			}
		}

		ActionListener listener = new GridListener();
		button.addActionListener(listener);

	}

	private void createPanel() {
		panel = new JPanel();
		panel.add(width);
		panel.add(widthField);
		panel.add(height);
		panel.add(heightField);
		panel.add(button);
		add(panel);
	}

	public static void main(String[] args) {
		int k=0;
		
		JFrame frame = new GridFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		do{
		frame.setTitle("GridViewer");
		frame.setVisible(true);
		}while( k<4);
	}

}
