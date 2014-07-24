

public class Graph {
	

	
	public static int countFreeSquares(int graph[][]){
		int numOfSquares=0;
		for(int i=0; i < graph.length; i++) {
			  for(int j=0; j < graph[i].length; j++) {
			    if(graph[i][j] == 0) {
			     numOfSquares++;
			  }
			}
		}
		return numOfSquares;
	}
	
	public static void colourSquare(int graph[][],int x, int y){
		graph[x][y]= -1;
	}
	
public static int checkFreeSquare(int graph[][]){
	int numOfSquares=0;
	for(int i=0; i < graph.length; i++) {
		  for(int j=0; j < graph[i].length; j++) {
		    if(graph[i][j] == 0) {
		     numOfSquares++;
		  }
		}
	}
	return numOfSquares;
}

public static int getWeight(int ia[][]){
	   
	   int i=ia.length;
	   int j =ia[0].length;
	   return (i*j);
}
public static int getI(int ia[][]){
	   
	   int i=ia.length;
	   return (i);
}
public static int getJ(int ia[][]){
	   
	   int j=ia[0].length;
	   return (j);
}
public static void getACopy(int ia[][], int ib[][]){
	   
	for(int i=0; i < ia.length; i++) {
		  for(int j=0; j < ia[i].length; j++) {
		   ia[i][j] = ib[i][j];
		    
		  }
		}
	}

public static void addLabel(int ia[][],int label){
	   
	for(int i=0; i < ia.length; i++) {
		  for(int j=0; j < ia[i].length; j++) {
		   ia[i][j] = label;
		    
		  }
		}
	}

//checks if pattern a will fit inside tempGraph. It checks for free squares denoted by 0;
public static boolean checkAndReplace(int[][] a, int[][] graph, int[][] tempGraph){
	
    for(int i = 0; i < tempGraph.length; i++){
    	
        for(int j = 0; j < tempGraph[i].length; j++){
   	
            if(tempGraph[i][j] == 0){ //c[i][j] should match up with a[0][0].
                // Start verifying the rest of A
                boolean flag = true;
                for (int k = 0; k < a.length; k++) {
                    for (int l = 0; l < a[k].length; l++) {
                        if ((i+k) >= tempGraph.length || (j+l) >= tempGraph[i].length) {
                            flag = false;
                           break;
                        }
                        if (tempGraph[i+k][j+l] != 0) {
                            flag = false;
                        }
                    }
                }
                // If there are free spaces then place pattern a inside tempGraph
                if (flag) {
                    for (int k = 0; k < a.length; k++) {
                        for (int l = 0; l < a[k].length; l++) {
                            tempGraph[i+k][j+l] = a[0][0];
                            graph[i+k][j+l]=a[0][0];
                        }
                    }
                   return true;
                }
                
                
            }
         
    }
    

    
    
}
    return false;
}
}


