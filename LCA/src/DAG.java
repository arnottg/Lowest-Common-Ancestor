
public class DAG {
	private int V;//Number of vertices in graph
    private int E;//Number of edges in graph
    private int[][] adjList; //2D array adjacency list for vertex v
    private int[] outdegree;//outdegree of vertex v
    private int[] indegree; // indegree of vertex v
    private int[] visited;  //2D array of visited vertices
   
	//constructor to initialize and empty graph with size V
	public DAG(int V){
	    if(V < 0){
	        throw new IllegalArgumentException("DAG cannot have less than 0 vertices");
	    }
	    else{
	        this.V = V;
	        this.E = 0;
	        indegree = new int[V];
		    outdegree = new int[V];
		    visited = new int[V];
		    adjList = new int[V][V];
		    for(int i = 0; i<V; i++){//sets up an empty graph in 2D array
				for(int j=0;j<V;j++){
				    adjList[i][j] = 0;
				}
		    }
	    }
	}
	
	//returns number of vertices in DAG
	public int Vertices(){
	    return V;
	}
	
	//returns number of edges in DAG
	public int Edges(){
	    return E;
	}
}
