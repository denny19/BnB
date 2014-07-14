

import java.util.*;



public class BranchAndBound {//
	  protected List<Item> babItems;//The items that needs to be fit inside the map
	  protected int bnbGraph[][];//the Map of the area
	  protected int bnbCapacity;
	  protected int bnbI;
	  protected int bnbJ;
	
	private class Node implements Comparable<Node>{//
		
		public int nodelevel;//level of node in the bnb state space
		List<Item> nodetakenItems;//taken items inside this node
		public double nodebound;//bound of this node
		public double nodevalue;//value already inside this node
		public double nodeweight;//weight of this node 
		public int nodeGraph[][];//the Map for this node with the nodes items fitted inside
			
	public Node(){
		//create a new Node for the root node
		nodetakenItems = new ArrayList<Item>();
		nodeGraph=new int [bnbI][bnbJ];
		Graph.getACopy(nodeGraph, bnbGraph);
		
	}

	
	public Node(Node parent){
		//Create a new node from a parent node in the state space
		nodelevel= parent.nodelevel + 1;//child nodes level is one more than its parent
		nodetakenItems = new ArrayList<Item>(parent.nodetakenItems);//
		nodebound= parent.nodebound;//nodes bounds are same as the parents bound
		nodevalue= parent.nodevalue;//nodes value is the same as the parents
		nodeweight= parent.nodeweight;
		nodeGraph=new int [bnbI][bnbJ];
		Graph.getACopy(nodeGraph, parent.nodeGraph);//nodes graph is the same as the parents
	}
	
	public Node(Node Same,boolean k){
		//Used to create a node in the same level in the state space diagram
		//this is useful when an item is fit inside the Map in different orientations or different places.
		nodelevel= Same.nodelevel ;
		nodetakenItems = new ArrayList<Item>(Same.nodetakenItems);//
		nodebound= Same.nodebound;
		nodevalue= Same.nodevalue;
		nodeweight= Same.nodeweight;
		nodeGraph=new int [bnbI][bnbJ];
		Graph.getACopy(nodeGraph, Same.nodeGraph);
	}
	
	
	public int compareTo(Node other){//used to order nodes
		return (int) (other.nodebound - nodebound);
	}
	
    public void computeBound() {
    	//Used to compute the bound of a node
        int i = nodelevel;
        double w = nodeweight;
        nodebound = nodevalue;//the nodebound is changed to the node value and then subsequent items from the items available are added
        //together to get the new node bound
        Item item;
        if(i<babItems.size()){
        do {
           item = babItems.get(i);
           if (w + item.weight > bnbCapacity) break;
                    w += item.weight;//item is taken in the node
           nodebound += item.value;
           i++;
                } while (i < babItems.size());//
        nodebound += (bnbCapacity - w) * (item.getRatio());//change to integer bound not fractional??
     }
    }
    
	}
    public BranchAndBound (List<Item> items, int graph[][]) {//
    	//The main program with data related to the items and data on how the graph looks like
        this.babItems = items;
        this.bnbGraph=new int[graph.length][graph[0].length];
       Graph.getACopy(bnbGraph,graph );;
        this.bnbCapacity=  Graph.checkFreeSquare(bnbGraph);
        this.bnbI=   new Integer (Graph.getI(bnbGraph));
        this.bnbJ=new Integer (Graph.getJ(bnbGraph));
     }
   
