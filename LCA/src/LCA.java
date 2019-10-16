
public class LCA {
	
	Node root;
	Node findingLCA(int input1, int input2) {
		if(!isContained(input1) || !isContained(input2)) return null;
		return findingLCA(root, input1, input2); //The parameters are the 2 input numbers & the top of the tree (root)
	}
	
	Node findingLCA(Node node, int input1, int input2) { //This function will use recursion to find the LCA of 2 inputs.
		
		if(node == null) return node; //If the input node is null then null is returned
		
		if(node.value == input1 || node.value == input2) return node; 
		//If either of the input values are reached in the tree then the
		//node is returned as the LCA cannot be in a subtree of one of the inputs.
		
		Node leftNode = findingLCA(node.left, input1, input2); //Checks all subtrees to left of a node
		Node rightNode = findingLCA(node.right, input1, input2); //Checks all subtrees to right of a node
		
		if(leftNode != null && rightNode != null) return node; 
		//If neither of the subtrees are null then the node is returned
		
		if(leftNode != null) return leftNode; //If only leftNode is not null then that node is returned
		else if(rightNode != null) return rightNode; //Returns rightNode if it is not null	
		else return null; //Returns null if both nodes are null
	}
	
	boolean isContained(int value) { //returns true if a value is contained in the tree, false if not

		return isContained(value, root);
	}
	
	boolean isContained(int value, Node node) {
		
		if(node == null) return false;
		if(node.value == value) return true;
		
		boolean containedLeft = isContained(value, node.left);
		boolean containedRight = isContained(value, node.right);
		
		return containedLeft || containedRight;
	}
	
}

class Node {
	
	public int value; // The data in the node - changed to int for initial building of functions - could be using
	//values & keys in long run
	public Node left, right; // Left & Right subtree node
	
	public Node(int val) {
		this.value = val;
		this.left = null;
		this.right = null;
	}
}