import java.util.LinkedList;
import java.util.Queue;

public class HW21HeightOfBinaryTree {
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

    public static int height(Node root) {
        if (root == null)
            return -1;

        int l = height(root.left);
        int r = height(root.right);

        return Math.max(l, r) + 1;
    }
}
