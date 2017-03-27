import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: kimbits
 */
public class kimbits {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int n, l, f[][];

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("kimbits.in"));
		out = new PrintWriter(new FileWriter("kimbits.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		n = nextInt();
		l = nextInt();
		in.nextToken();
		long t = (long) in.nval;
		f = new int[n][l + 1];
		for (int i = 0; i <= l; i++) {
			f[0][i] = 1;
		}
		for (int i = 0; i < n; i++) {
			f[i][0] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= l; j++) {
				f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			if (f[i][l] < t) {
				out.print(1);
				t -= f[i][l--];
			} else {
				out.print(0);
			}
		}
		out.println();
		out.close();
	}
}