import java.util.*;
import java.io.*;

public class PancakeHouseNaive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pancake.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("pancake.out"));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());
            long n = 1;

            while (L >= n || R >= n) {
                if (L >= R) {
                    L -= n;
                } else {
                    R -= n;
                }
                n++;
            }
            String output = String.format("Case #%d: %d %d %d", t, n - 1, L, R);
            pw.println(output);
        }
        pw.flush();
        br.close();
        pw.close();
    }
}
