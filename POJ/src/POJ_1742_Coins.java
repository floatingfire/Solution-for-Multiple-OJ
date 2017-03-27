import java.io.*;

public class POJ_1742_Coins {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (true) {
			int[] a = new int[nextInt()], c = new int[a.length];
			boolean f[] = new boolean[nextInt() + 1];
			if (a.length == 0 && f.length == 1) {
				break;
			}
			for (int i = 0; i < a.length; i++) {
				a[i] = nextInt();
			}
			for (int i = 0; i < c.length; i++) {
				c[i] = nextInt();
			}
			f[0] = true;
			int cnt = 0;
			for (int i = 0; i < a.length; i++) {
				int use[] = new int[f.length];
				for (int j = a[i]; j < f.length; j++) {
					if (!f[j] && f[j - a[i]] && use[j - a[i]] < c[i]) {
						f[j] = true;
						use[j] = use[j - a[i]] + 1;
						cnt++;
					}
				}
			}
			out.println(cnt);
		}
		out.close();
	}
}