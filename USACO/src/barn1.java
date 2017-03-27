/*
 ID: lloo1351
 LANG: JAVA
 PROG: barn1
 */
import java.io.*;
import java.util.*;

public class barn1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("barn1.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		st.nextToken();
		int c = Integer.parseInt(st.nextToken());
		if (m > c) {
			pw.println(c);
			pw.close();
			System.exit(0);
		}
		int[] stalls = new int[c];
		int[] min = new int[c - 1];
		for (int i = 0; i < c; i++) {
			stalls[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(stalls);
		for (int i = 1; i < c; i++) {
			min[i - 1] = stalls[i] - stalls[i - 1];
		}
		Arrays.sort(min);
		int sum = stalls[c - 1] - stalls[0] + m;
		for (int i = 1; i < m; i++) {
			sum -= min[c - i - 1];
		}
		pw.println(sum);
		br.close();
		pw.close();
	}
}
