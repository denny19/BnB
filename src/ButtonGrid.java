import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonGrid implements ActionListener {

	JFrame frame; // creates frame
	JPanel titlePanel, itemPanel, componentPanel;//creates panels for items to be places inside
	JButton[][] grid; // names the grid of buttons that represents the area map
	// JButton[][] itemGrid;
	int width, height;//width and height of the area map
	JButton resetButton, calButton, addButton, dButton, remButton;//buttons used inside the program
	JLabel heightL, widthL, benefitL;
	JTextField widthTF, heightTF, benefitTF;//Text fields used to obtain information about an item
	int widthC, heightC, benefitC;//int versions of the text fields
	JPanel totalGUI;
	JButton itemGrid2[] = new JButton[80];//Used to display the items
	Integer counter = 1;
	ArrayList<Item> items = new ArrayList<Item>();//The arraylist of items that will be the parameter to the branch and bound algorithm
	JTextArea resultArea = new JTextArea();//Displays the results obtained from the algorithm

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resetButton) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {

					grid[x][y].setBackground(Color.WHITE);
				}

			}
		}

		else if (e.getSource() == addButton) {

			widthC = Integer.parseInt(widthTF.getText());
			heightC = Integer.parseInt(heightTF.getText());
			benefitC = Integer.parseInt(benefitTF.getText());

			String text = ((counter) + " ," + widthTF.getText() + "*"
					+ heightTF.getText() + " ,B: " + benefitTF.getText());
			itemGrid2[counter] = new JButton("");
			itemGrid2[counter].setSize(42, 42);
			itemGrid2[counter].setFont(new Font("Arial", Font.BOLD, 11));
			itemGrid2[counter].setText(text);
			componentPanel.add(itemGrid2[counter]);

			items.add(new Item(benefitC, widthC, heightC, counter));

			counter++;

		}

		else if (e.getSource() == calButton) {

			int[][] graph = new int[width][height];
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < graph[i].length; j++) {
					if (grid[i][j].getBackground() == Color.WHITE) {
						graph[i][j] = 0;
					} else {
						graph[i][j] = -1;
					}
				}
			}
			BranchAndBound d = new BranchAndBound(items, graph);

			graph = d.solve();
			System.out.println("Solved!");

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {

					if (graph[x][y] == -1) {
						grid[x][y].setBackground(Color.BLACK);
					} else if (graph[x][y] == 0) {
						grid[x][y].setBackground(Color.WHITE);
					} else {
						grid[x][y].setBackground(Color.WHITE);
						String texts = graph[x][y] + "";
						grid[x][y].setText(texts);
					}
				}

			}
			resultArea.setText(d.returnSol());

		}

		else {

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (e.getSource() == grid[x][y]) {
						if (grid[x][y].getBackground() == Color.WHITE) {
							grid[x][y].setBackground(Color.BLACK);
						} else {
							grid[x][y].setBackground(Color.WHITE);
						}
					}

				}
			}
		}
		totalGUI.revalidate();
		// frame.validate();

	}

	public ButtonGrid(int width, int length) { // constructor
		frame = new JFrame("Town Planning");
		totalGUI = new JPanel();
		this.width = width;
		this.height = length;
		totalGUI.setLayout(null);
		grid = new JButton[width][length]; // allocate the size of grid
		titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setLocation(20, 50);
		titlePanel.setSize(42 * (length + 4), 42 * (width + 3));
		totalGUI.add(titlePanel);
		resultArea.setLocation(60, 42 * (width + 5));
		resultArea.setSize(350, 250);
		totalGUI.add(resultArea);

		// componentPanel.setLayout(new FlowLayout());

		resetButton = new JButton("Reset");
		resetButton.setLocation(42 * (length + 1), 0);
		resetButton.setSize(70, 42);
		resetButton.addActionListener(this);
		titlePanel.add(resetButton);

		calButton = new JButton("Calculate!");
		calButton.setLocation(60, 42 * (width + 1));
		calButton.setSize(150, 30);
		calButton.addActionListener(this);
		titlePanel.add(calButton);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < length; y++) {
				grid[x][y] = new JButton(""); // creates new button
				// adds button to grid
				grid[x][y].setLocation(y * 42, x * 42);
				grid[x][y].setSize(42, 42);
				grid[x][y].addActionListener(this);
				grid[x][y].setBackground(Color.WHITE);
				titlePanel.add(grid[x][y]);
			}
		}

		itemPanel = new JPanel();
		itemPanel.setLayout(null);
		itemPanel.setLocation(42 * (length + 5), 50);
		itemPanel.setSize(400, 300);
		totalGUI.add(itemPanel);

		heightL = new JLabel("Height");
		heightL.setLocation(0, 40);
		heightL.setSize(100, 20);
		heightL.setForeground(Color.black);
		itemPanel.add(heightL);

		widthL = new JLabel("Width");
		widthL.setLocation(0, 0);
		widthL.setSize(100, 20);
		widthL.setForeground(Color.black);
		itemPanel.add(widthL);

		benefitL = new JLabel("Benefit");
		benefitL.setLocation(0, 80);
		benefitL.setSize(100, 20);
		benefitL.setForeground(Color.black);
		itemPanel.add(benefitL);

		heightTF = new JTextField(5);
		heightTF.setText(1 + "");
		heightTF.setLocation(100, 40);
		heightTF.setSize(100, 20);
		itemPanel.add(heightTF);

		widthTF = new JTextField(1);
		widthTF.setText(1 + "");
		widthTF.setLocation(100, 0);
		widthTF.setSize(100, 20);
		itemPanel.add(widthTF);

		benefitTF = new JTextField(5);
		benefitTF.setText(10 + "");
		benefitTF.setLocation(100, 80);
		benefitTF.setSize(100, 20);
		itemPanel.add(benefitTF);

		addButton = new JButton("Add Item");
		addButton.setLocation(220, 0);
		addButton.setSize(100, 30);
		addButton.addActionListener(this);
		itemPanel.add(addButton);

		componentPanel = new JPanel();
		componentPanel.setLayout(new FlowLayout());
		if (length < 11) {
			componentPanel.setLocation(450, 200);
		} else {
			componentPanel.setLocation(42 * (length + 3), 200);
		}

		componentPanel.setSize(500, 500);
		totalGUI.add(componentPanel);

		totalGUI.setOpaque(true);
		totalGUI.setPreferredSize(new Dimension(1000, 1000));
		JScrollPane scrPane = new JScrollPane(totalGUI);
		frame.getContentPane().add(scrPane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // makes frame visible
	}

	public static void main(String[] args) {
		new ButtonGrid(6, 6);// makes new ButtonGrid with 2 parameters
	}
}