import java.io.*;
import java.util.*;

public class MedianSort {
    static int queryCnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numTrials = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        queryCnt = 0;

        for (int t = 1; t <= numTrials; t++) {
            int[] arr = new int[n + 1]; // 1-indexed
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            arr = solve(arr); // O(n lg n) queries, O(n^2) time
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < n; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append(arr[n]);
            System.out.println(sb.toString());
            int result = Integer.parseInt(br.readLine());
            if (result == -1)
                break;
        }
    }

    static int[] solve(int[] arr) throws IOException {
        int n = arr.length - 1;
        for (int i = 2; i < n; i++) {
            arr = binarySearch(arr, 1, i, i + 1); // O(lg n) queries, O(n) time
            // System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    static int query(int a, int b, int c) throws IOException {
        System.out.println(a + " " + b + " " + c);
        queryCnt++;
        int m = Integer.parseInt(br.readLine());
        if (m == -1) {
            int[] arr = new int[1];
            m = arr[1];
        }
        return m;
    }

    static int[] binarySearch(int[] arr, int start, int end, int idx) throws IOException {
        // base case
        if (start == idx - 1)
            return arr;
        if (end == 1)
            return shift(arr, 1, idx);
        if (end - start <= 1) {
            int m = query(arr[start], arr[start + 1], arr[idx]);
            if (m == arr[start]) {
                return shift(arr, start, idx);
            } else if (m == arr[idx]) {
                return shift(arr, start + 1, idx);
            } else {
                return shift(arr, start + 2, idx);
            }
        }
        int mid = (start + end) / 2;
        int m = query(arr[mid], arr[mid + 1], arr[idx]);

        if (m == arr[idx]) {
            return shift(arr, mid + 1, idx);
        } else if (m == arr[mid]) {
            return binarySearch(arr, start, mid - 1, idx);
        } else {
            return binarySearch(arr, mid + 1, end, idx);
        }
    }

    static int[] shift(int[] arr, int dest, int idx) {
        // inserts arr[idx] at index dest
        for (int i = idx; i > dest; i--) {
            int temp = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = temp;
        }
        return arr;
    }
}
