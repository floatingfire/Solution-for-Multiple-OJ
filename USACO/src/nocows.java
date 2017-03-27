import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: nocows
 */
public class nocows {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("nocows.in"));
		out = new PrintWriter(new FileWriter("nocows.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = nextInt();
		int k = nextInt();
		int f[][] = new int[k + 1][n + 1];
		for (int i = 1; i <= k; i++) {
			f[i][1] = 1;
			for (int j = 1; j <= n; j += 2) {
				for (int l = 1; l < j; l += 2) {
					f[i][j] = (f[i][j] + f[i - 1][l] * f[i - 1][j - l - 1]) % 9901;
				}
			}
		}
		out.println((f[k][n] - f[k - 1][n] + 9901) % 9901);
		out.close();
	}
}