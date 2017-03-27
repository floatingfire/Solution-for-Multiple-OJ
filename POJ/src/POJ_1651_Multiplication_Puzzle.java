import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class POJ_1651_Multiplication_Puzzle {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int dp(int cd[]) {
		int f[][] = new int[cd.length][cd.length];
		for (int le = 2; le < cd.length; le++) {
			for (int i = 0; i + le < cd.length; i++) {
				f[i][i + le] = Integer.MAX_VALUE;
				for (int j = i + 1; j < i + le; j++) {
					f[i][i + le] = Math.min(f[i][i + le], f[i][j]
							+ f[j][i + le] + cd[i] * cd[j] * cd[i + le]);
				}
			}
		}
		return f[0][cd.length - 1];
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int cd[] = new int[nextInt()];
		for (int i = 0; i < cd.length; i++) {
			cd[i] = nextInt();
		}
		out.println(dp(cd));
		out.close();
	}
}