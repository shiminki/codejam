import java.util.*;
import java.io.*;

public class PixelatedCircle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int R = Integer.parseInt(br.readLine());

            boolean[][] correctGrid = drawCirclesFilled(R);
            boolean[][] wrongGrid = drawCirclesFilledWrong(R);

            int ans = 0;

            int cnt1 = 0, cnt2 = 0;

            for (int i = 0; i < 2 * R + 1; i++) {
                for (int j = 0; j < 2 * R + 1; j++) {
                    if (correctGrid[i][j] != wrongGrid[i][j]) {
                        ans++;
                    }
                    if (correctGrid[i][j]) {
                        cnt1++;
                    }
                    if (wrongGrid[i][j]) {
                        cnt2++;
                    }
                }
            }
            if (t == 5)
                pw.println(String.format("%d %d", cnt1, cnt2));

            /*
             * for (int i = 0; i < correctGrid.length; i++) {
             * pw.println(Arrays.toString(correctGrid[i]));
             * }
             * pw.println();
             * for (int i = 0; i < correctGrid.length; i++) {
             * pw.println(Arrays.toString(wrongGrid[i]));
             * }
             */

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }

    static boolean[][] drawCirclePerimeter(boolean[][] grid, int r, int R) {
        for (int i = 0; i < grid.length; i++) {
            int x = getPos(i, R);
            if (x * x <= r * r) {
                int y = (int) Math.round(Math.sqrt(r * r - x * x));
                int j = getIndex(y, R), k = getIndex(-1 * y, R);
                grid[i][j] = true;
                grid[i][k] = true;
                grid[j][i] = true;
                grid[k][i] = true;
            }
        }
        return grid;
    }

    static boolean[][] drawCirclesFilledWrong(int R) {
        boolean[][] grid = new boolean[2 * R + 1][2 * R + 1];
        for (int r = 0; r <= R; r++) {
            grid = drawCirclePerimeter(grid, r, R);
        }
        return grid;
    }

    static boolean[][] drawCirclesFilled(int R) {
        boolean[][] grid = new boolean[2 * R + 1][2 * R + 1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int x = getPos(i, R), y = getPos(j, R);

                if (Math.round(Math.sqrt(x * x + y * y)) <= R) {
                    grid[i][j] = true;
                }
            }
        }
        return grid;
    }

    static int getPos(int idx, int R) {
        return idx - R;
    }

    static int getIndex(int x, int R) {
        return x + R;
    }
}