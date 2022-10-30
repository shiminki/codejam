import java.util.*;
import java.io.*;

public class CodeJam {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int ans = 0;

			pw.println(String.format("Case #%d: %d", t, ans));
			
		}
		pw.flush();
		pw.close();
		br.close();
	}
}