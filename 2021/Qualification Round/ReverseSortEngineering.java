import java.util.*;
import java.io.*;

public class ReverseSortEngineering {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTrials = Integer.parseInt(br.readLine());
        for (int t = 1; t <= numTrials; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            if (c < n - 1 || c > n * (n + 1) / 2 - 1)
                System.out.println("Case #" + t + ": IMPOSSIBLE");
            else {
                int[] arr = solve(n, c);
                System.out.print("Case #" + t + ":");
                for (int i : arr) {
                    System.out.print(" " + i);
                }
                System.out.println();
                int cost = getCost(arr);
                if (c != cost) {
                    System.out.println("Wrong Answer");
                    System.out.println("Cost: " + cost);
                }
            }
        }
        br.close();
    }

    static int[] solve(int n, int c) {
        int[] endIdx = new int[n];
        int cost = n - 1;

        for (int i = 0; i < n - 1; i++) {
            if (cost + n - 1 - i <= c) {
                endIdx[i] = n - 1;
                cost += n - 1 - i;
            } else {
                int j = c - cost + i;
                endIdx[i] = j;
                cost += j - i;
            }
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            arr = reverse(arr, i, endIdx[i]);
        }

        return arr;
    }

    static int[] reverse(int[] arr, int i, int j) {
        for (int k = i; k <= (i + j) / 2; k++) {
            int idx = j - (k - i);
            int temp = arr[k];
            arr[k] = arr[idx];
            arr[idx] = temp;
        }
        return arr;
    }

    static int getCost(int[] arr) {
        // performs reverse sort and prints the total cost
        int n = arr.length, cost = 0;

        for (int i = 0; i < n - 1; i++) {
            int j = i;
            for (int idx = i; idx < n; idx++) {
                if (arr[idx] < arr[j])
                    j = idx;
            }
            cost += j - i + 1;
            reverse(arr, i, j);
        }
        return cost;
    }
}
