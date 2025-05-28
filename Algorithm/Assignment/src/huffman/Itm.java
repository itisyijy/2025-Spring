package huffman;

public class Itm {

  public String decoder(HuffmanCodeTree huffmanTree, String encodedString) {
    HNode current = huffmanTree.root;
    // String 대신 사용. append() 사용 가능.
    StringBuilder result = new StringBuilder();

    // encodedString의 비트 하나씩 검토
    for (int i = 0; i < encodedString.length(); i++) {
      char bit = encodedString.charAt(i);

      if (bit == '1') {
        current = current.right;
      } else {
        current = current.left;
      }

      // 현재 노드가 null이라면 encodedString이 잘못 구성됨.
      if (current == null) {
        return "Invalid";  // 잘못된 비트 경로
      }

      if (current.character != '\0') {  // 리프 노드 도달
        result.append(current.character);
        current = huffmanTree.root;  // 다시 루트로 이동
      }
    }

    if (current != huffmanTree.root) {
      return "Invalid";  // 모든 비트 처리 후 반드시 루트여야 함
    }

    return result.toString();
  }
}
