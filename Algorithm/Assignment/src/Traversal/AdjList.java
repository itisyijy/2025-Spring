package Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjList<T extends Edge> implements Graph {

  private List<List<T>> adjList;

  // Constructor to initialize the adjacency list with the number of vertices
  public AdjList(int numVertices) {
    adjList = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; i++) {
      adjList.add(new ArrayList<>());
    }
  }

  // Adds an edge to the adjacency list
  public void addEdge(int u, int v, double w) {
    adjList.get(u).add((T) new Edge(u, v, w, null));
  }

  // Returns a string representation of the adjacency list
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < adjList.size(); i++) {
      result.append("\n[").append(i).append("]: ");
      for (T edge : adjList.get(i)) {
        result.append(edge).append(" ");
      }
    }
    return result.toString();
  }

  @Override
  public int[] bfs(int start) {
    int numVertices = adjList.size();                // 정점의 총 개수
    boolean[] visited = new boolean[numVertices];    // 정점 방문 여부 기록용 배열
    List<Integer> result = new ArrayList<>();        // 방문한 정점들의 순서를 저장할 리스트
    Queue<Integer> queue = new LinkedList<>();       // BFS 탐색에 사용할 큐

    visited[start] = true;                           // 시작 정점 방문 처리
    queue.offer(start);                              // 시작 정점을 큐에 삽입

    while (!queue.isEmpty()) {
      int current = queue.poll();                  // 큐에서 정점을 꺼냄
      result.add(current);                         // 방문 순서에 추가

      // 현재 정점과 인접한 모든 정점 확인
      for (T edge : adjList.get(current)) {
        int neighbor = edge.getHead();           // 간선의 도착 정점 (이웃 정점)

        if (!visited[neighbor]) {                // 아직 방문하지 않은 정점이라면
          visited[neighbor] = true;            // 방문 처리
          queue.offer(neighbor);               // 큐에 추가
        }
      }
    }

    // 방문 결과를 int 배열로 변환해서 반환
    int[] resArray = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      resArray[i] = result.get(i);
    }

    return resArray;
  }


  @Override
  public int[] dfs(int start) {
    int numVertices = adjList.size();                  // 정점의 총 개수
    boolean[] visited = new boolean[numVertices];      // 정점 방문 여부 기록용 배열
    List<Integer> result = new ArrayList<>();          // 방문한 정점들의 순서를 저장할 리스트

    dfsRecursive(start, visited, result);              // 재귀 DFS 시작

    // 방문 결과를 int 배열로 변환해서 반환
    int[] resArray = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      resArray[i] = result.get(i);
    }

    return resArray;
  }

  // 재귀 DFS 함수
  private void dfsRecursive(int current, boolean[] visited, List<Integer> result) {
    visited[current] = true;                          // 현재 정점 방문 처리
    result.add(current);                              // 방문 순서에 현재 정점 추가

    // 현재 정점의 모든 인접 정점에 대해
    for (T edge : adjList.get(current)) {
      int neighbor = edge.getHead();                // 간선의 도착 정점 (이웃 정점)

      if (!visited[neighbor]) {                     // 아직 방문하지 않은 정점이면
        dfsRecursive(neighbor, visited, result);  // 해당 정점으로 재귀 호출
      }
    }
  }
}
