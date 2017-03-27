import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: inflate
 */
public class inflate {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int m, n, t[], p[], f[];

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("inflate.in"));
		out = new PrintWriter(new FileWriter("inflate.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		int m = nextInt(), n = nextInt();
		p = new int[n];
		t = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = nextInt();
			t[i] = nextInt();
		}
		f = new int[m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = t[i]; j <= m; j++) {
				f[j] = Math.max(f[j], f[j - t[i]] + p[i]);
			}
		}
		out.println(f[m]);
		out.close();
	}
}