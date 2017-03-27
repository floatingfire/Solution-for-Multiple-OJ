import java.io.*;
import java.util.Arrays;

public class POJ_3260_The_Fewest_Coins {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int[] v = new int[nextInt()], c = new int[v.length];
		int t = nextInt(), mv = 0, MAX = 1000000000;
		for (int i = 0; i < v.length; i++) {
			v[i] = nextInt();
			mv = Math.max(mv, v[i]);
		}
		for (int i = 0; i < c.length; i++) {
			c[i] = nextInt();
		}
		int[] fm = new int[t + mv * mv + 1], fp = new int[mv * mv + 1];
		Arrays.fill(fm, 1, fm.length, MAX);
		Arrays.fill(fp, 1, fp.length, MAX);
		for (int i = 0; i < v.length; i++) {
			int use[] = new int[fm.length];
			for (int j = v[i]; j < fp.length; j++) {
				if (use[j - v[i]] < c[i]) {
					fm[j] = Math.min(fm[j], fm[j - v[i]] + 1);
					use[j] = use[j - v[i]] + 1;
				}
				fp[j] = Math.min(fp[j], fp[j - v[i]] + 1);
			}
			for (int j = fp.length; j < fm.length; j++) {
				if (use[j - v[i]] < c[i]) {
					fm[j] = Math.min(fm[j], fm[j - v[i]] + 1);
					use[j] = use[j - v[i]] + 1;
				}
			}
		}
		int ans = MAX;
		for (int i = 0; i < fp.length; i++) {
			ans = Math.min(ans, fp[i] + fm[t + i]);
		}
		out.println(ans == MAX ? -1 : ans);
		out.close();
	}
}