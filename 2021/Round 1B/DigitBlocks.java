import java.util.*;
import java.io.*;

public class DigitBlocks {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
		int numTests = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int 

		for (int test = 1; test <= numTests; test++) {
			st = new StringTokenizer(br.readLine());

			String output = String.format("Case #%d:", test);
			pw.println(output);
		}
		pw.flush();
		pw.close();
		br.close();
	}
}