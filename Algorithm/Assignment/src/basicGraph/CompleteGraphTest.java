package basicGraph;

public class CompleteGraphTest {
    public static void main(String[] args) {
        int numVertices = 3; // Example graph with 5 vertices

        // Instantiate each graph implementation
        Graph<Edge> adjList = new AdjList<>(numVertices);
        Graph<Edge> adjMatrix = new AdjMatrix(numVertices);

        // Define some edges
        int[][] edges = {
                {0, 1, 1},
                {0, 2, 1},
                {1, 0, 1},
                {1, 2, 1},
                {2, 0, 1},
                {2, 1, 1}
        };
        Graph<Edge> incMatrix = new IncMatrix(numVertices, edges.length); // Assume numEdges = numVertices for simplicity

        // Add edges to AdjList
        for (int[] edge : edges) {
            adjList.addEdge(edge[0], edge[1], edge[2]);
        }

        // Add edges to AdjMatrix
        for (int[] edge : edges) {
            adjMatrix.addEdge(edge[0], edge[1], edge[2]);
        }

        // Add edges to IncMatrix
        for (int[] edge : edges) {
            incMatrix.addEdge(edge[0], edge[1], edge[2]);
        }

        // Print the graph representations
        System.out.println("Adjacency List Representation:"+ adjList.toString());
        System.out.println();

        System.out.println("\nAdjacency Matrix Representation:");
        System.out.println(adjMatrix.toString());

        System.out.println("\nIncidence Matrix Representation:");
        System.out.println(incMatrix.toString());
    }
}
