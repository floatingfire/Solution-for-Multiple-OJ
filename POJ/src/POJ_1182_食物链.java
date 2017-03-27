import java.io.*;

public class POJ_1182_ ≥ŒÔ¡¥ {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int f[], d[];

	static void init(int n) {
		f = new int[n + 1];
		d = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			f[i] = i;
		}
	}

	static int find(int a) {
		if (f[a] != a) {
			int k = f[a];
			f[a] = find(f[a]);
			d[a] = (d[a] + d[k]) % 3;
		}
		return f[a];
	}

	static boolean union(int a, int b, int dis) {
		int ra = find(a), rb = find(b);
		if (ra != rb) {
			f[ra] = rb;
			d[ra] = (d[b] + dis - d[a] + 3) % 3;
			return true;
		}
		return d[a] == (d[b] + dis) % 3;
	}

	public static void main(String[] args) throws Exception {
		int n = nextInt(), cnt = 0;
		init(n);
		for (int k = nextInt(); k > 0; k--) {
			int dis = nextInt(), a = nextInt(), b = nextInt();
			if (a > n || b > n || (dis == 2 && a == b) || !union(b, a, dis - 1)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}