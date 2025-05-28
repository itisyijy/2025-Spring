package greedy;

import java.util.Arrays;

public class Itm {

  /**
   * maxWeight 함수는 주어진 로프들 중 일부를 선택하여 들어올릴 수 있는 최대 무게를 계산한다. 각 선택된 로프는 전체 하중을 균등하게 분담하므로, 모든 로프가 w/k
   * 이하를 버틸 수 있어야 한다.
   * <p>
   * 즉, 정렬된 상태에서 i번째 로프부터 끝까지 선택했을 때, 전체 하중은 ropes[i] * (선택한 로프 수)로 계산된다. 이를 모든 i에 대해 계산하고 그 중 최댓값을
   * 반환한다.
   *
   * @param ropes 각 로프가 버틸 수 있는 최대 하중을 담은 배열
   * @return 선택 가능한 최대 하중
   */
  public static int maxWeight(int[] ropes) {
    // 로프들을 오름차순 정렬 → 하중이 낮은 로프부터 탐색
    Arrays.sort(ropes);

    int max = 0;

    /*
     * ❌ 아래의 이중 반복문은 비효율적임.
     * ----------------------------------------------------
     * for (int i = 0; i < ropes.length; i++) {
     *   for (int k = 1; k <= ropes.length - i; k++) {
     *     max = Math.max(max, ropes[i] * k);
     *   }
     * }
     *
     * 이중 루프는 i번째 로프부터 시작해서 가능한 모든 k (선택할 로프 수)를 고려하지만,
     * 실제로는 k = (ropes.length - i)일 때만 최대 하중이 나올 수 있음.
     * 왜냐하면:
     *  - ropes[i]는 해당 그룹 내 최소값
     *  - 그 이상 하중은 어떤 로프도 못 견딤
     *  - 고른 개수가 많을수록 total weight는 커지므로
     *  → 하나의 k만 고려해도 충분함
     */

    // ✅ 단일 반복문으로 최적화된 코드
    for (int i = 0; i < ropes.length; i++) {
      int k = ropes.length - i;       // i번째 로프부터 끝까지 선택
      int weight = ropes[i] * k;      // 각 로프가 버틸 수 있는 하중 * 개수
      max = Math.max(max, weight);   // 최대값 갱신
    }

    return max; // 최종적으로 들어올릴 수 있는 최대 하중
  }

}
