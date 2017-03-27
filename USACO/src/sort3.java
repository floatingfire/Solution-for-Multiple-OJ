import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: sort3
 */
public class sort3 {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("sort3.in"));
		out = new PrintWriter(new FileWriter("sort3.out"));
		int n = nextInt();
		int cnt[] = new int[4];
		int lis[] = new int[n];
		int chg[][] = new int[4][4];
		for (int i = 0; i < n; i++) {
			lis[i] = nextInt();
			cnt[lis[i]]++;
		}
		for (int i = 1; i < 4; i++) {
			cnt[i] += cnt[i - 1];
			for (int j = cnt[i - 1]; j < cnt[i]; j++) {
				chg[i][lis[j]]++;
			}
		}
		int ans1 = 0, ans2 = 0;
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < i; j++) {
				ans1 += Math.min(chg[i][j], chg[j][i]);
				ans2 += Math.abs(chg[i][j] - chg[j][i]);
			}
		}
		out.println(ans1 + ans2 * 2 / 3);
		out.close();
	}
}