     public void solve() {
    	 //The branch and bound implemetnation
        
        Collections.sort(babItems, Item.byRatio());//Items sorted by value/weight
        
        Node best = new Node();//the node with the current best solution
        Node root = new Node();//the node that is the root of the state space
        root.computeBound();
        
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        //priority queue used to store the nodes. It will give priority to node with the most promise
        q.offer(root);
        
        while (!q.isEmpty()) {
           Node node = q.poll();
           
           if (node.nodebound > best.nodevalue && node.nodelevel < babItems.size() ) {//if a better solution is available
        	   Node with = new Node(node);
        	   //an item is added to the state space to see if it gives a better solution
               Item item = babItems.get(node.nodelevel);//
               with.nodeweight += item.weight;
             
               if (with.nodeweight <= bnbCapacity) {
            	   //first the item is added in a horizontal orientation
            	   int tempGraph[][] = new int[bnbI][bnbJ];
            	   Graph.getACopy(tempGraph,with.nodeGraph);
            	   boolean cantReplace=true;
            	   do{
            	 //do loop used to make sure this item has been added in as many ways as possible in the horizontal orientation
            	   Node withHoriz = new Node(with,true);
            	   if(Graph.checkAndReplace(item.dimensions,withHoriz.nodeGraph,tempGraph)==true){
                   //if the item could be added to the Map
                   withHoriz.nodetakenItems.add(item);
                   withHoriz.nodevalue += item.value;
                   withHoriz.computeBound();
                   //a new bound and new value is calculated for the node
                   if (withHoriz.nodevalue > best.nodevalue) {
                      best = withHoriz;//if this solution was better the best solution found so far then change this solution to be the best
                   }
                   if (withHoriz.nodebound > best.nodevalue) {
                      q.offer(withHoriz);//if this solution has a better bound than the bound for the best value
                   }
               }
            	   else{cantReplace=false;}
            	   
            	   } while(cantReplace==true);
                }
               
               //Now the item will be tried in the vertical orientation to be added
               if (with.nodeweight <= bnbCapacity) {
            	   //add vertical as well
            	   int tempGraph[][] = new int[bnbI][bnbJ];
            	   Graph.getACopy(tempGraph,with.nodeGraph);
            	   boolean cantReplace=true;
            	   do{
            		 //do loop used to make sure this item has been added in as many ways as possible in the vertical orientation
            	   Node withVertical = new Node(with,true);
            	   if(Graph.checkAndReplace(item.altDimensions,withVertical.nodeGraph,tempGraph)==true){
                   //Item tried to be added in the vertical orientation
                   withVertical.nodetakenItems.add(item);
                   withVertical.nodevalue += item.value;
                   withVertical.computeBound();
                   //a new bound and new value is calculated for the node
                   if (withVertical.nodevalue > best.nodevalue) {
                      best = withVertical;
                   }
                   if (withVertical.nodebound > best.nodevalue) {
                      q.offer(withVertical);
                   }
               }
            	   else{cantReplace=false;}
            	   
            	   } while(cantReplace==true);
                }
     
              Node without = new Node(node);//A new node in the same level which considers the scenario were the item is not added
              without.computeBound();//
              
              if (without.nodebound > best.nodevalue) {//if this bound is better the value of the best noded then added to the queue
                 q.offer(without);
              }
           }
                
        }
        
        String approach;
        List<Item> items;//Solution has been found
        double weight;
         double value;
        
       value = best.nodevalue;
        weight = best.nodeweight;
       items = best.nodetakenItems;
        approach = "Using Branch and Bound the best feasible solution found";
        StringBuilder builder = new StringBuilder();
        builder.append(approach);
        builder.append(": ");
        builder.append(value);//
        builder.append(" ");
        builder.append(weight);
        builder.append("\n");
        
        Collections.sort(items, Item.byLabel());
        
    	for(int i=0; i < best.nodeGraph.length; i++) {
  		  for(int j=0; j < best.nodeGraph[i].length; j++) {
  		   System.out.print(best.nodeGraph[i][j]);
  		}
  		  System.out.println();
  	}
        
        for (Item item : items) {
           builder.append(item.label);//
           builder.append(" ");
        }
        
        
        System.out.println(builder);
       
     }
     
     public static void main(String args[]){
    	 final long startTime = System.currentTimeMillis();
    		ArrayList<Item> items = new ArrayList<Item>();//Items to be fitted inside the map
    	
    		items.add(new Item(3,2,1,1));
    		items.add(new Item(8,2,2,2));
    		items.add(new Item(4,3,1,3));
    	//	items.add(new Item(3,4,2,4));
    	//	items.add(new Item(4,8,2,5));
    		
    		int square[][] = new int[3][3];//Dimensions of the map
    		Graph.colourSquare(square, 1, 2);//To colour a certain square to make it redundant

    	BranchAndBound x = new BranchAndBound(items, square)	;
    	x.solve();
    	final long endTime = System.currentTimeMillis();

    	System.out.println("Total execution time: " + (endTime - startTime) );
     }
    
  }


