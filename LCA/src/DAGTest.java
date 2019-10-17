import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class DAGTest {
	
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
	
	

}
