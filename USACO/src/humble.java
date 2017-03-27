import java.io.*;
import java.util.Stack;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: humble
 */
public class humble {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int k, n, s[], f[], r[];

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("humble.in"));
		out = new PrintWriter(new FileWriter("humble.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		int k = nextInt(), n = nextInt();
		s = new int[k];
		for (int i = 0; i < k; i++) {
			s[i] = nextInt();
		}
		f = new int[n + 1];
		r = new int[k];
		f[0] = 1;
		for (int i = 1; i <= n; i++) {
			f[i] = Integer.MAX_VALUE;
			Stack<Integer> t = new Stack<Integer>();
			for (int j = 0; j < k; j++) {
				if (f[r[j]] * s[j] < f[i]) {
					f[i] = f[r[j]] * s[j];
					t.clear();
					t.add(j);
				} else if (f[r[j]] * s[j] == f[i]) {
					t.add(j);
				}
			}
			while (!t.isEmpty()) {
				r[t.pop()]++;
			}
		}
		out.println(f[n]);
		out.close();
	}
}