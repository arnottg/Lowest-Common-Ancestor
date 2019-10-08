
public class LCA {
	
	class Node {
		
		private int value; // The data in the node - changed to int for initial building of functions
		private Node left, right; // Left & Right subtree node
		private int N; // Number of nodes in subtree, not sure if this will be needed
		
		public Node(int val, int N) {
			this.value = val;
			this.N= N;
			this.left = null;
			this.right = null;
		}
		
	Node root;
	Node findingLCA(int input1, int input2) {
		return findingLCA(root, input1, input2);
	}
	
	Node findingLCA(Node node, int input1, int input2) {
		return null;
	}
	
}


	
}
