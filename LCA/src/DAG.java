
public class DAG {
	
	private int V; //number of vertices in graph
	private int E; //number of edges in graph
	private int[][] adjList; //Adjacency list for a vertex v
	private int[] outdegree;//Outdegree of vertex v
    private int[] indegree; //Indegree of vertex v
    private int[] visited;  //List of visited vertices
    
    //Constructor for empty DAG of size size
    public DAG(int size) {
    	if(size < 0) {
    		throw new IllegalArgumentException("The DAG cannot have a negative number of vertices.");
    	}
    	else {
    		this.V = size;
    		this.E = 0; //Empty graph so there are no edges
            indegree = new int[V];
		    outdegree = new int[V];
		    visited = new int[V];
		    adjList = new int[V][V];
		    for(int i = 0; i<V; i++){ //sets up an empty graph in 2D array
				for(int j=0;j<V;j++){
				    adjList[i][j] = 0;
				}
	    }
    		
    	}
    }
	
}