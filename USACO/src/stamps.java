import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: stamps
 */
public class stamps {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("stamps.in"));
		out = new PrintWriter(new FileWriter("stamps.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		int k = nextInt(), n = nextInt(), max = 0;
		int stps[] = new int[n];
		for (int i = 0; i < n; i++) {
			stps[i] = nextInt();
			max = Math.max(max, stps[i]);
		}
		int f[] = new int[max * k + 1];
		for (int i = 1; i < f.length; i++) {
			f[i] = 2000000;
		}
		for (int i = 0; i < n; i++) {
			for (int j = stps[i]; j < f.length; j++) {
				f[j] = Math.min(f[j], f[j - stps[i]] + 1);
			}
		}
		int i = 0;
		while (i < f.length && f[i] <= k) {
			i++;
		}
		out.println(i - 1);
		out.close();
	}
}