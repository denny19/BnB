

import java.util.*;



public class BranchAndBound {//
	  protected List<Item> babItems;//
	  protected int bnbGraph[][];//
	  protected int bnbCapacity;
	  protected int bnbI;
	  protected int bnbJ;
	
	private class Node implements Comparable<Node>{//
		
		public int nodelevel;
		List<Item> nodetakenItems;
		public double nodebound;
		public double nodevalue;//
		public double nodeweight;
		public int nodeGraph[][];
			
	public Node(){
		nodetakenItems = new ArrayList<Item>();
		nodeGraph=new int [bnbI][bnbJ];
		
	}

	
	public Node(Node parent){
		nodelevel= parent.nodelevel + 1;
		nodetakenItems = new ArrayList<Item>(parent.nodetakenItems);//
		nodebound= parent.nodebound;
		nodevalue= parent.nodevalue;
		nodeweight= parent.nodeweight;
		nodeGraph=new int [bnbI][bnbJ];
		Graph.getACopy(nodeGraph, parent.nodeGraph);
	}
	
	public Node(Node Same,boolean k){
		nodelevel= Same.nodelevel ;
		nodetakenItems = new ArrayList<Item>(Same.nodetakenItems);//
		nodebound= Same.nodebound;
		nodevalue= Same.nodevalue;
		nodeweight= Same.nodeweight;
		nodeGraph=new int [bnbI][bnbJ];
		Graph.getACopy(nodeGraph, Same.nodeGraph);
	}
	
	
	public int compareTo(Node other){//
		return (int) (other.nodebound - nodebound);
	}
	
    public void computeBound() {
        int i = nodelevel;
        double w = nodeweight;
        nodebound = nodevalue;
        Item item;
        if(i<babItems.size()){
        do {
           item = babItems.get(i);
           if (w + item.weight > bnbCapacity) break;
                    w += item.weight;//item is taken in the node
           nodebound += item.value;
           i++;
                } while (i < babItems.size());//
        nodebound += (bnbCapacity - w) * (item.getRatio());//change to integer bound not fractional
     }
    }
    
	}
    public BranchAndBound (List<Item> items, int graph[][]) {//
        this.babItems = items;
        this.bnbGraph=new int[8][4];
     //    Graph.getACopy(bnbGraph, );;
        this.bnbCapacity=  Graph.checkFreeSquare(bnbGraph);
        this.bnbI=   new Integer (Graph.getI(bnbGraph));
        this.bnbJ=new Integer (Graph.getJ(bnbGraph));
     }
    //
    public double getWeight(List<Item> items) {
        double weight = 0;
        for (Item item : items) {
           weight += item.weight;//
        }
        return weight;
     }
     
     public double getValue(List<Item> items) {//
        double value = 0;
        for (Item item : items) {
           value += item.value;//
        }
        return value;
     }
     
     
     public void solve() {//
        
        Collections.sort(babItems, Item.byRatio());
        
        Node best = new Node();
        Node root = new Node();//
        root.computeBound();
        
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        q.offer(root);
        
        while (!q.isEmpty()) {
           Node node = q.poll();
           
           if (node.nodebound > best.nodevalue && node.nodelevel < babItems.size() ) {//
        	   Node with = new Node(node);
        	   
               Item item = babItems.get(node.nodelevel);//
               with.nodeweight += item.weight;
             
               if (with.nodeweight <= bnbCapacity) {
            	   //add vertical as well
            	   int tempGraph[][] = new int[8][4];
            	   Graph.getACopy(tempGraph,with.nodeGraph);
            	   boolean cantReplace=true;
            	   do{
            	 
            	   Node withHoriz = new Node(with,true);
            	   if(Graph.checkAndReplace(item.dimensions,withHoriz.nodeGraph,tempGraph)==true){
                   
                   withHoriz.nodetakenItems.add(item);
                   withHoriz.nodevalue += item.value;
                   withHoriz.computeBound();
                   
                   if (withHoriz.nodevalue > best.nodevalue) {
                      best = withHoriz;
                   }
                   if (withHoriz.nodebound > best.nodevalue) {
                      q.offer(withHoriz);
                   }
               }
            	   else{cantReplace=false;}
            	   
            	   } while(cantReplace==true);
                }
               //add vertical as well
     
              Node without = new Node(node);
              without.computeBound();//
              
              if (without.nodebound > best.nodevalue) {//
                 q.offer(without);
              }
           }
                
        }
        
        String approach;
        List<Item> items;//
        double weight;
         double value;
        
       value = best.nodevalue;
        weight = best.nodeweight;//
       items = best.nodetakenItems;
        approach = "Using Branch and Bound the best feasible solution found";//
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
        
        for (Item item : items) {//
           builder.append(item.label);//
           builder.append(" ");
        }
        
        
        System.out.println(builder);
       
     }
     
     public static void main(String args[]){
    	 final long startTime = System.currentTimeMillis();
    		ArrayList<Item> items = new ArrayList<Item>();
    	
    		items.add(new Item(5,3,1,1));
    		items.add(new Item(8,2,1,2));
    		items.add(new Item(3,4,2,3));
    		items.add(new Item(3,4,2,4));
    		items.add(new Item(4,8,2,5));

    	BranchAndBound x = new BranchAndBound(items, new int [8][4])	;
    	x.solve();
    	final long endTime = System.currentTimeMillis();

    	System.out.println("Total execution time: " + (endTime - startTime) );
     }
    
  }


