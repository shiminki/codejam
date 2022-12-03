import java.util.*;
import java.io.*;

public class SpiralingIntoControl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] grid = spiralGrid(N); // O(N^2)

            List<Map<Integer, Integer>> P = new ArrayList<>();
            // P[u][k] = v indicates that one can move from 1 to u
            // in k moves. Also, one has to visit v right before u.

            for (int i = 0; i <= N * N; i++) {
                P.add(new HashMap<>());
            }
            // base case
            P.get(1).put(0, 1);

            for (int k = 0; k < K; k++) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        for (int neighbor : getNeighbors(x, y, N)) {
                            int u = neighbor / N, v = neighbor % N;
                            if (grid[u][v] > grid[x][y] && P.get(grid[x][y]).containsKey(k)) {
                                P.get(grid[u][v]).put(k + 1, grid[x][y]);
                            }
                        }
                    }
                }
            }

            if (!P.get(N * N).containsKey(K)) {
                pw.println(String.format("Case #%d: IMPOSSIBLE", t));
            } else {
                int ans = 0;
                int num = N * N;
                List<Integer> start = new ArrayList<>();
                List<Integer> end = new ArrayList<>();

                for (int k = K; k > 0; k--) {
                    int parent = P.get(num).get(k);
                    if (parent != num - 1) {
                        ans++;
                        start.add(parent);
                        end.add(num);
                    }
                    num = parent;
                }

                String output = String.format("Case #%d: %d", t, ans);
                pw.println(output);

                for (int i = start.size() - 1; i >= 0; i--) {
                    pw.println(String.format("%d %d", start.get(i), end.get(i)));
                }
            }
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static List<Integer> getNeighbors(int x, int y, int n) {
        List<Integer> neighbors = new ArrayList<>();

        if (y < n - 1) {
            neighbors.add(n * x + y + 1);
        }
        if (y > 0) {
            neighbors.add(n * x + y - 1);
        }
        if (x < n - 1) {
            neighbors.add(n * (x + 1) + y);
        }
        if (x > 0) {
            neighbors.add(n * (x - 1) + y);
        }
        return neighbors;
    }

    static int[][] spiralGrid(int N) {
        int[][] grid = new int[N][N];
        grid[0][0] = 0;
        int num = 1;
        int current_x = 0, current_y = 0;
        int past_x = 0, past_y = -1;

        while (num <= N * N) {
            if (current_x >= 0 && current_x < N && current_y >= 0 && current_y < N
                    && grid[current_x][current_y] == 0) {
                int x = current_x + (current_x - past_x);
                int y = current_y + (current_y - past_y);
                past_x = current_x;
                past_y = current_y;
                current_x = x;
                current_y = y;
            } else {
                // change direction
                // if right, move down
                if (past_y == current_y - 1) {
                    current_x = past_x;
                    current_y = past_y;
                    current_x++;
                }
                // if down, move left
                else if (past_x == current_x - 1) {
                    current_x = past_x;
                    current_y = past_y;
                    current_y--;
                }
                // if left, move up
                else if (past_y == current_y + 1) {
                    current_x = past_x;
                    current_y = past_y;
                    current_x--;
                }
                // if up, move right
                else if (past_x == current_x + 1) {
                    current_x = past_x;
                    current_y = past_y;
                    current_y++;
                }
                num--;
            }
            grid[past_x][past_y] = num++;
        }
        return grid;
    }
}
