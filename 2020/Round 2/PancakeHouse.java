import java.util.*;
import java.io.*;
import java.lang.System;

public class PancakeHouse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long start = System.currentTimeMillis();
        // BufferedReader br = new BufferedReader(new FileReader("pancake.in"));

        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        // BufferedReader testInput = new BufferedReader(new FileReader("pancake.out"));

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long L = Long.parseLong(st.nextToken());
            long R = Long.parseLong(st.nextToken());

            long n = 0, i = 1;

            while (true) {
                if (L < n + 1 && R < n + 1) {
                    break;
                }
                if (L >= R) {
                    n = binarySearch(L - R, i, i, (long) Math.sqrt(2*(L - R)), L);
                    L -= sum(i, n);
                } else {
                    n = binarySearch(R - L, i, i, (long) Math.sqrt(2*(R - L)), R);
                    R -= sum(i, n);
                }
                // pw.println(String.format("%d %d %d", n, L, R));
                i = n + 1;
            }

            String output = String.format("Case #%d: %d %d %d", t, n, L, R);
            pw.println(output);
        }
        pw.println(System.currentTimeMillis() - start);
        pw.flush();
        pw.close();
        br.close();
    }

    static long binarySearch(long diff, long i, long start, long end, long num) {
        // returns minimum n such that (i + n)(n - i + 1)/2 >= diff
        if (diff < i) {
            return i;
        }
        if (start > end) {
            long a = sum(i, start), b = sum(i, end);
            // a > b

            if (b < diff && a <= num)
                return start;
            else
                return end;
        }

        long mid = (start + end) / 2;

        if (sum(i, mid) > diff) {
            return binarySearch(diff, i, start, mid - 1, num);
        } else if (sum(i, mid) < diff) {
            return binarySearch(diff, i, mid + 1, end, num);
        } else {
            return mid;
        }
    }

    static long sum(long i, long n) {
        if (i + n > Long.MAX_VALUE / (n - i + 1)) {
            return Long.MAX_VALUE;
        }

        return (i + n) * (n - i + 1) / 2;
    }
}