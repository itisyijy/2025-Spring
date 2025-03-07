public class HW22BSTInsertion {
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

    public Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);

        if (root.data == data) {
            return root;
        } else if (root.data < data) {
            if (root.right != null) {
                insert(root.right, data);
            } else {
                root.right = new Node(data);
            }
        } else {
            if (root.left != null) {
                insert(root.left, data);
            } else {
                root.left = new Node(data);
            }
        }
        return root;
    }
}
