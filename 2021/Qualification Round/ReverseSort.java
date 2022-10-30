import java.util.*;
import java.io.*;

public class ReverseSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTrials = Integer.parseInt(br.readLine());
        for (int t = 1; t <= numTrials; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int cost = solve(arr);
            System.out.println("Case #" + t + ": " + cost);
        }
        br.close();
    }

    static int solve(int[] arr) {
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

    static int[] reverse(int[] arr, int i, int j) {
        for (int k = i; k <= (i + j) / 2; k++) {
            int idx = j - (k - i);
            int temp = arr[k];
            arr[k] = arr[idx];
            arr[idx] = temp;
        }
        return arr;
    }
}