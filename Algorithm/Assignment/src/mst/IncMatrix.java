package mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncMatrix implements Graph {

  private int[][] m;          // Incidence Matrix (Tail: -1, Head: 1)
  private double[] weights;   // Array to store weights of edges
  private int cur;            // Current edge index

  // Constructor initializes the incidence matrix and weights array
  public IncMatrix(int numVertices, int numEdges) {
    m = new int[numVertices][numEdges];
    weights = new double[numEdges];
    cur = 0;
  }

  // Adds an edge with its weight stored separately
  public void addEdge(int u, int v, double weight) {
    m[u][cur] = -1;          // Tail vertex marked with -1
    m[v][cur] = 1;           // Head vertex marked with 1
    weights[cur] = weight;   // Store the weight in a separate array
    cur++;
  }

  // Returns a string representation of the incidence matrix and weights
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Incidence Matrix:\n");
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < cur; j++) {
        sb.append(String.format("%4d ", m[i][j]));
      }
      sb.append("\n");
    }
    sb.append("Weights:\n");
    for (int j = 0; j < cur; j++) {
      sb.append(String.format("%6.2f ", weights[j]));
    }
    sb.append("\n");
    return sb.toString();
  }

  @Override
  public int[] bfs(int start) {
    return null;
  }

  @Override
  public int[] dfs(int start) {
    return null;
  }

  // Extract edges from the incidence matrix
  public List<Edge> getEdges() {
    List<Edge> edges = new ArrayList<>();
    for (int j = 0; j < cur; j++) {
      int u = -1, v = -1;
      for (int i = 0; i < m.length; i++) {
        if (m[i][j] == -1) {
          u = i;
        } else if (m[i][j] == 1) {
          v = i;
        }
      }
      edges.add(new Edge(u, v, weights[j], null));
    }
    return edges;
  }

  // Kruskal algorithm for MST
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
}