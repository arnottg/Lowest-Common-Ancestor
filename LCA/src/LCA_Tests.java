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
		//Checking with one value equal to 1
		assertEquals("Attempting to find LCA for tree with single node", null, tree.findingLCA(1,4));
		//Checking with neither value equal to 1
		assertEquals("Attempting to find LCA for tree with single node", null, tree.findingLCA(17,4));
	}
	
	@Test 
	void testIsContained(){
		LCA tree = new LCA();
		Node node1 = new Node(13);//              13
		tree.root = node1;        //             / 
		Node node2 = new Node(3); //            3
		Node node3 = new Node(6); //             \
		tree.root.left = node2;   //              6
		tree.root.left.right = node3;
		
		//Checking if root is contained
		assertEquals("Checking if 13 is contained in the tree",  true, tree.isContained(node1.value));
		//Checking root.left
		assertEquals("Checking if 3 is contained in the tree",  true, tree.isContained(node2.value));
		//Checking node 3 deep
		assertEquals("Checking if 6 is contained in the tree",  true, tree.isContained(node3.value));
		//Checking node not contained
		assertEquals("Checking if 8 is contained in the tree",  false, tree.isContained(8));
	}
	
	@Test
	void testTwoNodes() {
		//Testing a tree that only contains 2 nodes
		LCA tree = new LCA();    //               1
		Node node1 = new Node(1);//              /
		Node node2 = new Node(2);//             2
		tree.root = node1;
		tree.root.left = node2;
		//Checking LCA of 1 & 2 (both contained)
		assertEquals("Attempting to find LCA for tree with two nodes", node1, tree.findingLCA(1,2));
		//Checking LCA of 1 & 3 (one contained)
		assertEquals("Attempting to find LCA for tree with two nodes", null, tree.findingLCA(1,3));
		//Checking LCA of 2 & 5 (neither contained)
		assertEquals("Attempting to find LCA for tree with two nodes", null, tree.findingLCA(2,5));
	}
	
	@Test
	void testStandardTree() {
		//Building a standard tree
		LCA tree = new LCA();//             Tree Diagram:
		Node node1 = new Node(1);//              1
 		Node node2 = new Node(2);//            /   \
		Node node3 = new Node(3);//          2       3
		Node node4 = new Node(4);//         / \     / \  
		Node node5 = new Node(5);//        4   5   6   7
		Node node6 = new Node(6);//
		Node node7 = new Node(7);//
		tree.root = node1;
		tree.root.left = node2;
		tree.root.right = node3;
		tree.root.left.left = node4;
		tree.root.left.right = node5;
		tree.root.right.left = node6;
		tree.root.right.right = node7;
		
		//Testing different levels
		assertEquals("LCA of 2 & 4", node2, tree.findingLCA(2,4));
		//Testing LCA of left side
		assertEquals("LCA of 4 & 5", node2, tree.findingLCA(4,5));
		//Testing LCA of right side
		assertEquals("LCA of 6 & 7", node3, tree.findingLCA(6,7));
		//Testing LCA of different sides
		assertEquals("LCA of 4 & 7", node1, tree.findingLCA(4,7));
		//Testing different levels, different sides
		assertEquals("LCA of 2 & 7", node1, tree.findingLCA(2,7));
		
	}
	
}
