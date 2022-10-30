import java.util.*;
import java.io.*;

public class SavingTheJelly {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[] childrenX = new int[N + 1];
            int[] childrenY = new int[N + 1];
            int[] candyX = new int[N + 2];
            int[] candyY = new int[N + 2];

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                childrenX[i] = Integer.parseInt(st.nextToken());
                childrenY[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N + 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                candyX[i] = Integer.parseInt(st.nextToken());
                candyY[i] = Integer.parseInt(st.nextToken());
            }
            List<List<Integer>> permutation = getPermutation(N);

            for (List<Integer> order : permutation) {

            }
        }
        pw.flush();
        pw.close();
        br.close();
    }
    static List<List<Integer>> getPermutation(int N) {
        if (N == 1) {
            // base case
            List<Integer> list = new ArrayList<>();
            list.add(0);
            List<List<Integer>> permuation = new ArrayList<>();
            permuation.add(list);
            return permuation;
        } else {
            // use recursion
            List<List<Integer>> permutation = getPermutation(N - 1);
            List<List<Integer>> newPermuation = new ArrayList<>();

            for (List<Integer> list : permutation) {
                for (int i = 0; i < N; i++) {
                    List<Integer> listCopy = list.copy();
                    
                }
            }
        }
    }
}
