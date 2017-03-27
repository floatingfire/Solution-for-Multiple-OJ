import java.io.*;

public class POJ_3264_Balanced_Lineup {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = nextInt(), q = nextInt();
		int k = (int) (Math.log(n) / Math.log(2));
		int max[][] = new int[n][k + 1];
		int min[][] = new int[n][k + 1];
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				min[j][i] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < n; i++) {
			min[i][0] = max[i][0] = nextInt();
		}
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j + (1 << i - 1) < n; j++) {
				max[j][i] = Math.max(max[j][i - 1],
						max[j + (1 << i - 1)][i - 1]);
				min[j][i] = Math.min(min[j][i - 1],
						min[j + (1 << i - 1)][i - 1]);
			}
		}
		for (int i = 0; i < q; i++) {
			int l = nextInt() - 1, r = nextInt() - 1;
			k = (int) (Math.log(r - l + 1) / Math.log(2));
			out.println(Math.max(max[l][k], max[r - (1 << k) + 1][k])
					- Math.min(min[l][k], min[r - (1 << k) + 1][k]));
		}
		out.close();
	}
}