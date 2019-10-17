
public class DAG {
	
	public int V;//Number of vertices in graph
	private int E;//Number of edges in graph
	private int[][] adjList; //2D array adjacency list for vertex v
	private int[] outdegree;//outdegree of vertex v
	private int[] indegree; // indegree of vertex v
	private int[] visited;  //2D array of visited vertices
	public int invalidV;
	
    //constructor to initialize and empty graph with size V
    public DAG(int V){
		if(V < 0){
			invalidV = -1;
	    	System.out.println("DAG cannot have less than 0 vertices");
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
	    			adjList[i][j] = 0; //Creating a DAG with no edges yet connecting vertices
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
		if((v < 0)||(v >= V)){ //Vertex must be between 0 and V or is out of bounds
    		throw new IllegalArgumentException("Input vertex is out of bounds, v must be between 0 and " + V);
		}
	}
    
  //adds directed edge from v to w
	public void addEdge(int v, int w){
		//Checking if vertices are in bounds
		checkVertex(v);
		checkVertex(w);
		adjList[v][w] = 1; //Adds the edge to the adjacent list
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
    
    //returns the vertices adjacent from vertex v
    public int[] adjList(int v){
    	checkVertex(v); //Checking if v is in bounds
    	int[] adj = new int[outdegree[v]]; //Creates an Array of size outdegree of vertex v
    	int count = 0;
    	for(int i = 0; i < V; i++){
    		if(adjList[v][i] == 1){ //If there is a directed edge between v and i, it is added to the adj list
    			adj[count] = i;
    			count++;
    		}
    	}
    	return adj;
    }
    
    //returns true if the graph contains a cycle, else false
    public boolean hasCycle(){
    	boolean hasCycle = false; //A boolean tracking whether or not the DAG has a cycle
    	int count = 0; //Counter token to be incremented & used to find cycle
    	for(int i = 0; i < V; i++){
    		visited[count] = i;
    		for(int j = 0; j < V; j++){
    			for(int k = 0; k < V; k++){
    				if(visited[k] == j && adjList[i][j] == 1){
    					hasCycle=true; //If a cycle is contained, true is returned
    					return hasCycle;
    				}
    			}	
    		}
    		count++;
    	}
    	return hasCycle;
    }
    
    //Found some nice code for LCA of DAG online, adjustments will be necessary to get my tests to work
    //This public function is used to find the LCA in a DAG
    public int findingLCA(int v, int w){
    	checkVertex(v);
    	checkVertex(w);
    	if(E > 0 && !hasCycle()){ //If the DAG does not contain a cycle and has edges > 0, we can attempt to find the LCA
    		int[] vArray = new int[E];
    		int[] wArray = new int[E];
    		boolean[] vMarked = new boolean[V];
    		boolean[] wMarked = new boolean[V];
    		int vCount =0;
    		int wCount = 0;
    		vArray[vCount] = v;
    		wArray[wCount] = w;
    		for(int j=0; j<V;j++){ //mark all vertices as not been visited yet
    			vMarked[j] = false;
    			wMarked[j] = false;
    		}
    		for(int i =0; i < V; i++){
    			vMarked[v] = true;
    			wMarked[w] = true;
    			for(int j = 0; j < V; j++){
    				if(adjList[i][j] == 1 && vMarked[i]){
    					vCount++;
    					vArray[vCount]=j;
    					vMarked[j]=true;
    				}
    				if(adjList[i][j] == 1 && wMarked[i]){
    					wCount++;
    					wArray[wCount]=j;
    					wMarked[j]=true;
    				}
    				if(wArray[wCount] == vArray[vCount]){
    					return wArray[wCount];
    				}
    			}
    		}
    		return -1;//returns -1 if no ancestor found
    	}
    	else{
    		throw new IllegalArgumentException("This graph is not an acyclic graph - it may not contain any edges or have a cycle");
    	}
    }
    
}
