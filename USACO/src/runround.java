import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: runround
 */
public class runround {
	static StreamTokenizer in;
	static PrintWriter out;

	static long nextLong() throws Exception {
		in.nextToken();
		return (long) in.nval;
	}

	static boolean check(Long val) {
		char c[] = val.toString().toCharArray();
		boolean b[] = new boolean[c.length];
		boolean d[] = new boolean[11];
		int i = 0, cnt = 0;
		d[0] = true;
		while (!b[i] && !d[c[i] - '0']) {
			b[i] = true;
			d[c[i] - '0'] = true;
			i = (i + c[i] - '0') % c.length;
			cnt++;
		}
		return i == 0 && cnt == c.length;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("runround.in"));
		out = new PrintWriter(new FileWriter("runround.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		Long n = nextLong();
		while (!check(++n))
			;
		out.println(n);
		out.close();
	}
}