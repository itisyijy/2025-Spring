
# Kruskal 알고리즘과 Disjoint Set 정리

## ✅ 문제 설명

주어진 그래프는 **가중치가 있는 무방향 그래프**이며, **Incidence Matrix** 형태로 표현된다.  
이 그래프에서 **최소 신장 트리(MST, Minimum Spanning Tree)** 를 Kruskal 알고리즘을 통해 찾는 것이 목표이다.

```java
public List<Edge> kruskalMST()
```

출력은 MST를 구성하는 간선들의 리스트이며, 각 간선은 다음과 같은 `Edge` 클래스로 표현된다:

```java
public class Edge implements Comparable<Edge> {
    int u, v;
    double weight;
}
```


## ✅ Kruskal 알고리즘

### 🔹 개요

Kruskal 알고리즘은 **탐욕적(Greedy)** 방식으로 최소 신장 트리를 찾는 알고리즘이다.  
모든 간선을 **가중치 기준으로 정렬**하고, **사이클을 만들지 않는** 간선을 순차적으로 선택한다.

### 🔹 알고리즘 절차

1. 모든 간선을 가중치 기준으로 오름차순 정렬
2. 가장 작은 간선부터 하나씩 선택하며,
   - **두 정점이 서로 다른 집합에 속한 경우**에만 MST에 추가
   - **사이클이 생기는 경우**는 제외
3. MST에 (정점 수 - 1)개의 간선이 선택되면 종료

### 🔹 시간 복잡도

- 간선 정렬: O(E log E)
- Union-Find 연산: O(α(N)), 거의 상수 시간


## ✅ Disjoint Set (서로소 집합, Union-Find)

### 🔹 개념

서로소 집합 자료구조는, 다음과 같은 연산을 빠르게 수행하는 데 사용된다:

- `find(x)`: 원소 x가 속한 집합의 대표(root)를 반환
- `union(x, y)`: 두 집합을 병합

### 🔹 동작 방식

- 각 원소는 배열로 표현되며, 부모 노드를 가리키는 방식으로 트리 구조를 이룸
- `find()`는 경로 압축(Path Compression)을 통해 성능을 향상시킴
- `union()`은 랭크(rank)를 기준으로 트리를 병합해 트리 깊이를 최소화함

### 🔹 Kruskal에서의 역할

- 간선 (u, v)를 선택할 때, `find(u) == find(v)` 이면 사이클이 생기므로 제외
- `find(u) != find(v)` 이면 MST에 추가 가능 → `union(u, v)` 수행


## ✅ 코드 예시 (주석 포함)

```java
public List<Edge> kruskalMST() {
    List<Edge> mst = new ArrayList<>();                  // MST 결과를 저장할 리스트
    List<Edge> edges = getEdges();                       // 모든 간선 수집
    Collections.sort(edges);                             // 가중치 기준 정렬

    DisjointSet ds = new DisjointSet(m.length);          // 정점 수만큼 Disjoint Set 생성

    for (Edge e : edges) {
        int uRoot = ds.find(e.getTail());                // u의 루트 찾기
        int vRoot = ds.find(e.getHead());                // v의 루트 찾기

        if (uRoot != vRoot) {                            // 다른 집합이면 사이클 없음
            mst.add(e);                                  // 간선 추가
            ds.union(uRoot, vRoot);                      // 집합 병합
        }
    }

    return mst;                                           // 최종 MST 반환
}
```


## ✅ 실행 예시

### 입력 그래프:

- 정점: 0~3
- 간선:
    - (0,1): 1.0
    - (0,2): 3.0
    - (1,2): 1.5
    - (1,3): 2.0
    - (2,3): 4.0

### 출력 MST:

```
(0->1): 1.0
(1->2): 1.5
(1->3): 2.0
```

→ 총 3개의 간선 = 정점 수 - 1 = 4 - 1 → MST 조건 만족


## ✅ 참고 개념 요약

| 개념 | 설명 |
|------|------|
| MST | 모든 정점을 잇되 사이클이 없고 가중치의 합이 최소인 트리 |
| Kruskal | 간선 정렬 후 사이클 없이 선택하는 MST 알고리즘 |
| Disjoint Set | 각 정점이 어떤 연결 그룹에 속하는지 관리하는 자료구조 |
| `find()` | 집합의 대표를 찾는 연산 (경로 압축 포함) |
| `union()` | 두 집합을 하나로 합치는 연산 |
