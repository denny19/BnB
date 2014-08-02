
import java.util.ArrayList;
import java.util.Comparator;

import sun.nio.cs.ext.ISCII91;

public class Item {

	public static Comparator<Item> byLabel() {
		return new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				return i1.label - i2.label;
			}
		};
	}

	public static Comparator<Item> byRatio() {
		return new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				return Double.compare(i2.getRatio(), i1.getRatio());
			}
		};
	}

	private int label;
	private boolean isSquare;
	private int value;
	private int weight;
	private int x;
	private int y;
	private int[][] dimensions;
	private int[][] altDimensions;

	public double getRatio() {
		return value / weight;
	}

	public Item(int value, int x, int y, int label) {
		this.value = value;
		this.x = x;
		this.label = label;//
		this.y = y;
		createItem();

	}

	public Item(Item item) {
		this.value =item. value;
		this.x =item. y;
		this.label = item.label;//
		this.y = item.x;
		createItem();

	}
	public int getWeight() {
		return weight;
	}

	public void createItem() {
		dimensions = new int[x][y];
		altDimensions = new int[y][x];
		if (x == y) {
			isSquare = true;
		} else {
			isSquare = false;
		}
		weight = x * y;
		Graph.addLabel(dimensions, label);
		Graph.addLabel(altDimensions, label);
	}

	public boolean getIsSquare() {
		return isSquare;
	}

	public int getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

	public int[][] getDimensions() {
		return dimensions;
	}

	public int[][] getAltDimensions() {
		return altDimensions;
	}
	
	

}