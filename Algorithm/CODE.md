# Ternary

```java
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
```

# Huffman

```java
public String decoder(HuffmanCodeTree huffmanTree, String encodedString) {
  if (huffmanTree == null || encodedString == null) {
    return "invalid";
  }

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
```

# Graph

```java
package basicGraph;

public class GraphTest {

  public static void main(String[] args) {
    // 그래프의 정점 수: 0, 1, 2 총 3개의 정점을 갖는 완전 그래프를 구성함
    int numVertices = 3;

    // 세 가지 그래프 표현 방식 인스턴스를 생성
    // 1. 인접 리스트 (Adjacency List)
    Graph<Edge> adjList = new AdjList<>(numVertices);
    // 2. 인접 행렬 (Adjacency Matrix)
    Graph<Edge> adjMatrix = new AdjMatrix(numVertices);
    // 3. 발생 행렬 (Incidence Matrix)
    // 간선 수는 방향성을 고려하여 총 6개 (예: 0→1, 1→0은 서로 다른 간선으로 취급)
    Graph<Edge> incMatrix = new IncMatrix(numVertices, 6);

    // 간선 정의 (총 6개)
    // 완전 그래프이므로 정점 0, 1, 2 사이의 모든 쌍에 대해 양방향 간선을 추가
    // 즉, (0→1), (1→0), (0→2), (2→0), (1→2), (2→1)
    int[][] edges = {
        {0, 1, 1},
        {0, 2, 1},
        {1, 0, 1},
        {1, 2, 1},
        {2, 0, 1},
        {2, 1, 1}
    };

    // 각 간선을 인접 리스트에 추가
    // 방향성 간선을 따로 저장하므로 (0→1)과 (1→0)은 각각 하나의 간선
    for (int[] edge : edges) {
      adjList.addEdge(edge[0], edge[1], edge[2]);
    }

    // 각 간선을 인접 행렬에 추가
    // 인접 행렬에서도 방향성을 표현할 수 있도록 각 간선을 개별적으로 추가
    for (int[] edge : edges) {
      adjMatrix.addEdge(edge[0], edge[1], edge[2]);
    }

    // 각 간선을 발생 행렬에 추가
    // 방향성 간선 6개를 각각 하나의 간선 열로 표현함
    for (int[] edge : edges) {
      incMatrix.addEdge(edge[0], edge[1], edge[2]);
    }

    // 세 가지 그래프 표현 방식 출력
    // toString() 메서드를 활용하여 각 내부 구조를 확인
    System.out.println("Adjacency List Representation:\n" + adjList.toString());
    System.out.println("\nAdjacency Matrix Representation:\n" + adjMatrix.toString());
    System.out.println("\nIncidence Matrix Representation:\n" + incMatrix.toString());
  }
}
```

# DFS

```java
public int[] dfs_stack(int start) {
  int numVertice = adjList.size();             // 그래프의 정점 개수
  boolean[] visited = new boolean[numVertice]; // 정점 방문 여부를 저장하는 배열
  Stack<Integer> stack = new Stack<>();        // DFS 탐색을 위한 스택
  List<Integer> result = new ArrayList<>();    // 방문 순서를 저장할 리스트

  stack.push(start); // 시작 정점을 스택에 삽입

  while (!stack.isEmpty()) {
    int current = stack.pop(); // 스택에서 현재 정점을 꺼냄

    if (!visited[current]) {
      visited[current] = true;      // 방문 처리
      result.add(current);          // 방문 순서 리스트에 추가

      // 현재 정점의 인접 정점 리스트 가져오기
      List<T> neighbors = adjList.get(current);

      // 인접 정점을 역순으로 순회하며 스택에 추가
      // (역순 push → 스택은 LIFO이므로 DFS 순서를 보장)
      for (int i = neighbors.size() - 1; i >= 0; i--) {
        int neighbor = neighbors.get(i).getHead(); // 인접 정점 번호

        if (!visited[neighbor]) {
          stack.push(neighbor); // 아직 방문하지 않은 정점이면 스택에 push
        }
      }
    }
  }

  // List<Integer>를 int[] 배열로 변환하여 반환
  int[] resArray = new int[result.size()];
  for (int i = 0; i < result.size(); i++) {
    resArray[i] = result.get(i);
  }

  return resArray; // DFS 방문 순서 결과 반환
}

public int[] dfs_recursive(int start) {
  int numVertices = adjList.size();                  // 정점의 총 개수
  boolean[] visited = new boolean[numVertices];      // 정점 방문 여부 기록용 배열
  List<Integer> result = new ArrayList<>();          // 방문한 정점들의 순서를 저장할 리스트

  recursive(start, visited, result);              // 재귀 DFS 시작

  // 방문 결과를 int 배열로 변환해서 반환
  int[] resArray = new int[result.size()];
  for (int i = 0; i < result.size(); i++) {
    resArray[i] = result.get(i);
  }

  return resArray;
}

// 재귀 DFS 함수
private void recursive(int current, boolean[] visited, List<Integer> result) {
  visited[current] = true;                          // 현재 정점 방문 처리
  result.add(current);                              // 방문 순서에 현재 정점 추가

  // 현재 정점의 모든 인접 정점에 대해
  for (T edge : adjList.get(current)) {
    int neighbor = edge.getHead();                // 간선의 도착 정점 (이웃 정점)

    if (!visited[neighbor]) {                     // 아직 방문하지 않은 정점이면
      recursive(neighbor, visited, result);  // 해당 정점으로 재귀 호출
    }
  }
}
```

