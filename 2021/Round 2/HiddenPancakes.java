import java.util.*;
import java.io.*;

public class HiddenPancakes {
    static final int NUM = 1000000007;
    static List<Long> fact = new ArrayList<>();
    static List<Long> inverseFact = new ArrayList<>();
    // inverseFact[n] * n! = 1 mod NUM

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        fact.add((long) 1);
        inverseFact.add((long) 1);

        for (int i = 1; i <= 100001; i++) {
            long next = (fact.get(i - 1) * i) % NUM;
            fact.add(next % NUM);
            inverseFact.add(power(fact.get(i), NUM - 2));
            // use Fermat's theorem of a^p = a mod p
        }

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] V = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                V[i] = Integer.parseInt(st.nextToken());
            }
            long ans = 0;

            for (int i = 0; i < N; i++) {
                // pancake w/ radius N can only be present at position where V[i] = 1
                // We thus use divide and conquer method to recursively solve the
                // left and right half of the array, given that V[i] = 1
                if (V[i] == 1) {
                    long add = (nCr(N - 1, i) * f(V, 0, i, 0)) % NUM * f(V, i + 1, N, 1);
                    ans += add % NUM;
                    ans %= NUM;
                }
            }

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
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

    static long f(int[] V, int start, int end, int decrease) {
        if (start >= end)
            return 1;

        long ans = 0;
        int n = end - start;

        for (int i = start; i < end; i++) {
            if (V[i] - decrease == 1) {
                long add = (nCr(n - 1, i - start) * f(V, start, i, decrease)) % NUM * f(V, i + 1, end, decrease + 1);
                ans += add % NUM;
                ans %= NUM;
            } else if (V[i] - decrease < 1 || V[i] - decrease > i - start + 1) {
                return 0;
            }
        }
        return ans;
    }

    static long nCr(int n, int r) {
        return ((fact.get(n) * inverseFact.get(n - r)) % NUM * inverseFact.get(r)) % NUM;
    }
}