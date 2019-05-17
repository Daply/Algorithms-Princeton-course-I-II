

public class WeghtedFind {
     private int [] id;
     
     public WeghtedFind (int N) {
    	 id = new int[N];
    	 for(int i = 0; i < N; i++) {
    		 id[i] = i;
    	 }
     }
     
     public boolean connected(int p, int q) {
    	 return root(p) == root(q);
     }
     
     public int root(int p) {
    	 int i = p;
    	 while(id[i] != i) {
    		 id[i] = id[id[i]];
    		 i = id[i];
    	 }
    	 return i;
     }
     
     public void union(int p, int q) {
    	 int pid = root(p);
    	 int qid = root(q); 
    	 id[pid] = qid;
     }
}
