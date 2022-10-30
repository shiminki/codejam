import java.util.*;
import java.io.*;

public class HackedExam {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numTests = Integer.parseInt(br.readLine());
        PrintWriter testPW = new PrintWriter(new FileWriter("test_output.txt"));

        for (int test = 1; test <= numTests; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            long z = 0, w = 0;

            if (N == 1) {
                st = new StringTokenizer(br.readLine());
                String seq = st.nextToken();
                int score = Integer.parseInt(st.nextToken());

                if (score > Q / 2) {
                    sb = new StringBuilder(seq);
                    z = score;
                } else {
                    for (char c : seq.toCharArray()) {
                        if (c == 'T')
                            sb.append('F');
                        else
                            sb.append('T');
                    }
                    z = Q - score;
                }
                w = 1;
            } else if (N >= 2) {
                boolean[][] ans = new boolean[N][Q];
                int[] score = new int[N];

                for (int i = 0; i < N; i++) {
                    st = new StringTokenizer(br.readLine());
                    String seq = st.nextToken();
                    score[i] = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < Q; j++) {
                        ans[i][j] = seq.charAt(j) == 'T';
                    }
                }
                // Try all 2^Q permutations
                List<List<Boolean>> set = new ArrayList<>();

                for (int i = 0; i < Math.pow(2, Q); i++) { // O(Q*2^Q) time
                    List<Boolean> correctAns = new ArrayList<>();
                    int num = i;
                    for (int j = 0; j < Q; j++) {
                        correctAns.add(num % 2 == 0);
                        num /= 2;
                    }
                    boolean match = true;
                    for (int idx = 0; idx < N; idx++) {
                        int predictedScore = 0;
                        for (int j = 0; j < Q; j++) {
                            if (correctAns.get(j) == ans[idx][j]) {
                                predictedScore++;
                            }
                        }
                        if (predictedScore != score[idx]) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        set.add(correctAns);
                    }
                }
                long[] oneCnt = new long[Q];
                w = set.size();

                for (int i = 0; i < Q; i++) {
                    for (List<Boolean> correctAns : set) {
                        if (correctAns.get(i)) {
                            oneCnt[i]++;
                        }
                    }
                }
                for (int i = 0; i < Q; i++) {
                    if (oneCnt[i] > w / 2) {
                        sb.append('T');
                        z += oneCnt[i];
                    } else {
                        sb.append('F');
                        z += (w - oneCnt[i]);
                    }
                }
                long gcd = getGCD(z, w);
                z /= gcd;
                w /= gcd;
            }

            String output = String.format("Case #%d: %s %d/%d", test, sb.toString(), z, w);
            pw.println(output);
            testPW.println(output);
        }
        testPW.close();
        pw.flush();
        pw.close();
        br.close();
    }

    static long getGCD(long a, long b) {
        if (a % b == 0)
            return b;
        else
            return getGCD(b, a % b);
    }
}