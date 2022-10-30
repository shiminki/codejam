import java.util.*;
import java.io.*;

public class PixelatedCircle2 {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int R = Integer.parseInt(br.readLine());
            long ans = circleFilled(R) - circleFilledWrong(R);

            if (t == 5) {
                System.out.println(circleFilled(R));
                System.out.println(circleFilledWrong(R));
            }

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static long circleFilled(int R) {
        long cnt = 0;
        for (int x = -1 * R; x <= R; x++) {
            long y = Math.round(Math.sqrt((long) R * R - (long) x * x));
            while (Math.round(Math.sqrt((long) x * x + (long) (y + 1) * (y + 1))) <= R) {
                y++;
            }
            cnt += 2 * y + 1;
        }
        return cnt;
    }

    static long circleFilledWrong(int R) {
        long cnt = 1; // for r = 0 case
        for (int r = 1; r <= R; r++) {
            long x = Math.round(r / Math.sqrt(2.0));
            long y = Math.round(Math.sqrt((long) r * r - x * x));
            if (x == y) {
                cnt += 8 * x;
            } else {
                cnt += 4 * (2 * Math.min(x, y) + 1);
            }
        }
        return cnt;
    }
}