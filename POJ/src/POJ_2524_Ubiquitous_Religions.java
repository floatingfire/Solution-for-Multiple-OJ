import java.io.*;

public class POJ_2524_Ubiquitous_Religions {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int f[], cnt;

	static void init(int n) {
		f = new int[n + 1];
		cnt = n;
		for (int i = 0; i < n; i++) {
			f[i] = i;
		}
	}

	static int find(int a) {
		if (f[a] != a) {
			f[a] = find(f[a]);
		}
		return f[a];
	}

	static void union(int a, int b) {
		int ra = find(a), rb = find(b);
		if (ra != rb) {
			f[ra] = rb;
			cnt--;
		}
	}

	public static void main(String[] args) throws Exception {
		for (int c = 1;; c++) {
			int n = nextInt(), m = nextInt();
			if (n == 0 && m == 0) {
				break;
			}
			init(n);
			for (int i = 0; i < m; i++) {
				union(nextInt(), nextInt());
			}
			System.out.println("Case " + c + ": " + cnt);
		}
	}
}