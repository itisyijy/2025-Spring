public class HW23LowestCommonAncestor {
  class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  public static Node lca(Node root, int v1, int v2) {
    // Write your code here.
    if (root == null)
      return null;
    if (root.data > v1 && root.data > v2) {
      return lca(root.left, v1, v2);
    } else if (root.data < v1 && root.data < v2) {
      return lca(root.right, v1, v2);
    } else {
      return root;
    }
  }
}
