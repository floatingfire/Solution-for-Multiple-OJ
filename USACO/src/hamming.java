import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: hamming
 */
public class hamming {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int n, b, d;

	static boolean check(int rem[], int num, int cou) {
		for (int i = 0; i < cou; i++) {
			int cnt = 0, val = rem[i] ^ num;
			while (val > 0) {
				cnt++;
				val -= -val & val;
			}
			if (cnt < d) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("hamming.in"));
		out = new PrintWriter(new FileWriter("hamming.out"));
		n = nextInt();
		b = nextInt();
		d = nextInt();
		int cnt = 1;
		int rem[] = new int[n];
		for (int i = (1 << d) - 1; cnt < n; i++) {
			if (check(rem, i, cnt)) {
				rem[cnt++] = i;
			}
		}
		for (int i = 0; i < n; i++) {
			if (i % 10 == 9 || i == n - 1) {
				out.println(rem[i]);
			} else {
				out.print(rem[i] + " ");
			}
		}
		out.close();
	}
}
