public class HW17TreePreorderTraversal {
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

    public static void preOrder(Node root) {
        System.out.print(root.data + " ");
        if (root.left != null)
            preOrder(root.left);
        if (root.right != null)
            preOrder(root.right);

    }
}
