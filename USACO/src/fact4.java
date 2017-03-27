import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: fact4
 */
public class fact4 {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("fact4.in"));
		out = new PrintWriter(new FileWriter("fact4.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		int n = nextInt(), last = 1;
		for (int i = 2; i <= n; i++) {
			last *= i;
			while (last % 10 == 0) {
				last /= 10;
			}
			last %= 10000;
		}
		out.println(last % 10);
		out.close();
	}
}