import java.util.*;
import java.io.*;

public class MinSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numTests = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean wrong = false;

        for (int test = 1; test <= numTests; test++) {
            for (int i = 1; i < N; i++) {
                if (wrong)
                    break;
                pw.println(String.format("M %d %d", i, N));
                pw.flush();

                int idx = Integer.parseInt(br.readLine());
                if (idx == -1) {
                    wrong = true;
                }
                if (idx == i)
                    continue;
                pw.println(String.format("S %d %d", i, idx));
                pw.flush();
                br.readLine();
            }
            pw.println("D");
            pw.flush();
            int result = Integer.parseInt(br.readLine());
            if (result == -1)
                wrong = true;
        }
        pw.flush();
        pw.close();
        br.close();
    }
}