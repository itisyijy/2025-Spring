package shortest;

import java.util.LinkedList;
import java.util.Queue;

public class Itm {
    public int minKnightMoves(int N, int M, int R, int C, int S, int K) {
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
            if (x == S && y == K)
                return moves;

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
}