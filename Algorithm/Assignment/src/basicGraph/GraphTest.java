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
