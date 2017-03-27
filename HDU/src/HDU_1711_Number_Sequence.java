import java.io.*;

public class HDU_1711_Number_Sequence {
	static int[] getnext(int p[]) {
		int next[] = new int[p.length + 1];
		int k = 0;
		for (int i = 1; i < p.length; i++) {
			while (k > 0 && p[k] != p[i]) {
				k = next[k];
			}
			if (p[k] == p[i]) {
				k++;
			}
			next[i + 1] = k;
		}
		return next;
	}

	static int kmp(int t[], int p[]) {
		int next[] = getnext(p);
		int k = 0;
		for (int i = 0; i < t.length; i++) {
			while (k > 0 && p[k] != t[i]) {
				k = next[k];
			}
			if (p[k] == t[i]) {
				k++;
			}
			if (k == p.length) {
				return i - k + 2;
			}
		}
		return -1;
	}

	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {

		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		for (int cs = nextInt(); cs > 0; cs--) {
			int t[] = new int[nextInt()];
			int p[] = new int[nextInt()];
			for (int i = 0; i < t.length; i++) {
				t[i] = nextInt();
			}
			for (int i = 0; i < p.length; i++) {
				p[i] = nextInt();
			}
			out.println(kmp(t, p));
		}
		out.close();
	}
}