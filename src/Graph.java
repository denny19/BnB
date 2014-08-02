import java.util.*;
public class Graph {

	public static int countFreeSquares(int graph[][]) {
		int numOfSquares = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] == 0) {
					numOfSquares++;
				}
			}
		}
		return numOfSquares;
	}

	public static void colourSquare(int graph[][], int x, int y) {
		graph[x][y] = -1;
	}
	
	public static void doubleItems(ArrayList<Item> d){
		ArrayList<Item> copy = new ArrayList<Item>(d);
		for(Item temp:copy){
			if(temp.getIsSquare()==true){
				
			}
			else{
				Item jk = new Item(temp);
				d.add(jk);
			}
		}
	}

	public static int checkFreeSquare(int graph[][]) {
		int numOfSquares = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] == 0) {
					numOfSquares++;
				}
			}
		}
		return numOfSquares;
	}

	public static int getI(int ia[][]) {

		int i = ia.length;
		return (i);
	}

	public static int getJ(int ia[][]) {

		int j = ia[0].length;
		return (j);
	}

	public static void getACopy(int ia[][], int ib[][]) {

		for (int i = 0; i < ia.length; i++) {
			for (int j = 0; j < ia[i].length; j++) {
				ia[i][j] = ib[i][j];

			}
		}
	}
	
	public static boolean exactlySame(int a[][], int b[][]){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != b[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void addLabel(int ia[][], int label) {

		for (int i = 0; i < ia.length; i++) {
			for (int j = 0; j < ia[i].length; j++) {
				ia[i][j] = label;

			}
		}
	}

	// checks if pattern a will fit inside tempGraph. It checks for free squares
	// denoted by 0;
	public static boolean checkAndReplace(int[][] a, int[][] graph,int[][] tempGraph,int[][] lastCordinates) {
		for (int i = lastCordinates[0][0]; i < tempGraph.length; i++) {
			for (int j = 0; j < tempGraph[i].length; j++) {
				if ((tempGraph[i][j] == 0) || (tempGraph[i][j] == -2)) { 
					// Start verifying the rest of A
					boolean flag = true;
					int atleastOnce = 0;
					for (int k = 0; k < a.length; k++) {
						for (int l = 0; l < a[k].length; l++) {
							if ((i + k) >= tempGraph.length
									|| (j + l) >= tempGraph[i].length) {
								flag = false;
								break;
							}
							if (tempGraph[i + k][j + l] != 0
									&& tempGraph[i + k][j + l] != -2) {
								flag = false;
							} else {
								if (tempGraph[i + k][j + l] == 0) {
									atleastOnce++;
								}
							}
						}
					}
					// If there are free spaces then place pattern a inside tempGraph
					if (flag && atleastOnce > 0) {
						for (int k = 0; k < a.length; k++) {
							for (int l = 0; l < a[k].length; l++) {
								tempGraph[i + k][j + l] = -2;
								graph[i + k][j + l] = a[0][0];
								lastCordinates[0][0]=(i+k);
								lastCordinates[0][1]=(j+l);
							}
						}
						return true;
					}

				}

			}

		}
		return false;
	}

	public static boolean checkFits(int[][] a, int[][] tempGraph) {

		for (int i = 0; i < tempGraph.length; i++) {

			for (int j = 0; j < tempGraph[i].length; j++) {

				if ((tempGraph[i][j] == 0)) {
					boolean flag = true;
					for (int k = 0; k < a.length; k++) {
						for (int l = 0; l < a[k].length; l++) {
							if ((i + k) >= tempGraph.length
									|| (j + l) >= tempGraph[i].length) {
								flag = false;
								break;
							}
							if (tempGraph[i + k][j + l] != 0) {
								flag = false;
							}

						}
					}

					if (flag) {
						return true;
					}
					
				}

			}

		}
		return false;
	}
	   public static void main(String args[]){
	    	
		  
   		int jk[][]= new int[4][5];
   		Arra square = new Arra(jk);//Dimensions of the map
   		
   		Arra square2 = new Arra(jk);
   		Arra square3 = new Arra(jk);
   	
   		//Graph.colourSquare(square, 0, 2);//To colour a certain square to make it redundant
   HashSet<Arra> g = new HashSet<Arra>();
 	
	   g.add(square);
	   g.add(square2);
	  g.add(square3);
//	   for( int dom[][]:g){
//		   if(exactlySame(dom, square2)){
//			   System.out.println("true");
//			  break;
//		   }
//	   }
//    
     
    	 System.out.println("true");
    
   	
    }
}