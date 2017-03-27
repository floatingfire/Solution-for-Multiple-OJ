import java.io.*;
import java.util.Arrays;

public class POJ_1717_Dominoes {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int MAX = 0, d[] = new int[nextInt()];
		for (int i = 0; i < d.length; i++) {
			d[i] = nextInt() - nextInt();
			MAX += Math.abs(d[i]);
		}
		int f[][] = new int[2][MAX + MAX + 1];
		Arrays.fill(f[0], 1000000000);
		f[f[0][MAX + d[0]] = 0][MAX - d[0]] = 1;
		for (int i = 1; i < d.length; i++) {
			Arrays.fill(f[1], 1000000000);
			for (int j = 0; j < f[1].length; j++) {
				if (0 <= j - d[i] && j - d[i] < f[1].length) {
					f[1][j] = Math.min(f[0][j - d[i]], f[1][j]);
				}
				if (0 <= j + d[i] && j + d[i] < f[1].length) {
					f[1][j] = Math.min(f[0][j + d[i]] + 1, f[1][j]);
				}
			}
			int t[] = f[0];
			f[0] = f[1];
			f[1] = t;
		}
		for (int i = 0; i <= MAX; i++) {
			if (f[0][i + MAX] != 1000000000 || f[0][MAX - i] != 1000000000) {
				out.println(Math.min(f[0][i + MAX], f[0][MAX - i]));
				break;
			}
		}
		out.close();
	}
}