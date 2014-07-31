


public  class Arra {
	private int kl[][];
	
	public Arra(int d[][]){
		this.kl=d;
	}
	
		@Override
	   public boolean equals(Object obj) {
	        if (Graph.exactlySame(this.kl, obj.kl)==true){
	        	return true;
	        }
	            return false;
	    }
		
		@Override
		public int hashCode() {
	        int hc = 17;
	        
	        hc = 37 * hc + name.hashCode();
	        
	        return 37 * hc + dateOfBirth.hashCode();
	    }
	   
}

