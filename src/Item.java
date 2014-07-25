

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
   
   public int label;
   public boolean isSquare;
   public double value;
   public int weight;
   public int x;
   public int y;
   public int[][] dimensions;
   public int[][] altDimensions;
   
   public double getRatio() {
      return value / weight;
   }
   
   
   
   public Item(double value, int x, int y, int label){
	   this.value=value;
	   this.x=x;
	   this.label=label;//
	   this.y=y;
	   createItem();
	   
   }
   
   public double getWeight(){
	   int[][] ia = new int[5][6];
	   int i=ia.length;
	   int j =ia[0].length;
	   return (i*j);
   }
   public void createItem(){
	   dimensions=new int[x][y];
	   altDimensions= new int[y][x];
	   if(x==y){
		   isSquare=true;
	   }
	   else{
		   isSquare=false;
	   }
	   weight=x*y;
	   Graph.addLabel(dimensions, label);
	   Graph.addLabel(altDimensions, label);
   }
   public int getX(){
	   return x;
   }
   public int getY(){
	   return y;
   }
   

}