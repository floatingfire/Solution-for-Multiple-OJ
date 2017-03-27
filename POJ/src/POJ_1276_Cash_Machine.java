import java.io.*;

public class POJ_1276_Cash_Machine {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			boolean f[] = new boolean[(int) in.nval + 1];
			int[] v = new int[nextInt()], c = new int[v.length];
			for (int i = 0; i < v.length; i++) {
				c[i] = nextInt();
				v[i] = nextInt();
			}
			f[0] = true;
			for (int i = 0; i < v.length; i++) {
				for (int j = v[i], use[] = new int[f.length]; j < f.length; j++) {
					if (!f[j] && f[j - v[i]] && use[j - v[i]] < c[i]) {
						f[j] = true;
						use[j] = use[j - v[i]] + 1;
					}
				}
			}
			for (int i = f.length - 1; i >= 0; i--) {
				if (f[i]) {
					out.println(i);
					break;
				}
			}
		}
		out.close();
	}
}