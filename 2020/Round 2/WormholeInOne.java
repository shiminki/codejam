import java.util.*;
import java.io.*;

public class WormholeInOne {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("wormhole_in_one.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] x = new int[N], y = new int[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }
            Set<Double> directions = new HashSet<>();

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double direction = getDirection(x[i] - x[j], y[i] - y[j]);
                    directions.add(direction);
                }
            }

            int ans = 1;

            for (double direction : directions) {
                Map<Double, Integer> yCnt = new HashMap<>();

                for (int i = 0; i < N; i++) {
                    double y0;

                    if (direction != Double.POSITIVE_INFINITY) {
                        y0 = (double) y[i] - direction * x[i];
                    } else {
                        y0 = x[i];
                    }

                    if (!yCnt.containsKey(y0)) {
                        yCnt.put(y0, 0);
                    }
                    int cnt = yCnt.get(y0);
                    yCnt.put(y0, cnt + 1);
                }

                int oneCnt = 0, oddCnt = 0, evenCnt = 0;

                for (Map.Entry<Double, Integer> e : yCnt.entrySet()) {
                    int cnt = e.getValue();

                    if (cnt == 1) {
                        oneCnt++;
                    } else if (cnt % 2 == 0) {
                        evenCnt ++;
                    } else {
                        oddCnt ++;
                    }
                }

                if (oddCnt % 2 == 0) {
                    ans = Math.max(ans, N - oneCnt + Math.min(2, oneCnt));
                } else {
                    ans = Math.max(ans, N - oneCnt + Math.min(1, oneCnt));
                }
            }

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static double getDirection(double x, double y) {
        if (x == 0)
            return Double.POSITIVE_INFINITY;
        else
            return y / x;
    }
}