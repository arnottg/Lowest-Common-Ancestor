
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
	
	//throws illegal exception if the vertex put in is invalid
	private void checkVertex(int v){
		if((v<0)||(v>=V)){
    		throw new IllegalArgumentException("Input vertex is out of bounds, v must be between 0 and " + V);
		}
	}
    
  //adds directed edge from v to w
	public void addEdge(int v, int w){
		//Checking if vertices are in bounds
		checkVertex(v);
		checkVertex(w);
		adjList[v][w]=1; //Adds the edge to the adjacent list
		indegree[w]++; //The number of edges entering vertex w is increased by 1
		outdegree[v]++; //The number of edges leaving vertex v is decreased by 1
		E++;
    }
    
    //Removes an edge from v to w
    public void removeEdge(int v, int w){
    	//Checking if vertices are in bounds
    	checkVertex(v);
    	checkVertex(w);
    	adjList[v][w]=0; //Removing the edge from the adjacent list
    	indegree[w]--; //The number of edges entering vertex w is decreased by 1
    	outdegree[v]--; //The number of edges leaving vertex v is decreased by 1
    	E--;
    }
    
    //returns outdegree (number of directed edges leaving) of vertex v
    public int outdegree(int v){
    	checkVertex(v); //Checks if the vertex is in bounds
    	return outdegree[v];
    }
    
    //returns indegree (number of directed edges leaving) of vertex v
    public int indegree(int v){
    	checkVertex(v); //Checks if the vertex is in bounds
    	return indegree[v];
    }
    
}
