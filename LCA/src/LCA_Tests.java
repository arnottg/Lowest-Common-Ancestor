import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class LCA_Tests {
	
	@Test
	public void testEmptyTree() {
		LCA tree = new LCA(); // Creates a new tree with root = null
		assertEquals("Attepmting to find LCA for null tree: ", null, tree.findingLCA(3, 5));
	}
	
	@Test
	void testSingleNode() {
		LCA tree = new LCA();
		Node node = new Node(1);
		tree.root = node;
		//Checking with both values equal to 1
		assertEquals("Attempting to find LCA for tree with single node",  node, tree.findingLCA(1, 1));
		//Checking with one value equal to 1 - doesn't currently work - need to debug
		assertEquals("Attempting to find LCA for tree with single node", node, tree.findingLCA(1,4));
		//Checking with neither value equal to 1
		assertEquals("Attempting to find LCA for tree with single node", null, tree.findingLCA(17,4));
	}
	
	@Test
	void testTwoNodes() {
		
	}
	
	@Test
	void testStandardTree() {
		
	}
	
}
