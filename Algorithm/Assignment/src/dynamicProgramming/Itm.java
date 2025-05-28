package dynamicProgramming;

import java.util.Arrays;

public class Itm {

  public static int getWays(int n, int[] c) {
    // dp[i]는 i 금액을 만드는 방법의 수를 저장
    int dp[] = new int[n + 1];

    // 0원을 만드는 방법은 아무 동전도 사용하지 않는 1가지
    dp[0] = 1;

    // 동전을 오름차순 정렬 (정렬은 필수는 아니지만 안정적 순회 보장)
    Arrays.sort(c);

    // 각 동전 금액에 대해 dp 배열 업데이트
    for (int coin : c) {
      // coin부터 시작하여 n까지 순회
      for (int i = coin; i <= n; i++) {
        // i원을 만드는 방법은
        // (i - coin)원을 만드는 모든 방법 뒤에 coin을 붙이는 것과 같음
        dp[i] += dp[i - coin];
      }
    }

    // n원을 만드는 전체 방법 수 반환
    return dp[n];
  }
}
