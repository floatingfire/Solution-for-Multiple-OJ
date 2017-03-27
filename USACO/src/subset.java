import java.io.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: subset
 */
public class subset {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int n, cnt = 0;

	static void dfs(int id, int len, int sum) {
		if (len == n >> 1) {
			cnt += (n * (n + 1)) >> 2 == sum ? 1 : 0;
		} else if (id <= n) {
			dfs(id + 1, len + 1, id + sum);
			dfs(id + 1, len, sum);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("subset.in"));
		out = new PrintWriter(new FileWriter("subset.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		n = nextInt();
		int mul = n * (n + 1) >> 1;
		long rem[] = new long[mul + 1];
		rem[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = mul; j >= i; j--) {
				rem[j] = rem[j] + rem[j - i];
			}
		}
		out.println((mul & 1) == 1 ? "0" : rem[mul >> 1] >> 1);
		out.close();
	}
}