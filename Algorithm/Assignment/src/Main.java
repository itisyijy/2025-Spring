import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {
    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge: edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        boolean[] visited = new boolean[n + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, s});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int u = current[1];

            if (visited[u])
                continue;

            visited[u] = true;

            for (int[] neighbor: adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                if (!visited[v] && dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i == s)
                continue;

            if (dist[i] == Integer.MAX_VALUE)
                result.add(-1);
            else
                result.add(dist[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
