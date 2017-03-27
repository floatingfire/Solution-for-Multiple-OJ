import java.io.*;

public class POJ_1014_Dividing {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void dp(int MAX, int c[]) {
		boolean f[] = new boolean[MAX];
		f[0] = true;
		for (int i = 0; i < 6; i++) {
			int use[] = new int[MAX];
			for (int j = i + 1; j < MAX; j++) {
				if (!f[j] && f[j - i - 1] && use[j - i - 1] < c[i]) {
					f[j] = true;
					use[j] = use[j - i - 1] + 1;
					if (MAX - j == j) {
						out.println("Can be divided.");
						return;
					}
				}
			}
		}
		out.println("Can't be divided.");
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int c[] = new int[6];
		for (int tst = 1;; tst++) {
			int MAX = 0;
			for (int i = 0; i < 6; i++) {
				c[i] = nextInt();
				MAX += c[i] * (i + 1);
			}
			if (MAX == 0) {
				break;
			}
			out.println("Collection #" + tst + ":");
			dp(MAX, c);
			out.println();
		}
		out.close();
	}
}