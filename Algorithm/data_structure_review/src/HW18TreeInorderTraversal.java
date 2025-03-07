public class HW18TreeInorderTraversal {
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

    public static void inOrder(Node root) {
        if (root.left != null)
            inOrder(root.left);
        System.out.print(root.data + " ");
        if (root.right != null)
            inOrder(root.right);
    }
}
