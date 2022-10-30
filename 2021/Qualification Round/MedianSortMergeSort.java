import java.util.*;
import java.io.*;

public class MedianSortMergeSort {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int qCnt = 0, q;
    static int[] x;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numTrials = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        x = new int[n + 1];

        for (int t = 1; t <= numTrials; t++) {
            for (int i = 0; i < x.length; i++) {
                x[i] = i;
            }
            int min = getMin(x);
            mergeSort(1, n, min);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < n; i++) {
                sb.append(x[i] + " ");
            }
            sb.append(x[n]);
            System.out.println(sb.toString());
            int result = Integer.parseInt(br.readLine());
            if (result == -1)
                break;
        }
        br.close();
    }

    static void mergeSort(int start, int end, int min) throws IOException {
        if (end - start <= 0)
            return;

        int mid = (start + end) / 2;
        mergeSort(start, mid, min);
        mergeSort(mid + 1, end, min);
        int[] left = new int[x.length], right = new int[x.length];
        for (int i = start; i <= mid; i++) {
            left[i] = x[i];
        }
        for (int i = mid + 1; i <= end; i++) {
            right[i] = x[i];
        }
        // merge
        int l = start, r = mid + 1;

        for (int idx = start; idx <= end; idx++) {
            if (l == mid + 1) {
                x[idx] = right[r];
                r++;
            } else if (r == end + 1) {
                x[idx] = left[l];
                l++;
            } else {
                int first = query(min, left[l], right[r]);
                if (first == left[l]) {
                    x[idx] = left[l];
                    l++;
                } else {
                    x[idx] = right[r];
                    r++;
                }
            }
        }
    }

    static int getMin(int[] x) throws IOException {
        int a = x[1], b = x[2];

        for (int i = 3; i < x.length; i++) {
            int m = query(a, b, x[i]);

            if (m == a)
                a = x[i];
            else if (m == b)
                b = x[i];
        }
        return a;
    }

    static int query(int a, int b, int c) throws IOException {
        if (a == b || a == c)
            return a;
        else if (b == c)
            return b;

        System.out.println(a + " " + b + " " + c);
        qCnt++;
        if (qCnt > q) {
            int[] arr = new int[1];
            qCnt = arr[1];
        }
        return Integer.parseInt(br.readLine());
    }
}