import java.util.*;
import java.io.*;

public class SpiralingIntoControl2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (K % 2 == 1 || K >= N * N || K < N - 1) {
                pw.println(String.format("Case #%d: IMPOSSIBLE", t));
            } else {
                int R = (N - 1) / 2;
                int[] moveSaved = new int[R];
                int r = 6;
                int num = N * N - 1 - K;
                int cnt = 0;

                for (int i = R - 1; i >= 0; i--) {
                    if (num >= 8 * i) {
                        while (num < 8 * i + r && r >= 0) {
                            r -= 2;
                        }
                        moveSaved[i] = 8 * i + r;
                    }
                    num -= moveSaved[i];
                    if (moveSaved[i] != 0) {
                        cnt++;
                    }
                }

                pw.println(String.format("Case #%d: %d", t, cnt));

                // pw.println(Arrays.toString(moveSaved));

                int x = 2;
                r = 6;

                for (int i = R - 1; i >= 0; i--) {
                    if (moveSaved[i] != 0) {
                        x += (r - moveSaved[i] % 8) * (i + 1);
                        r = moveSaved[i] % 8;
                        pw.println(String.format("%d %d", x, x + moveSaved[i] + 1));
                        x += moveSaved[i] + 2;
                    } else {
                        x += 8 * i + r + 2;
                    }
                    // pw.println(x);
                }
            }
        }
        pw.flush();
        pw.close();
        br.close();
    }
}