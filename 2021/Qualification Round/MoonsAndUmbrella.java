import java.util.*;
import java.io.*;

// TODO: Change the class name to Solution
public class MoonsAndUmbrella {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTrials = Integer.parseInt(br.readLine());
        for (int t = 1; t <= numTrials; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            char[] current = st.nextToken().toCharArray();
            int cost = solve(x, y, current);
            System.out.println("Case #" + t + ": " + cost);
        }
        br.close();
    }

    static int solve(int x, int y, char[] current) {
        int n = current.length;
        int[] cost = new int[n];

        // preprocessing
        // try to use dp from cost[idx] to cost[0] and fill current[:idx]
        if (current[0] == '?') {
            int idx = 0;
            while (idx < n && current[idx] == '?') {
                idx++;
            }
            if (idx == n) {
                int a = x * (n/2) + y * (n - n/2), b = x*(n - n/2) + y*(n/2);
                int min = 0;

                if (a < min) min = a;
                if (b < min) min = b;
                return min;
            }
            if (x >= 0 && y >= 0) {
                for (int i = idx; i > 0; i--)
                    current[i - 1] = current[i];
            }
            else {
                for (int i = idx; i > 0; i--) {
                    if (current[i] == 'C') {
                        current[i - 1] = y >= 0 ? 'C' : 'J';
                    } else {
                        current[i - 1] = x >= 0 ? 'J' : 'C';
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (current[i] != '?') {
                cost[i] = cost[i - 1];
                if (current[i] != current[i - 1]) {
                    cost[i] += current[i] == 'J' ? x : y;
                }
            } else {
                // a is when current[i] = 'C' and b is when current[i] = 'J'
                int a = cost[i - 1], b = cost[i - 1];
                if (current[i - 1] == 'C')
                    b += x;
                else
                    a += y;

                if (a < b) {
                    // fill C
                    current[i] = 'C';
                    cost[i] = a;
                } else {
                    current[i] = 'J';
                    cost[i] = b;
                }
            }
        }
        // System.out.println(Arrays.toString(current));
        return cost[n - 1];
    }
}