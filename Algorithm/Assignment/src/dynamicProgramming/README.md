# Dynamic Programming (DP) 개념 및 동전 문제 정리

## 📌 DP란 무엇인가?

동적 계획법(Dynamic Programming, DP)은 **복잡한 문제를 작은 하위 문제로 나누어 푸는 최적화 기법**이다.

- **중복되는 하위 문제(subproblems)**를 풀고
- 그 결과를 **저장(memoization)**하거나 **재사용(bottom-up table)**하여 전체 문제를 해결한다.

### DP의 핵심 조건
1. **Overlapping Subproblems (중복 부분 문제)**
2. **Optimal Substructure (최적 부분 구조)**

---

## 🔄 Top-down vs Bottom-up

| 방식         | 설명 |
|--------------|------|
| Top-down     | 재귀 + 메모이제이션으로 큰 문제부터 작게 나눔 |
| Bottom-up    | 작은 문제부터 테이블을 채워가며 큰 문제 해결 |

---

## 💰 동전 조합 문제 개요

### 문제 설명
- 정해진 동전으로 **총 금액 `n`을 만드는 서로 다른 조합의 수**를 구함
- 동전은 **중복 사용 가능**

#### 예시
```text
Coins: [1, 2, 3], Amount: 4
결과: 4 (조합: {1,1,1,1}, {1,1,2}, {1,3}, {2,2})
```

---

## ✅ Bottom-up 풀이 (Iterative DP)

```java
public int getWays(int n, int[] c) {
    int[] dp = new int[n + 1];
    dp[0] = 1;

    Arrays.sort(c);
    for (int coin : c) {
        for (int i = coin; i <= n; i++) {
            dp[i] += dp[i - coin];
        }
    }
    return dp[n];
}
```

### 동작 방식
- `dp[i]`: 금액 i를 만들 수 있는 경우의 수
- `dp[i] += dp[i - coin]` (coin 사용 추가 조합)

---

## 🔁 Top-down 풀이 (Memoization + Recursion)

```java
private int countWays(int amount, int[] coins, int index) {
    if (amount == 0) return 1;
    if (amount < 0 || index == coins.length) return 0;
    if (memo[amount][index] != -1) return memo[amount][index];

    int include = countWays(amount - coins[index], coins, index);
    int exclude = countWays(amount, coins, index + 1);

    memo[amount][index] = include + exclude;
    return memo[amount][index];
}
```

### 주요 개념
- 현재 동전을 **포함하거나 포함하지 않는 분기**
- 메모 배열 `memo[amount][index]`에 중간 결과 저장

---

## 🧠 요약

| 요소 | 설명 |
|------|------|
| 문제 유형 | Unbounded Knapsack (중복 사용 가능) |
| 시간 복잡도 | O(n × k) (`n`: 목표 금액, `k`: 동전 종류) |
| 공간 복잡도 | O(n) 또는 O(n × k) (Top-down) |

---

## ✅ 활용 팁
- 재귀 구조는 직관적이지만 성능은 bottom-up이 더 좋음
- `dp[0] = 1`은 항상 **초기화 필수** (0원을 만드는 방법은 아무 것도 선택하지 않는 1가지)