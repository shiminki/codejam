import java.util.*;
import java.io.*;

public class ChainReactionReduction {
    public static void main(String[] args) throws IOException {
        // Will solve this problem by reducing each vertex into simpler form
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int numTests = Integer.parseInt(br.readLine());

        for (int t = 1; t <= numTests; t++) {
            int n = Integer.parseInt(br.readLine());
            List<Set<Integer>> inAdj = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                inAdj.add(new HashSet<>());
            }
            int[] fun = new int[n + 1], pointer = new int[n + 1];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                fun[i] = Integer.parseInt(st1.nextToken());
                pointer[i] = Integer.parseInt(st2.nextToken());
                inAdj.get(pointer[i]).add(i);
            }
            List<Integer> initiators = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (inAdj.get(i).size() == 0) {
                    initiators.add(i);
                }
            }

            for (int u = n; u > 0; u--) {
                if (inAdj.get(u).size() == 0) {
                    continue;
                }
                int p = pointer[u];
                int x = -1;
                // x will be the vertex with the least fun
                // u will be reduced such that every verticies pointing u
                // will now point to p. However, x will be called first
                // so fun of x will be now fun of u
                for (int v : inAdj.get(u)) {
                    if (x == -1 || fun[x] > fun[v])
                        x = v;
                }
                inAdj.get(p).add(x);
                inAdj.get(p).remove(u);
                fun[x] = Math.max(fun[x], fun[u]);
            }
            long ans = 0;

            for (int u : initiators) {
                ans += fun[u];
                // pw.println(String.format("%d %d", u, fun[u]));
            }
            pw.println(String.format("Case #%d: %d", t, ans));
        }
        pw.flush();
        br.close();
    }
}
