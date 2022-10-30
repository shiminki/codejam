import java.util.*;
import java.io.*;

public class HiddenPancakesLinear {
    static final int NUM = 1000000007;
    static List<Long> fact = new ArrayList<>();
    static List<Long> inverseFact = new ArrayList<>();
    // inverseFact[n] * n! = 1 mod NUM

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // BufferedReader br = new BufferedReader(new FileReader("pancake.in"));

        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        fact.add((long) 1);
        inverseFact.add((long) 1);

        for (int i = 1; i <= 100001; i++) {
            fact.add((fact.get(i - 1) * i) % NUM);
            inverseFact.add(power(fact.get(i), NUM - 2));
            // use Fermat's theorem of a^p = a mod p
        }

        for (int t = 1; t <= T; t++) {
            // System.out.println((long) 100 * t / T);
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] V = new int[N];
            for (int i = 0; i < V.length; i++) {
                V[i] = Integer.parseInt(st.nextToken());
            }

            List<Set<Integer>> adj = new ArrayList<>();
            // Tree graph (V, E) where for every (A, B) in E,
            // Pancake at A > Pancake at B
            for (int i = 0; i < N; i++) {
                adj.add(new HashSet<>());
            }
            Stack<Integer> stack = new Stack<>();
            stack.add(0);

            boolean notValid = false;

            for (int i = 1; i < V.length; i++) {
                if (V[i] > V[i - 1] + 1) {
                    notValid = true;
                    break;
                } else if (V[i] == V[i - 1] + 1) {
                    // Pancake at stack.last > pancake at i
                    // because no pancakes got covered
                    adj.get(stack.peek()).add(i);
                } else {
                    int x = stack.pop();
                    while (stack.size() >= V[i]) {
                        x = stack.pop();
                    }
                    adj.get(i).add(x); // Pancake at i > pancake at x
                    if (V[i] > 1) {
                        adj.get(stack.peek()).add(i); // pancake at stack.last > pancake at i
                        adj.get(stack.peek()).remove(x); // remove redundant edges
                    }
                }
                stack.add(i);
            }

            // pw.println(adj);

            long ans = 0;

            if (!notValid) {
                int root = getRoot(adj);
                int[] size = getTreeSize(adj, root);
                // pw.println(Arrays.toString(size));
                ans = f(adj, size, root);
            }

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static long f(List<Set<Integer>> adj, int[] size, int root) {
        long ans = 1;

        for (int u : adj.get(root)) {
            ans *= f(adj, size, u);
            ans %= NUM;
            ans *= inverseFact.get(size[u]);
            ans %= NUM;
        }
        ans *= fact.get(size[root] - 1);
        ans %= NUM;
        return ans;
    }

    static int getRoot(List<Set<Integer>> adj) {
        // perform topological sort and return the first node
        int n = adj.size();
        boolean[] visited = new boolean[n];
        List<Integer> order = new ArrayList<Integer>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                order = dfs(adj, visited, order, i);
            }
        }
        return order.get(n - 1);
    }

    static List<Integer> dfs(List<Set<Integer>> adj, boolean[] visited, List<Integer> order, int u) {
        visited[u] = true;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                order = dfs(adj, visited, order, v);
            }
        }
        order.add(u);
        return order;
    }

    static int[] getTreeSize(List<Set<Integer>> adj, int root) {
        int n = adj.size();
        int[] size = new int[n];

        size = getSize(adj, size, root);
        return size;
    }

    static int[] getSize(List<Set<Integer>> adj, int[] size, int root) {
        size[root] = 1;

        for (int u : adj.get(root)) {
            size = getSize(adj, size, u);
            size[root] += size[u];
        }
        return size;
    }

    static long power(long n, int r) {
        // return n^r mod NUM in O(log r) time
        if (r == 0)
            return 1;

        long p = power(n, r / 2);
        long sqr = (p * p) % NUM;

        if (r % 2 == 0) {
            return sqr;
        } else {
            return (sqr * n) % NUM;
        }
    }
}