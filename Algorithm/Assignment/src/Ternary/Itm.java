package Ternary;

public class Itm {
  public boolean searchTST(TSTree root, String word) {
    if (root == null || word == null || word.isEmpty())
      return false;

    Node head = root.root;

    int index = 0;
    while (head != null) {
      char c = word.charAt(index);

      if (c < head.data) {
        head = head.left;
      } else if (c > head.data) {
        head = head.right;
      }
      else {
        if (index == word.length() - 1) {
          return head.isEndOfString;
        }
        index++;
        head = head.eq;
      }
    }
    return false;
  }
}
