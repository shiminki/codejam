import java.util.*;
import java.io.*;

public class PancakeDeque {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            Deque<Integer> dq = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

			int ans = 0, taste = 0;

            for (int i = 0; i < N; i++) {
                int front = dq.peekFirst(), end = dq.peekLast();
                int next = 0;

                if (front < end) {
                    next = dq.pollFirst();
                } else {
                    next = dq.pollLast();
                }
                if (taste <= next) {
                    ans++;
                    taste = next;
                }
            }

			String output = String.format("Case #%d: %d", t, ans);
			pw.println(output);
		}
		pw.flush();
		pw.close();
		br.close();
	}
}