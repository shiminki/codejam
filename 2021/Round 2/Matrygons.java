import java.util.*;
import java.io.*;

public class Matrygons {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numTests = Integer.parseInt(br.readLine());

        for (int test = 1; test <= numTests; test++) {
            int N = Integer.parseInt(br.readLine());
            pw.println(String.format("Case #%d: %d", test, getMatrygons(N)));
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static int getMatrygons(int N) {
        int[] K = new int[N + 1];
        int ans = 1;

        for (int i = 3; i < N; i++) {
            if (N % i == 0) {
                ans = Math.max(ans, getK(K, N / i));
            }
        }
        // System.out.println(Arrays.toString(K));
        return ans;
    }

    static int getK(int[] K, int n) {
        if (n <= 2) {
            K[n] = 1;
        } else if (K[n] == 0) {
            K[n] = 2;
            for (int i = 2; i <= n; i++) {
                if ((n - 1) % i == 0) {
                    K[n] = Math.max(K[n], getK(K, (n - 1) / i) + 1);
                }
            }
        }
        // System.out.println(String.format("Input N = %d, K = %d", n, K[n]));
        return K[n];
    }
}