import java.util.*;
import java.io.*;

public class ChainReaction {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(System.out);
        BufferedReader testInput = new BufferedReader(new FileReader("testOutput.txt"));

        int numTests = Integer.parseInt(br.readLine());

        for (int t = 1; t <= numTests; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] fun = new int[n + 1], pointer = new int[n + 1];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                fun[i] = Integer.parseInt(st1.nextToken());
                pointer[i] = Integer.parseInt(st2.nextToken());
            }
            Set<Integer> initiator = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                initiator.add(i);
            }
            for (int p : pointer) {
                if (initiator.contains(p))
                    initiator.remove(p);
            }
            int[] max_fun = fun.clone();

            for (int i = 1; i <= n; i++) {
                int current = i;
                boolean[] visited = new boolean[n + 1];
                while (pointer[current] != 0) {
                    visited[current] = true;
                    int p = pointer[current];
                    max_fun[p] = Math.max(max_fun[p], max_fun[current]);
                    current = p;
                    if (visited[p]) {
                        break;
                    }
                }
            }
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }
            int[] parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                adj.get(pointer[i]).add(i);
            }
            for (int i = 1; i <= n; i++) {
                if (parent[i] == 0)
                    parent = dfs(i, adj, parent, max_fun);
            }

            long totalFun = 0;

            for (int i : initiator) {
                int current = i, pathMax = fun[i];

                while (current != 0) {
                    pathMax = Math.max(pathMax, fun[current]);

                    if (parent[current] == 0) {
                        totalFun += pathMax;
                        break;
                    }
                    current = parent[current];
                }
            }
            // pw.println(String.format("Case #%d: %d", t, totalFun));

            if (totalFun != Long.parseLong(testInput.readLine())) {
                pw.println("Wrong");
            }
        }
        pw.flush();
        br.close();
    }

    static int[] dfs(int u, List<List<Integer>> adj, int[] parent, int[] max_fun) {
        int next = 0;
        for (int v : adj.get(u)) {
            if (parent[v] != 0)
                continue;
            if ((next == 0 || max_fun[v] < max_fun[next])) {
                next = v;
            }
        }
        if (next == 0)
            return parent;
        else {
            parent[next] = u;
            return dfs(next, adj, parent, max_fun);
        }
    }
}
