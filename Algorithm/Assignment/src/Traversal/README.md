# 📘 그래프 탐색 알고리즘 구현 문제 정리

## 🔍 문제 개요

- **그래프 표현 방식**: 인접 리스트(`AdjList<T extends Edge>`)
- **정점**: 0부터 시작하는 정수형 인덱스
- **간선(Edge)**: 출발 정점(u), 도착 정점(v), 가중치(weight)를 갖는 객체
- **그래프 방향성**: 무방향 그래프로 가정 (따라서 `addEdge(u, v)`와 `addEdge(v, u)` 모두 필요)
- **목표**: 
  - `dfs(int start)`: 깊이 우선 탐색 (Depth-First Search) 결과 반환
  - `bfs(int start)`: 너비 우선 탐색 (Breadth-First Search) 결과 반환
  - 반환형: `int[]` — 정점 방문 순서

---

## 🧠 문제 해석

그래프는 다음과 같은 구조로 정의되어 있음:

```java
List<List<T>> adjList;
```

- 각 `adjList.get(u)`는 `u` 정점에서 연결된 간선(`Edge`) 리스트
- `Edge` 클래스는 다음과 같은 구조:
  ```java
  class Edge {
      int u;         // 출발 정점
      int v;         // 도착 정점
      double weight; // 가중치
  }
  ```

따라서 인접 정점은 `edge.v` 또는 `edge.getHead()`로 접근할 수 있음.

---

## ✨ DFS (Depth-First Search) 접근 방법

### 📌 방법 1: 스택 기반 (비재귀)

- 스택(Stack)을 이용해 직접 DFS 흐름 제어
- 방문 여부는 `boolean[] visited`로 체크
- 인접 정점은 **역순으로 스택에 push** 해야 DFS 특성 유지

```java
stack.push(start);
while (!stack.isEmpty()) {
    int current = stack.pop();
    if (!visited[current]) {
        visited[current] = true;
        result.add(current);
        for (int i = neighbors.size() - 1; i >= 0; i--) {
            int neighbor = neighbors.get(i).getHead();
            if (!visited[neighbor]) {
                stack.push(neighbor);
            }
        }
    }
}
```

### 📌 방법 2: 재귀 기반

- 방문한 정점마다 재귀적으로 이웃 정점 방문
- 코드가 간결하고 직관적이지만, **호출 깊이에 제한** 있음

```java
dfsRecursive(start, visited, result);
...
void dfsRecursive(int current, ...) {
    visited[current] = true;
    result.add(current);
    for (Edge e : adjList.get(current)) {
        int neighbor = e.getHead();
        if (!visited[neighbor]) {
            dfsRecursive(neighbor, ...);
        }
    }
}
```

---

## 🌐 BFS (Breadth-First Search) 접근 방법

- 큐(Queue)를 이용해 가까운 정점부터 순차적으로 탐색
- **최단 거리 계산, 최소 간선 탐색**에 적합

```java
queue.offer(start);
visited[start] = true;

while (!queue.isEmpty()) {
    int current = queue.poll();
    result.add(current);

    for (Edge e : adjList.get(current)) {
        int neighbor = e.getHead();
        if (!visited[neighbor]) {
            visited[neighbor] = true;
            queue.offer(neighbor);
        }
    }
}
```

---

## 🧪 테스트 시 유의사항

1. 무방향 그래프이므로 `addEdge(u, v)` 후 반드시 `addEdge(v, u)`도 호출
2. 연결되지 않은 정점이 있는 경우, 탐색 결과에 포함되지 않음
3. DFS는 인접 리스트 순서에 따라 결과가 달라질 수 있음

---

## ✅ 반환 형식

모든 탐색 함수는 결과를 다음과 같이 반환:

```java
int[] result = resultList.stream().mapToInt(i -> i).toArray();
```

---

## 📝 요약 비교

| 항목 | DFS | BFS |
|------|-----|-----|
| 구조 | 스택 (또는 재귀) | 큐 |
| 순서 | 깊게 탐색 | 넓게 탐색 |
| 활용 | 백트래킹, 경로 추적 | 최단 거리, 최소 횟수 탐색 |
| 구현 난이도 | 재귀로 간단 / 스택은 다소 복잡 | 큐 구조만 익히면 간단 |
| 주의사항 | 스택 오버플로우 (재귀 시) | 인접 정점 순서 민감도 낮음 |

---