# Kruskal

```java
public List<Edge> kruskalMST() {
  // 최종적으로 MST를 구성할 간선들을 저장할 리스트
  List<Edge> mst = new ArrayList<>();

  // 그래프에서 모든 간선 정보를 가져옴
  List<Edge> edges = getEdges();

  // 간선들을 가중치 기준으로 오름차순 정렬
  Collections.sort(edges);

  // Disjoint Set(서로소 집합) 자료구조 초기화
  // m.length는 정점 수로 가정 (Incidence Matrix 행 수)
  DisjointSet ds = new DisjointSet(m.length);

  // 가중치가 낮은 간선부터 하나씩 확인
  for (Edge e : edges) {
    // 간선의 두 끝 정점(u, v)에 대해 각 집합의 대표(루트)를 찾음
    int uRoot = ds.find(e.getTail()); // e.getTail() = u
    int vRoot = ds.find(e.getHead()); // e.getHead() = v

    // 두 정점이 서로 다른 집합에 속해 있다면 → 사이클이 없음
    if (uRoot != vRoot) {
      // MST에 이 간선을 추가
      mst.add(e);

      // 두 정점을 같은 집합으로 합침 → 다음부터는 같은 컴포넌트로 취급
      ds.union(uRoot, vRoot);
    }
    // 같은 집합이면 사이클 생기므로 추가하지 않음 (자동으로 건너뜀)
  }

  // MST를 구성하는 간선들의 리스트 반환
  return mst;
}
```

# Knight BFS

```java
public int minKnightMoves(int N, int M, int R, int C, int S, int K) {
  // 1-based index → 0-based index
  R--;
  C--;
  S--;
  K--;

  // Possible Direction of Knight
  int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
  int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

  // Check visited before
  boolean[][] visited = new boolean[N][M]; // visited coordinates
  visited[R][C] = true; // initial position of knight

  // Ready for BFS
  Queue<int[]> queue = new LinkedList<>(); // for BFS
  queue.add(new int[]{R, C, 0}); // {x, y, moves}

  // BFS Implementation
  while (!queue.isEmpty()) {
    int[] current = queue.poll();
    // {x, y, moves}
    int x = current[0];
    int y = current[1];
    int moves = current[2];

    // arrival at target
    if (x == S && y == K) {
      return moves;
    }

    // possible 8 direction moves
    for (int i = 0; i < 8; i++) {
      // candidate of move
      int nx = x + dx[i];
      int ny = y + dy[i];

      // Validation of candidate
      // (nx,ny) is not out of border
      if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
        // (nx,ny) position is not visited.
        if (!visited[nx][ny]) {
          visited[nx][ny] = true;
          // Record how many times you need to move to that location.
          queue.add(new int[]{nx, ny, moves + 1});
        }
      }
    }
  }
  return -1;
}
```

# Rope Greedy

```java
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
```

# Bruteforce

```java
public int maxSubarraySum(int[] arr) {
  int max = Integer.MIN_VALUE;
  for (int i = 0; i < arr.length; i++) {
    int tmp = 0;
    for (int j = i; j < arr.length; j++) {
      tmp += arr[j];
      max = Math.max(max, tmp);
    }
  }
  return max;
}
```

# DnC Recursive

```java
public int processDigits(String str) {
  int len = str.length();

  // 자릿수가 1자리일 경우 → 숫자 그대로 반환
  if (len == 1) {
    return str.charAt(0) - '0';
  }

  // 자릿수가 2자리일 경우 → 두 숫자의 곱 반환
  if (len == 2) {
    return (str.charAt(0) - '0') * (str.charAt(1) - '0');
  }

  // 중앙 인덱스를 기준으로 나눔
  int mid = len / 2;
  String left = str.substring(0, mid);  // 왼쪽 절반

  if (len % 2 == 0) {
    // 자릿수가 짝수일 경우 → 좌우 절반으로 나누어 곱함
    String right = str.substring(mid);  // 오른쪽 절반
    return processDigits(left) * processDigits(right);
  } else {
    // 자릿수가 홀수일 경우 → 가운데 숫자 제외하고 좌우로 나눠서 곱한 뒤, 가운데 숫자를 더함
    String right = str.substring(mid + 1);      // 가운데 이후 오른쪽 절반
    int center = str.charAt(mid) - '0';       // 가운데 숫자
    return processDigits(left) * processDigits(right) + center;
  }
}
```

# Dynamic Programming

```java
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
```
