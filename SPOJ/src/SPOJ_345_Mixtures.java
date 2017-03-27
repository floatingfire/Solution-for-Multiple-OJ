import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class SPOJ_345_Mixtures {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int dp(int c[]) {
		int f[][] = new int[c.length][c.length];
		for (int l = 1; l < c.length; l++) {
			for (int i = 1; i + l < c.length; i++) {
				f[i][i + l] = Integer.MAX_VALUE;
				for (int j = i; j < i + l; j++) {
					f[i][i + l] = Math.min(f[i][i + l], f[i][j]
							+ f[j + 1][i + l] + ((c[j] - c[i - 1]) % 100)
							* ((c[i + l] - c[j]) % 100));
				}
			}
		}
		return f[1][c.length - 1];
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int c[] = new int[(int) in.nval + 1];
			for (int i = 1; i < c.length; i++) {
				c[i] = c[i - 1] + nextInt();
			}
			out.println(dp(c));
			out.flush();
		}
		out.close();
	}
}
