# CompleteGraphTest 문제 정리

## 🧾 문제 개요

정점이 3개인 **완전 그래프(Complete Graph)**를 세 가지 그래프 표현 방식으로 구현하고, 각 표현 방식이 정상적으로 구성되었는지 `toString()`을 통해 출력 확인하는 프로그램을 작성한다.

### 구현 대상 클래스

- `AdjList` : 인접 리스트(Adjacency List)
- `AdjMatrix` : 인접 행렬(Adjacency Matrix)
- `IncMatrix` : 발생 행렬(Incidence Matrix)

---

## 📚 이론 정리

### ✅ 완전 그래프 (Complete Graph)

- 정점 n개를 갖는 완전 그래프는 **모든 정점 쌍 사이에 간선이 존재**
- 정점 3개일 때의 간선 수: 3개 (무방향 간선 기준)
- 방향성 있는 경우: 6개

### ✅ 그래프 표현 방식

#### 1. 인접 리스트 (Adjacency List)
- 각 정점에 연결된 이웃 정점들을 리스트로 저장
- 양방향 간선을 표현하려면 양쪽 모두 추가 필요

#### 2. 인접 행렬 (Adjacency Matrix)
- 2차원 배열을 사용하여 정점 간 연결 여부(또는 가중치)를 저장
- `matrix[i][j] = weight`일 때, 정점 i에서 j로 간선이 존재함을 의미

#### 3. 발생 행렬 (Incidence Matrix)
- 행: 정점, 열: 간선
- 방향성 있는 간선 (u→v)은 u 행에 `-1`, v 행에 `1`
- 무방향 간선도 보통 한 번만 표현하지만, 문제에서는 **방향성 간선을 모두 포함하여 6개 간선을 기대함**

---

## 💡 구현 설명

### 🎯 목표
정점 3개 (0, 1, 2)를 갖는 완전 그래프를 구성하여 다음과 같이 출력되도록 한다.

---

### 📦 코드 구성 요약

```java
int numVertices = 3;

// 그래프 표현 인스턴스 생성
Graph<Edge> adjList = new AdjList<>(numVertices);
Graph<Edge> adjMatrix = new AdjMatrix(numVertices);
Graph<Edge> incMatrix = new IncMatrix(numVertices, 6); // 방향성 간선 6개

// 방향성 있는 간선 정의
int[][] edges = {
    {0, 1, 1},
    {0, 2, 1},
    {1, 0, 1},
    {1, 2, 1},
    {2, 0, 1},
    {2, 1, 1}
};

// 간선 추가 (각 표현 방식별)
for (int[] edge : edges) {
    adjList.addEdge(edge[0], edge[1], edge[2]);
    adjMatrix.addEdge(edge[0], edge[1], edge[2]);
    incMatrix.addEdge(edge[0], edge[1], edge[2]);
}
```

---

## 🧪 출력 예시 (Expected Result)

```
Adjacency List Representation:
[0]: (0->1): 1.0 (0->2): 1.0
[1]: (1->0): 1.0 (1->2): 1.0
[2]: (2->0): 1.0 (2->1): 1.0

Adjacency Matrix Representation:
    0.00     1.00     1.00
    1.00     0.00     1.00
    1.00     1.00     0.00

Incidence Matrix Representation:
 -1.00  -1.00   1.00   0.00   1.00   0.00
  1.00   0.00  -1.00  -1.00   0.00   1.00
  0.00   1.00   0.00   1.00  -1.00  -1.00
```

---

## 🛠️ 구현 시 유의사항

| 항목 | 설명 |
|------|------|
| 정점 수 | 3개 (0, 1, 2) |
| 간선 수 | 6개 (방향성 포함) |
| 가중치 | 모두 1.0 고정 |
| IncMatrix | 간선 방향별로 한 번씩 추가해야 함 (예: (0→1), (1→0) 별도로 추가) |

---

## ✅ 결론

- 문제는 **방향성 있는 간선 표현 6개를 기준**으로 세 그래프 표현 방식을 구현하는 것이 핵심
- `addEdge()` 메서드는 각 표현 방식에 맞게 간선을 삽입하고,
- `toString()` 결과를 통해 정상적으로 완전 그래프가 구성되었는지 확인 가능

---
