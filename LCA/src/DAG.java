
public class DAG {
	
	private int V;//Number of vertices in graph
	private int E;//Number of edges in graph
	private int[][] adjList; //2D array adjacency list for vertex v
	private int[] outdegree;//outdegree of vertex v
	private int[] indegree; // indegree of vertex v
	private int[] visited;  //2D array of visited vertices
	public int invalidV;
	private boolean hasCycle;
	
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
	    	for(int i = 0; i<V; i++){ //sets up an empty graph in 2D array
	    		for(int j=0;j<V;j++){
	    			adjList[i][j] = 0; //Creating a DAG with no edges
	    		}
	    	}
	    }
	}
	
	//returns number of vertices in DAG - Used for tests
	public int vertices(){
		return V;
	}
	
	//returns number of edges in DAG - Used for tests
	public int edges(){
		return E;
	}
	
	//throws illegal exception if the vertex put in is invalid
	public int checkVertex(int v){
		if((v < 0)||(v >= V)){ //Vertex must be between 0 and V-1 or is out of bounds
    		return -1; //Returns -1 if v is out of bounds
		}
		return 1; //returns 1 if v is in bounds
	}
    
  //adds directed edge from v to w
	public int addEdge(int v, int w){
		//Checking if vertices are in bounds
		if(checkVertex(v) == 1 && checkVertex(w) == 1) {
			adjList[v][w] = 1; //Adds the edge to the adjacent list
			indegree[w]++; //The number of edges entering vertex w is increased by 1
			outdegree[v]++; //The number of edges leaving vertex v is decreased by 1
			E++;
			return 1; //Returns 1 if addEdge is successful
		}
		return -1; //Returns -1 if addEdge is unsuccessful
    }
    
    //Removes an edge from v to w
    public int removeEdge(int v, int w){
    	//Checking if vertices are in bounds
    	if(checkVertex(v) == 1 && checkVertex(w) == 1 && adjList[v][w] == 1) {;
	    	adjList[v][w] = 0; //Removing the edge from the adjacent list
	    	indegree[w]--; //The number of edges entering vertex w is decreased by 1
	    	outdegree[v]--; //The number of edges leaving vertex v is decreased by 1
	    	E--;
	    	return 1; //Returns 1 if removeEdge is successful
    	}
    	else return -1; //Returns -1 if removeEdge is unsuccessful
    }
    
    //returns outdegree (number of directed edges leaving) of vertex v
    public int outdegree(int v){
    	if(checkVertex(v) == 1) return outdegree[v]; // successful
    	else return -1; //unsuccessful
    }
    
    //returns indegree (number of directed edges leaving) of vertex v
    public int indegree(int v){
    	if(checkVertex(v) == 1) return indegree[v];//Checks if the vertex is in bounds
    	else return -1; //unsuccessful
    }
    
    //returns the vertices adjacent from vertex v
    public int[] adjList(int v){
    	if(checkVertex(v) == 1 && outdegree[v] != 0) { //Checking if v is in bounds
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
    	else return null;
    	
    }
    
    //returns true if the graph contains a cycle, else false
    public boolean hasCycle(){
    	boolean hasCycle = false; //A boolean tracking whether or not the DAG has a cycle
    	int count = 0; //Counter token to be incremented & used to find cycle
    	for(int visit = 0; visit < V; visit++) {
    		visited[visit] = 0; // resetting visited array
    	}
    	for(int i = 0; i < V; i++){
    		visited[count] = i;
    		for(int j = 0; j < V; j++){
    			for(int k = 0; k < V; k++){
    				if(visited[k] == j && adjList[i][j] == 1){
    					hasCycle = true; //If a cycle is contained, true is returned
    					this.hasCycle = hasCycle;
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
    public int findingLCA(int v, int w) {
    	
    	if(checkVertex(v) == 1 && checkVertex(w) == 1) {
    		hasCycle();
	    	if(E > 0 && !hasCycle){ //If the DAG does not contain a cycle and has edges > 0, we can attempt to find the LCA
	    		return LCAHelper(v, w);	
	    	}
	    	return -2;
    	}
    	return -3; //returns -3 if v or w are out of bounds
    }
    
    
    
    public int LCAHelper(int v, int w) { //Code to help findingLCA
    	if(v == w) return v;
    	int output = -1;
		int[] vArray = new int[E];
		int[] wArray = new int[E];
		boolean[] vMarked = new boolean[V];
		boolean[] wMarked = new boolean[V];
		int vCount =0;
		int wCount = 0;
		vArray[vCount] = v;
		wArray[wCount] = w;
		int count1;
		int count2 = 0;
		for(int j = 0; j < V; j++){ //mark all vertices as not been visited yet
			vMarked[j] = false;
			wMarked[j] = false;
		}
		for(int k = 0; k < V; k++) {
			for(int i =0; i < V; i++){
				vMarked[v] = true;
				wMarked[w] = true;
				for(int j = 0; j < V; j++){
					if(adjList[j][i] == 1 && vMarked[i]){
						vCount++;
						vArray[vCount] = j;
						vMarked[j]=true;
					}
					if(adjList[j][i] == 1 && wMarked[i]){
						wCount++;
						wArray[wCount] = j;
						wMarked[j] = true;
					}
					if(wArray[wCount] == vArray[vCount]){
						j = V;
						k = V;
						i = V;
						output = wArray[wCount];
					}
				}
			}
		}
		return output;
	}
}
