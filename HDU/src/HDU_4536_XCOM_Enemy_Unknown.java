import java.io.*;
import java.util.Arrays;

public class HDU_4536_XCOM_Enemy_Unknown {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int ans;

	static void dfs(int id, int co[], int na[], int nc[], int cp[][]) {
		loo: for (int i = 0; i < 3; i++) {
			int nat[] = Arrays.copyOfRange(na, 0, na.length);
			// System.out.println(id);
			int a = cp[id][i], b = cp[id][(i + 1) % 3], c = cp[id][(i + 2) % 3];
			nat[a] = nat[a] - 2 < 1 ? nat[a] - 2 : 1;
			if (++nat[b] > 5 || ++nat[c] > 5) {
				continue;
			}
			for (int j = co[nc[b]]; j > 0; j -= -j & j) {
				if (++nat[(int) (Math.log(-j & j) / Math.log(2))] > 5) {
					continue loo;
				}
			}
			for (int j = co[nc[c]]; j > 0; j -= -j & j) {
				if (++nat[(int) (Math.log(-j & j) / Math.log(2))] > 5) {
					continue loo;
				}
			}
			ans = Math.max(ans, id + 1);
			if (id + 1 < cp.length) {
				dfs(id + 1, co, nat, nc, cp);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = 1, ttt = nextInt(); tst <= ttt; tst++) {
			int n = nextInt(), m = nextInt(), k = nextInt();
			int[] co = new int[m], na = new int[n], nc = new int[n];
			for (int i = 0; i < n; i++) {
				nc[i] = nextInt();
				co[nc[i]] |= 1 << i;
			}
			for (int i = 0; i < n; i++) {
				na[i] = nextInt();
			}
			int cp[][] = new int[k][3];
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < 3; j++) {
					cp[i][j] = nextInt();
				}
			}
			dfs(ans = 0, co, na, nc, cp);
			out.println("Case #" + tst + ": " + ans);
		}
		out.close();
	}
}