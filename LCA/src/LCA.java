
public class LCA <Value extends Comparable<Value>>{
	
	public class Node {
		
		private Value value; // The data in the node
		private Node left; // Left subtree node
		private Node right; // Right subtree node
		private int N; // Number of nodes in subtree
		
		public Node(Value val, int N) {
			this.value = val;
			this.N= N;
			this.left = null;
			this.right = null;
		}
		
	}
}
