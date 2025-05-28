package Ternary;

public class Itm {

  public boolean searchTST(TSTree root, String word) {
    // 탐색 시작 노드 (루트의 데이터는 ' ')
    Node current = root.root;
    int index = 0;

    // null 노드 도달 전까지 반복
    while (current != null) {
      char c = word.charAt(index);

      if (c < current.data) {
        // c가 현재 노드보다 작으면 왼쪽 서브트리로 이동
        current = current.left;
      } else if (c > current.data) {
        // c가 현재 노드보다 크면 오른쪽 서브트리로 이동
        current = current.right;
      } else {
        // 문자가 일치하는 경우
        if (index == word.length() - 1) {
          // 마지막 문자일 경우 단어의 끝인지 확인
          return current.isEndOfString;
        }
        // 다음 문자 탐색을 위해 eq 방향으로 이동
        index++;
        current = current.eq;
      }
    }

    // 탐색 실패
    return false;
  }
}
