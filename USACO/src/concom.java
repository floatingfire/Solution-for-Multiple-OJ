import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: concom
 */
public class concom {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static final int MAX = 101;
	static int map[][] = new int[MAX][MAX];
	static int pow[][] = new int[MAX][MAX];
	static boolean col[][] = new boolean[MAX][MAX];

	static void dfs() {
		boolean flushed = true;
		while (flushed) {
			flushed = false;
			for (int i = 1; i < MAX; i++) {
				for (int j = 1; j < MAX; j++) {
					if (pow[i][j] > 50 && !col[i][j]) {
						col[i][j] = true;
						for (int k = 1; k < MAX; k++) {
							if (map[j][k] > 0) {
								pow[i][k] += map[j][k];
								flushed = true;
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("concom.in"));
		out = new PrintWriter(new FileWriter("concom.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		for (int n = nextInt(); n > 0; n--) {
			int i = nextInt();
			int j = nextInt();
			pow[i][j] = map[i][j] = nextInt();
		}
		dfs();
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				if (col[i][j] && i != j) {
					out.println(i + " " + j);
				}
			}
		}
		out.close();
	}
}