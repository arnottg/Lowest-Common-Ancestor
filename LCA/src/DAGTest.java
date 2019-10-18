import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class DAGTest {
	
	DAG acyclic = new DAG(8);
	
	@Test
	void testConstructor() { //This will test the constructor and the vertices() function
		//Test error case
		assertEquals("Testing case for V < 0: ", -1, new DAG(-1).invalidV);
		
		//Test regular case (V = 0)
		assertEquals("Testing case for V = 0", 0, new DAG(0).vertices());
		
		//Test regular case (V > 0)
		assertEquals("Testing case for V > 0", 4, new DAG(4).vertices());
	}
	
	@Test
	void testCheckVertex() { //Tests the checkVertex() function
		DAG dag1 = new DAG(0);
		DAG dag2 = new DAG(3);
		assertEquals("Testing v = -1: ", -1, dag2.checkVertex(-1));
		assertEquals("Testing v = 0 when V = 0: ", -1, dag1.checkVertex(0));
		assertEquals("Testing v = 0 when V > 0: ", 1, dag2.checkVertex(0));
		assertEquals("Testing v > 0, v < V when V > 0: ", 1, dag2.checkVertex(2));
		assertEquals("Testing v > V when V > 0: ", -1, dag2.checkVertex(4));
	}
	
	//indegree(), outdegree() and adjList() functions are also tested in the following 2 tests:
	
	@Test
	void testAddEdge() {
		
		DAG dag1 = new DAG(4);
		//The following 6 tests are testing a regular case for addEdge
		assertEquals("Testing addEdge for a regular case: ", 1, dag1.addEdge(1, 2));
		assertEquals("Checking outdegree of 1: ", 1, dag1.outdegree(1));
		assertEquals("Checking indegree of 1: ", 0, dag1.indegree(1));
		assertEquals("Checking indegree of 2: ", 1, dag1.indegree(2));
		assertEquals("Checking outdegree of 2: ", 0, dag1.outdegree(2));
		assertEquals("Checking adjacent List: ", 2, dag1.adjList(1)[0]);
		
		DAG dag2 = new DAG(2);
		//Testing for adding an edge to itself
		assertEquals("Testing addEdge from 1 to 1: ", 1, dag2.addEdge(1, 1));
		
		DAG dag3 = new DAG(3);
		//Inputting out of bounds edges
		assertEquals("Testing addEdge from -1 to 1: ", -1, dag3.addEdge(-1, 1));
		assertEquals("Testing addEdge from 0 to 4: ", -1, dag3.addEdge(0, 4));
		
	}
	
	@Test
	void testRemoveEdge() {
		DAG dag1 = new DAG(4);
		//The following 6 tests are testing a regular case for removeEdge
		dag1.addEdge(1,2);
		assertEquals("Testing addEdge for a regular case: ", 1, dag1.removeEdge(1, 2));
		assertEquals("Checking outdegree of 1: ", 0, dag1.outdegree(1));
		assertEquals("Checking indegree of 1: ", 0, dag1.indegree(1));
		assertEquals("Checking indegree of 2: ", 0, dag1.indegree(2));
		assertEquals("Checking outdegree of 2: ", 0, dag1.outdegree(2));
		assertEquals("Checking adjacent List: ", null, dag1.adjList(1));
		
		DAG dag2 = new DAG(4);
		//Testing for removing non-existent edge
		assertEquals("Testing non-existent edge: ", -1, dag2.removeEdge(1, 3));
		
		DAG dag3 = new DAG(4);
		dag3.addEdge(2, 2);
		//Testing for v = w
		assertEquals("Testing edge where v = w; ", 1, dag3.removeEdge(2, 2));
		
		DAG dag4 = new DAG(5);
		//Testing for removing invalid vertices:
		assertEquals("Testing for invalid inputs: ", -1, dag4.removeEdge(-1, 2));
		assertEquals("Testing for invalid inputs: ", -1, dag4.removeEdge(1, 6));
		
	}
	
	@Test
	void testHasCycle() {
		DAG cycle = new DAG(5);
		DAG acyclic = new DAG(4);
		
		cycle.addEdge(0, 1);
		cycle.addEdge(1, 2);
		cycle.addEdge(3, 0);
		cycle.addEdge(2, 3);
		cycle.addEdge(2, 4);
		cycle.addEdge(4, 2);
		
		acyclic.addEdge(0, 1);
		acyclic.addEdge(1, 3);
		acyclic.addEdge(0, 2);
		acyclic.addEdge(2, 3);
		
		//Testing hasCycle on cyclic graph:
		assertEquals("Testing cycle: ", true, cycle.hasCycle());
		
		//Testing hasCycle on acyclic graph:
		assertEquals("Testing cycle: ", false, acyclic.hasCycle());
		
		DAG empty = new DAG(0);
		//Testing hasCycle on empty graph:
		assertEquals("Testing empty: ", false, empty.hasCycle());
	}
	
	@Test
	void testFindingLCA() {
		DAG cycle = new DAG(5);
		DAG acyclic = new DAG(8);
		DAG same = new DAG(4);
		
		cycle.addEdge(0, 1);
		cycle.addEdge(1, 2);
		cycle.addEdge(3, 0);
		cycle.addEdge(2, 3);
		cycle.addEdge(2, 4);
		cycle.addEdge(4, 2);
		
		same.addEdge(1, 2);
		
		acyclic.addEdge(0, 1);
		acyclic.addEdge(0, 2);
		acyclic.addEdge(1, 3);
		acyclic.addEdge(2, 4);
		acyclic.addEdge(3, 5);
		acyclic.addEdge(4, 6);
		acyclic.addEdge(5, 7);
		acyclic.addEdge(6, 7);
		acyclic.addEdge(7, 8);
		
		
		
		//Testing finding LCA for invalid inputs:
		assertEquals("Testing invalid inputs: ", -3, acyclic.findingLCA(-1, 3));
		assertEquals("Testing invalid inputs: ", -3, acyclic.findingLCA(1,  9));
		
		//Testing findingLCA for graph with a cycle: 
		assertEquals("Testing graph with cycle: ", -2, cycle.findingLCA(1, 2));
		
		//Testing findingLCA for same input:
		assertEquals("Testing for v = w: ", 2, same.findingLCA(2, 2));
		
		//Testing LCA for acyclic graph
		assertEquals("", 0, acyclic.findingLCA(3, 4));
		assertEquals("", 0, acyclic.findingLCA(1, 4));
		assertEquals("", 0, acyclic.findingLCA(5, 2));
		
	}
}
