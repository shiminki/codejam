import java.util.*;
import java.io.*;

public class Subtransmutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numTests = Integer.parseInt(br.readLine());

        for (int test = 1; test <= numTests; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int[] U = new int[N + 1];
            boolean impossible = false;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                U[i] = Integer.parseInt(st.nextToken());

                if ((N - i) % gcd(A, B) != 0 && U[i] > 0) {
                    impossible = true;
                    break;
                }
            }
            String output = "";
            if (impossible)
                output = String.format("Case #%d: IMPOSSIBLE", test);
            else {
                int M = N;
                boolean found = false;

                while (!found) {
                    M++;
                    int[] x = new int[M + 1];
                    x[M] = 1;

                    for (int i = M; i > N; i--) {
                        if (i - A > 0) {
                            x[i - A] += x[i];
                        }
                        if (i - B > 0) {
                            x[i - B] += x[i];
                        }
                        x[i] = 0;
                    }
                    for (int i = N; i > 0; i--) {
                        if (x[i] < U[i])
                            break;

                        int add = x[i] - U[i];
                        x[i] = U[i];
                        if (i - A > 0)
                            x[i - A] += add;
                        if (i - B > 0)
                            x[i - B] += add;
                    }

                    found = true;
                    for (int i = 1; i <= N; i++) {
                        if (x[i] < U[i]) {
                            found = false;
                            break;
                        }
                    }
                    if (M > 1000)
                        break;
                }
                output = String.format("Case #%d: %d", test, M);
            }

            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static int gcd(int A, int B) {
        if (A % B == 0)
            return B;
        else
            return gcd(B, A % B);
    }
}