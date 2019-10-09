
public class LCA {
	
	class Node {
		
		private int value; // The data in the node - changed to int for initial building of functions
		private Node left, right; // Left & Right subtree node
		
		public Node(int val) {
			this.value = val;
			this.left = null;
			this.right = null;
		}
	}
		
	Node root;
	Node findingLCA(int input1, int input2) {
		return findingLCA(root, input1, input2); //The parameters are the 2 input numbers & the top of the tree (root)
	}
	
	Node findingLCA(Node node, int input1, int input2) { //This function will use recursion to find the LCA of 2 inputs.
		
		if(node == null) return node;
		
		if(node.value == input1 || node.value == input2) return node; 
		//If either of the input values are reached in the tree then the
		//node is returned as the LCA cannot be in a subtree of one of the inputs.
		
		Node leftNode = findingLCA(node.left, input1, input2); //Checks all subtrees to left of a node
		Node rightNode = findingLCA(node.right, input1, input2); //Checks all subtrees to right of a node
		
		return null;
	}
}