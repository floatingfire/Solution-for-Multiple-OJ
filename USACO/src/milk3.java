/*
 ID: lloo1351
 LANG: JAVA
 PROG: milk3
 */
import java.io.*;
import java.util.StringTokenizer;

public class milk3 {
	static boolean flag[][];
	static int aa, bb, cc;

	static void dfs(int a, int b, int c) {
		int x, y;
		if (!flag[a][b]) {
			flag[a][b] = true;
			x = Math.min(a + b, bb);
			y = a + b - x;
			dfs(y, x, c);
			x = Math.min(a + b, aa);
			y = a + b - x;
			dfs(x, y, c);
			x = Math.min(a + c, cc);
			y = a + c - x;
			dfs(y, b, x);
			x = Math.min(a + c, aa);
			y = a + c - x;
			dfs(x, b, y);
			x = Math.min(c + b, bb);
			y = c + b - x;
			dfs(a, x, y);
			x = Math.min(c + b, cc);
			y = c + b - x;
			dfs(a, y, x);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk3.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		aa = Integer.parseInt(st.nextToken());
		bb = Integer.parseInt(st.nextToken());
		cc = Integer.parseInt(st.nextToken());
		flag = new boolean[aa + 1][bb + 1];
		dfs(0, 0, cc);
		int cnt = 0;
		for (int i = bb; i >= 0; i--) {
			if (flag[0][i]) {
				if (cnt++ == 0) {
					out.print(cc - i);
				} else {
					out.print(" " + (cc - i));
				}
			}
		}
		out.println();
		out.close();
	}
}
